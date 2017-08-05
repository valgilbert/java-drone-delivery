package com.coding.exercise.dronedelivery.core.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.exercise.dronedelivery.core.model.Package;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PackageService {

    private static final Logger LOG = Logger.getLogger(PackageService.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    HTTPClientService client;

    public List<Package> getPackages() {

	List<Package> packages = null;
	try {
	    String response = client
		    .getRequest("https://codetest.kube.getswift.co/packages");

	    packages = mapper.readValue(response, mapper.getTypeFactory()
		    .constructCollectionType(List.class, Package.class));
	} catch (JsonParseException e) {
	    LOG.error(e.getMessage());
	} catch (JsonMappingException e) {
	    LOG.error(e.getMessage());
	} catch (IOException e) {
	    LOG.error(e.getMessage());
	} catch (Exception e) {
	    LOG.error(e.getMessage());
	}

	return packages;

    }
}
