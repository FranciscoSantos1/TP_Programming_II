import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppointmentBLL {
    public static void createAppointment(Appointment appointment, Customer customer) {
        appointment.setState(AppointmentState.PROCESSED);

        Map<String, List<Appointment>> appointmentsList = Repository.getRepository().getAppointments();

        List<Appointment> appointments = appointmentsList.get(customer.getNIF());
        if(appointments == null){
            appointments = new ArrayList<>();
            appointmentsList.put(customer.getNIF(), appointments);
        }

        appointments.add(appointment);

        Repository.getRepository().getCustomers().get(customer.getNIF()).getAppointments().add(appointment);
        System.out.println("Appointment created successfully!");
        Repository.getRepository().serialize("users.repo");
    }

}
