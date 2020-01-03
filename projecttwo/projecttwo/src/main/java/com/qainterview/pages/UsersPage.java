package com.qainterview.pages;

import com.qainterview.base.TestBase;
import com.qainterview.util.FilterPair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class UsersPage extends TestBase {

	@FindBy(id = "page_title") //<h2 id="page_title">Users</h2>
			WebElement pageTitle;

	@FindBy(id = "index_table_users")  //index_table_users
			WebElement usersTable;

	@FindBy(id = "q_username") WebElement userName;

	@FindBy(id = "q_email") WebElement email;

	@FindBy(id = "q_created_at_gteq_datetime") WebElement dateFrom;
	//*[@id="new_q"]

	@FindBy(id = "q_created_at_lteq_datetime") WebElement dateTo;

	@FindBy(xpath = "//input[@type='submit' and @value='Filter']") WebElement filterBtn;

	List<WebElement> rows;
	HashMap<String, List<FilterPair>> filterPairHashMap;// = new HashMap<String, List<FilterPair>>();

	// Initializing the Page Objects:
	public UsersPage() {
		PageFactory.initElements(driver, this);
		filterPairHashMap = new HashMap<String, List<FilterPair>>();
	}

	public HashMap<String, List<FilterPair>> getFilterPairHashMap() {
		return filterPairHashMap;
	}

	public String verifyUsersPage() {
		return driver.getTitle();
	}

	public List<WebElement> getRows() {
		return rows;
	}

	public void applyUserFilter(String luserName, String namePredicate, String lemail, String emailPredicate, String lcreatedFrom, String lcreatedTo) {
		Select selectUserName = new Select(driver.findElement(By.xpath("/html/body/div/div[4]/div[2]/div[1]/div/form/div[1]/select")));
		List<WebElement> selectionUserPredicate = selectUserName.getOptions();
		List<FilterPair> userFilterPairList = new ArrayList<FilterPair>();
		for (WebElement element : selectionUserPredicate) {
			FilterPair filterPair = new FilterPair(element.getText(), luserName);
			if (namePredicate.equalsIgnoreCase(element.getText())) {
				userFilterPairList.add(filterPair);
				element.click();
				userName.sendKeys(luserName);
				filterPairHashMap.put("Username", userFilterPairList);
				break;
			}
		}

		Select selectEmail = new Select(driver.findElement(By.xpath("/html/body/div/div[4]/div[2]/div[1]/div/form/div[2]/select")));
		List<WebElement> selectionEmailPredicate = selectEmail.getOptions();
		List<FilterPair> emailFilterPairList = new ArrayList<FilterPair>();
		for (WebElement element : selectionEmailPredicate) {
			FilterPair filterPair = new FilterPair(element.getText(), lemail);
			if (emailPredicate.equalsIgnoreCase(element.getText())) {
				emailFilterPairList.add(filterPair);
				element.click();
				email.sendKeys(lemail);
				filterPairHashMap.put("Email", emailFilterPairList);
				break;
			}
		}

		List<FilterPair> createdAtFilterPairList = new ArrayList<FilterPair>();
		FilterPair filterPairFrom = new FilterPair("greater or equal to", lcreatedFrom);
		FilterPair filterPairTo = new FilterPair("lesser or equal to", lcreatedTo);
		createdAtFilterPairList.add(filterPairFrom);
		createdAtFilterPairList.add(filterPairTo);

		this.dateFrom.sendKeys(lcreatedFrom);
		this.dateTo.sendKeys((lcreatedTo));
		filterPairHashMap.put("Created at", createdAtFilterPairList);

		filterBtn.click();
		rows = usersTable.findElements(By.xpath("//html/body/div/div[4]/div[1]/div/form/div[2]/div[1]/div/div/table/tbody/tr"));
		//getAllFilterKeysAndValues();
		//readUserTable();
	}

	private void getAllFilterKeysAndValues() {
		// Get all keys
		Set<String> keys = filterPairHashMap.keySet();
		for (String k : keys) {
			//	System.out.println("Key: " + k);
		}

		// Get all values
		Collection<List<FilterPair>> values = filterPairHashMap.values();
		for (List<FilterPair> v : values) {
			for (FilterPair w : v) {
				//System.out.println("Value: " + w.toString());
			}
		}
	}

	private void readUserTable() {
		//Print data from each row
		rows = usersTable.findElements(By.xpath("//html/body/div/div[4]/div[1]/div/form/div[2]/div[1]/div/div/table/tbody/tr"));
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			for (WebElement col : cols) {
				//System.out.print(col.getText() + "\t");
			}
			System.out.println();
		}
	}
}
