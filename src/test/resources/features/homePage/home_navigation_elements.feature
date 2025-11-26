Feature: Navigation to Elements section from Home Page

  As a DemoQA user
  I want to open the Elements section from the Home Page
  So that I can test various interactive elements

  Scenario: User opens the Elements section
    Given the user opens the Home Page
    When the user clicks on the "Elements" card
    Then the Elements page should be displayed
    And the page title should contain "Elements"
