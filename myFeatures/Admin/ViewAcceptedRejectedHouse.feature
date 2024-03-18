Feature: Printing Accepted Rejected House Table

  Scenario: Print the Accepted table with House information
    When I choose to view the Accepted table:
      | RequestID   | OwnerUserName  | Name         | City        | Street          | Description         | ContactNumber   | House For  | R/month | Number of Room | Number of Bathroom | Area  | Home Features       | Image   | Status   |
      |   1         | Ahmad          | Ahmad mhmad  | Nablus      | Rafidia         | A lovely cottage    | 555-123-4567    | Rent       | 1500    | 2              | 1                  | 1000  | Garden, Furnished   | image   | Accepted |
    Then I should see the Accepted House table displayed on the screen


  Scenario: Print the rejected table  with House information
    When I choose to view the Rejected table:
      | RequestID   | OwnerUserName  | Name           | City        | Street          | Description         | ContactNumber   | House For  | R/month | Number of Room | Number of Bathroom | Area  | Home Features       | Image   | Status   |
      |   2        | rashed          | rashed khaled  | Nablus      | Rafidia         | A lovely cottage    | 555-123-4567    | Rent       | 1500    | 2              | 1                  | 1000  | Garden, Furnished   | image   | Rejected |
    Then I should see the Rejected House table displayed on the screen




