package com.acutest.cdaf.stepdefs;
//trigger build
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

public class DemoStepDefs {

    private static Logger logger = LogManager.getLogger(DemoStepDefs.class);
    private WebDriver driver = DriverFactory.initialize();
    private CdafWebTest webTestPage;
    private String bulletPoint;
    private String URL = "https://cdafwebapptesttarget2-dev-as.azurewebsites.net/";
    private LoginPageObject loginPage;
    String bulletxPath = "//*[@class='row']//*[@class='col-md-3']//li";

    @Given("I am on Acutest's test target web page")
    public void iAmOnAcutestSTestTargetWebPage() {
        String xPath = "//*[@class='navbar navbar-inverse navbar-fixed-top']//*[@class='navbar-header']";
        driver.get(URL);
        webTestPage = new CdafWebTest(driver);
        String title = webTestPage.getTitle(xPath);
        Assert.assertEquals("The title does not match the expected value",
                title, "cdafWebAppTestTarget_2");
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

//     @When("I navigate to the About page")
//     public void iNavigateToTheAboutPage() {
//         driver.get(URL + "/About");
//     }
    
    @When("I navigate to the {string} page")
    public void iNavigateToTheAboutPage(String page) {
        driver.get(URL + "/" + page);
    }

    @Then("the title is {string}")
    public void theTitleIsTitle(String title) {
        String xPath = "//h2";
        WebElement element = driver.findElement(By.xpath(xPath));
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

    @Then("the title of the new page is {string}")
    public void theTitleOfTheNewPageIsTitle(String title) {
        String xPath = "//*[@id='overview-of-aspnet-core-security']";
        String actualTitle = webTestPage.getTitle(xPath);
        Assert.assertEquals("The given text does not match the expected title", title, actualTitle);
    }
}
