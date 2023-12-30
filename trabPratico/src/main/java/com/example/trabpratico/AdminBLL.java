public class AdminBLL {
    public static void createAdmin(Admin admin) {
        Repository.getRepository().getAdmins().put(admin.getId(), admin);

        System.out.println("Admin created successfully!");
        Repository.getRepository().serialize("users.repo");
    }
}
