package com.acutest.cdaf.pageobjects.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JiraIssue {
    private static WebDriver webDriver;
    private By summaryField = By.id("summary");
    private By descriptionField = By.id("description");
    //private By issueTypeDropDown = By.className("icon aui-ss-icon noloading drop-menu");
    private By createButton = By.id("create-issue-submit");
    private String parentWindowHandler;
    private String subWindowHandler;

    public JiraIssue(WebDriver webDriver) {this.webDriver = webDriver;};

    public void enterStoryDetails(String summary, String description)
    {
        //parentWindowHandler = webDriver.getWindowHandle();
        webDriver.switchTo().alert();
        webDriver.findElement(summaryField).sendKeys(summary);
        webDriver.findElement(descriptionField).sendKeys(description);
        webDriver.findElement(createButton).click();
    }

}
