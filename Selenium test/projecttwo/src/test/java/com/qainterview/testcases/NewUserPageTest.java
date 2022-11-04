package com.qainterview.testcases;

import com.qainterview.base.TestBase;
import com.qainterview.pages.HomePage;
import com.qainterview.pages.NewUserPage;
import com.qainterview.util.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewUserPageTest extends TestBase {

	HomePage homePage;
	NewUserPage newUserPage;

	public NewUserPageTest(){
		super();
	}


	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
		newUserPage = homePage.newUserLink();
		// = homePage.clickOnUsersLink();
	}

	@Test(priority=2)
	public void verifyNewUserPageLabel(){
		Assert.assertEquals(newUserPage.verifyNewUserPage(), "New User | Active Admin Depot", "New User page title not matched");
	}


	@DataProvider
	public Object[][] getSuccessUserTestData(){
		String sheetName = "users";
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	@DataProvider
	public Object[][] getDupUserTestData(){
		String sheetName = "dup_user";
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	@DataProvider
	public Object[][] getDupEmailTestData(){
		String sheetName = "dup_email";
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}


	@Test(priority=1, dataProvider="getSuccessUserTestData")
	public void validateCreateNewUser_success(String name, String password, String email){
		newUserPage.createNewUser(name, password, email);
		String path = driver.getCurrentUrl();
		// Split path into segments
		String segments[] = path.split("/");
		// Grab the last segment
		String newUserId = segments[segments.length - 1];
		String userId ="//*[@id=\"user_" + newUserId + "\"]"; ////*[@id="user_100"]/
		if(TestUtil.isNumeric(newUserId)) {
			System.out.println("Successfully created new user " + newUserId);
			driver.navigate().to("http://qainterview.merchante-solutions.com:8080/admin/users");

			WebElement newUserNameEle = driver.findElement(By.xpath(userId + "/td[3]"));  //*[@id="user_100"]/td[3]
			WebElement newUserEmailEle = driver.findElement(By.xpath(userId + "/td[4]")); //*[@id="user_100"]/td[4]
			WebElement newUserCreateAtEle = driver.findElement(By.xpath(userId + "/td[5]"));
			Assert.assertEquals(name, newUserNameEle.getText(), " New User name is not equal");
			Assert.assertEquals(email, newUserEmailEle.getText(), " New User email is not equal");
			Assert.assertNotNull(newUserCreateAtEle.getText());

			System.out.println(driver.getCurrentUrl());
			//if error exists then url is not changing.
			//assert create user failed with input error
		} else{
			Assert.assertFalse(false, "failed to add new user");
		}

	}

	@Test(priority=2)
	public void validateCreateNewUser_failed_no_password (){
		String name = "name1";
		String password = "";
		String email = "abc@some.com";
		newUserPage.createNewUser(name, password, email);
		WebElement passwordEle = driver.findElement(By.className("inline-errors"));
		Assert.assertEquals("can't be blank", passwordEle.getText());
	}

	@Test(priority=3, dataProvider="getDupUserTestData")
	public void validateCreateNewUser_failed_dup_username(String name, String password, String email){
		newUserPage.createNewUser(name, password, email);
		WebElement passwordEle = driver.findElement(By.className("inline-errors"));
		Assert.assertEquals("has already been taken", passwordEle.getText());

	}
	@Test(priority=4, dataProvider="getDupEmailTestData")
	public void validateCreateNewUser_failed_dup_email(String name, String password, String email){
		newUserPage.createNewUser(name, password, email);
		WebElement passwordEle = driver.findElement(By.className("inline-errors"));
		Assert.assertEquals("has already been taken", passwordEle.getText());
	}
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}




}

