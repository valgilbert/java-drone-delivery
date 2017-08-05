package com.coding.exercise.dronedelivery.core.model;

import java.util.List;

public class AssignResponse {
    private List<Assignment> assignments;
    private List<Integer> unassignedPackages;

    public AssignResponse(List<Assignment> assignments,
	    List<Integer> unassignedPackages) {
	super();
	this.assignments = assignments;
	this.unassignedPackages = unassignedPackages;
    }

    public List<Assignment> getAssignments() {
	return assignments;
    }

    public List<Integer> getUnassignedPackages() {
	return unassignedPackages;
    }

}
