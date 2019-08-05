package com.acutest.cdaf.pageobjects.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.*;

import java.util.List;

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
        WebDriverWait wait2 = new WebDriverWait(webDriver,20);
        wait2.until(ExpectedConditions.presenceOfElementLocated(By.className("css-eaycls")));
    }

    /**
     * Mehtod used for Xray demo. Checks the page is correct
     * @return email form placeholder
     */
    public String checkPage(){
        WebElement webElement = webDriver.findElement(usernameField);
        return webElement.getAttribute("placeholder");
    }

    /**
     * Mehtod used for Xray demo. Checks the landed on page is correct
     * @return
     */
    public String checkSandboxProject() {
        String xPath = "//*[@id='breadcrumbs-container']//span[@class='css-eaycls']";
        List<WebElement> list = webDriver.findElements(By.xpath(xPath));
        WebElement element = list.stream().filter(e -> e.getAttribute("innerText")
                .equals("CDAF Demonstration sandbox"))
                .findFirst().get();
        return element.getText();
    }
}
