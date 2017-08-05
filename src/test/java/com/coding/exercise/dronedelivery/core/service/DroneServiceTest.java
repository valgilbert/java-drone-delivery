package com.coding.exercise.dronedelivery.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.coding.exercise.dronedelivery.core.model.AssignResponse;
import com.coding.exercise.dronedelivery.core.model.Drone;
import com.coding.exercise.dronedelivery.core.model.Package;

public class DroneServiceTest {

    private static final String response = "[{\"droneId\":14415,\"location\":{\"latitude\":-37.77536827009795,\"longitude\":144.85951559530142},\"packages\":[{\"packageId\":86,\"destination\":{\"latitude\":-37.77097958840915,\"longitude\":144.8568797807805},\"deadline\":1501928830}]},{\"droneId\":17936,\"location\":{\"latitude\":-37.76970705171888,\"longitude\":144.8496750789519},\"packages\":[{\"packageId\":183,\"destination\":{\"latitude\":-37.78174024236887,\"longitude\":144.8540638495196},\"deadline\":1501929633}]}]";

    private String apiUrl = "https://codetest.kube.getswift.co/drones";

    @Mock
    private HTTPClientService client;

    @InjectMocks
    private DroneService droneService;

    @Before
    public void before() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDrones() throws Exception {
	Mockito.when(client.getRequest(apiUrl)).thenReturn(response);
	assertEquals(2, droneService.getDrones().size());
	Mockito.verify(client, Mockito.times(1)).getRequest(apiUrl);
    }

    @Test
    public void testAssignPackagesToDrones() throws Exception {
	AssignResponse response = droneService.assignPackagesToDrones(
		new ArrayList<Drone>(), new ArrayList<Package>());
	assertNotNull(response);
    }
}
