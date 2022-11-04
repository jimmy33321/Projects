package com.qainterview.pages;

import com.qainterview.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

	public void createNewUser(String luserName, String lpassword, String lemail) {
		userName.sendKeys(luserName);
		password.sendKeys(lpassword);
		email.sendKeys(lemail);
		createUserBtn.click();

	}
	/*
	public void createNewUserFailDupName(String luserName, String lpassword, String lemail) {
		userName.sendKeys(luserName);
		password.sendKeys(lpassword);
		email.sendKeys(lemail);
		createUserBtn.click();
	}
	public void createNewUserFailDupEmail(String luserName, String lpassword, String lemail) {
		userName.sendKeys(luserName);
		password.sendKeys(lpassword);
		email.sendKeys(lemail);
		createUserBtn.click();
	}
	*/
}
