package com.acutest.cdaf.common;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.Scenario;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Based on shared webdriver implementation in cucumber-jvm examples
 * A new instance of SharedDriver is created for each Scenario and passed to  Stepdef classes via Dependency Injection
 */
public class SharedDriver extends EventFiringWebDriver {
    private static final WebDriver REAL_DRIVER;
    public Map<String, Object> config;
    private static Logger logger = LogManager.getLogger();

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };

    static {
    	System.setProperty("webdriver.gecko.driver", "C:\\cdaf_tools\\SeleniumWebDriver\\geckodriver.exe");

        REAL_DRIVER = new FirefoxDriver();
        REAL_DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public SharedDriver() {
        super(REAL_DRIVER);
    }


    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    @Before("@web")
    /**
     * Delete all cookies at the start of each scenario to avoid
     * shared state between tests
     */
    public void deleteAllCookies() {
        logger.debug("Deleting cookies");
        manage().deleteAllCookies();
    }


    @After("@web")
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
        scenario.write("Current Page URL is " + getCurrentUrl());
        try {
            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }

}
