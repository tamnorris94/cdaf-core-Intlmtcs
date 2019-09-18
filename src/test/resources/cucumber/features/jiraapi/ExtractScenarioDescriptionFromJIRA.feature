Feature: Extract scenario description from jira
  As a tester
  I want scenario define in a jira description to be extracted from Jira
  So that I can show the interface is working and can use the description in other cdaf features

  @jiraapi-test
  Scenario: Read jira description through Jira API

    Given an issue with key CDAFSBX-13
    When the Jira api loads the issue
    Then the description should contain the words "Feature: Check Windows services status"
