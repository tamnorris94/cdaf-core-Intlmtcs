Feature: Create rules file from Jira issue description
  As a tester
  I want to define rules in a jira issue description which is used by automation rules expression engine
  So that I can use Jira to manage rules used in testing


  Scenario: Read scenario file from jira issue
    Given an issue with key SCBSBXA-92
    When the Jira api loads the issue
    Then the description should contain the words "Transmission"

    When the description is written to the file "target/test-classes/rules.txt"
    Then the file should contain the words "Transmission"
