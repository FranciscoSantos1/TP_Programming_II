public class SessionData {
    public static Admin loggedAdmin;
    public static CompanyOwner loggedCompanyOwner;
    public static Employee loggedEmployee;
    public static Customer loggedCustomer;

    public static void setLoggedAdmin(Admin admin) {
        loggedAdmin = admin;
    }

    public static void setLoggedCompanyOwner(CompanyOwner companyOwner) {
        loggedCompanyOwner = companyOwner;
    }

    public static void setLoggedEmployee(Employee employee) {
        loggedEmployee = employee;
    }

    public static void setLoggedCustomer(Customer customer) {
        loggedCustomer = customer;
    }

    public static Admin getLoggedAdmin() {
        return loggedAdmin;
    }

    public static CompanyOwner getLoggedCompanyOwner() {
        return loggedCompanyOwner;
    }

    public static Employee getLoggedEmployee() {
        return loggedEmployee;
    }

    public static Customer getLoggedCustomer() {
        return loggedCustomer;
    }

}
