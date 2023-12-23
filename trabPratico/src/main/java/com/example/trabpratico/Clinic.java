import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Clinic implements Serializable {
    private Company company;
    private String address;
    private String location;
    private String appointmentType;
    private ArrayList<Employee> employees;
    private String type;

    public Clinic() {
        this.employees = new ArrayList<>();
    }

    public Clinic(Company company, String address, String location, String appointmentType, String type) {
        this.company = company;
        this.address = address;
        this.location = location;
        this.appointmentType = appointmentType;
        this.employees = new ArrayList<>();
        this.type = type;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
