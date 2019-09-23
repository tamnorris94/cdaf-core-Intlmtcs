package com.acutest.cdaf.stepdefs;

import com.acutest.cdaf.core.helpers.DriverFactory;
import com.acutest.cdaf.jiraapi.IssueInstance;
import com.acutest.cdaf.jiraapi.JiraApi;
import com.acutest.cdaf.pageobjects.cdafWebTest.CdafWebTest;
import com.acutest.cdaf.pageobjects.jira.LoginPageObject;
import com.acutest.cdaf.xray.XrayGraphQL;
import com.acutest.cdaf.xray.XrayToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class DemoStepDefs {

    private static Logger logger = LogManager.getLogger(DemoStepDefs.class);
    private WebDriver driver = DriverFactory.initialize();
    private CdafWebTest webTestPage;
    private String bulletPoint;
    private String URL = "https://cdafwebapptesttarget2-dev-as.azurewebsites.net/";
    private LoginPageObject loginPage;
    String bulletxPath = "//*[@class='row']//*[@class='col-md-3']//li";
    private File stagedTestsLog = new File("./src/test/resources/stagedTestsLog.txt");
    private File stagedTestsYaml = new File("C:\\cdaf_tools\\stagedTestsInfo\\stagedTestsLog.yaml");
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
    private static Scenario scenario;

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
        String newBullet = bulletPoint.replaceAll("\\s+", "");
        int location = newBullet.indexOf(subS) + 4;
        String bulletSub = newBullet.substring(location);
        String base = newBullet.substring(0, location);
        String expectedText = base + word;
        Assert.assertEquals("The bullet point doesn't have the correct text", expectedText, newBullet);
    }

