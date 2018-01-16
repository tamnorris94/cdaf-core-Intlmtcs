Feature: Extract and run test scenarios linked to test set in Jira
As a tester
I want to create a Test Plan issuetype which has links to a number of Scenario issue types containing feature file text and be able to request execution of the Test Plan which results in the outcome of each test updated in Jira as described in CDAF-20 Backlog
So that I can manage test creation and execution from Jira.Scenario: Get list of scenario issuetypes from a test set

  @cdaf-24-1 @autorun2
  Scenario: Extract list of scenarios linked to a test set issue
  #Given an issue with key SCBSBXA-94 which is linked to scenario issues
    When I extract jira data for issues with the jquery: issue+in+linkedIssues("scbsbxa-94","relates+to")

    Then the following issue should be found "SCBSBXA-93"
    And  the following issue should be found "SCBSBXA-95"

  @cdaf-24-2
  Scenario: Write jira descriptions to feature files
  #Given an issue with key SCBSBXA-94 which is linked to scenario issues
    When I extract jira data for issues with the jquery: issue+in+linkedIssues("scbsbxa-94","relates+to")
    And create feature files

#    Then feature file named "SCBSBXA-93.feature" exists
#    Then feature file named "SCBSBXA-95.feature" exists

#@cdaf-24-3
#Scenario: Invoke cucumber run from IDE
##Given scenario files have been written to the target folder
#  When The test operations user invokes the test run utility from the IDE
#  #Then all the cucumber runs should complete

