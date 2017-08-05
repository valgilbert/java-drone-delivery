package com.coding.exercise.dronedelivery.core.model;


public class Assignment {
    private int droneId;
    private int packageId;

    public Assignment(int droneId, int packageId) {
	super();
	this.droneId = droneId;
	this.packageId = packageId;
    }

    public int getDroneId() {
	return droneId;
    }

    public int getPackageId() {
	return packageId;
    }

}
