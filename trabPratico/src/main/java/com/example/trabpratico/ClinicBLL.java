public class ClinicBLL {
    public static void createClinic (Clinic clinic, Company company){
        clinic.setCompany(company);
        Repository.getRepository().getCompanieClinicsMap().put(clinic.getCompany(), clinic);
        Repository.getRepository().getClinicsMap().put(clinic.getNIF(), clinic);
        System.out.println("Clinic created successfully!");
        Repository.getRepository().serialize("users.repo");

    }
}
