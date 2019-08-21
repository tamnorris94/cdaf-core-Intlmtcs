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
    private By searchBox = By.xpath("//*[@placeholder='Search Jira']");
    private By projectField = By.id("project-field");
    private By openIssueType = By.className("sc-RbTVP jFtvcR");
    private By commentField = By.id("comment");
    private By riskImpact = By.id("customfield_10500");
    private By riskLikelihood = By.id("customfield_10501");

    String identifyIssue = LocalDateTime.now().toString();
    private By issueStatus = By.xpath("//*[contains(@class,'elro8wh2 css-1robokv')]");
    private By issueStatus2 = By.xpath("//*[contains(@class,'sc-QRZpq dEHKOP')]");
    private By issueStage1 = By.xpath("//*[contains(@id,'react-select-2-option-1')]");
    private By issueStage2 = By.xpath("//*[contains(@id,'react-select-3-option-2')]");
    private By riskVisible = By.xpath("//*[contains(text(),'2 - Visible')]");
    private By riskQuiteLikely = By.xpath("//*[contains(text(),'2 - Quite likely')]");
    private By issueStatus3 = By.xpath("//*[contains(@class,'sc-QRZpq dEHKOP')]");
    private By issueStage3 = By.xpath("//*[contains(@id,'react-select-4-option-3')]");
    private By testAutomationStatus = By.id("customfield_10825");
    private By testAutomationPassed = By.xpath("//*[contains(text(),'Passed')]");
    private By issueStatus4 = By.xpath("//*[contains(@class,'sc-QRZpq dEHKOP')]");
    private By issueStage4 = By.xpath("//*[contains(@id,'react-select-5-option-3')]");
    private By testExecutionStatus = By.id("customfield_10817");
    private By testExecutionPassed = By.xpath("//*[contains(text(),'In Progress')]");


    private By confirmStage = By.id("issue-workflow-transition-submit");

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

        WebDriverWait wait = new WebDriverWait(webDriver, 15);
        try {
            Thread.sleep(3500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("aui-flag-container")));
        webDriver.findElement(By.id("aui-flag-container")).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStatus));
        webDriver.findElement(issueStatus).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStage1));
        webDriver.findElement(issueStage1).click();

        element = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        webDriver.findElement(commentField).sendKeys("Stage 1 complete");
        webDriver.findElement(confirmStage).click();
        try {
            Thread.sleep(3500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
        /* Stage 2 transition*/
        element = wait.until(ExpectedConditions.presenceOfElementLocated(issueStatus2));
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStatus2));
        webDriver.findElement(issueStatus2).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStage2));
        webDriver.findElement(issueStage2).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(riskImpact));
        webDriver.findElement(riskImpact).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(riskVisible));
        webDriver.findElement(riskVisible).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(riskLikelihood));
        webDriver.findElement(riskLikelihood).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(riskQuiteLikely));
        webDriver.findElement(riskQuiteLikely).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        webDriver.findElement(commentField).sendKeys("Stage 2 complete");
        element = wait.until(ExpectedConditions.elementToBeClickable(confirmStage));
        webDriver.findElement(confirmStage).click();
        try {
            Thread.sleep(3500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        element = wait.until(ExpectedConditions.elementToBeClickable(issueStatus3));
        webDriver.findElement(issueStatus3).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStage3));
        webDriver.findElement(issueStage3).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(testAutomationStatus));
        webDriver.findElement(testAutomationStatus).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(testAutomationPassed));
        webDriver.findElement(testAutomationPassed).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        webDriver.findElement(commentField).sendKeys("Stage 3 complete");
        element = wait.until(ExpectedConditions.elementToBeClickable(confirmStage));
        webDriver.findElement(confirmStage).click();

        try {
            Thread.sleep(3500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        element = wait.until(ExpectedConditions.elementToBeClickable(issueStatus4));
        webDriver.findElement(issueStatus4).click();

        element = wait.until(ExpectedConditions.elementToBeClickable(issueStage4));
        webDriver.findElement(issueStage4).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(testExecutionStatus));
        webDriver.findElement(testExecutionStatus).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(testExecutionPassed));
        webDriver.findElement(testExecutionPassed).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        webDriver.findElement(commentField).sendKeys("Stage 4 complete");
        element = wait.until(ExpectedConditions.elementToBeClickable(confirmStage));
        webDriver.findElement(confirmStage).click();

        try {
            Thread.sleep(3500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }

}
