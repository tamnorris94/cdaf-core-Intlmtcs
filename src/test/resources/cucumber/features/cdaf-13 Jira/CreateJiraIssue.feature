@cdfjXX-13 @web
Feature: Create Jira issue with automation framework		
As a CDF developer
I want a  scenario implementation to create a Jira issue
So that I can develop a prototype multi-page order processing scenario template

  Scenario: Create new Story
    Given I am on the acutesttraining Jira instanc
    When I create a new story
    And title is "Automation test"
    Then I should get a new Jira issue id