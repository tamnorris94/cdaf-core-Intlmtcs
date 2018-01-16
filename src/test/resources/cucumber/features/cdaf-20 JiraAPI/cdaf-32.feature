Feature: "Run requested - In progress" execution workflow
As a tester
I want automated tests to have a status "Run requested" which is updated to "In Progress" when the test run is set up by automation engine
So that I can queue up requests to run tests by a continuously running execution loop

  Scenario: Update status of test to "In Progress" once captured for a test run

    Given an issue with key SCBSBXA-58
    When I extract jira data for issues with the jquery: issue+in+linkedIssues("scbsbxa-94","relates+to")
