Feature: User Login
  Description: The User should login in to the system and also logout by his username and password
  Actor: User


  Scenario Outline: The User login successfully
    When the user enter a valid "<username>" and "<password>"
    Then the user should be login to the system
    Examples:
      | username | password |
      | Diaa     | 1234     |
      | Rashed   | 2142     |
      | Mousa    | 14253    |

    # here invalid user name mean dose not exist in the database :
  Scenario Outline: User login with invalid username
    When Enter an invalid "<username>" is entered
    Then an error message should be displayed
    Examples:
      | username |
      | Ahmad    |
      | Omar     |
      | Khaled   |

  Scenario Outline: User login with valid username and Invalid Password
    When user enter valid "<username>" and  invalid "<password>"
    Then an error message should be displayed
    Examples:
      | username   | password |
      | Rami       | 1234     |
      | Mohammad   | 2142     |
      | Abd        | 14253    |

  Scenario Outline: User login with blank field
    When  user enter blanks "<username>" and "<password>"
    Then an error message should be displayed
    Examples:
      | username  | password |
      |           |          |
      | Ahmad     |          |
      |           | 1234     |

