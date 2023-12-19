import java.util.ArrayList;

public class Employee extends User{
    private EmployeeType type;
    private ArrayList<Appointment> appointments;
    private Company company;

    public Employee(EmployeeType type) {
        this.type = type;
        appointments = new ArrayList<>();
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
