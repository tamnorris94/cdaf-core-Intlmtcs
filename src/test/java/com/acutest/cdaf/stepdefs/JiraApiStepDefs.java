package com.acutest.cdaf.stepdefs;

import com.acutest.cdaf.common.testConfiguration;
import com.acutest.cdaf.jiraapi.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

import static com.acutest.cdaf.jiraapi.JiraInvoker.runScenarios;
import static com.acutest.cdaf.jiraapi.JiraUtils.isValidIssueKey;
import static com.acutest.cdaf.jiraapi.JiraUtils.deleteFeatureFiles;

public class JiraApiStepDefs {

    IssueInstance issue1;
    String timestampedText;
    String description;
    String filepath;
    Map<String, IssueInstance> issues;

    @Given("^an issue with key (\\w+-\\d+)$")
    public void an_issue_with_key(String issueId) throws Throwable {
        Assert.assertTrue(isValidIssueKey(issueId));
        issue1 = new IssueInstance(issueId);
    }

    @When("^a comment is added to the jira issue with text \"([^\"]*)\" and a timestamp$")
    public void a_comment_is_added_to_the_jira_issue_with_text_and_a_timestamp(String commentText) throws Throwable {
        String timestamp = JiraUtils.timestampTextShort();
        timestampedText = commentText + " " + timestamp;
        issue1.addComment(timestampedText);
    }

    @Then("^the jira issue should have a comment with the text and timestamp$")
    public void the_jira_issue_should_have_a_comment_with_the_text_and_timestampi() throws Throwable {
        issue1.loadIssue();
        String comments = issue1.getComments();
        Assert.assertTrue(comments.contains(timestampedText));
    }

    @Then("^issueKeys identified should be \"([^\"]*)\"")
    public void issuekeys_identified_should_be_SCBSBXA(String expectedKeysString) throws Throwable {
        String foundKeysString = JiraCukeHooks.getTagIssuekeysString();
        Assert.assertEquals(expectedKeysString, foundKeysString);
    }

    @When("^the Jira api loads the issue$")
    public void the_Jira_api_loads_the_issue() throws Throwable {
        issue1.loadIssue();
    }

    @Then("^the title should be \"([^\"]*)\"$")
    public void the_title_should_be(String expectedTitle) throws Throwable {
        String actualTitle = issue1.getSummary();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Then("^the description should contain the words \"([^\"]*)\"$")
    public void the_descrption_should_contain_the_words(String expectedDescription) throws Throwable {
        description = issue1.getDescription();
        Assert.assertTrue(description.contains(expectedDescription));
    }

    @When("^the description is written to the file \"([^\"]*)\"$")
    public void the_description_is_written_to_the_folder(String requestedPath) throws Throwable {
        filepath = requestedPath;
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filepath), StandardCharsets.UTF_8));
        try {
            writer.write(description);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    @Then("^the file should contain the words \"([^\"]*)\"$")
    public void the_file_should_contain_the_words(String expectedWords) throws Throwable {
        String fileContents = new String(Files.readAllBytes(Paths.get(filepath))
                , StandardCharsets.UTF_8);
        Assert.assertTrue(fileContents.contains(expectedWords));
    }

    @When("^the scenario is written to the file \"([^\"]*)\"$")
    public void the_scenario_is_written_to_the_file(String requestedPath) throws Throwable {
        filepath = requestedPath;
        Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filepath), StandardCharsets.UTF_8));
        try {
            writer.write(description);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }

    @Given("^the test execution status is \"([^\"]*)\"$")
    public void the_test_execution_status_is(String expectedStatus) throws Throwable {
        issue1.loadIssue();
        String actualStatus = issue1.getExecutionStatus();
        Assert.assertTrue(expectedStatus.equals(actualStatus));
    }

    @When("^the test execution status is updated to \"([^\"]*)\"$")
    public void the_test_execution_status_is_updated_to(String newStatus) throws Throwable {
        issue1.updateExecutionStatus(newStatus);
        // Write code here that turns the phrase above into concrete actions
    }

    @When("^I extract jira data for issues with the jquery: (.*)$")
    public void i_extract_jira_data_for_issues_with_the_jquery(String jquery) throws Throwable {

        issues = JiraApi.runJQuery(jquery);
    }

    @Then("^the following issue should be found \"(.*)\"$")
    public void the_following_issue_should_be_found(String issuekey) throws Throwable {
        Long matchCount = issues.entrySet().stream()
                .filter(map -> map.getKey().contains(issuekey))
                .collect(Collectors.counting());
        Assert.assertTrue(matchCount == 1);

    }

    @When("^create feature files$")
    public void create_feature_files() throws Throwable {
        String targetLocation = testConfiguration.getProperty("testrun.featurefileLocation");

        deleteFeatureFiles(targetLocation);
        for (Map.Entry<String, IssueInstance> map : issues.entrySet()) {
          map.getValue().createFeaturefile(targetLocation) ;
        }
    }

    @Then("^feature file named \"([^\"]*)\" exists$")
    public void feature_file_named_exists(String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^The test operations user invokes the test run utility from the IDE$")
    public void the_test_operations_user_invokes_the_test_run_utility_from_the_IDE() throws Throwable {
        runScenarios();
    }

}
