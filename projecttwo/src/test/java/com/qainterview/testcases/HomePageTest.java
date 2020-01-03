package com.qainterview.testcases;

import com.qainterview.base.TestBase;
import com.qainterview.pages.HomePage;
import com.qainterview.pages.UsersPage;
import com.qainterview.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTest extends TestBase {
	HomePage homePage;
	TestUtil testUtil;
	UsersPage usersPage;

	public HomePageTest() {
		super();
	}

	//test cases should be separated -- independent with each other
	//before each test case -- launch the browser and login
	//@test -- execute test case
	//after each test case -- close the browser

	@BeforeMethod public void setUp() {
		initialization();
		//testUtil = new TestUtil();
		homePage = new HomePage();
		usersPage = new UsersPage();
	}

	@Test(priority = 1) public void verifyHomePageTitleTest() {
		String homePageTitle = homePage.verifyHomePageTitle();
		Assert.assertEquals(homePageTitle, "Users | Active Admin Depot", "Home page title not matched");
	}

	@Test(priority = 2) public void verifyUsersLinkTest() {
		usersPage = homePage.clickOnUsersLink();
		Assert.assertEquals(usersPage.verifyUsersPage(), "Users | Active Admin Depot", "users Page title not matched");
	}

	@AfterMethod public void tearDown() {
		driver.quit();
	}
}
