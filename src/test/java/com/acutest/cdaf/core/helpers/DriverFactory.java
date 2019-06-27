package com.acutest.cdaf.core.helpers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class selects which browser to open before each scenario and closes the browser at the end.
 */
public class DriverFactory {


    protected static WebDriver driver;
    private static Logger log = LogManager.getLogger();

    private static long implicitWaitTimeInSeconds;
    private static final Thread closeDriverThread = new Thread() {
        @Override
        public void run() {
            if (driver != null) {
                driver.quit();
            }
        }
    };

    String localDir = System.getProperty("user.dir");

    private static DriverFactory driverFactoryInstance;
    private DriverFactory() {


        initialize();

    }


    /**
     * initialises the driver as a singleton if not already initialised otherwise returns driver
     * @return
     */
    public static WebDriver initialize() {
        if(driverFactoryInstance == null) {
            driverFactoryInstance = new DriverFactory();
            createNewDriverInstance();
        }
         return driver;
    }

   

    /**
     * Opens the browser specified in testConfiguration.yaml
     *
     */
    private static void createNewDriverInstance() {
        String downloadFilepath = "C:\\Users\\Public\\Jenkins-Automation-Framework\\csvFiles\\adminReport\\";
        String path = "C:\\cdaf_tools\\SeleniumWebDriver\\";
        File driverPath;
        // system variables
        String browser = System.getProperty("browser", "firefox");
        String driverExec;
        boolean isHeadless;
        switch (browser){
            case "firefox":
                driverPath = new File(path + "geckodriver.exe");
                driverExec = (String) System.getProperty("driverExec", String.valueOf(driverPath));
                isHeadless = Boolean.valueOf(System.getProperty("headless", "false"));
                System.setProperty("webdriver.gecko.driver", driverExec);
                driver = new FirefoxDriver();
                Runtime.getRuntime().addShutdownHook(closeDriverThread);
                break;
            case "chrome":
                driverPath = new File(path + "chromedriver.exe");
                driverExec = (String) System.getProperty("driverExec", String.valueOf(driverPath));
                isHeadless = Boolean.valueOf(System.getProperty("headless", "false"));
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
                break;
            default:
                log.error("property not valid: browser " + browser);
                break;
        }

       // log.info(String.format("Executing test using %S located %S", browser, driverExec));
     //   log.info("Message created 20180629 to check changes applied");

        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        driver = eventFiringWebDriver.register(new WebEventListener());

        Assert.assertNotNull("Driver failed initialization", driver);
    }

    /**
     * returns the instance of the webdriver
     * @return
     */
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Quits the browser at the end of each scenario
     */

    public static void destroyDriver() {

        driver.quit();
        driver = null;
    }

    public static long getImplicitWait() {
        return implicitWaitTimeInSeconds;
    }

    public static void setImplicitWait(long waitTime) {
        DriverFactory.implicitWaitTimeInSeconds = waitTime;
    }
}
