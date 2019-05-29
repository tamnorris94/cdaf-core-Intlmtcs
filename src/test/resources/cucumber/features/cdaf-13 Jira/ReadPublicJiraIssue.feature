@web @dev
Feature: Read public jira issue page.

  Scenario: Check title of issue PUBLICA-1
    Given user accesses acutesttraining project accessible to anonymous users
    When user opens page at "https://acutesttraining.atlassian.net/browse/PUBLICA-1"
    Then the page contains the word "20180615T1711"