//    @When("I navigate to the About page")
//    public void iNavigateToTheAboutPage() {
//        driver.get(URL + "/About");
//    }

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
        try {
            element = driver.findElement(By.id("displayName"));
        } catch (NoSuchElementException e) {

        }

        Assert.assertNull("Your username is incorrect", element);
        loginPage.enterPassword(System.getenv("JIRA_PASSWORD"));
        //WebDriverWait wait = new WebDriverWait(driver,50);
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.className("css-eaycls")));
    }

    @Then("the jira page contains the phrase {string}")
    public void thePageContainsThePhrasePhrase(String phrase) {

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Projects']")));
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

//    @Given("test one was run more than two hours ago")
//    public void testOneWasRunMoreThanTwoHoursAgo() throws InterruptedException {
//        String xPath = "//div[@id='xray-test-testruns-panel']";
//                //"//table//tbody/tr[1]";
//        driver.get("https://acutesttraining.atlassian.net/browse/CDAFSBXC-885");
//        loginPage = new LoginPageObject(driver);
//        loginPage.enterUsername(System.getProperty("jira.jiraUsername"));
//        loginPage.enterPassword(System.getenv("JIRA_PASSWORD"));
//        loginPage.getExecution();
//
//    }

    @Then("the timestamp is added to file with test number <{int}>")
    public void theTimestampIsAddedToFileWithTestNumber(int num) {
        String time = getTime();

//        boolean founfFile = stagedTestsLog.exists();
//        logger.info("Staged tests log file exists: " + founfFile);
//
        try {
//            FileWriter fileWriter = new FileWriter(stagedTestsLog, true);
//            BufferedWriter br = new BufferedWriter(fileWriter);
//            br.newLine();
//            br.write("Test " + num + " run at: " + time);
//            br.close();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
            yamlLastTestRun run = new yamlLastTestRun(num, time);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.writeValue(stagedTestsYaml, run);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private String getTime(){
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/London"));
        return now.format(format);
    }

    @Given("the previous test was run more than two hours ago")
    public void thePreviousTestWasRunMoreThanTwoHoursAgo() {
        String time = getTime();
        String lastLine = null;
        yamlLastTestRun last = null;
        try{
//            ReversedLinesFileReader fr = new ReversedLinesFileReader(stagedTestsLog, 4000, "UTF-8");
//            lastLine = fr.readLine();
//            fr.close();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            last = mapper.readValue(stagedTestsYaml, yamlLastTestRun.class);
        } catch (IOException e){
            e.printStackTrace();
        }
        //int test = Character.getNumericValue(lastLine.charAt(5));
        //boolean correctTest = test == 1;
        int test = last.getTestNumber();
        boolean correctTest = test == 1;
        Assume.assumeTrue("The last test run was not test No. 1", correctTest);

        //String line2 = lastLine.substring(15);
        //LocalDateTime test1 = LocalDateTime.parse(line2);
        LocalDateTime test2 = LocalDateTime.parse(time);
        LocalDateTime test1 = LocalDateTime.parse(last.getRunAt());
        //Duration duration = Duration.between(test1, test2);
        Duration duration = Duration.between(test1, test2);
        long hours = duration.toHours();
        boolean timePassed = hours >= 2;
        Assume.assumeTrue("Not enough time has passed to run this test", timePassed);
    }


//    @Then("info is embedded into the jira test execution")
//    public static void infoIsEmbeddedIntoTheJiraTestExecution(){
//        try{
//           // yamlPractice myYaml = new yamlPractice("George", "Cooper");
//            File jiraYaml = new File("./screenshots/nestedJson.json");
//            //ObjectMapper mapper = new ObjectMapper(new JsonFactory());
//            //mapper.writeValue(jiraYaml, myYaml);
//            scenario = Hooks.getScenario();
//            scenario.embed(FileUtils.readFileToByteArray(jiraYaml), "application/json");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    //            File jiraInfo = new File("./screenshots/jiraInfo.txt");
//            FileWriter fw = new FileWriter(jiraInfo);
//            PrintWriter pw = new PrintWriter(fw);
//            pw.print("Test");
//            pw.close();

    @Given("test one was run more than two hours ago {string}")
    public void testOneWasRunMoreThanTwoHoursAgoIssueKey(String issuekey) throws InterruptedException {
        String xPath = "//div[@id='xray-test-testruns-panel']";
        //"//table//tbody/tr[1]";
        driver.get("https://acutesttraining.atlassian.net/browse/" + issuekey);
        loginPage = new LoginPageObject(driver);
        loginPage.enterUsername(System.getProperty("jira.jiraUsername"));
        loginPage.enterPassword(System.getenv("JIRA_PASSWORD"));
        loginPage.getExecution();
    }

    @Then("test staged data is sent to jiraIssue")
    public void testStagedDataIsSentToJiraIssue() {

    }

    @Given("GET last test executions staged data from test plan {string}")
    public void iGETLastTestExecutionsStagedDataFromTestPlanTestPlan(String testPlan) throws Exception {
        IssueInstance instance = new IssueInstance(testPlan);
        instance.loadIssue();
        JsonNode issueResponse = instance.getIssueNode();
        String issueId = instance.getIssueId();
        XrayGraphQL graph = new XrayGraphQL(issueId);
        graph.setGetTotalNumberOfExecutionsFromTestPlanjsonString();
        String totalExecutionsJson = graph.getGetTotalNumberOfExecutionsFromTestPlanjsonString();
        String executionsResponse = graph.xrayGraphQLRequest(totalExecutionsJson);
        graph.findTotalExecutions(executionsResponse);
        graph.setGetLastExecutionJsonString();
        String lastExecutionJson = graph.getGetLastExecutionJsonString();
        String lastExecutionResponse = graph.xrayGraphQLRequest(lastExecutionJson);
        String lastExecution = graph.findLastExecutionKey(lastExecutionResponse);




//        int totalExecutions = getTotalExecutions(issueId);
//        String executionKey = getLastExecution(totalExecutions - 1, issueId);
        String response = JiraApi.request("GET", "/rest/api/2/issue/" + lastExecution);
        getLastExecutionInfo(response);

    }

    private void getLastExecutionInfo(String response) throws IOException {
        String customfield = System.getProperty("jira.customfieldId.customfield");
        logger.info("customfield name: " + customfield);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode testStagedData = root.path("fields").path(customfield);
    }

    private String getLastExecution(int totalExecutions, String issueId) throws Exception {
        java.net.URL url = new URL("https://xray.cloud.xpand-it.com/api/v1/graphql");
        String token = XrayToken.produceXrayToken();
        String jsonString = "{ \"query\": \"{getTestPlan (issueId:\\\"" + issueId + "\\\")" +
                "{ issueId testExecutions (limit:1, start:" + totalExecutions +
                ") {total results {issueId   jira(fields: [\\\"key\\\"])}}}}\"}";
        logger.info("HTTP request body: " + jsonString);
        String lastExecutionKey = null;

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("x-access-token", token);
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        byte[] inputJson = jsonString.getBytes("utf-8");
        os.write(inputJson, 0, inputJson.length);
        os.close();
        int responseCode = con.getResponseCode();
        logger.info("Http response code: " + responseCode);
        if(responseCode == HttpStatus.SC_OK){
            InputStream input = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            StringBuffer response = new StringBuffer();
            while((line = reader.readLine()) != null){
                response.append(line);
            }
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.toString());
            lastExecutionKey = root.path("data").path("getTestPlan").path("testExecutions")
                    .path("results").get(0).path("jira").path("key").asText();
            //lastExecutionKey = response.toString().substring(117, response.length() - 8);

            reader.close();
            os.flush();
            os.close();
            con.disconnect();
        } else{
            logger.error("failed: " + con.getResponseMessage() + con.getErrorStream());
        }
        return lastExecutionKey;
    }

    private int getTotalExecutions(String issueId) throws Exception {
        java.net.URL url = new URL("https://xray.cloud.xpand-it.com/api/v1/graphql");
        String token = XrayToken.produceXrayToken();
        String jsonString = "{\"query\": \"{getTestPlan (issueId:\\\"" + issueId + "\\\")" +
                "{ issueId testExecutions (limit:1) {total }}}\"}";
        logger.info("HTTP request body: " + jsonString);
        int total = 0;
        try{
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("x-access-token", token);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            con.setDoInput(true);
            OutputStream os = con.getOutputStream();
            byte[] inputJson = jsonString.getBytes("utf-8");
            os.write(inputJson, 0, inputJson.length);
            os.close();
            //con.connect();
            int responseCode = con.getResponseCode();
            logger.info("Http response code: " + responseCode);

            if(responseCode == HttpURLConnection.HTTP_OK){
                InputStream input = con.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String line;
                StringBuffer response = new StringBuffer();
                while((line = reader.readLine()) != null){
                    response.append(line);
                }
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(response.toString());
                total = root.path("data").path("getTestPlan").path("testExecutions").path("total").intValue();
                reader.close();
                os.flush();
                os.close();
                con.disconnect();
            } else{
                logger.error("failed: " + con.getResponseMessage() + con.getErrorStream());
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return total;
    }
}