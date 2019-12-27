package com.qainterview.api.stepDefinitions;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import com.qainterview.common.apispecificMethod;

import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class getDetails extends apispecificMethod {
	public String endPoint;
	public String contentType;
	public JSONObject j;

	@Given("^The Endpoint \"([^\"]*)\"$")
	public void the_Endpoint(String ep) {
		System.out.println("The EndPoint is " + ep);
		endPoint = ep;
	}

	@When("^Content type \"([^\"]*)\"$")
	public void content_type(String ctype) {
		System.out.println("Content type is " + ctype);
		contentType = ctype;
	}

	@Then("^Get all posts*")
	public void get_all_posts() throws IOException, JSONException {
		System.out.println("Getting all post *************************");
		j = getMethod(endPoint + "posts");
		Assert.assertNotNull(j);
	}

	@And("^Retrieve all comments$")
	public void retrieve_all_comments() throws IOException, JSONException {
		System.out.println("Getting all comments *******************");
		j = getMethod(endPoint + "comments");
		Assert.assertNotNull(j);
	}

	@And("^Fetch all users$")
	public void fetch_all_users() throws IOException, JSONException {
		System.out.println("Getting all users *******************");
		j = getMethod(endPoint + "users");
		Assert.assertNotNull(j);
	}

	@But("^Check response header$")
	public void check_response_header() throws IOException {
		String header = getResponseHeader(endPoint + "users");
		Assert.assertNotSame("Header is correct ", "application/json; charset=utf-8", header);
	}
}
