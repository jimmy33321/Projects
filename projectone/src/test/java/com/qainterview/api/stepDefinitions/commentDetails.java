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

public class commentDetails extends apispecificMethod {

	public String endPoint;
	public String contentType;
	public JSONObject json;

	@Given("^The comment endpoint \"([^\"]*)\"$")
	public void the_comment_endpoint(String pep) {
		System.out.println("The comment EndPoint is " + pep);
		endPoint = pep;
	}

	@When("^Comment content type \"([^\"]*)\"$")
	public void comment_content_type(String pctype) {
		System.out.println("Content type is " + pctype);
		contentType = pctype;
	}

	@Then("^Send comment (-?\\d+)*")
	public void send_comment(int itr) throws IOException, JSONException {
		System.out.println("sending new comment *************************");
		json = addComment(endPoint + "comments", contentType, itr);
		Assert.assertNotNull(json);
	}

	@And("^Update comment*")
	public void update_comment() throws IOException, JSONException {
		System.out.println("updating comment *****************************");
		int rcode = putComment(endPoint + "comments", contentType);
		Assert.assertEquals("PUT request update failed ", 200, rcode);
	}

}
