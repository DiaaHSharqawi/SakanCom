Feature: Printing Owner Table

  Scenario Outline: Print the owner table with owner information
    When I print the owner table "<Owner UserName>" with "<Owner Name>", "<Owner Phone>", and "<Owner Email>"
    Then I should see the owner information displayed on the screen

    Examples:
      | Owner UserName | Owner Name       | Owner Phone      | Owner Email       |
      | Ahmad          | Ahmad Khaled     | 123-456-7890     | AhmadKh@gmail.com |

