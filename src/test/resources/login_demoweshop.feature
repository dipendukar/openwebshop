Feature: login positive test case

  Background:
    Given user navigate to site url "https://demowebshop.tricentis.com"
    Then verify login page title "Demo Web Shop. Login"

  @datatable
  Scenario: verify login functionality with multiple credentials
    When user login to site with multiple credentials
      | username            | password    |
      | sample123@outlook.com | sample123  |
      | sample1234@outlook.com   | sample12345 |