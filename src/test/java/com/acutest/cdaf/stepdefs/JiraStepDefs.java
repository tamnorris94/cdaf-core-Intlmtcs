package com.acutest.cdaf.stepdefs;

import com.acutest.cdaf.core.helpers.DriverFactory;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
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
import junit.framework.*;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JiraStepDefs {
	private LoginPageObject loginPage;
	private NavigationBarObject navigationBar;
	protected WebDriver webDriver;

	private String acutesttrainingUrl =
			"https://acutesttraining.atlassian.net/projects/CDFJ/issues";

	private String acutestJiraIssuePage =
			"https://acutesttraining.atlassian.net/browse/PUBLICA-1";

	private static Logger logger = LogManager.getLogger();

	//public JiraStepDefs(SharedDriver webDriver) {
	//    logger.debug("Initialising JiraStepDefs");
	//	this.webDriver = webDriver;
	//	loginPage = new LoginPageObject(webDriver);
	//	navigationBar = new NavigationBarObject(webDriver);
	//}
	@Given("^user accesses acutesttraining project accessible to anonymous users$")
	public void user_accesses_acutesttraining_project_accessible_to_anonymous_users() throws Throwable {
		webDriver = new DriverFactory().getDriver();
		webDriver.get(acutestJiraIssuePage);
		//Jira Public Issue Page;
	}
	@Given("^I am on the acutesttraining Jira Instance$")
	public void i_am_on_the_acutesttraining_Jira_instance() throws Throwable {
		webDriver = new DriverFactory().getDriver();
		webDriver.get(acutesttrainingUrl);
		//loginPage.enterUsername("mike.jennings@acutest.co.uk");
		String jiraPassword = System.getenv("JIRA_PASSWORD");
		//loginPage.enterPassword(jiraPassword);
	}
	@When("^user opens page at \"([^\"]*)\"$")
	public void i_open_page_at(String arg1) throws Throwable {
		webDriver.get(arg1);
	}
	@When("^I create a new story$")
	public void i_create_a_new_story() throws Throwable {
		navigationBar.create();
	}

	@When("^title is \"([^\"]*)\"$")
	public void title_is(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the page contains the word \"([^\"]*)\"$")
	public void i_the_page_should_contain_the_word(String arg1)throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        String locator = String.format(".//*[contains(text(), '%s')]", arg1);
		webDriver.findElement(By.xpath(locator));
		List<WebElement> elem = webDriver.findElements(By.xpath(locator));
		assert(!elem.isEmpty());
		webDriver.quit();
	}



	@Then("^I should get a new Jira issue id$")
	public void i_should_get_a_new_Jira_issue_id() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
