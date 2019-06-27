package com.acutest.cdaf.pageobjects.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class LoginPageObject {
    private static WebDriver webDriver;

    public LoginPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By usernameField = By.id("username");
    private By nextButton = By.id("login-submit");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-submit");

    public void enterUsername(String username) {
        WebElement webElement = webDriver.findElement(usernameField);
        webElement.sendKeys(username);
        webDriver.findElement(nextButton).click();
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(webDriver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        element.sendKeys(password);
        webDriver.findElement(loginButton).click();
    }
}
