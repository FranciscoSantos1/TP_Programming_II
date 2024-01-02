public class CustomerBLL {
    public static void createCustomer(Customer customer) {
        customer.setId(Repository.getRepository().getCustomers().size() + 1);
        Repository.getRepository().getCustomers().put(customer.getNIF(), customer);


        System.out.println("Customer created successfully!");
        Repository.getRepository().serialize("users.repo");
    }
}
