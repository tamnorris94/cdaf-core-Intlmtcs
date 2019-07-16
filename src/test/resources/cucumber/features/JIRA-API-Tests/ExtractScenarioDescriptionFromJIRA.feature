@web @dev
Feature: Extract scenario description from jira
  As a tester
  I want scenario define in a jira description to be extracted from Jira
  So that I can show the interface is working and can use the description in other cdaf features

  Scenario: Create feature file in target folder

    Given an issue with key CDAFSBX-13
    When the Jira api loads the issue
    Then the description should contain the words "Feature: Check Windows services status"
    #When the scenario is written to the file "target/test-classes/cucumber/features/scbsbxa-93.feature"
    #When the scenario is written to the file "target/test-classes/scbsbxa-93.feature"
    #Then the file should contain the words "I have a Jira"
