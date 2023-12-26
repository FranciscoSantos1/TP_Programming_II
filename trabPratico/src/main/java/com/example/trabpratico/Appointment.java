import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class Appointment implements Serializable {
    private int idConsulta;
    private Date appointmentDate;
    private String employee;
    private String clinicName;
    private double totalValue;
    private Customer customer;
    private AppointmentState state;
    private Service services;

    public Appointment(){};

    public Appointment(Date appointmentDate, String employee, String clinicName, double totalValue, Customer customer, AppointmentState state, int idConsulta, Service services) {
        this.appointmentDate = appointmentDate;
        this.employee = employee;
        this.clinicName = clinicName;
        this.totalValue = totalValue;
        this.customer = customer;
        this.state = state;
        this.idConsulta = idConsulta;
        this.services = services;
    }

    public String getAppointmentDate() {
        return this.appointmentDate.toString();
    }

    public void setAppointmentDate(Date date) {
        this.appointmentDate = date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
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
}
