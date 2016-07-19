package com.glima.getninjas.parser;

import com.glima.getninjas.model.Address;
import com.glima.getninjas.model.Offer;
import com.glima.getninjas.model.User;
import com.glima.getninjas.network.parser.OfferParser;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

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
    public void parseSuccesFromList() {
        assertNotNull(offers);
        assertEquals(3, offers.size());

        Offer offer = offers.get(0);

        //offer
        assertNotNull(offer);
        assertEquals(TRUE, offer.isRead());
        assertEquals("offer-1", offer.getId());
        assertEquals("Buffet Completo", offer.getTitle());
        assertNull(offer.getRequestingDistance());

        //Uer
        User user = offer.getRequestingUser();

        assertEquals("Eduardo L'Hotellier", user.getName());
        assertNull(user.getEmail());
        assertTrue(user.getPhones().isEmpty());

        //Address
        Address address = offer.getRequestingAddress();

        assertEquals("São Paulo", address.getCity());
        assertEquals("Vila Leopoldina", address.getNeighborhood());
        assertEquals("SP", address.getState());

        assertNull(address.getCoordinates());

        Date creationDate = offer.getCreationDate();
        assertEquals("Fri Mar 04 11:47:05 BRT 2016", creationDate.toString());
    }
}
