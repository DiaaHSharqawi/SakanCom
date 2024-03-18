Feature: View Booked House
  Actor : Tenant

  Scenario: view the Booked House table for Tenant after
    Given The following Booked House Inforamation :
      | BookingID   | tenantName     | ownerName    | BookingDate |
      |   1         | Ahmad          | Ahmad mhmad  | 1-8-2023      |
    When I choose to view the Booked House
    Then I should see the Booked Details  displayed on the screen




