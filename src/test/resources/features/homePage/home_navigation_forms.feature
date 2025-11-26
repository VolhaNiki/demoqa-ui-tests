Feature: Navigation to Forms section from Home Page

  As a DemoQA user
  I want to open the Forms section from the Home Page
  So that I can test the Practice Form functionality

  Scenario: User opens the Forms section
    Given the user opens the Home Page
    When the user clicks on the "Forms" card
    Then the Forms page should be displayed
    And the page title should contain "Forms"
