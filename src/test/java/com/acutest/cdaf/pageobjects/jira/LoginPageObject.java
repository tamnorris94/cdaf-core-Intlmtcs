package com.acutest.cdaf.pageobjects.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.*;

/**
 * Class enables automatic login through username-and-password authentication
 */
public class LoginPageObject {

    private static WebDriver webDriver;

    /**
     * @param webDriver
     * Method initializes an instance of webdriver
     */
    public LoginPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By usernameField = By.id("username");
    private By ContinueButton = By.id("login-submit");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-submit");

    /**
     * @param username
     * Method locates username field and inputs a given username, then navigates
     * to next page
     */
    public void enterUsername(String username) {

        WebElement webElement = webDriver.findElement(usernameField);
        webElement.sendKeys(username);
        webDriver.findElement(ContinueButton).click();
    }

    /**
     * @param password
     *Method locates password field and inputs a given password, then navigates
     *to next page
     */
    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        element.sendKeys(password);
        webDriver.findElement(loginButton).click();
        //element = wait.until(ExpectedConditions.elementToBeClickable(By.id("createGlobalIssue")));
        //webDriver.findElement(By.id("createGlobalIssue")).click();
        //element = wait.until(ExpectedConditions.elementToBeClickable(By.id("create-issue-submit")));
        //webDriver.findElement(By.id("createGlobalIssue"));
    }
}
