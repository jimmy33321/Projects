package com.qainterview.api.stepDefinitions;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import com.qainterview.common.apispecificMethod;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class usersDetails extends apispecificMethod {
	public String endPoint;
	public String contentType;
	public JSONObject j;

	@Given("^The user endpoint \"([^\"]*)\"$")
	public void the_user_endpoint(String pep) {
		System.out.println("The user EndPoint is " + pep);
		endPoint = pep;
	}

	@When("^User content type \"([^\"]*)\"$")
	public void user_content_type(String pctype) {
		System.out.println("Content type is " + pctype);
		contentType = pctype;
	}

	@Then("^Send user (-?\\d+)*")
	public void send_user(int itr) throws IOException, JSONException {
		System.out.println("sending new user *************************");
		j = addUser(endPoint + "users", contentType, itr);
		Assert.assertNotNull(j);
	}

	@And("^Update user*")
	public void update_user() throws IOException, JSONException {
		System.out.println("updating user *****************************");
		int rcode = putUser(endPoint + "users", contentType);
		Assert.assertEquals("PUT request update failed ", 200, rcode);
	}

}
