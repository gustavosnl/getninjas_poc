package com.glima.getninjas.network.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glima.getninjas.model.Job;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by gustavo on 21/07/16.
 */
public class SingleJobParser implements Parser<Job> {
    @Override
    public Job parse(InputStream inputStream) throws IOException, ParseException, Exception {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(inputStream);

        return null;

    }
}
