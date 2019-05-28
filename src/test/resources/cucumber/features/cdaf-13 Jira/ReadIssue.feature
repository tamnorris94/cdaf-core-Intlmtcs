@cdaf-13 @web @dev
Feature: Read Jira issue with automation framework
As a CDF developer
I want a  scenario implementation to create a Jira issue
So that I can develop a prototype multi-page order processing scenario template

  Scenario: Create new Story
    Given I am on the acutesttraining Jira instance
    When I open page at "https://acutesttraining.atlassian.net/browse/PUBLICA-1"
    Then I the page should contain the word "20180615T1711"