Feature: Add highest priced item to cart

  Scenario: Login and add the highest priced item to the cart
    Given I am on the SauceDemo login page
    When I login with username "standard_user" and password "secret_sauce"
    And I add the highest priced item to the cart
    Then I should see the item added to the cart
