import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ClinicBLL {
    public static void createClinic(Clinic clinic, Company company) {
        clinic.setCompany(company);

        Repository repo = Repository.getRepository();
        repo.deserialize("users.repo");

        Map<String, List<Clinic>> clinicsPerCompanyOwner = repo.getClinicsPerCompanyOwner();

        // Check if the company already has clinics
        List<Clinic> companyClinics = repo.getCompanieClinicsMap().get(company);
        if (companyClinics == null) {
            companyClinics = new ArrayList<>();
            repo.getCompanieClinicsMap().put(company, companyClinics);
        }

       List<Clinic> clinicsOwned = clinicsPerCompanyOwner.get(company.getCompanyOwner().getNIF());
        if(clinicsOwned == null){
            clinicsOwned = new ArrayList<>();
            clinicsPerCompanyOwner.put(company.getCompanyOwner().getNIF(), clinicsOwned);
        }

        companyClinics.add(clinic);
        clinicsOwned.add(clinic);


        repo.getClinicsMap().put(company.getCompanyOwner().getNIF(), clinic);

        System.out.println("Clinic created successfully!");

        repo.serialize("users.repo");
    }
}