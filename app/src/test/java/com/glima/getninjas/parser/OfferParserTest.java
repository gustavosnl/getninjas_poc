package com.glima.getninjas.parser;

import com.glima.getninjas.model.Offer;
import com.glima.getninjas.network.parser.OfferParser;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * Created by gustavo on 17/07/16.
 */
public class OfferParserTest {

    List<Offer> offers;

    @Before
    public void setup() throws Exception {
        offers = new OfferParser().parse(new FileInputStream("src/test/resources/sample_offer_list_response.json"));
    }

    @Test
    public void parseSucces() {
        assertNotNull(offers);
        assertEquals(3, offers.size());
    }

}
