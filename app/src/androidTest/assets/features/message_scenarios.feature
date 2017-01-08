Feature: I want to write my message and see it in the chat list
@go
  Scenario Outline: When user log in and write a message he can see it in the message list
    Given I am on the login main page
    When I type the username <USERNAME> in the textfield
    And I click on the login button
    Then I see the message list
    When I type the message <MESSAGE> in the messagebox
    And I click on the button to send the message


    Examples:
      | USERNAME  | MESSAGE |
      | "Stefano" | "this is a test demo message written by Espresso" |
      | "Gramantieri" | "this is another authomated test demo message written by Espresso engine which is running good" |
