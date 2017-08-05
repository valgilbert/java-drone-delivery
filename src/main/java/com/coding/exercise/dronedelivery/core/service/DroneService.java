package com.coding.exercise.dronedelivery.core.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.exercise.dronedelivery.core.model.AssignResponse;
import com.coding.exercise.dronedelivery.core.model.Assignment;
import com.coding.exercise.dronedelivery.core.model.Drone;
import com.coding.exercise.dronedelivery.core.model.Location;
import com.coding.exercise.dronedelivery.core.model.Package;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DroneService {

    private static final Logger LOG = Logger.getLogger(DroneService.class);

    private static final Location depo = new Location(-37.9179535, 144.6405204);

    private static final int droneSpeed = 50;

    private ObjectMapper mapper = new ObjectMapper();

    private static final String apiUrl = "https://codetest.kube.getswift.co/drones";

    @Autowired
    private HTTPClientService client;

    public List<Drone> getDrones() {

	List<Drone> drones = null;
	try {
	    String response = client.getRequest(apiUrl);

	    drones = mapper.readValue(response, mapper.getTypeFactory()
		    .constructCollectionType(List.class, Drone.class));
	} catch (JsonParseException e) {
	    LOG.error(e.getMessage());
	} catch (JsonMappingException e) {
	    LOG.error(e.getMessage());
	} catch (IOException e) {
	    LOG.error(e.getMessage());
	} catch (Exception e) {
	    LOG.error(e.getMessage());
	}

	return drones;
    }

    public AssignResponse assignPackagesToDrones(List<Drone> drones,
	    List<Package> packages) {

	List<Assignment> assignments = new ArrayList<Assignment>();

	List<Integer> unassignedPackageIds = packages
		.stream()
		// sort package by delivery date
		.sorted((p1, p2) -> Long.compare(p1.getDeadline(),
			p2.getDeadline()))
		.filter(p -> drones
			.stream()
			// sort drones by distance to Depo
			.sorted((d1, d2) -> Double.compare(d1.getLocation()
				.calcDistanceToDestination(depo), d2
				.getLocation().calcDistanceToDestination(depo)))
			.filter(d -> {
			    // if drone has no package
			    if (d.getPackages().size() == 0) {
				// check if able to deliver the package on-time
				if (isDeliveredOnTime(d, p)) {
				    // assign package to drone
				    d.addPackage(p);
				    // add assignment
				    assignments.add(new Assignment(d
					    .getDroneId(), p.getPackageId()));
				    return true;
				}
			    }
			    return false;
			}).count() == 0).map(s -> s.getPackageId())
		.collect(Collectors.toList());

	return new AssignResponse(assignments, unassignedPackageIds);
    }

    private boolean isDeliveredOnTime(Drone drone, Package pack) {

	double totalDistance = (drone.getLocation().calcDistanceToDestination(
		depo) + depo.calcDistanceToDestination(pack.getDestination()));

	long totalTimeInMillis = (long) ((totalDistance / droneSpeed) * 3600);

	Date deliveryDate = new Date(System.currentTimeMillis()
		+ totalTimeInMillis);
	Date deadlineDate = new Date(pack.getDeadline() * 1000);

	return deliveryDate.before(deadlineDate);
    }
}
