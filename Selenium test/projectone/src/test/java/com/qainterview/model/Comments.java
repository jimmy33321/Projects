package com.qainterview.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Comments {
	public Comments() {}
	public Comments(int postId, int id, String name, String email, String body) {
		super();
		this.postId = postId;
		this.id = id;
		this.name = name;
		this.email = email;
		this.body = body;
	}
	private int postId;
	private int id;
	private String name ;
	private String email;
	private String body ;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "comments [postId=" + postId + ", id=" + id + ", name=" + name + ", email=" + email + ", body=" + body
				+ "]";
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
