public class CompanyBLL {
    public static void createCompany(Company company, CompanyOwner co){
        company.setCompanyOwner(co);
        Repository.getRepository().getCompanyFromCompanyOwner().put(company.getCompanyOwner(), company);
        Repository.getRepository().getCompaniesLocation().put(company.getLocation(), company);
        Repository.getRepository().getCompanyOwners().get(co.getNIF()).getCompanies().add(company);
        System.out.println("Company created successfully!");
        Repository.getRepository().serialize("src\\main\\resources\\data\\users.repo");
    }
}
