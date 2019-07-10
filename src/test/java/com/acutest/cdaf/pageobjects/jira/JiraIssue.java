package com.acutest.cdaf.pageobjects.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.*;

public class JiraIssue {
    private static WebDriver webDriver;
    private By summaryField = By.id("summary");
    private By descriptionField = By.id("description");
    //private By issueTypeDropDown = By.className("icon aui-ss-icon noloading drop-menu");
    private By createButton = By.id("create-issue-submit");
    private By issueLink = By.className("issue-created-key issue-link");
    private By searchBox = By.className(("sc-ZxTAX igUICu"));
    private String parentWindowHandler;
    private String subWindowHandler;


    public JiraIssue(WebDriver webDriver) {this.webDriver = webDriver;};

    public void enterStoryDetails(String summary, String description)
    {
        //subWindowHandler = webDriver.getWindowHandle();
        //List<String> handles =new ArrayList<String>(webDriver.getWindowHandles());
        //webDriver.switchTo().window(subWindowHandler);
        //parentWindowHandler = webDriver.getWindowHandle();
        //webDriver.switchTo().parentFrame();
        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("createGlobalItem")));
        webDriver.findElement(By.id("createGlobalItem")).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(summaryField));
        webDriver.findElement(summaryField).sendKeys(summary);
        webDriver.findElement(descriptionField).sendKeys(description);
        webDriver.findElement(createButton).click();
        //element = wait.until(ExpectedConditions.elementToBeClickable(By.className("aui-message aui-message-success success closeable shadowed aui-will-close")));
        //String issueKey = webDriver.switchTo().alert().getText();
        //element = wait.until(ExpectedConditions.elementToBeClickable(issueLink));
        //webDriver.findElement(issueLink).click();
        //try {
          //  Thread.sleep(15000);
        //} catch(InterruptedException e) {
        //    System.out.println("got interrupted!");
        //}
        //element = wait.until(ExpectedConditions.elementToBeClickable(issueLink));
        //element = wait.until(ExpectedConditions.elementToBeClickable(By.className("Icon__IconWrapper-dyhwwi-0 jdkWJB")));

    }
    public void addTestAttributes(String riskImpact, String riskLikelihood, String execStatus,String comment)
    {
        //WebDriverWait wait = new WebDriverWait(webDriver, 15);
        //WebElement element = wait.until

    }

}
