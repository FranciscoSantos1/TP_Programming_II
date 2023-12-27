public class SessionData {
    public static Admin admin;
    public static CompanyOwner companyOwner;
    public static Employee employee;
    public static Customer customer;

    public void getLoggedUsers() {
        if (admin != null) {
            System.out.println("Admin: " + admin.getUsername());
        } else if (companyOwner != null) {
            System.out.println("Company Owner: " + companyOwner.getUsername());
        } else if (employee != null) {
            System.out.println("Employee: " + employee.getUsername());
        } else if (customer != null) {
            System.out.println("Customer: " + customer.getUsername());
        }
    }
}
