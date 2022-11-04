package com.qainterview.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Users {
	public Users() {
		company = new Company();
		address = new Address();
	}
	public Users(int id, String name, String username, String email, Address address, String phone,
			String website, Company company) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.website = website;
		this.company = company;
	}
	
	private int id;
	private String name ="Kurtis Weissnat";
	private String username ="Elwyn.Skiles";
	private String email="Telly.Hoeger@billy.biz";
	private Address address;
	private String phone ="210.067.6132";
	private String website ="elvis.io";
	private Company company;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", username=" + username + ", email=" + email + ", address="
				+ address.toString() + ", phone=" + phone + ", website=" + website + ", company=" + company.toString() + "]";
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
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
