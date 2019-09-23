package XrayFiles;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        monochrome = true,
        features = "target/test-classes/XrayFiles",
        glue = {"com/acutest/cdaf"},
        tags = {"@XrayBuildTest"},
        plugin = {"json:target/test-classes/XrayFiles/cucumber-report.json"}
)
public class XrayJiraXR {
}
