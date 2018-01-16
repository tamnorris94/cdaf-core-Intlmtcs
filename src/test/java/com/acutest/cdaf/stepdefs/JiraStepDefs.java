package com.acutest.cdaf.stepdefs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.acutest.cdaf.common.*;
import com.acutest.cdaf.pageobjects.jira.LoginPageObject;
import com.acutest.cdaf.pageobjects.jira.NavigationBarObject;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JiraStepDefs {
	private LoginPageObject loginPage;
	private NavigationBarObject navigationBar;
	private WebDriver webDriver;

	private String acutesttrainingUrl =
			"https://acutesttraining.atlassian.net/projects/CDFJ/issues";
	private static Logger logger = LogManager.getLogger();

	public JiraStepDefs(SharedDriver webDriver) {
	    logger.debug("Initialising JiraStepDefs");
		this.webDriver = webDriver;
		loginPage = new LoginPageObject(webDriver);
		navigationBar = new NavigationBarObject(webDriver);
	}
	@Given("^I am on the acutesttraining Jira instance$")
	public void i_am_on_the_acutesttraining_Jira_instance() throws Throwable {
		webDriver.get(acutesttrainingUrl);
		loginPage.enterUsername("mike.jennings@acutest.co.uk");
		String jiraPassword = System.getenv("JIRA_PASSWORD");
		loginPage.enterPassword(jiraPassword);
	}
	@When("^I open page at \"([^\"]*)\"$")
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

	@Then("^I the page should contain the word \"([^\"]*)\"$")
	public void i_the_page_should_contain_the_word(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
        String locator = String.format("//*[contains(text(), '%s')]", arg1);
        webDriver.findElement(By.xpath(locator));
	}



	@Then("^I should get a new Jira issue id$")
	public void i_should_get_a_new_Jira_issue_id() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
