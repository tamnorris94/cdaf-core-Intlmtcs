Feature: Extract scenario from jira and execute
  As a tester
  I want scenario define in a jira description to be extracted from Jira and executed
  So that I can define automated test scenarios in Jira

  Scenario: Create feature file in target folder

    Given an issue with key SCBSBXA-93
    When the Jira api loads the issue
    Then the description should contain the words "Find first valid jira"

    When the scenario is written to the file "target/test-classes/cucumber/features/scbsbxa-93.feature"
    Then the file should contain the words "Find first valid jira"
