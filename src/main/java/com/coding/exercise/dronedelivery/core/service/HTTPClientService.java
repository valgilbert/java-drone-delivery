package com.coding.exercise.dronedelivery.core.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class HTTPClientService {

    private static final Logger LOG = Logger.getLogger(HTTPClientService.class);

    /**
     * Pooling HTTP Client Connection manager
     */
    private PoolingHttpClientConnectionManager httpClientConnMgr;

    /**
     * HTTP Client
     */
    private CloseableHttpClient httpClient;

    /**
     * HTTP Get Request
     */
    private HttpGet httpGet;

    private ResponseHandler<String> responseHandler;

    @PostConstruct
    public void init() throws Exception {
	httpGet = new HttpGet();
	httpGet.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);

	// Initialize Pooling HTTP Client Connection Manager
	this.httpClientConnMgr = new PoolingHttpClientConnectionManager();
	// set max connections to GET SWIFT API
	this.httpClientConnMgr.setMaxTotal(5);

	// Create HTTP Client w/ connection manager and retry strategy
	this.httpClient = HttpClients.custom()
		.setConnectionManager(httpClientConnMgr).build();

	this.responseHandler = new BasicResponseHandler();

    }

    @PreDestroy
    public void cleanUp() {

	if (null != this.httpClient) {
	    try {
		this.httpClient.close();
	    } catch (IOException e) {
		LOG.error(e.getMessage());
	    }
	}

	if (null != this.httpClientConnMgr) {
	    this.httpClientConnMgr.close();
	}

    }

    public String getRequest(final String url) throws Exception {

	CloseableHttpResponse httpResponse = null;
	String respBody = null;

	try {

	    httpGet.setURI(new URL(url).toURI());

	    // Set connection timeout in milliseconds
	    final RequestConfig requestConfig = RequestConfig.custom()
		    .setConnectionRequestTimeout(1000).setConnectTimeout(1000)
		    .setSocketTimeout(1000).build();

	    httpGet.setConfig(requestConfig);

	    httpResponse = httpClient.execute(httpGet);

	    LOG.info("http response received [" + httpResponse.getStatusLine()
		    + "]");

	    respBody = responseHandler.handleResponse(httpResponse);

	    LOG.info("FROM API;  respHttpBody=[" + respBody + "]");

	} catch (final MalformedURLException e) {
	    LOG.error("Invalid Configuration of API URL", e);
	} catch (URISyntaxException e) {
	    LOG.error("Invalid Configuration of API URL", e);
	} catch (ClientProtocolException e) {
	    LOG.error("Client Protocol Exception occured while sending request to API");
	} catch (Exception e) {
	    e.printStackTrace();
	    LOG.error("IO Exception occured while sending request to API");
	} finally {
	    if (httpResponse != null) {
		try {
		    httpResponse.close();
		} catch (IOException e) {
		    LOG.error(e.getMessage());
		}
	    }

	}

	return respBody;

    }
}
