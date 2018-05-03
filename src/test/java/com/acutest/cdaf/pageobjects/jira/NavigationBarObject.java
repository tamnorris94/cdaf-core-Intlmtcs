package com.acutest.cdaf.pageobjects.jira;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationBarObject {
    private static WebDriver webDriver;

    public NavigationBarObject(WebDriver webDriver) {
        NavigationBarObject.webDriver = webDriver;
    }

    private By createButton = By.id("create_link");


    public void create() {
        webDriver.findElement(createButton).click();
    }


}