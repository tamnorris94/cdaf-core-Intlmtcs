package com.acutest.cdaf.stepdefs;

import com.acutest.cdaf.core.helpers.DriverFactory;
import org.apache.commons.lang3.ObjectUtils;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.acutest.cdaf.pageobjects.jira.LoginPageObject;
import com.acutest.cdaf.pageobjects.jira.NavigationBarObject;
import com.acutest.cdaf.pageobjects.jira.JiraIssue;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.lang.Object;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import junit.framework.*;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;


/**
 * Executes the steps defined in feature files
 */
public class JiraStepDefs {

	protected NavigationBarObject navigationBar;
	protected WebDriver webDriver = DriverFactory.initialize();
	protected LoginPageObject loginPage;
	protected JiraIssue jiraIssue;
	private String acutesttrainingUrl =
			"https://acutesttraining.atlassian.net/login";

	private static Logger logger = LogManager.getLogger();

	/**
	 * Navigates to acutest's publica Jira page which doesn't require authentication
	 * @throws Throwable
	 */
	@Given("^user accesses acutesttraining project accessible to anonymous users$")
	public void user_accesses_acutesttraining_project_accessible_to_anonymous_users() throws Throwable {
		/**
		 * navigates to Jira login page, enters username specified in testConfuration.yaml
		 * and password from environment variables
		 * @throws Throwable
		 */

		webDriver = DriverFactory.initialize();
	}

	/**
	 * navigates to Jira login page, enters username specified in testConfuration.yaml
	 * and password from environment variables
	 * @throws Throwable
	 */
	@Given("^I am on the acutesttraining Jira Instance$")
	public void i_am_on_the_acutesttraining_Jira_instance() throws Throwable {
		webDriver.get(acutesttrainingUrl);
		LoginPageObject loginPage = new LoginPageObject(webDriver);
		String userName = System.getenv("jiraUsername");
		loginPage.enterUsername(userName);
		String jiraPassword = System.getenv("JIRA_PASSWORD");
		loginPage.enterPassword(jiraPassword);
	}

	@Given("^Acutest test automation developer logs in to Jira$")
	public void user_logs_in_to_Jira() throws Throwable {
		webDriver.get(acutesttrainingUrl);
		LoginPageObject loginPage = new LoginPageObject(webDriver);
		String userName = System.getenv("jiraUsername");
		loginPage.enterUsername(userName);
		String jiraPassword = System.getenv("JIRA_PASSWORD");
		loginPage.enterPassword(jiraPassword);
	}
	/**
	 * Navigates to specific page in browser
	 *

	 * @param webPage
	 * @throws Throwable
	 */

	@When("^user opens page at \"([^\"]*)\"$")
	public void i_open_page_at(String webPage) throws Throwable {
		webDriver.get(webPage);
	}

	/**
	 * Creates a new Jira story
	 *
	 * @throws Throwable
	 */

	@When("^user creates a new issue with description \"([^\"]*)\", summary \"([^\"]*)\", Project Name \"([^\"]*)\"$")
	public void user_creates_a_new_issue(String descr, String summary, String projectName) throws Throwable {
		NavigationBarObject navigationBar = new NavigationBarObject(webDriver);
		JiraIssue jiraIssue = new JiraIssue(webDriver);
		jiraIssue.enterStoryDetails(descr, summary, projectName);

	}

	@When("^user provides description \"([^\"]*)\", automation status\"([^\"]*)\", risk likelihood\"([^\"]*)\", risk impact \"([^\"]*)\", execution status\"([^\"]*)\" comment \"([^\"]*)\"$")
	public void user_provides_attributes(String description2, String autoStatus, String riskLi, String riskIm, String execStatus, String comment) throws Throwable
	{
		JiraIssue jiraIssue = new JiraIssue(webDriver);
		jiraIssue.addStage1Attributes(description2,comment);
		jiraIssue.addStage2Attributes(riskLi,riskIm);
		jiraIssue.addStage3Attributes(autoStatus);
		jiraIssue.addStage4Attributes(execStatus);
	}
	/**
	 * Returns the title of a jira page and compares against expected title.
	 * @param title
	 * @throws Throwable
	 */
	@When("^title is \"([^\"]*)\"$")
	public void title_is(String title) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	/**
	 * Searches a whole web page for a specified word
	 * @param word
	 * @throws Throwable
	 */
	@Then("^the page contains the word \"([^\"]*)\"$")
	public void i_the_page_should_contain_the_word(String word)throws Throwable {
        String locator = String.format(".//*[contains(text(), '%s')]", word);
		webDriver.findElement(By.xpath(locator));
		List<WebElement> elem = webDriver.findElements(By.xpath(locator));
		Assert.assertTrue("Unable to find the expected word",!elem.isEmpty());
	}

	@Then("^the issue with the given summary \"([^\"]*)\" is created$")
	public void issue_with_attributes_is_created(String summary) throws Throwable
	{
		JiraIssue jiraIssue = new JiraIssue(webDriver);
		jiraIssue.verifyIssueCreation(summary);
	}

}
