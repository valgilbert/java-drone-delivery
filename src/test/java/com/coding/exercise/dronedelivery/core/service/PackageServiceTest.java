package com.coding.exercise.dronedelivery.core.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class PackageServiceTest {

    private static final String response = "[{\"packageId\": 293,\"destination\": {\"latitude\": -37.77741302203189,\"longitude\": 144.85828999588824},\"deadline\": 1501927756},{\"packageId\": 328,\"destination\": {\"latitude\": -37.76593979845064,\"longitude\": 144.86085926698647},\"deadline\": 1501928423}]";

    private String apiUrl = "https://codetest.kube.getswift.co/packages";

    @Mock
    private HTTPClientService client;

    @InjectMocks
    private PackageService packageService;

    @Before
    public void before() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetDrones() throws Exception {
	Mockito.when(client.getRequest(apiUrl)).thenReturn(response);
	assertEquals(2, packageService.getPackages().size());
	Mockito.verify(client, Mockito.times(1)).getRequest(apiUrl);
    }
}
