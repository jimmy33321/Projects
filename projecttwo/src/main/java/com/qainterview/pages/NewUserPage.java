package com.qainterview.pages;

import com.qainterview.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class NewUserPage extends TestBase {

	@FindBy(id = "page_title") WebElement pageTitle;

	@FindBy(id = "new_user") WebElement newUserInput;

	@FindBy(id = "user_username") WebElement userName;

	@FindBy(id = "user_password") WebElement password;

	@FindBy(id = "user_email") WebElement email;

	@FindBy(xpath = "//input[@type='submit' and @value='Create User']") WebElement createUserBtn;

	@FindBy(partialLinkText = "Cancel") WebElement getCancelLink;

	@FindBy(xpath = "//a[contains(.,'Cancel')]") WebElement transferBtn;

	// Initializing the Page Objects:
	public NewUserPage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyNewUserPage() {
		return driver.getTitle();
	}

/*
		public void selectContactsByName(String name){
			driver.findElement(By.xpath("//a[text()='"+name+"']//parent::td[@class='datalistrow']"
					+ "//preceding-sibling::td[@class='datalistrow']//input[@name='contact_id']")).click();
		}
*/

	public void createNewUser(String luserName, String lpassword, String lemail) {
		//Select select = new Select(driver.findElement(By.id("new_user")));
	//	select.selectByVisibleText("new_user");
		userName.sendKeys(luserName);
		password.sendKeys(lpassword);
		email.sendKeys(lemail);
		createUserBtn.click();

		// test success
		String output = driver.findElement(By.xpath("/html/body/div[1]/div[5]/div/div/div[1]/div[2]/div[1]/div")).getText();
		String message = "my message";
		if (output.contains("User was successfully created")) {
			String successMsg = "success";
			Assert.assertEquals(message, successMsg);
		}
		// test unsuccessful login
		else if (output.contains("502 Bad Gateway")) {
			String successMsg = "bad gateway";
			Assert.assertEquals(message, successMsg);
		}
		// test field was left blank
		else if (output.contains("Username or password can't be blank")) {
			String successMsg = "field was left blank";
			Assert.assertEquals(message, successMsg);
		}
		// invalid email
		else if (output.contains("is invalid")) {
			String successMsg = "invalid email, please use valid email";
			Assert.assertEquals(message, successMsg);
		}

		else if (output.contains("internal server error")) {
			String successMsg = "internal server error, please refresh the page or go back";
			Assert.assertEquals(message, successMsg);
		}
	}

}
