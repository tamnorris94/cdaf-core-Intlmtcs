# new feature
@cdaf-21-feature

Feature: Jira integration - create jira comment on execution completion
  As a tester
  I want Jira issue representing a test scenario to be updated on completion of a scenario execution with a comment which includes test outcome and status of the test
  So that I can track and report the outcomes of automated test runs from Jira.

  @cdaf-21-1
  Scenario: Api load issue from Jira
    Given an issue with key SCBSBXA-58
    When the Jira api loads the issue
    Then the title should be "Sample scenario for testing: Arundel"

  @cdaf-21-2
  Scenario: Add comment to jira issue
    Given an issue with key SCBSBXA-58
    When a comment is added to the jira issue with text "Claridge" and a timestamp
    Then the jira issue should have a comment with the text and timestamp

  @cdaf-21-3 @cdaf-bad-23 @scbsbxa-58
  Scenario: Find first valid jira issuey key in tags
  #  Given the set of tags defined for this story
  #  When execution starts
    Then issueKeys identified should be "SCBSBXA-58"

  @cdaf-21-4 @scbsbxa-58
  Scenario: Update jira on completion of scenario execution
    Given an issue with key SCBSBXA-58
  #When the scenario completes
# Then the scenario should have comment added

  @scbsbxa-58
  Scenario Outline: cdaf-21_5 Update jira after completion of last example
    Given an issue with key <issue-key>
   #When all the scenario examples complete
 # Then the scenario should have one comment added

    Examples:
      |issue-key|
      |SCBSBXA-58|
      |SCBSBXA-58|
      |SCBSBXA-58|
      |SCBSBXA-58|
 @scbsbxa-58
  Scenario Outline: cdaf-21_7 Update jira after completion of last example
    Given an issue with key <issue-key>
  #When all the scenario examples complete
# Then the scenario should have one comment added
   # and the comment should have three passed and one failed

    Examples:
      |issue-key|
      |SCBSBXA-58|
      |SCBSBXA-58|
      |SCBSBXA-58|
      |SCBSBXA-58|

  @cdaf-21-6
    Scenario Outline: cdaf-21-6 Update jira after completion of last example
    Given an issue with key <issue-key>
  #When all the scenario examples complete
# Then the scenario should have one comment added
  @sbcsbxa-58
    Examples:
      |issue-key|
      |SCBSBXA-58|
      |SCBSBXA-58|
      |SCBSBXA-58|
      |SCBSBXA-58|


    Examples:
    |issue-key|
    |SCBSBXA-58|
    |SCBSBXA-58|
    |SCBSBXA-58|
    |SCBSBXA-58|

