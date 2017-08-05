package com.coding.exercise.dronedelivery.core.model;

import java.util.List;

public class AssignRequest {
    private List<Drone> drones;
    private List<Package> packages;

    public AssignRequest() {
	super();
    }

    public List<Drone> getDrones() {
	return drones;
    }

    public void setDrones(List<Drone> drones) {
	this.drones = drones;
    }

    public List<Package> getPackages() {
	return packages;
    }

    public void setPackages(List<Package> packages) {
	this.packages = packages;
    }

}
