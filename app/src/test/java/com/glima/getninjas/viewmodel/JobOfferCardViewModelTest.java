package com.glima.getninjas.viewmodel;

import com.glima.getninjas.network.parser.JobListParser;
import com.glima.getninjas.view.model.JobOfferCardViewModel;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by gustavo on 23/07/16.
 */
public class JobOfferCardViewModelTest {

    JobOfferCardViewModel viewModel;

    @Before
    public void setup() throws Exception {
        viewModel = new JobOfferCardViewModel(new JobListParser().parse(new FileInputStream("src/test/resources/sample_offer_list_response.json")).get(0));
    }

    @Test
    public void gettersBringingCorrectInfo() {

        assertTrue(viewModel.isStatusVisible());
        assertTrue(viewModel.isRead());
        assertEquals("Buffet Completo", viewModel.getTitle());
        assertEquals("Eduardo L'Hotellier", viewModel.getUserName());
        assertEquals("Vila Leopoldina - São Paulo", viewModel.getRequestingLocal());
        assertEquals("04/03/2016", viewModel.getDate());
    }
}
