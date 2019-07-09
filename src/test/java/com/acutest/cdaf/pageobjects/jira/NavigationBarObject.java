package com.acutest.cdaf.pageobjects.jira;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

public class NavigationBarObject
{
    private static WebDriver webDriver;

    public NavigationBarObject(WebDriver webDriver) {
        NavigationBarObject.webDriver = webDriver;
    }

    private By createButton = By.id("createGlobalIssue");
    private By globalButton = By.id("productLogoGlobalItem");
    private By searchButton = By.id("quickSearchGlobalItem");


    public void create() {
        webDriver.findElement((createButton)).click();
    }

    public void navigate(){webDriver.findElement(globalButton).click();}

    public void search() {webDriver.findElement(searchButton).click();}

}
