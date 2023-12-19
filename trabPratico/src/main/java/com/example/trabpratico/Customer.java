import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private List<Appointment> appointments;

    public Customer() {
        appointments = new ArrayList<>();
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
