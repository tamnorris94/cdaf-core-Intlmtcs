package com.acutest.cdaf;

import com.acutest.cdaf.core.helpers.DriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.*;
import java.util.concurrent.TimeUnit;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static Logger log = LogManager.getLogger();
    private WebDriver driver;
    private static boolean dunit = false;

    public Hooks() {
    }


    /**
     * Method contains necessary setups and initializations needed in place before any tests can run
     */

    @Before
    public void beforeAll(Scenario scenario )throws Exception {
        log.trace("Executing beforeAll, about to check 'dunit' value");
        if (!dunit) {


            log.trace("Initialising before first scenario.");            // Set up afterAll to run at end.
            Runtime.getRuntime().addShutdownHook(new Thread(){
                public void run()  {
                    log.trace("Executing shutdownHook.");
                    afterAll();
                }
            });
            // Add items here to run before all scenarios.
            TestConfiguration.loadAPropertiesFile("./src/test/resources/testConfiguration.yaml");
            dunit = true;

        }
    }

    /**
     * Method contains setups and initializations that need to be in place before each scenarion run
     */
    @Before
    public void beforeEachScenario(Scenario scenario) throws MalformedURLException {

        log.debug("@Before scenario " + scenario.getName());

        if (isUsingWebdriver(scenario)) {
            driver = DriverFactory.initialize();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            DriverFactory.setImplicitWait(5); // Default value for rest of run
            driver.manage().timeouts().implicitlyWait(DriverFactory.getImplicitWait(), TimeUnit.SECONDS);
        }
    }

    /**
     * Method contains statements that terminate or shut down setups after each scenario has
     * finished running
     *
     */
    @After
    public void afterEachScenario(Scenario scenario) {
        log.debug("@After scenario " + scenario.getName());
        if (isUsingWebdriver(scenario)) {
            try {
                if (scenario.isFailed()) {
                    try {
                        scenario.write("Current Page URL is " + driver.getCurrentUrl());
                        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                        scenario.embed(screenshot, "image/png");
                    } catch (WebDriverException e1) {
                        log.error(e1.getMessage());
                    } catch (ClassCastException e2) {
                        e2.printStackTrace();
                    }
                }

            } finally {



                // Use below line if you have a memory issue.
//                new DriverFactory().destroyDriver();
            }
        }
    }

    /**
     * determines if the scenario requires webdriver to be used
     * @param scenario
     * @return
     * Method returns a boolean value asserting whether webdriver is used
     */
    private boolean isUsingWebdriver(Scenario scenario) {
        boolean isWeb = true;
        List<String> scenarioTagList = (List<String>) scenario.getSourceTagNames();
        for (String scenarioTag : scenarioTagList) {
            log.debug(scenarioTag);
            if (scenarioTag.equalsIgnoreCase("@web")) {
                // by convention API testing will use @API
                isWeb = false;
            }
        }
        return isWeb;
    }

    /**
     * AFTER ALL SCENARIOS AND FEATURES
     */
    /**
     * Method deletes the driver instance after scenarios have finished running
     */
    public void afterAll(){
        log.trace("Executing afterAll");


// Add items here to run after tests
        DriverFactory.destroyDriver();
    }

}