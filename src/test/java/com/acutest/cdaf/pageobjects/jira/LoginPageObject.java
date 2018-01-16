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


    public void enterUsername(String username) {
        WebElement webElement = webDriver.findElement(usernameField);
        webElement.sendKeys(username);
        webDriver.findElement(nextButton).click();
    }

    public void enterPassword(String password) {
        WebElement webElement = webDriver.findElement(passwordField);
        webElement.sendKeys(password);
        webDriver.findElement(nextButton).click();
    }

}
