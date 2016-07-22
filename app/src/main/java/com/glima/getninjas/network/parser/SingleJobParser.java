package com.glima.getninjas.network.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glima.getninjas.model.Address;
import com.glima.getninjas.model.Coordinates;
import com.glima.getninjas.model.Info;
import com.glima.getninjas.model.Job;
import com.glima.getninjas.model.User;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gustavo on 21/07/16.
 */
public class SingleJobParser implements Parser<Job> {

    @Override
    public Job parse(InputStream inputStream) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(inputStream);

        return new Job(rootNode.at("/distance").asInt(),
                rootNode.at("/lead_price").asInt(),
                rootNode.at("/title").asText(),
                getInfo(rootNode.at("/_embedded/info")),
                getUserInfo(rootNode.at("/_embedded/user")),
                getAddressInfo(rootNode.at("/_embedded/address")),
                getLeadId(rootNode.at("/_links/accept/href").asText()));
    }

    private Address getAddressInfo(JsonNode addressNode) {
        return new Address(
                addressNode.at("/city").asText(),
                addressNode.at("/neighborhood").asText(),
                addressNode.at("/uf").asText(),
                getCoordinates(addressNode.at("/geolocation")));
    }

    private Coordinates getCoordinates(JsonNode locationNode) {
        return new Coordinates(locationNode.at("/latitude").asText(),
                locationNode.at("/longitude").asText());
    }

    private List<Info> getInfo(JsonNode infoRootNode) {
        List<Info> infoList = new ArrayList<>();
        for (JsonNode infoNode : infoRootNode) {
            infoList.add(new Info(infoNode.at("/label").asText(), getInfoValuesList(infoNode.at("/value"))));
        }
        return infoList;
    }

    private List<String> getInfoValuesList(JsonNode valuesNode) {
        String[] values;
        if (valuesNode.isArray()) {
            values = new String[valuesNode.size()];

            for (int i = 0; i < valuesNode.size(); i++) {
                values[i] = valuesNode.get(i).asText();
            }
        } else {
            values = new String[]{valuesNode.asText()};
        }
        return Arrays.asList(values);
    }

    private User getUserInfo(JsonNode userNode) {
        return new User(
                userNode.at("/name").asText(),
                userNode.at("/email").asText(),
                getPhonesList(userNode.at("/_embedded/phones")));
    }

    private List<String> getPhonesList(JsonNode phonesNode) {
        List<String> phonesList = new ArrayList<>();
        for (JsonNode phone : phonesNode) {
            phonesList.add(phone.at("/number").asText());
        }
        return phonesList;
    }

    private String getLeadId(String url) {
        try {
            return new URL(url).getPath().replaceFirst("/", "");
        } catch (MalformedURLException e) {
            return "";
        }
    }
}
