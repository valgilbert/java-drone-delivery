package com.coding.exercise.dronedelivery.core.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
public class Package implements Serializable {

    private static final long serialVersionUID = -6343070467350899545L;

    @JsonProperty("packageId")
    private int packageId;

    @JsonProperty("destination")
    private Location destination;

    @JsonProperty("deadline")
    private long deadline;

    public Package() {
    }

    public Package(int packageId, Location destination, long deadline) {
	super();
	this.packageId = packageId;
	this.destination = destination;
	this.deadline = deadline;
    }

    public int getPackageId() {
	return packageId;
    }

    public Location getDestination() {
	return destination;
    }

    public long getDeadline() {
	return deadline;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (deadline ^ (deadline >>> 32));
	result = prime * result
		+ ((destination == null) ? 0 : destination.hashCode());
	result = prime * result + packageId;
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Package other = (Package) obj;
	if (deadline != other.deadline)
	    return false;
	if (destination == null) {
	    if (other.destination != null)
		return false;
	} else if (!destination.equals(other.destination))
	    return false;
	if (packageId != other.packageId)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Package [packageId=" + packageId + ", destination="
		+ destination + ", deadline=" + deadline + "]";
    }

    public Object compareTo(Package p2) {
	// TODO Auto-generated method stub
	return null;
    }

}
