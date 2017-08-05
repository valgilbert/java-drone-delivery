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
public class Location implements Serializable {

    private static final long serialVersionUID = 4340842332522735176L;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    public Location() {
    }

    public Location(double latitude, double longitude) {
	super();
	this.latitude = latitude;
	this.longitude = longitude;
    }

    public double getLatitude() {
	return latitude;
    }

    public void setLatitude(double latitude) {
	this.latitude = latitude;
    }

    public double getLongitude() {
	return longitude;
    }

    public void setLongitude(double longitute) {
	this.longitude = longitute;
    }

    public double calcDistanceToDestination(Location destination) {

	double theta = this.getLongitude() - destination.getLongitude();

	double distance = Math.sin(deg2rad(this.latitude))
		* Math.sin(deg2rad(destination.getLatitude()))
		+ Math.cos(deg2rad(this.latitude))
		* Math.cos(deg2rad(destination.getLatitude()))
		* Math.cos(deg2rad(theta));
	distance = Math.acos(distance);
	distance = rad2deg(distance);
	distance = distance * 60 * 1.1515;
	distance = distance * 1.609344;

	return distance;
    }

    private double deg2rad(double deg) {
	return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
	return (rad * 180 / Math.PI);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(latitude);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(longitude);
	result = prime * result + (int) (temp ^ (temp >>> 32));
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
	Location other = (Location) obj;
	if (Double.doubleToLongBits(latitude) != Double
		.doubleToLongBits(other.latitude))
	    return false;
	if (Double.doubleToLongBits(longitude) != Double
		.doubleToLongBits(other.longitude))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Location [latitude=" + latitude + ", longitude=" + longitude
		+ "]";
    }

    public Object compareTo(Location destination) {
	// TODO Auto-generated method stub
	return null;
    }

}
