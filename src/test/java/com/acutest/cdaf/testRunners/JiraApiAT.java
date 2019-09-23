package com.acutest.cdaf.testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        // glue location must also contain cucumber hooks class.
		monochrome = true,
		//features = "target/test-classes/cucumber/features/",
		features = "src/test/resources/cucumber/features",
		glue = "com/acutest/cdaf/jiraapi",
		tags = {"@jiraapi-test"},
		plugin = {"pretty", "html:target/cucumber-report/autocorrect"})
public class JiraApiAT {



}
