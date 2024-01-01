import java.io.Serializable;
import java.time.LocalDate;

public class Appointment implements Serializable {
    private int idConsulta;
    private LocalDate appointmentDate;
    private Employee employee;
    private Clinic clinic;
    private double totalValue;
    private Customer customer;
    private AppointmentState state;
    private Service service;
    private String description;

    public Appointment(){};

    public Appointment(LocalDate appointmentDate, Employee employee, Clinic clinic, double totalValue, Customer customer, AppointmentState state, int idConsulta, Service services) {
        this.appointmentDate = appointmentDate;
        this.employee = employee;
        this.clinic = clinic;
        this.totalValue = totalValue;
        this.customer = customer;
        this.state = state;
        this.idConsulta = idConsulta;
        this.service = services;
    }

    public LocalDate getAppointmentDate() {
        return this.appointmentDate;
    }

    public void setAppointmentDate(LocalDate date) {
        this.appointmentDate = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
