import java.util.ArrayList;
import java.util.List;

public class CompanyOwner extends User{
    private List<Company> companies;

    public CompanyOwner() {
        companies = new ArrayList<>();
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public void listCompanies() {
        for(Company aux : this.companies){
            System.out.println("Nome: " + aux.getName());
            System.out.println("NIF: " + aux.getNIF());
            System.out.println("Morada: " + aux.getAddress());
            System.out.println("Telemovel: " + aux.getPhoneNumber());
        }
    }


}
