package com.glima.getninjas.parser;

import com.glima.getninjas.model.Address;
import com.glima.getninjas.model.Job;
import com.glima.getninjas.model.JobCondition;
import com.glima.getninjas.model.User;
import com.glima.getninjas.network.parser.JobListParser;

import org.junit.Test;

import java.io.FileInputStream;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

/**
 * Created by gustavo on 17/07/16.
 */
public class JobParserTest {

    List<Job> jobs;

    @Test
    public void parseSuccesFromOfferList() throws Exception {
        jobs = new JobListParser().parse(new FileInputStream("src/test/resources/sample_offer_list_response.json"));
        assertNotNull(jobs);
        assertEquals(3, jobs.size());

        Job job = jobs.get(0);

        //job
        assertNotNull(job);
        assertEquals(TRUE, job.isRead());
        assertEquals("offer-1", job.getId());
        assertEquals("Buffet Completo", job.getTitle());
        assertNull(job.getRequestingDistance());
        assertEquals(JobCondition.OFFER, job.getCondition());

        //Uer
        User user = job.getRequestingUser();

        assertEquals("Eduardo L'Hotellier", user.getName());
        assertTrue(user.getEmail().isEmpty());
        assertTrue(user.getPhones().isEmpty());

        //Address
        Address address = job.getRequestingAddress();

        assertEquals("São Paulo", address.getCity());
        assertEquals("Vila Leopoldina", address.getNeighborhood());
        assertEquals("SP", address.getState());

        assertNull(address.getCoordinates());


        assertEquals("04/03/2016", job.getCreationDate());
    }

    @Test
    public void parseSuccesFromLeadList() throws Exception {
        jobs = new JobListParser().parse(new FileInputStream("src/test/resources/sample_lead_list_response.json"));
        assertNotNull(jobs);
        assertEquals(3, jobs.size());

        Job job = jobs.get(0);

        //job
        assertNotNull(job);
        assertNull(job.isRead());
        assertEquals("lead-1", job.getId());
        assertEquals("Buffet Completo", job.getTitle());
        assertNull(job.getRequestingDistance());
        assertEquals(JobCondition.LEAD, job.getCondition());

        //Uer
        User user = job.getRequestingUser();

        assertEquals("Eduardo L'Hotellier", user.getName());
        assertEquals("eduardo@hotmail.com", user.getEmail());
        assertTrue(user.getPhones().isEmpty());

        //Address
        Address address = job.getRequestingAddress();

        assertEquals("São Paulo", address.getCity());
        assertEquals("Vila Leopoldina", address.getNeighborhood());
        assertEquals("Avenida Imperatriz Leopoldina", address.getStreet());
        assertEquals("SP", address.getState());

        assertNull(address.getCoordinates());


        assertEquals("04/03/2016", job.getCreationDate());
    }
}
