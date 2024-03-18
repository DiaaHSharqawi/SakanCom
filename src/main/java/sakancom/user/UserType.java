package sakancom.user;
import sakancom.admin.Admin;
import sakancom.tenant.Tenant;
import sakancom.testinput.ValidateInput;
import sakancom.dashboard.DashboardStatus;

import java.util.Scanner;
import static sakancom.dashboard.DashboardStatus.DISPLAYED;

// this class depending on userName and password it will query from db and determine the type of user
public class UserType {
    private Admin admin;

    private Tenant tenant;
    public Tenant getTenant() {
        return tenant;
    }
    public void setTenant(Tenant tenant){
        this.tenant=tenant;
    }

    private  DashboardStatus dashboardStatus;


    public Admin getAdmin() {
        return admin;
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
    public  DashboardStatus getDashboardStatus() {
        return dashboardStatus;
    }



    public  void determineType(User userInput)  {
        ValidateInput validateInput=new ValidateInput();
        Scanner scanner=new Scanner(System.in); //<---------------

        switch (userInput.getType()){
          case "Admin":
              if(admin==null)
                  admin = new Admin(scanner, validateInput);
              else
                  admin=getAdmin();

              admin.getAdminDashBoard().adminMenu(userInput);
              dashboardStatus=DISPLAYED;
              break;
          case "Owner":
              dashboardStatus=DISPLAYED;


              break;
          case "Tenant":
                if(tenant==null){
                    tenant=new Tenant(scanner,validateInput);
                }
                else
                    tenant=getTenant();

                tenant.getTenantDashBoard().tenantMenu(userInput);
                dashboardStatus=DISPLAYED;
                break;
            default:
                break;
        }
    }

}
