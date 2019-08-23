@web @dev
Feature: Jira Issue Management

  Scenario Outline: As an automation framework developer
  I want to implement a web application test scenario which is similar to an order processing scenario
  So that I can develop and test a prototype implementation which can be used as a design pattern for other implementations.

    Given Acutest test automation developer logs into Jira

    When user creates a new issue with description "<description>", summary "<summary>", Project Name "CDAF Sandbox B (CDAFSBXB)"

    And user provides "<summary>", description "<Description2>", automation status"<autoStatus>", risk likelihood"<riskLi>", risk impact "<riskIm>", execution status"<exStatus>" comment "<comment>"

     #Then the issue with the given summary "<summary>" and execution status "<exStatus>" is successfully created

    Examples:
      | description                    |Description2 |summary                | riskIm      | riskLi |exStatus  |autoStatus| comment   |
      | First Automated Test Creation  |Update Stage |Test Test creation no1 | Visible     |Quite Likely| Passed   |Ready     | First run |
      #| Second Automated Test Creation|             |Test Test creation no2 | Low         |        | To Do    |          | Second run|
      #| Third Automated Test Creation |             |Test Test creation no3 | High        |        | Failed   |          | Third run |
      #| Fourth Automated Test Creation|             |Test Test creation no4 | Highest     |        | Passed   |          | Fourth run|