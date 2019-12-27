package com.qainterview.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qainterview.model.Comments;
import com.qainterview.model.Posts;
import com.qainterview.model.Users;

public class testDataHelper {
	
  public static Posts preparePosts (String dataFilePath) throws IOException { 
	  
		byte[] jsonData = Files.readAllBytes(Paths.get(dataFilePath));
		ObjectMapper objectMapper = new ObjectMapper();
		Posts post = objectMapper.readValue(jsonData, Posts.class);
		System.out.println("Posts Object\n" + post.toString());
		return post;
	}
  public static Comments prepareComments (String dataFilePath) throws IOException { 
		
		byte[] jsonData = Files.readAllBytes(Paths.get(dataFilePath));
		ObjectMapper objectMapper = new ObjectMapper();
		Comments comment = objectMapper.readValue(jsonData, Comments.class);
		System.out.println("Comments Object\n" + comment.toString());
		return comment;
	}
  public static Users prepareUsers (String dataFilePath) throws IOException {
		byte[] jsonData = Files.readAllBytes(Paths.get(dataFilePath));
		ObjectMapper objectMapper = new ObjectMapper();
		Users user = objectMapper.readValue(jsonData, Users.class);
		System.out.println("Users Object\n" + user.toString());
		return user;
  }
  }
