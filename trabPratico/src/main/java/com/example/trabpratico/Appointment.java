import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class Appointment implements Serializable {
    private int idConsulta;
    private LocalDate appointmentDate;
    private String employee;
    private Clinic clinic;
    private double totalValue;
    private Customer customer;
    private AppointmentState state;
    private Service services;
    private String description;

    public Appointment(){};

    public Appointment(LocalDate appointmentDate, String employee, Clinic clinic, double totalValue, Customer customer, AppointmentState state, int idConsulta, Service services) {
        this.appointmentDate = appointmentDate;
        this.employee = employee;
        this.clinic = clinic;
        this.totalValue = totalValue;
        this.customer = customer;
        this.state = state;
        this.idConsulta = idConsulta;
        this.services = services;
    }

    public LocalDate getAppointmentDate() {
        return this.appointmentDate;
    }

    public void setAppointmentDate(LocalDate date) {
        this.appointmentDate = date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AppointmentState getState() {
        return state;
    }

    public void setState(AppointmentState state) {
        this.state = state;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Service getServices() {
        return services;
    }

    public void setServices(Service services) {
        this.services = services;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
