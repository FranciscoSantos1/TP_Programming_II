import java.util.Map;

public class CompanyBLL {
    public static void createCompany(Company company, CompanyOwner co){

        company.setCompanyOwner(co);
        Repository.getRepository().getCompanyFromCompanyOwner().put(company.getCompanyOwner(), company);

        Repository.getRepository().getCompaniesLocation().put(company.getLocation(), company);
        Repository.getRepository().getCompanyOwners().get(co.getNIF()).getCompanies().add(company);
        Repository.getRepository().getCompany().put(co.getNIF(), company);
        System.out.println("Company created successfully!");
        Repository.getRepository().serialize("users.repo");


    }
}

