import java.util.ArrayList;

public class Employee extends User {
    private EmployeeType type;
    private ArrayList<Appointment> appointments;
    private Clinic clinic;
    private EmployeeType employeeType;

    public Employee() {
        appointments = new ArrayList<>();
    }

    public Employee(EmployeeType type) {
        this.type = type;
        appointments = new ArrayList<>();
    }


    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
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

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

}
