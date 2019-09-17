Feature: DemoJiraTrigger

  Scenario: As an Automation test developer
            I want to seek for a random word in a webpage
            So that it serves as an illustrative test in a demo


    Given I am on Acutest's test target web page

    When a check for the word "Target" is performed for the landing page

    Then the page contains the phrase "Target"