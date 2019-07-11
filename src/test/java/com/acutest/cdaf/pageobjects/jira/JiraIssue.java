package com.acutest.cdaf.pageobjects.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.*;

public class JiraIssue {
    private static WebDriver webDriver;
    private By summaryField = By.id("summary");
    private By descriptionField = By.id("description");
    private By createButton = By.id("create-issue-submit");
    private By issueLink = By.className("issue-created-key issue-link");
    private By searchBox = By.xpath("//*[@placeholder='Search Jira']");
    private By wantedSearchItem = By.className("ItemParts__Content-sc-14xek3m-5 jRBaLt");
    private By projectField = By.id("project-field");
    private By openIssueType = By.className("sc-RbTVP jFtvcR");
    private By issueType_xTest = By.className("ItemParts__Content-sc-14xek3m-5 jRBaLt");
    private By commentField = By.id("comment");
    private By riskImpact = By.id("customfield_10500");
    private By riskLikelihood = By.id("customfield_10501");

    String identifyIssue = LocalDateTime.now().toString();
    private By issueStatus = By.className("Icon__IconWrapper-dyhwwi-0 jdkWJB");
    private By issueStage1 = By.className("css-q5kkvr");
    private By issueNextStage = By.className("ItemParts__Content-sc-14xek3m-5 jRBaLt");

    private By confirmStage = By.id("issue-workflow-transition-submit");
   // private By issueStage3 = By.className("ItemParts__Content-sc-14xek3m-5 jRBaLt");
   // private By issueStage4 = By.className("ItemParts__Content-sc-14xek3m-5 jRBaLt");

    private String parentWindowHandler;
    private String subWindowHandler;


    public JiraIssue(WebDriver webDriver) {this.webDriver = webDriver;};

    public void enterStoryDetails(String summary, String description, String projectName)
    {
        //subWindowHandler = webDriver.getWindowHandle();
        //List<String> handles =new ArrayList<String>(webDriver.getWindowHandles());
        //webDriver.switchTo().window(subWindowHandler);
        //parentWindowHandler = webDriver.getWindowHandle();
        //webDriver.switchTo().parentFrame();
        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("createGlobalItem")));
        try {
            Thread.sleep(2500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
        webDriver.findElement(By.id("createGlobalItem")).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(summaryField));
        webDriver.findElement(summaryField).sendKeys(summary + " " + identifyIssue );
        webDriver.findElement(descriptionField).sendKeys(description);
        element = wait.until(ExpectedConditions.elementToBeClickable(projectField));
        webDriver.findElement(projectField).click();
        webDriver.findElement(projectField).sendKeys(projectName);
        webDriver.findElement(createButton).click();
        webDriver.findElement(createButton).click();


        //element = wait.until(ExpectedConditions.elementToBeClickable(By.className("aui-message aui-message-success success closeable shadowed aui-will-close")));
        //String issueKey = webDriver.switchTo().alert().getText();
        //element = wait.until(ExpectedConditions.elementToBeClickable(issueLink));
        //webDriver.findElement(issueLink).click();
        //element = wait.until(ExpectedConditions.elementToBeClickable(issueLink));
        //element = wait.until(ExpectedConditions.elementToBeClickable(By.className("Icon__IconWrapper-dyhwwi-0 jdkWJB")));

    }
    public void addTestAttributes(String summary, String description,String autoStatus, String riskLi, String riskIm, String execStatus,String comment)
    {
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("quickSearchGlobalItem")));

        try {
            Thread.sleep(3500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
        webDriver.findElement(By.id("quickSearchGlobalItem")).click();
        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        webDriver.findElement(searchBox).sendKeys(summary);
        element = wait.until(ExpectedConditions.elementToBeClickable(wantedSearchItem));
        webDriver.findElement(wantedSearchItem).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStatus));
        webDriver.findElement(issueStatus).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStage1));
        webDriver.findElement(issueStage1).click();

        element = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        webDriver.findElement(commentField).sendKeys("Stage 1 complete");
        webDriver.findElement(confirmStage).click();
        /* Stage 2 transition*/
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStatus));
        webDriver.findElement(issueStatus).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(issueNextStage));
        webDriver.findElement(issueStage1).click();


        //element = wait.until(ExpectedConditions.elementToBeClickable(openIssueType));
        //webDriver.findElement(openIssueType).click();
        //element = wait.until(ExpectedConditions.elementToBeClickable(issueType_xTest));
        //webDriver.findElement(issueType_xTest).click();
   }

}
