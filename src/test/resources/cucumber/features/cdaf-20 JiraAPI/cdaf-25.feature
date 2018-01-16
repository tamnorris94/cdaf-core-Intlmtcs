Feature: Update Jira issue test execution status with run outcome
  As a tester
  I want the test execution custom field of the Jira issue for the scenario to be updated
  with the outcome of the test run
  So that I can report from Jira on the test status.
@cdaf-25-1
  Scenario: Update test execution status
  Given an issue with key SCBSBXA-58
  And the test execution status is "In Progress"

  When the test execution status is updated to "Passed"
  Then the test execution status is "Passed"

  When the test execution status is updated to "In Progress"
  Then the test execution status is "In Progress"
             #
