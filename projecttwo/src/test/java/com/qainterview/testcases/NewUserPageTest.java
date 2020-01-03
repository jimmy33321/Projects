package com.qainterview.testcases;

import com.qainterview.base.TestBase;
import com.qainterview.pages.HomePage;
import com.qainterview.pages.NewUserPage;
import com.qainterview.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewUserPageTest extends TestBase {

	HomePage homePage;
	TestUtil testUtil;
	NewUserPage newUserPage;

	public NewUserPageTest(){
		super();
	}


	@BeforeMethod
	public void setUp() {
		initialization();
		//testUtil = new TestUtil();
		homePage = new HomePage();
		newUserPage = homePage.newUserLink();
		// = homePage.clickOnUsersLink();
	}

	@Test(priority=2)
	public void verifyNewUserPageLabel(){
		Assert.assertEquals(newUserPage.verifyNewUserPage(), "New User | Active Admin Depot", "New User page title not matched");
	}

	//@Test(priority=2)
	public void selectSingleContactsTest(){
		//contactsPage.selectContactsByName("test2 test2");
	}

//	@Test(priority=3)
	public void selectMultipleContactsTest(){
		//contactsPage.selectContactsByName("test2 test2");
		//contactsPage.selectContactsByName("ui uiii");

	}

	@DataProvider
	public Object[][] getUsersTestData(){
		String sheetName = "users";
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}


	@Test(priority=1, dataProvider="getUsersTestData")
	public void validateCreateNewUser(String name, String password, String email){
		newUserPage.createNewUser(name, password, email);
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}




}

