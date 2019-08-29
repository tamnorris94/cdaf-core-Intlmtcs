package com.acutest.cdaf.pageobjects.jira;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
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
    private By projectField = By.id("project-field");
    private By commentField = By.id("comment");
    private By riskImpact = By.id("customfield_10500");
    private By riskLikelihood = By.id("customfield_10501");

    private By createGlobalItem = By.xpath("//*[contains(@id,'createGlobalItem')]/parent::div");
    String identifyIssue = LocalDateTime.now().toString();
    private By issueStatus = By.xpath("//*[contains(text(),'Backlog')]");
    private By issueStatus1 = By.xpath("//*[contains(text(),'CDAFSBXB Stage 1')]");
    private By issueStatus2 = By.xpath("//*[contains(text(),'CDAFSBXB Stage 2')]");
    private By issueStatus3 = By.xpath("//*[contains(text(),'CDAFSBXB Stage 3')]");
    private By issueStage1 = By.xpath("//*[contains(@id,'react-select-2-option-1')]");
    private By issueStage2 = By.xpath("//*[contains(@id,'react-select-3-option-2')]");
    private By issueStage3 = By.xpath("//*[contains(@id,'react-select-4-option-3')]");
    private By testAutomationStatus = By.id("customfield_10825");
    private By issueStage4 = By.xpath("//*[contains(@id,'react-select-5-option-3')]");
    private By testExecutionStatus = By.id("customfield_10817");
    private By confirmStage = By.id("issue-workflow-transition-submit");

    public JiraIssue(WebDriver webDriver) {this.webDriver = webDriver;};

    public void enterStoryDetails(String description, String summary, String projectName)
    {

        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        WebElement element;

        element = wait.until(ExpectedConditions.elementToBeClickable(createGlobalItem));

        webDriver.findElement(createGlobalItem).click();

        element = wait.until(ExpectedConditions.elementToBeClickable(summaryField));
        String concatSummary = summary +" " + identifyIssue;
        webDriver.findElement(summaryField).sendKeys(concatSummary );
        webDriver.findElement(descriptionField).sendKeys(description);
        element = wait.until(ExpectedConditions.elementToBeClickable(projectField));
        webDriver.findElement(projectField).click();
        webDriver.findElement(projectField).sendKeys(projectName);
        webDriver.findElement(createButton).click();
        webDriver.findElement(createButton).click();


    }
    public void addTestAttributes(String description2,String autoStatus, String riskLi, String riskIm, String execStatus,String comment)
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

        element = wait.until(ExpectedConditions.visibilityOfElementLocated(descriptionField));
        webDriver.findElement(descriptionField).sendKeys(description2);
        element = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        webDriver.findElement(commentField).sendKeys("Stage 1 complete");
        webDriver.findElement(confirmStage).click();
        try {
            Thread.sleep(3500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
        /* Stage 2 transition*/
        element = wait.until(ExpectedConditions.presenceOfElementLocated(issueStatus1));
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStatus1));
        webDriver.findElement(issueStatus1).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStage2));
        webDriver.findElement(issueStage2).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(riskImpact));
        webDriver.findElement(riskImpact).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + riskIm + "')]")));
        webDriver.findElement(By.xpath("//*[contains(text(),'" + riskIm + "')]")).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(riskLikelihood));
        webDriver.findElement(riskLikelihood).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + riskLi + "')]")));
        webDriver.findElement(By.xpath("//*[contains(text(),'" + riskLi + "')]")).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        webDriver.findElement(commentField).sendKeys("Stage 2 complete");
        element = wait.until(ExpectedConditions.elementToBeClickable(confirmStage));
        webDriver.findElement(confirmStage).click();

        /* Stage 3 Transition*/
        try {
            Thread.sleep(3500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        element = wait.until(ExpectedConditions.elementToBeClickable(issueStatus2));
        webDriver.findElement(issueStatus2).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(issueStage3));
        webDriver.findElement(issueStage3).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(testAutomationStatus));
        webDriver.findElement(testAutomationStatus).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + autoStatus + "')]")));
        webDriver.findElement(By.xpath("//*[contains(text(),'" + autoStatus + "')]")).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(commentField));
        webDriver.findElement(commentField).sendKeys("Stage 3 complete");
        element = wait.until(ExpectedConditions.elementToBeClickable(confirmStage));
        webDriver.findElement(confirmStage).click();

        /* Stage 4 Transition*/
        try {
            Thread.sleep(3500);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        element = wait.until(ExpectedConditions.elementToBeClickable(issueStatus3));
        webDriver.findElement(issueStatus3).click();

        element = wait.until(ExpectedConditions.elementToBeClickable(issueStage4));
        webDriver.findElement(issueStage4).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(testExecutionStatus));
        webDriver.findElement(testExecutionStatus).click();
        element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + execStatus + "')]")));
        webDriver.findElement(By.xpath("//*[contains(text(),'" + execStatus + "')]")).click();
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

    public void verifyIssueCreation(String summary)
    {
        String issueVerifier = String.format(".//*[contains(text(), '%s')]",summary + " " + identifyIssue);
        List<WebElement> identify = webDriver.findElements(By.xpath(issueVerifier));
        assert(!identify.isEmpty());
    }

}
