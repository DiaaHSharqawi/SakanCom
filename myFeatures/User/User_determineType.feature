Feature: User Determine Type
  Description: Since The User  login in to the system I want to determine his role to display each User role DashBoard
  Actor: User

  Scenario: Display Admin Dashboard
    When the User Type is Admin
    Then the Admin DashBoard Should be Displayed

    # should Added another when complete the owner and Tenant
  Scenario: Display Tenant Dashboard
    When the User Type is Tenant
    Then the Tenant DashBoard Should be Displayed








