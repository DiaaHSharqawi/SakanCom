Feature: View Tenant Information House
  Actor : Tenant

  Scenario: view the Tenant Information tableL
    Given The following Tenant table:
      | TenantUsername  | TenantName   | TenantPhone    | TenantEmail       |
      | Khaled          | Khaled Ahmad | 555-222-000    | KhaledA@gmail.com |
    When I choose to view the Tenant House
    Then I should see the Tenant Details  displayed on the screen




