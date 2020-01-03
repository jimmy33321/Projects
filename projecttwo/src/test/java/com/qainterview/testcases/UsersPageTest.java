package com.qainterview.testcases;

import com.qainterview.base.TestBase;
import com.qainterview.pages.HomePage;
import com.qainterview.pages.UsersPage;
import com.qainterview.util.FilterPair;
import com.qainterview.util.TestUtil;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UsersPageTest extends TestBase {

	HomePage homePage;
	TestUtil testUtil;
	UsersPage usersPage;

	public UsersPageTest() {
		super();
	}
	@BeforeMethod public void setUp() {
		initialization();
		homePage = new HomePage();
		usersPage = homePage.clickOnUsersLink();
	}

	@Test(priority = 1, dataProvider = "getFiltersTestData") public void verifyUserFilter(String userName, String namePredicate, String email, String emailPredicate, String createdFrom, String createdTo)
			throws IOException {
		//usersPage.applyUserFilter("xyz", "equals", "test@test.com", "contains", "2019-12-01", "2019-12-31");
		usersPage.applyUserFilter(userName, namePredicate, email, emailPredicate, createdFrom, createdTo);
		Collection<List<FilterPair>> values = usersPage.getFilterPairHashMap().values();
		for (List<FilterPair> v : values) {
			for (FilterPair w : v)
				System.out.println("Value: " + w.toString());
		}
		for (WebElement row : usersPage.getRows()) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			//for (WebElement col : cols) {
			boolean matchedRow = false;
			for (List<FilterPair> v : values) {
				for (FilterPair w : v) {
					System.out.println("Value: " + w.toString());

					if (w.toString().indexOf(cols.get(2).getText()) != -1) {
						System.out.println(cols.get(2).getText() + "\t");
						matchedRow = true;
					}
					if (w.toString().indexOf(cols.get(3).getText()) != -1) {
						System.out.println(cols.get(3).getText() + "\t");
						matchedRow = true;
					}
					if (w.toString().indexOf(cols.get(4).getText().substring(0, 6)) != -1) {
						System.out.println(cols.get(4).getText() + "\t");
						matchedRow = true;
					}
					Assert.assertEquals(matchedRow, true, "Filter is not working");
					break;
				}

			}
		}
		//testUtil.takeScreenshotAtEndOfTest("filterTest");
	}

	@DataProvider public Object[][] getFiltersTestData() {
		String sheetName = "filters";
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

	@AfterMethod public void tearDown() {
		driver.quit();
	}

}


