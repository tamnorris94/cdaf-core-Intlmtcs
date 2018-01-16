package com.acutest.cdaf.testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features = "target/test-classes/cucumber/features/", 
		tags = {"@search"}, 
		plugin = {"pretty", "html:target/cucumber-report/autocorrect", "junit:target/cucumber-report/autocorrect/cucumber.xml"})
public class SearchXX {
}
