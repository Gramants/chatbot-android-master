Feature: I want to see the username on the titlebar for different user
@go
  Scenario Outline: Show the entered username in the chat titlebar
    Given I am on the login main page
    When I type the username <USERNAME> in the textfield
    And I click on the login button
    Then I should see <USERNAME> on the titlebar

    Examples:
      | USERNAME  |
      | "Stefano" |
      | "Gramantieri" |
      | "Testdemo" |
      | "ChatBotDemo" |