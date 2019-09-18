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

You can change the browser in the following way:

1) navigate to src>test>java>resources>testConfiguration.yaml and update `driver: firefox` to `driver: chrome`

If having problems changing the browser open the logger and check the class `TestConfiguration` is loading the contents of `testConfiguration.yaml` into `System properties`

Jira Password is set as an environment variable: JIRA_PASSWORD

Current version of chromedriver is 74.

When running tests that do not need a browser add `@api` tag to the test to not open a browser

