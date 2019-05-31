package com.acutest.cdaf.core.helpers;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    protected String downloadFilepath = "C:\\Users\\Public\\Jenkins-Automation-Framework\\csvFiles\\adminReport\\";
    protected static WebDriver driver;
    protected static Logger log;
    private static long implicitWaitTimeInSeconds;
    private final Thread closeDriverThread = new Thread() {
        @Override
        public void run() {
            if (driver != null) {
                driver.quit();
            }
        }
    };

    String localDir = System.getProperty("user.dir");
    //File driverPath = new File(localDir + "\\src\\test\\resources\\driver\\chromedriver.exe");
    //File driverPath = new File("C:\\cdaf_tools\\SeleniumWebDriver\\");
    //private String filePath = "File driverPath = new File(\"C:\\\\cdaf_tools\\\\SeleniumWebDriver\\\\\");";

    // system variables
    private String browser = System.getProperty("browser", "firefox");

    File driverPath = browser.equalsIgnoreCase("firefox") ? new File("C:\\cdaf_tools\\SeleniumWebDriver\\geckodriver.exe") : new File("C:\\cdaf_tools\\SeleniumWebDriver\\chromedriver.exe");
    //private String browser = "chrome";
    // Chrome driver path has been changed
    private String driverExec = (String) System.getProperty("driverExec", String.valueOf(driverPath));
    private boolean isHeadless = Boolean.valueOf(System.getProperty("headless", "false"));

    public DriverFactory() {

        log = Logger.getLogger(DriverFactory.class);
        initialize();

    }

    protected void initialize() {
         if (driver == null)
             createNewDriverInstance();
     }

    private void createNewDriverInstance() {


       // log.info(String.format("Executing test using %S located %S", browser, driverExec));
     //   log.info("Message created 20180629 to check changes applied");

        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", driverExec);
            driver = new FirefoxDriver();
         } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", driverExec);

       //     log.info("Setting ChromeOptions for fix 20180629");
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", downloadFilepath);


            ChromeOptions options = new ChromeOptions()
                    .addArguments("--start-maximized")
                    .addArguments("--disable-gpu")
                    //When you ar  running headless this line needs to be commented
                    .addArguments("window-size=1200x600")
                    // When you are not using headless-->set window size like below.Commen line:  .addArguments("window-size=1200x600") and use .addArguments("window-size=1200x1100")
                    //When you are  running headless this line needs to be uncommented
//                   .addArguments("window-size=1200x1100")
                    .addArguments("--no-sandbox")
                    .addArguments("--disable-dev-shm-usage");

            options.setExperimentalOption("prefs", prefs);

            if (isHeadless) {
                options.setHeadless(true).addArguments("--headless");
            }

            driver = new ChromeDriver(options);

            Runtime.getRuntime().addShutdownHook(closeDriverThread);

        } else {
            log.error("property not valid: browser " + browser);
        }

        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        driver = eventFiringWebDriver.register(new WebEventListener());

        Assert.assertNotNull("Driver failed initialization", driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void destroyDriver() {
        driver.quit();
        driver = null;
    }



    public long getImplicitWait() {
        return implicitWaitTimeInSeconds;
    }

    public void setImplicitWait(long waitTime) {
        DriverFactory.implicitWaitTimeInSeconds = waitTime;
    }
}
