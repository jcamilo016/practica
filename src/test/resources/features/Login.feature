Feature: Login

  Scenario Outline: Unsuccessful Login
    Given the user wants go to the url "<url>"
    When Enter valid username "<user>" and pass "<pass>"
    Then unsuccessful Login
    Examples:
      | url                   |user                   | pass
      | https://www.etsy.com/ |mileani.mile@gmail.com | aaa