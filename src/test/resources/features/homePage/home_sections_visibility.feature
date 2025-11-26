Feature: Visibility of all section cards on Home Page

  As a DemoQA user
  I want to see all section cards on the Home Page
  So that I can easily navigate to different parts of the site

  Scenario: All section cards are visible and clickable
    Given the user opens the Home Page
    Then all section cards should be displayed
    And each card should have a title and an icon
    And each card should be clickable
