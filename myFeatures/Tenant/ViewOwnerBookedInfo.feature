Feature: View Booked Owner information House
  Actor : Tenant

  Scenario: view the Owner information table whose I Booked with
    Given The following Owner information table:
      | OwnerUserName  | OwnerName        | OwnerPhone       | OwnerEmail        |
      | Rashed         | Rashed Sawallha  | 123-456-7890     | RashedSaw20@gmail.com |
    When I choose to view the View Owner Information Details
    Then I should see the Details  displayed on the screen




