import java.util.ArrayList;
import java.util.List;

public class CompanyOwner extends User{
    private List<Company> companies;

    public CompanyOwner() {
        companies = new ArrayList<>();
    }
}
