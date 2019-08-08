package com.acutest.cdaf.testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features = "target/test-classes/cucumber/features/",
		glue = "com/acutest/cdaf/stepdefs/",
		tags = {"@dev"},
		plugin = {"pretty", "html:target/cucumber-report/autocorrect"})
public class JiraAxT {
}
