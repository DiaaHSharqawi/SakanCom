Feature: View Reservation House
  Actor: Admin

  Scenario: view the Reservation table with House information
    Given The following Reservation house:
      | RequestID   | OwnerUserName  | Name         | City        | Street          | Description         | ContactNumber   | House For  | R/month | Number of Room | Number of Bathroom | Area  | Home Features       | Image   | Status |
      |   1         | Ahmad          | Ahmad mhmad  | Nablus      | Rafidia         | A lovely cottage    | 555-123-4567    | Rent       | 1500    | 2              | 1                  | 1000  | Garden, Furnished   | image   | Active |
    When I choose to view the Reservation of the  House
    Then I should see the Reservation displayed on the screen




