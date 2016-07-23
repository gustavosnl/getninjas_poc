package com.glima.getninjas.viewmodel;

import com.glima.getninjas.network.parser.SingleJobParser;
import com.glima.getninjas.view.model.DetailActivityViewModel;

import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by gustavo on 23/07/16.
 */
public class DetailActivityViewModelTest {

    DetailActivityViewModel viewModel;

    @Before
    public void setup() throws Exception {
        viewModel = new DetailActivityViewModel(new SingleJobParser().parse(new FileInputStream("src/test/resources/sample_single_offer_response.json")));
    }

    @Test
    public void gettersBringingCorrectInfo() {

        assertTrue(viewModel.getIsOffer());
        assertEquals("Pedreiro", viewModel.getTitle());
        assertEquals("Felix", viewModel.getUserName());
        assertEquals("f****@hotmail.com", viewModel.getUserEmail());
        assertEquals("Butantã - São Paulo", viewModel.getRequestingLocal());
        assertEquals("a aproximadamente 325 metros de você", viewModel.getFormattedDistance());
        assertEquals(-23.571629,viewModel.getLatitude());
        assertEquals(-46.754039,viewModel.getLongitude());

    }
}
