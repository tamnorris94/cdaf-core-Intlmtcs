package com.acutest.cdaf.testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "target/test-classes/cucumber/features/", 
		tags = {"@none"},
		plugin = {"pretty", "html:target/cucumber-report/autocorrect"})
public class AutoCorrectAT {
}
