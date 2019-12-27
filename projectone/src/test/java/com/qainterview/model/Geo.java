package com.qainterview.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Geo {
	public Geo() {}
	public Geo(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	private String lat = "24.8918";
    private String lng = "21.8984";
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	@Override
	public String toString() {
		return "geo [lat=" + lat + ", lng=" + lng + "]";
	}
	public String toJson() {
		ObjectMapper Obj = new ObjectMapper(); 
		  
        try { 
            return Obj.writeValueAsString(this); 
        } 
  
        catch (IOException e) { 
            e.printStackTrace(); 
        } 
        return "failed to convert to json";
	}
}
