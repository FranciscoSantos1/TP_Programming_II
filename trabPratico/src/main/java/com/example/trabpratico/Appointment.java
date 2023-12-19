import java.io.Serializable;
import java.util.Date;

public class Appointment implements Serializable {
    private Date appointmentDate;
    private String employee;
    private String clinicName;
    private double totalValue;
    private Customer customer;
    private AppointmentState state;
    private int idConsulta;
    private Service services;

    public Appointment(){};

    public Appointment(Date appointmentDate, String employee, String clinicName, double totalValue, Customer customer, AppointmentState state, int idConsulta) {
        this.appointmentDate = appointmentDate;
        this.employee = employee;
        this.clinicName = clinicName;
        this.totalValue = totalValue;
        this.customer = customer;
        this.state = state;
        this.idConsulta = idConsulta;
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
}
