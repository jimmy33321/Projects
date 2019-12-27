package com.qainterview.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Address {
	public Address() {}
	public Address(String street, String suite, String city, String zipcode, Geo geo) {
		super();
		this.street = street;
		this.suite = suite;
		this.city = city;
		this.zipcode = zipcode;
		this.geo = geo;
	}
	private String street= "Rex Trail";
    private String suite = "Suite 280";
    private String city= "Howemouth";
    private String zipcode = "58804-1099";
    private Geo geo;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getSuite() {
		return suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
	public Geo getGeo() {
		return geo;
	}
	public void setGeo(Geo geo) {
		this.geo = geo;
	}
	@Override
	public String toString() {
		return "Address [street=" + street + ", suite=" + suite + ", city=" + city + ", zipcode=" + zipcode + ", geo="
				+ geo.toString() + "]";
	}
}
