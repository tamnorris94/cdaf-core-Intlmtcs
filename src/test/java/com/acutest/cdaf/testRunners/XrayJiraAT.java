package com.acutest.cdaf.testRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(

        monochrome = true,
        features = "target/test-classes/com/acutest/cdaf/",
        glue = {"com/acutest/cdaf/xray/stepdefs"},
        tags = {"@XrayBuildTest"},
        plugin = {"json:target/cucumber-report/autocorrect/cucumber-report.json"}
)
public class XrayJiraIT
{ }