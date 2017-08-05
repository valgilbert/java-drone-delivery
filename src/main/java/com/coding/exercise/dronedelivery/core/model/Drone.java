package com.coding.exercise.dronedelivery.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = Visibility.NONE, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE)
public class Drone implements Serializable {

    private static final long serialVersionUID = 6729126150033642605L;

    @JsonProperty("droneId")
    private int droneId;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("packages")
    private List<Package> packages;

    public Drone() {
    }

    public Drone(int droneId, Location location) {
	super();
	this.droneId = droneId;
	this.location = location;
	this.packages = new ArrayList<Package>();
    }

    public int getDroneId() {
	return droneId;
    }

    public Location getLocation() {
	return location;
    }

    public void setLocation(Location location) {
	this.location = location;
    }

    public List<Package> getPackages() {
	return packages;
    }

    public void addPackage(Package pack) {
	this.packages.add(pack);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + droneId;
	result = prime * result
		+ ((location == null) ? 0 : location.hashCode());
	result = prime * result
		+ ((packages == null) ? 0 : packages.hashCode());
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
	Drone other = (Drone) obj;
	if (droneId != other.droneId)
	    return false;
	if (location == null) {
	    if (other.location != null)
		return false;
	} else if (!location.equals(other.location))
	    return false;
	if (packages == null) {
	    if (other.packages != null)
		return false;
	} else if (!packages.equals(other.packages))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Drone [droneId=" + droneId + ", location=" + location
		+ ", packages=" + packages + "]";
    }

}
