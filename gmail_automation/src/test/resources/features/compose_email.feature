gherkin

Feature: Compose and send email using Gmail

  Scenario: Compose and send an email
    Given I am on the Gmail login page
    When I log in with username "your.email@gmail.com" and password "your_password"
    Then I compose a new email with subject "Incubyte" and body content
      """
      Automation QA test for Incubyte
      
      Deliverables:
      
      Tabular Style Test Cases: These are the traditional test cases typically written in a table format
      
      BDD-Style Test Cases: These test cases should be written in the Behavior-Driven Development (BDD) format
      
      Both positive and negative test cases should be there and the assignment should be submitted via Git URL using java selenium
      """
    And I send the email
    Then I should see the email sent confirmation