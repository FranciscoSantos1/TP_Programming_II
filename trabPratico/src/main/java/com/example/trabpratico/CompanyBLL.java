import java.util.Map;
import java.util.List;
import java.util.ArrayList;



public class CompanyBLL {
    public static void createCompany(Company company, CompanyOwner co) {
        if (co != null && company != null) {
            company.setCompanyOwner(co);

            Repository repo = Repository.getRepository();
            Map<CompanyOwner, List<Company>> companyFromCompanyOwner = repo.getCompanyFromCompanyOwner();
            Map<String, Company> companiesLocation = repo.getCompaniesLocation();
            Map<String, Company> companyMap = repo.getCompany();

            // Check if the companyOwner already has a list of companies
            List<Company> ownerCompanies = companyFromCompanyOwner.get(company.getCompanyOwner());
            if (ownerCompanies == null) {
                ownerCompanies = new ArrayList<>();
                companyFromCompanyOwner.put(co, ownerCompanies);
            }

            // Add the new company to the list of companies for this companyOwner
            ownerCompanies.add(company);

            companiesLocation.put(company.getLocation(), company);
            co.getCompanies().add(company);
            companyMap.put(company.getNIF(), company);
            Repository.getRepository().getCompanyOwners().put(co.getNIF(), co);
            System.out.println("Company created successfully!");
            Repository.getRepository().serialize("users.repo");


        }


    }
    }




/*import java.util.Map;

public class CompanyBLL {
    public static void createCompany(Company company, CompanyOwner co){

        company.setCompanyOwner(co);
        Repository.getRepository().getCompanyOwners().put(co.getNIF(), co);
        Repository.getRepository().getCompanyFromCompanyOwner().put(company.getCompanyOwner(), company);
        Repository.getRepository().getCompaniesLocation().put(company.getLocation(), company);
        Repository.getRepository().getCompanyOwners().get(co.getNIF()).getCompanies().add(company);
        Repository.getRepository().getCompany().put(company.getNIF(), company);
        System.out.println("Company created successfully!");
        Repository.getRepository().serialize("users.repo");


    }
}*/

