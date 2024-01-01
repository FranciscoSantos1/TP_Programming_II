import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppointmentBLL {
    public static void createAppointment(Appointment appointment, Customer customer) {
        appointment.setState(AppointmentState.PROCESSADA);
        appointment.setCustomer(customer);
        appointment.setIdConsulta(Repository.getRepository().getAppointments().values().size() + 1);

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

    public static void updateAppointmentStates() {
        Map<String, List<Appointment>> appointmentsList = Repository.getRepository().getAppointments();

        for (List<Appointment> appointments : appointmentsList.values()) {
            for (Appointment appointment : appointments) {
                if (appointment.getState() == AppointmentState.PROCESSADA && LocalDate.now().isAfter(appointment.getAppointmentDate())) {
                    appointment.setState(AppointmentState.REALIZADA);
                    System.out.println("Appointment ID " + appointment.getIdConsulta() + " foi REALIZADA.");
                }
                if(appointment.getState() == AppointmentState.PROCESSADA && LocalDate.now().isEqual(appointment.getAppointmentDate())){
                    appointment.setState(AppointmentState.REALIZADA);
                    System.out.println("Appointment ID " + appointment.getIdConsulta() + " foi NAO_REALIZADA.");
                }
            }
        }

        Repository.getRepository().serialize("users.repo");
    }
}
