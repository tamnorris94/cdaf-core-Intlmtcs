@web @dev
Feature: Jira Issue Management

  Scenario Outline: As an automation framework developer
  I want to implement a web application test scenario which is similar to an order processing scenario
  So that I can develop and test a prototype implementation which can be used as a design pattern for other implementations.

    Given Acutest test automation developer logs in to Jira

    When user creates a new issue with description "<description>", summary "<summary>", Project Name "CDAF Sandbox B (CDAFSBXB)"

    And user provides description "<Description2>", automation status"<autoStatus>", risk likelihood"<riskLi>", risk impact "<riskIm>", execution status"<exStatus>" comment "<comment>"

    Then the issue with the given summary "<summary>" is successfully created

    Examples:
      | description                    |Description2  |summary                | riskIm          | riskLi          |exStatus    |autoStatus| comment   |
      |  Automated Test Creation       |Update Stage  |Test Test creation no1 | 2 - Visible     | 2 - Quite likely| Blocked    |Ready     | First run |
      #| Second Automated Test Creation |              |Test Test creation no2 | 3 - Minor       | 3 - Unlikely    | In Progress|Passed    | Second run|
      #| Third Automated Test Creation  |              |Test Test creation no3 | 1 - Interruption| 1 - Very likely | Blocked    |Failed    | Third run |