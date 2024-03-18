Feature: Housing System Administrator
  Description: As admin I want to manage housing advertisements operation's
  Actor: Administrator

  Scenario Outline: accept Reject Request
    Given a request with ID "<RequestID>"
    When the user checks if the request exists
    Then the request with ID "<RequestID>" should be "<Existence>"
    And the user chooses to "<Action>"
    Then the request should be "<Result>"

    Examples:
      | RequestID | Existence | Action | Result    |
      | 1         | Yes       | Accept | Accepted  |
      | 2         | Yes       | Reject | Rejected  |
      | 3         | No        |        | Not Found |
      | 4         | No        |        | Not Found |

