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
			"https://acutesttraining.atlassian.net/projects/CDFJ/issues";

	private static Logger logger = LogManager.getLogger();

	//public JiraStepDefs(SharedDriver webDriver) {
	//    logger.debug("Initialising JiraStepDefs");
	//	this.webDriver = webDriver;
	//	loginPage = new LoginPageObject(webDriver);
	//	navigationBar = new NavigationBarObject(webDriver);
	//}

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
		//Jira Public Issue Page;
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

	@Given("^Acutest test automation developer logs into Jira$")
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
		navigationBar.create();

		JiraIssue jiraIssue = new JiraIssue(webDriver);
		jiraIssue.enterStoryDetails(summary, descr, projectName);
		//navigationBar.search();

	}

	@When("^user provides \"([^\"]*)\", risk impact \"([^\"]*)\" and risk likelihood \"([^\"]*)\", test execution status \"([^\"]*)\" and comment \"([^\"]*)\"$")
	public void user_provides_attributes(String summary, String riskImpact, String riskLike, String execStatus, String comment) throws Throwable
	{
		jiraIssue.addTestAttributes(summary, riskImpact, riskLike, execStatus, comment);
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
		// Write code here that turns the phrase above into concrete actions
        String locator = String.format(".//*[contains(text(), '%s')]", word);
		webDriver.findElement(By.xpath(locator));
		List<WebElement> elem = webDriver.findElements(By.xpath(locator));
		assert(!elem.isEmpty());
	}

	@Then("^the issue with the given summary \"([^\"]*)\" and execution status \"([^\"]*)\" is successfully created$")
	public void issue_with_attributes_is_created(String summary, String exc_Status) throws Throwable
	{

	}

	@Then("^I should get a new Jira issue id$")
	public void i_should_get_a_new_Jira_issue_id() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
}
