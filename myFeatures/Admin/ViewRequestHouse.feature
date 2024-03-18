Feature: Printing Request House Table

  Scenario: Print the RequestHouse table with House information
    When I choose to view the Request table:
      | RequestID   | OwnerUserName  | Name         | City        | Street          | Description         | ContactNumber   | House For  | R/month | Number of Room | Number of Bathroom | Area  | Home Features       | Image   | Status |
      |   1         | Ahmad          | Ahmad mhmad  | Nablus      | Rafidia         | A lovely cottage    | 555-123-4567    | Rent       | 1500    | 2              | 1                  | 1000  | Garden, Furnished   | image   | Active |
    Then I should see the Request House table displayed on the screen




