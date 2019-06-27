# cdaf-core
Acutest continuous delivery automation framework core component.
Baseline for client specific implementations.

This implementation includes a webdriver session by default. Firefox or chrome
can be selected by setting the configuration file, or overidden by run time option.

Webdriver agents are to be installed in C:\cdaf_tools\SeleniumWebDriver. You can set the location
in testConfig.yaml.

For an installation test run the feature "ReadPublicJiraIssue".

Configured so you can choose between running tests in either firefox of chrome.
Possible to update framework to accept other browers in the future.

Default setting is firefox.

You can change the browser in the following ways:

1) navigate to src>test>java>resources>environment.properties and update browser=firefox to browser=chrome

2) command line using maven - in the terminal run "mvn clean verify -Dbrowser=chrome" (without speach marks).

If having problems changing the browser navigate to src>test>java>Hooks. In the "beforeAll" method find line:
FileInputStream propFile = new FileInputStream("./src/test/resources/environment.properties");
Check file path is correct from your starting directory to environment.properties file.

Find your starting directory by navigating to src>test>java>com.acutest.cdaf>core.helpers>DriverFactory
put a break point on line:

String localDir = System.getProperty("user.dir");

run test in debug.

For chrome to work you need to use chromedriver 74.
