import java.util.ArrayList;

public class Employee extends User {
    private EmployeeType type;
    private ArrayList<Appointment> appointments;
    private Clinic clinic;

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

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public void printClinicData(){
        System.out.println("Nome: " + clinic.getName());
        System.out.println("NIF: " + clinic.getNIF());
        System.out.println("Morada: " + clinic.getAddress());
        System.out.println("Localizacao: " + clinic.getLocation());
    }
}
