package com.glima.getninjas.network.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glima.getninjas.model.Address;
import com.glima.getninjas.model.Offer;
import com.glima.getninjas.model.User;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gustavo on 17/07/16.
 */
public class OfferParser implements Parser<List<Offer>> {
    private final String IS_READ = "read";

    @Override
    public List<Offer> parse(InputStream inputStream) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(inputStream);

        List<Offer> offers = new ArrayList<>();

        if (rootNode.has("offers")) {
            for (JsonNode jsonOfferNode : rootNode.at("/offers")) {
                offers.add(createOffer(jsonOfferNode));
            }
        } else {
            offers.add(createOffer(rootNode));
        }
        return offers;
    }

    private Offer createOffer(JsonNode jsonOfferNode) throws MalformedURLException, ParseException {
        return new Offer(
                extractOfferId(jsonOfferNode.at("/_links/self/href").asText()),
                jsonOfferNode.at("/_embedded/request/title").asText(),
                getIsRead(jsonOfferNode.at("/state").asText()),
                createDate(jsonOfferNode.at("/_embedded/request/created_at").asText()),
                new User(jsonOfferNode.at("/_embedded/request/_embedded/user/name").asText()),
                createAddress(jsonOfferNode.at("/_embedded/request/_embedded/address")),
                null);
    }

    private Address createAddress(JsonNode addressNode) {
        return new Address(
                addressNode.at("/city").asText(),
                addressNode.at("/neighborhood").asText(),
                addressNode.at("/uf").asText());
    }

    private Date createDate(String formattedDate) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = format.parse(formattedDate);
        return date;
    }

    private Boolean getIsRead(String state) {
        return IS_READ.equals(state);
    }

    private String extractOfferId(String url) throws MalformedURLException {
        return new URL(url).getPath().replaceFirst("/", "");
    }

}
