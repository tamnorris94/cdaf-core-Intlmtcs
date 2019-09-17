
Feature: Execution results [1562235813171]

	#Login test
  @TEST_CDAFSBXC-33
  Scenario: Log in test for Jira
    Given I am on the acutesttraining Jira Instance
	#Jira Page access test
  @TEST_CDAFSBXC-32 @TESTSET_CDAFSBXC-45
  Scenario: Publica Jira Issue Page
    Given user accesses acutesttraining project accessible to anonymous users

    When user opens page at "xxhttps://acutesttraining.atlassian.net/browse/PUBLICA-1"

    Then the page contains the word "20180615T1711"
