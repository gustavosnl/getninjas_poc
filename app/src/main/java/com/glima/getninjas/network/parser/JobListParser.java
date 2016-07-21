package com.glima.getninjas.network.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glima.getninjas.model.Address;
import com.glima.getninjas.model.Info;
import com.glima.getninjas.model.Job;
import com.glima.getninjas.model.JobCondition;
import com.glima.getninjas.model.User;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by gustavo on 17/07/16.
 */
public class JobListParser implements Parser<List<Job>> {
    private final String IS_READ = "read";
    private List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> parse(InputStream inputStream) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(inputStream);

        if (rootNode.has("offers")) {
            createOffers(rootNode.at("/offers"));
        } else {
            createLeads(rootNode.at("/leads"));
        }
        return jobs;
    }

    private void createLeads(JsonNode rootNode) throws Exception {
        for (JsonNode jsonLeadNode : rootNode) {
            jobs.add(createLead(jsonLeadNode));
        }
    }

    private void createOffers(JsonNode rootNode) throws Exception {
        for (JsonNode jsonOfferNode : rootNode) {
            jobs.add(createOffer(jsonOfferNode));
        }
    }

    private Job createOffer(JsonNode jsonOfferNode) throws MalformedURLException, ParseException {
        return new Job(
                extractOfferId(jsonOfferNode.at("/_links/self/href").asText()),
                JobCondition.OFFER,
                jsonOfferNode.at("/_embedded/request/title").asText(),
                getIsRead(jsonOfferNode.at("/state").asText()),
                createDate(jsonOfferNode.at("/_embedded/request/created_at").asText()),
                createUser(jsonOfferNode.at("/_embedded/request/_embedded/user")),
                createAddress(jsonOfferNode.at("/_embedded/request/_embedded/address")),
                new ArrayList<Info>(),
                null);
    }


    private Job createLead(JsonNode jsonOfferNode) throws MalformedURLException, ParseException {
        return new Job(
                extractOfferId(jsonOfferNode.at("/_links/self/href").asText()),
                JobCondition.LEAD,
                jsonOfferNode.at("/_embedded/request/title").asText(),
                false,
                createDate(jsonOfferNode.at("/created_at").asText()),
                createUser(jsonOfferNode.at("/_embedded/user")),
                createAddress(jsonOfferNode.at("/_embedded/address")),
                new ArrayList<Info>(),
                null);
    }

    private Address createAddress(JsonNode addressNode) {
        return new Address(
                addressNode.at("/city").asText(),
                addressNode.at("/neighborhood").asText(),
                addressNode.at("/street").asText(),
                addressNode.at("/uf").asText());
    }

    private User createUser(JsonNode usernode) {
        return new User(
                usernode.at("/name").asText(),
                usernode.at("/email").asText(),
                new ArrayList<String>());
    }

    private String createDate(String formattedDate) throws ParseException {
        DateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));
        return outputDateFormat.format(inputDateFormat.parse(formattedDate));

    }

    private Boolean getIsRead(String state) {
        return IS_READ.equals(state);
    }

    private String extractOfferId(String url) throws MalformedURLException {
        return new URL(url).getPath().replaceFirst("/", "");
    }

}
