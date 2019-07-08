package com.acutest.cdaf.pageobjects.jira;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NavigationBarObject {
    private static WebDriver webDriver;

    public NavigationBarObject() {
        this.webDriver = webDriver;
    }

    private By createButton = By.id("createGlobalItem");
    private By globalButton = By.id("productLogoGlobalItem");
    private By searchButton = By.id("quickSearchGlobalItem");


    public void create() {
        WebElement createIssue = webDriver.findElement(By.xpath("//*[@id=\"createGlobalItem\"]"));
        createIssue.click();
    }

    public void navigate(){webDriver.findElement(globalButton).click();}

    public void search() {webDriver.findElement(searchButton).click();}

}
