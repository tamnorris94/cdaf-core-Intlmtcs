package com.acutest.cdaf.stepdefs;

import com.acutest.cdaf.core.helpers.DriverFactory;
import com.acutest.cdaf.pageobjects.cdafWebTest.CdafWebTest;
import com.acutest.cdaf.pageobjects.jira.LoginPageObject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DemoStepDefs {

    private static Logger logger = LogManager.getLogger(DemoStepDefs.class);
    private WebDriver driver = DriverFactory.getDriver();
    private CdafWebTest webTestPage;
    private String bulletPoint;
    private String URL = "https://cdafwebapptesttarget2-dev-as.azurewebsites.net/";
    private LoginPageObject loginPage;
    String bulletxPath = "//*[@class='row']//*[@class='col-md-3']//li";
    List<WebElement> bulletList;

    @Given("I am on Acutest's test target web page")
    public void iAmOnAcutestSTestTargetWebPage() {
        String xPath = "//*[@class='navbar navbar-inverse navbar-fixed-top']//*[@class='navbar-header']";
        driver.get(URL);
        webTestPage = new CdafWebTest(driver);
        String title = webTestPage.getTitle(xPath);
        Assert.assertEquals("The title does not match the expected value",
                title, "cdafWebAppTestTarget_2");
    }

    @Given("the customer is on the test target app")
    public void theUserIsOnTheTestTargetApp() {
        driver.get(URL);
        String myUrl = driver.getCurrentUrl();
        Assert.assertEquals("Not on the the correct page, current url is: " + myUrl, myUrl, URL);
    }

    @When("^I select the first bullet point of application uses$")
    public void iSelectTheFirstBulletPoitOfApplicationUses() {
        bulletPoint = webTestPage.getBulletPoint(bulletxPath, "textContent", "Target for Acutest CDAF");
        boolean a = bulletPoint.contains("Target for Acutest");
        Assert.assertTrue("Correct bullet point not selected", a);
    }

    @Then("after a change is made to the HTML the test picks up the new word {string}")
    public void afterAChangeIsMadeToTheHTMLTheTestPicksUpTheNewWordWord(String word) {
        String subS = "tion";
        String newBullet = bulletPoint.replaceAll("\\s+","");
        int location = newBullet.indexOf(subS) + 4;
        String bulletSub = newBullet.substring(location);
        String base = newBullet.substring(0, location);
        String expectedText = base + word;
        Assert.assertEquals("The bullet point doesn't have the correct text", expectedText, newBullet);
    }
    
    @When("I navigate to the {string} page")
    public void iNavigateToTheAboutPage(String page) {
        driver.get(URL + "/" + page);
    }

    @When("the {string} page is selected")
    public void thePagePageIsSelected(String page) {
        String xPath = String.format("//nav//a[text()='%s']", page);
        WebElement element = null;
        try{
            element = driver.findElement(By.xpath(xPath));
        }catch (NoSuchElementException e){

        }
        Assert.assertNotNull("No page found with the given name: " + page, element);
        element.click();
    }

    @Then("the title is {string}")
    public void theTitleIsTitle(String title) {
        String xPath = "//h1";
        String xPath2 = "//h2";
        WebElement element = null;
        try {
            element = driver.findElement(By.xpath(xPath));
        } catch (NoSuchElementException e){
            element = driver.findElement(By.xpath(xPath2));
        }
        String text = element.getText();
        Assert.assertEquals("The page title is not correct", title, text);
    }

    @Given("I have permission to access Acutest sandboxd project on Jira")
    public void iHavePermissionToAccessAcutestSandboxdProjectOnJira() {
        String URL = "https://acutesttraining.atlassian.net/secure" +
                "/RapidBoard.jspa?rapidView=42&projectKey=CDAFSBXD";
        driver.get(URL);
        loginPage = new LoginPageObject(driver);
        String text = loginPage.checkPage();
        Assert.assertEquals("You are not on the correct page", text, "Enter email");
    }

    @When("I enter my username {string} and password")
    public void iEnterMyUsernameUserNameAndPassword(String username) throws InterruptedException {
        loginPage.enterUsername(username);
        WebElement element = null;
        try{
            element = driver.findElement(By.id("displayName"));
        } catch (NoSuchElementException e){

        }

        Assert.assertNull( "Your username is incorrect", element);
        loginPage.enterPassword(System.getenv("JIRA_PASSWORD"));
        //WebDriverWait wait = new WebDriverWait(driver,50);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-eaycls")));
    }

    @Then("the page contains the phrase {string}")
    public void thePageContainsThePhrasePhrase(String phrase) {
        String locator = String.format(".//*[contains(text(), '%s')]", phrase);
        WebElement element = null;
        try {
            element = driver.findElement(By.xpath(locator));
        } catch (NoSuchElementException e){

        }

        Assert.assertNotNull("The page doesn't contain the given phrase: " + phrase, element);
    }

    @Then("the jira page contains the phrase {string}")
    public void theJiraPageContainsThePhrasePhrase(String phrase) {

        WebDriverWait wait = new WebDriverWait(driver,20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-eaycls")));
        String project = loginPage.checkSandboxProject();
        Assert.assertEquals("You are not on the correct page", phrase, project);
        logger.info("Test");
    }

    @When("I click on a link {string}")
    public void iClickOnALinkLink(String link) {
        String xPath = "//li/a[text()='Security']";
        webTestPage.clickBulletPoint(xPath);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("action-panel")));
    }

    @When("the hyperlink {string} is selected")
    public void theHyperlinkLinkIsSelected(String link) {
        //".//*[contains(text(), '%s')]", phrase)
        String xPath = String.format("//li/a[contains(text(), '%s')]", link);
        webTestPage.clickBulletPoint(xPath);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("action-panel")));
    }

    @Then("the title of the new page is {string}")
    public void theTitleOfTheNewPageIsTitle(String title) {
        String xPath = "//*[@id='overview-of-aspnet-core-security']";
        String actualTitle = webTestPage.getTitle(xPath);
        Assert.assertEquals("The given text does not match the expected title", title, actualTitle);
    }

    @Then("a bullet point contains the name {string}")
    public void aBulletPointContainsTheNameName(String name) {
        WebElement foundName = bulletList.stream().filter(e -> e.getAttribute("innerText")
                .equals(name))
                .findFirst()
                .orElse(null);
        Assert.assertNotNull("No bullet point contains the name: " + name, foundName);
    }

    @When("the {string} section is selected")
    public void theKeyCollaboratorsSectionIsSelected(String section) {
        String xPath = String.format("//h2[text()= '%s' ]/following-sibling::ul//li", section);
        bulletList = driver.findElements(By.xpath(xPath));
    }

    @Given("the user is on localhost port")
    public void theUserIsOnLocalhostPort() {

        driver.get(URL);
    }

    @When("the service section with title {string} is selected")
    public void theServiceSectionWithTitleTitleIsSelected(String title) {
        String xPathTitle = String.format("//div[@class='container body-content']//h2[text()='%s']/following-sibling::p", title);
        try{
            bulletPoint = driver.findElement(By.xpath(xPathTitle)).getText();
        } catch (NoSuchElementException e){

        }
        Assert.assertNotNull("Can not find element with title: " + title, bulletPoint);
    }

    @Then("the text contains {string}")
    public void theTextContainsText(String text) {
        boolean match = bulletPoint.contains(text);
        Assert.assertTrue("The section does not contain the given text: " + text, match);
    }

    @When("the page with {string} page browsed")
    public void thePageWithUrlPageBrowsed(String url) {
        String urlPrefix = "https://";
        try{
            driver.get(urlPrefix + url);
        } catch (Exception e) {
            driver.get("http://" + url);
        }
    }

    @Given("an anonymous web user")
    public void anAnonymousWebUsers() {
    }

//    IssueInstance instance = new IssueInstance("CDAFSBXC-1269");
//        instance.loadIssue();
//    String id = instance.getIssueId();
//    XrayGraphQL ql = new XrayGraphQL(id);
//        ql.setGetTotalNumberOfExecutionsFromTestPlanjsonString();
//    String totalResponse = ql.makeRequest(ql.getGetTotalNumberOfExecutionsFromTestPlanjsonString());
//        ql.findTotalExecutions(totalResponse);
//        ql.setGetLastExecutionJsonString();
//    String lastExecRes = ql.makeRequest(ql.getGetLastExecutionJsonString());
//    String lastExecution = ql.findLastExecutionKey(lastExecRes);
}
