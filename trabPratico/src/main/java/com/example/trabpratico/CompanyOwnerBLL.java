public class CompanyOwnerBLL {
    public static void createCompanyOwner(CompanyOwner companyOwner){

        Repository.getRepository().getCompanyOwners().put(companyOwner.getNIF(), companyOwner);
        System.out.println("Company owner created successfully!");
        Repository.getRepository().serialize("userdata.repo");
    }
}
