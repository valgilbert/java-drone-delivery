package com.coding.exercise.dronedelivery.core.service;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class HTTPClientServiceTest {

    private static final String response = "[{\"droneId\":14415,\"location\":{\"latitude\":-37.77536827009795,\"longitude\":144.85951559530142},\"packages\":[{\"packageId\":86,\"destination\":{\"latitude\":-37.77097958840915,\"longitude\":144.8568797807805},\"deadline\":1501928830}]},{\"droneId\":17936,\"location\":{\"latitude\":-37.76970705171888,\"longitude\":144.8496750789519},\"packages\":[{\"packageId\":183,\"destination\":{\"latitude\":-37.78174024236887,\"longitude\":144.8540638495196},\"deadline\":1501929633}]}]";

    private String apiUrl = "https://codetest.kube.getswift.co/drones";

    @Mock
    private CloseableHttpClient httpClient;

    @Mock
    private HttpGet httpGet;

    @Mock
    private CloseableHttpResponse httpResponse;

    @Mock
    private ResponseHandler<String> responseHandler;

    @InjectMocks
    private HTTPClientService client;

    @Before
    public void before() throws Exception {
	MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRequest() throws Exception {
	Mockito.when(httpClient.execute(httpGet)).thenReturn(httpResponse);
	Mockito.when(responseHandler.handleResponse(httpResponse)).thenReturn(
		response);

	client.getRequest(apiUrl);
	Mockito.verify(httpClient, Mockito.times(1));
    }
}
