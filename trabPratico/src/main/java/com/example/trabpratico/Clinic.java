import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Clinic implements Serializable {
    private String name;
    private Company company;
    private String address;
    private String phoneNumber;
    private String NIF;
    private String appointmentType;
    private ArrayList<Employee> employees;
    private Map<Service, Double> services;


    public Clinic() {
        this.employees = new ArrayList<>();
        this.services = new HashMap<>();
    }

    public Clinic(String name, Company company, String address, String location, String appointmentType, String NIF ) {
        this.name = name;
        this.company = company;
        this.address = address;

        this.appointmentType = appointmentType;
        this.NIF = NIF;
        this.employees = new ArrayList<>();
        this.services = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Map<Service, Double> getServices() {
        return services;
    }

    public void setServices(Map<Service, Double> services) {
        this.services = services;
    }

}
