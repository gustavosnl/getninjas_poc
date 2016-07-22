package com.glima.getninjas.parser;

import com.glima.getninjas.model.Address;
import com.glima.getninjas.model.Coordinates;
import com.glima.getninjas.model.Info;
import com.glima.getninjas.model.Job;
import com.glima.getninjas.model.User;
import com.glima.getninjas.network.parser.SingleJobParser;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.List;

import static java.lang.Integer.valueOf;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by gustavo on 17/07/16.
 */
public class JobParserTest {

    Job job;

    @Test
    public void parseOfferSuccess() throws Exception {
        job = new SingleJobParser().parse(new FileInputStream("src/test/resources/sample_single_offer_response.json"));

        assertNotNull(job);
        assertEquals("Pedreiro", job.getTitle());
        assertEquals(valueOf(30070), job.getRequestingDistance());
        assertTrue(job.isOffer());
        assertEquals("lead-2", job.getLeadId());
        assertEquals(valueOf(6), job.getLeadPrice());

        //Info
        List<Info> infoList = job.getJobInfo();

        assertNotNull(infoList);
        assertEquals(4, infoList.size());

        Info info = infoList.get(2);

        assertNotNull(info);
        assertEquals("Como você quer ser contactado?", info.getLabel());

        assertEquals(2, info.getContent().size());

        //User
        User user = job.getRequestingUser();

        assertEquals("Felix", user.getName());
        assertEquals("f****@hotmail.com", user.getEmail());
        assertEquals(1, user.getPhones().size());
        assertEquals("(11) 3***-****", user.getPhones().get(0));

        //Address
        Address address = job.getRequestingAddress();

        assertEquals("São Paulo", address.getCity());
        assertEquals("Butantã", address.getNeighborhood());
        assertEquals("SP", address.getState());

        //Coordinates
        Coordinates coordinates = address.getCoordinates();

        assertNotNull(coordinates);
        assertEquals("-23.571629", coordinates.getLatitude());
        assertEquals("-46.754039", coordinates.getLongitude());

    }

    @Test
    public void parseLeadSuccess() throws Exception {
        job = new SingleJobParser().parse(new FileInputStream("src/test/resources/sample_single_lead_response.json"));
        assertNotNull(job);
        assertEquals("Buffet Completo", job.getTitle());
        assertEquals(valueOf(100070), job.getRequestingDistance());
        assertFalse(job.isOffer());
        assertEquals("", job.getLeadId());
        assertEquals(valueOf(1), job.getLeadPrice());

        //Info
        List<Info> infoList = job.getJobInfo();

        assertNotNull(infoList);
        assertEquals(7, infoList.size());

        Info info = infoList.get(1);

        assertNotNull(info);
        assertEquals("Qual será o tipo de comida?", info.getLabel());

        assertEquals(1, info.getContent().size());

        //User
        User user = job.getRequestingUser();

        assertEquals("Eduardo L'Hotellier", user.getName());
        assertEquals("eduardo@hotmail.com", user.getEmail());
        assertEquals(1, user.getPhones().size());
        assertEquals("(11) 3791-7315", user.getPhones().get(0));

        //Address
        Address address = job.getRequestingAddress();

        assertEquals("São Paulo", address.getCity());
        assertEquals("Vila Leopoldina", address.getNeighborhood());
        assertEquals("SP", address.getState());

        //Coordinates
        Coordinates coordinates = address.getCoordinates();

        assertNotNull(coordinates);
        assertEquals("-23.5304898", coordinates.getLatitude());
        assertEquals("-46.7261564", coordinates.getLongitude());
    }
}
