package com.acutest.cdaf.stepdefs;

import com.acutest.cdaf.core.helpers.DriverFactory;
import com.acutest.cdaf.pageobjects.jira.LoginPageObject;
import com.acutest.cdaf.pageobjects.jira.NavigationBarObject;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


/**
 * Executes the steps defined in feature files
 */
public class ClientProjStepDefs {

	private NavigationBarObject navigationBar;
	protected WebDriver webDriver = DriverFactory.initialize();
	protected LoginPageObject loginPage;

	private static Logger logger = LogManager.getLogger();

	//public JiraStepDefs(SharedDriver webDriver) {
	//    logger.debug("Initialising JiraStepDefs");
	//	this.webDriver = webDriver;
	//	loginPage = new LoginPageObject(webDriver);
	//	navigationBar = new NavigationBarObject(webDriver);
	//}

	/**
	 *
	 *
	 */
}
