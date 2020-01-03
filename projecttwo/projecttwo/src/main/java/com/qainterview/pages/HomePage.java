package com.qainterview.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qainterview.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//a[contains(text(),'Dashboard')]")
	WebElement dashboardLink;
	
	@FindBy(xpath = "//a[contains(text(),'Products')]")
	WebElement productsLink;
	

	@FindBy(xpath = "//a[contains(text(),'Orders')]")
	WebElement ordersLink;

	@FindBy(xpath = "//a[contains(text(),'Users')]")
	WebElement usersLink;

	@FindBy (xpath = "//a[contains(text(), 'New User')]")
	WebElement newUserLink;

	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	/*
	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
	*/
	public UsersPage clickOnUsersLink(){
		usersLink.click();
		return new UsersPage();
	}

	public NewUserPage newUserLink(){
		newUserLink.click();
		return new NewUserPage();
	}
	
	/*

	public void clickOnNewUserLink(){
		Actions action = new Actions(driver);
		action.moveToElement(usersLink).build().perform();
		usersLink.click();
		*/
	}
	
	
	
