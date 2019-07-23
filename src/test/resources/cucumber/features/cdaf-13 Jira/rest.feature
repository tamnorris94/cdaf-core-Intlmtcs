@CDAFSBXC-44
Feature:

	#Login test
  @TEST_CDAFSBXC-33
  Scenario: As a Tester
  I want to Log in to Jira
  So that I can create, read and update issues, tests and stories

    Given I am on the acutesttraining Jira Instance
	#Jira Page access test
  @TEST_CDAFSBXC-32
  Scenario: Check title of issue PUBLICA-1

    Given user accesses acutesttraining project accessible to anonymous users

    When user opens page at "https://acutesttraining.atlassian.net/browse/PUBLICA-1"

    Then the page contains the word "20180615T1711"