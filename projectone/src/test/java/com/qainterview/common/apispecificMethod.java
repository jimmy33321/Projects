package com.qainterview.common;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.qainterview.model.Comments;
import com.qainterview.model.Posts;
import com.qainterview.model.Users;

public class apispecificMethod {

		URL url =null;
		HttpURLConnection conn=null;
		int responseCode ;
		JSONObject myResponse=null;
		BufferedReader rd=null;
		String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36";
		private static int currentId ;  
		
	private static void generateID(int range) {
		int max = range + 99;
		int min = range;
		Random rand = new Random(); 
		int tem = rand.nextInt((max - min) + 1) + min;
		System.out.println("generated "+ tem);
		currentId = tem ;
		System.out.println("range "+ range  + ": current " + currentId);
		
	}
	public JSONObject getMethod(String urltoload) throws IOException, JSONException {
		StringBuilder result = new StringBuilder();
		url = new URL(urltoload);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		responseCode = conn.getResponseCode();
		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
		//	System.out.println("Printing data " + line);
			result.append(line);
		}
		rd.close();
		conn.disconnect();
		System.out.println("Response code " + responseCode);
		myResponse = new JSONObject(result.toString().trim().charAt(0));
		return myResponse;
	}
	public JSONObject addComment(String urltoload,String type,int range) throws IOException {
		generateID(range);
		Comments comment = testDataHelper.prepareComments("resources/data/comments.json");
		comment.setId(currentId);
		comment.setPostId(currentId);
		return handlePost(urltoload, type, range, comment.toJson());
	}
	public JSONObject addUser(String urltoload,String type,int range) throws IOException {
		generateID(range);
		Users user = testDataHelper.prepareUsers("resources/data/users.json");
		user.setId(currentId);
		return handlePost(urltoload, type, range, user.toJson());
	}
	private JSONObject handlePost(String urltoload,String type,int range, String jsonStr) throws IOException {
		url = new URL(urltoload);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		conn.setRequestProperty("Content-Type",type);
		conn.setConnectTimeout(7000);
		System.out.println("Request ID "+ currentId);	
		conn.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(jsonStr);
		wr.flush();
		wr.close();
		responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + urltoload);
		System.out.println("Post parameters : " + jsonStr);
		System.out.println("Response Code : " + responseCode);
		rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = rd.readLine()) != null) {
			response.append(inputLine);
		}
		rd.close();
		conn.disconnect();
		myResponse = new JSONObject(response.toString().trim().charAt(0));
		return myResponse;
	}
	public JSONObject postMethod(String urltoload,String type,int range) throws IOException {
		generateID(range);
		Posts post = testDataHelper.preparePosts("resources/data/post.json");
		post.setId(currentId);
		post.setUserId(currentId);
		return handlePost(urltoload, type, range, post.toJson());
	}
	private int handlePut(String pathUrl, String type, String jsonStr, int currentId) throws IOException {
		URL url = new URL(pathUrl + "/" + currentId);
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", type);
		conn.setRequestProperty("Accept",type);
		conn.setConnectTimeout(7000);
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		System.out.print("put resource : " + jsonStr);
		out.write(jsonStr);
		out.flush();
		out.close();
		responseCode = conn.getResponseCode();
		conn.disconnect();
		return responseCode;
	}
	public int putPost(String urltoload,String type) throws IOException {
		System.out.print("current ID in put : " + currentId + "\n");
		System.out.print("PUT URL : " + urltoload + "\n");
		Posts post = testDataHelper.preparePosts("resources/data/post.json");
		post.setId(currentId);
		post.setUserId(currentId);
		return handlePut(urltoload, type, post.toJson(), currentId);
	}
	public int putComment(String urltoload,String type) throws IOException {
		System.out.print("current ID in put : " + currentId + "\n");
		System.out.print("PUT URL : " + urltoload + "\n");
		Comments comment = testDataHelper.prepareComments("resources/data/comments.json");
		comment.setId(currentId);
		comment.setPostId(currentId);
		return handlePut(urltoload, type, comment.toJson(), currentId);
	}
	public int putUser(String urltoload,String type) throws IOException {
		System.out.print("current ID in put : " + currentId + "\n");
		System.out.print("PUT URL : " + urltoload + "\n");
		Users user = testDataHelper.prepareUsers("resources/data/users.json");
		user.setId(currentId);
		return handlePut(urltoload, type, user.toJson(), currentId);
	}
	public String getResponseHeader(String urltoload) throws IOException {
		System.out.println(urltoload);
		url = new URL(urltoload);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		String hf = conn.getHeaderField("Content-Type");
		System.out.println("Printing header field " + hf);
		conn.disconnect();
		return hf;

	}
}
