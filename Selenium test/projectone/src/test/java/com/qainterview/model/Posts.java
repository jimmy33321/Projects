package com.qainterview.model;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Posts {
	public Posts() {}
	public Posts(int id, int userId, String title, String body)  {
		super();
		this.id = id;
		this.userId = userId;
		this.title = title;
		this.body = body;
		
	}
	private int id;
	private int userId ;
    private String title ;
    private String body ;
   
    
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Posts [id=" + id + ", userId=" + userId + ", title=" + title + ", body=" + body + "]";
	}
	
}
