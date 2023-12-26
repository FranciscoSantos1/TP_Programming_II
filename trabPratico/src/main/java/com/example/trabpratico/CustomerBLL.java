public class CustomerBLL {
    public static void createCustomer(Customer customer) {
        Repository.getRepository().getCustomers().put(customer.getNIF(), customer);
        System.out.println("Customer created successfully!");
        Repository.getRepository().serialize("src\\main\\resources\\data\\data.dat");
    }
}
