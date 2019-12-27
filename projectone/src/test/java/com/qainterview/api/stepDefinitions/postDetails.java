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

public class postDetails extends apispecificMethod {
	public String endPoint;
	public String contentType;
	public JSONObject j;

	@Given("^The post endpoint \"([^\"]*)\"$")
	public void the_post_endpoint(String pep) {
		System.out.println("The post EndPoint is " + pep);
		endPoint = pep;
	}

	@When("^Post content type \"([^\"]*)\"$")
	public void Post_content_type(String pctype) {
		System.out.println("Content type is " + pctype);
		contentType = pctype;
	}

	@Then("^Send new (-?\\d+)*")
	public void send_new(int itr) throws IOException, JSONException {
		System.out.println("sending new post *************************");
		j = postMethod(endPoint + "posts", contentType, itr);
		Assert.assertNotNull(j);
	}

	@And("^Update post*")
	public void update_post() throws IOException, JSONException {
		System.out.println("updating post *****************************");
		int rcode = putPost(endPoint + "posts", contentType);
		Assert.assertEquals("PUT request update failed ", 200, rcode);
	}

}
