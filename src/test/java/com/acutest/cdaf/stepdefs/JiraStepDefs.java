package com.acutest.cdaf.stepdefs;

import com.acutest.cdaf.core.helpers.DriverFactory;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.acutest.cdaf.pageobjects.jira.LoginPageObject;
import com.acutest.cdaf.pageobjects.jira.NavigationBarObject;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.lang.Object;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * Executes the steps defined in feature files
 */

public class JiraStepDefs {
	/**
	 * Navigates to acutest's publica Jira page which doesn't require authentication
	 * @throws Throwable
	 */

	private NavigationBarObject navigationBar;
	protected WebDriver webDriver;

	private String acutesttrainingUrl =
			"https://acutesttraining.atlassian.net/projects/CDFJ/issues";

	private static Logger logger = LogManager.getLogger();

	//public JiraStepDefs(SharedDriver webDriver) {
	//    logger.debug("Initialising JiraStepDefs");
	//	this.webDriver = webDriver;
	//	loginPage = new LoginPageObject(webDriver);
	//	navigationBar = new NavigationBarObject(webDriver);
	//}
	@Given("^user accesses acutesttraining project accessible to anonymous users$")
	public void user_accesses_acutesttraining_project_accessible_to_anonymous_users() throws Throwable {
		/**
		 * navigates to Jira login page, enters username specified in testConfuration.yaml
		 * and password from environment variables
		 * @throws Throwable
		 */

		webDriver = new DriverFactory().getDriver();
		//Jira Public Issue Page;
	}
	@Given("^I am on the acutesttraining Jira Instance$")
	public void i_am_on_the_acutesttraining_Jira_instance() throws Throwable {
		webDriver = new DriverFactory().getDriver();
		LoginPageObject loginPage  = new LoginPageObject(webDriver);
		webDriver.get(acutesttrainingUrl);
		loginPage.enterUsername("mike.jennings@acutest.co.uk");
		String jiraPassword = System.getenv("JIRA_PASSWORD");
		loginPage.enterPassword(jiraPassword);
	}

	/**
	 * Navigates to specific page in browser
	 *
	 * @param arg1
	 * @throws Throwable
	 */

	@When("^user opens page at \"([^\"]*)\"$")
	public void i_open_page_at(String arg1) throws Throwable {
		webDriver.get(arg1);
	}
	/**
	 * Creates a new Jira story
	 *
	 * @throws Throwable
	 */

	@When("^I create a new story$")
	public void i_create_a_new_story() throws Throwable {
		navigationBar.create();
	}

	/**
	 * Returns the title of a jira page and compares against expected title
	 * @param arg1
	 * @throws Throwable
	 */

	@When("^title is \"([^\"]*)\"$")
	public void title_is(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	/**
	 * Searches a whole web page for a specified word
	 * @param arg1
	 * @throws Throwable
	 */

	@Then("^the page contains the word \"([^\"]*)\"$")
	public void i_the_page_should_contain_the_word(String arg1)throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        String locator = String.format(".//*[contains(text(), '%s')]", arg1);
		webDriver.findElement(By.xpath(locator));
		List<WebElement> elem = webDriver.findElements(By.xpath(locator));
		assert(!elem.isEmpty());
	}



	@Then("^I should get a new Jira issue id$")
	public void i_should_get_a_new_Jira_issue_id() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
