import java.io.Serializable;

public class AppointmentBLL {
    public static void createAppointment(Appointment appointment, Customer customer) {
        appointment.setState(AppointmentState.PROCESSED);
        Repository.getRepository().getAppointments().put(appointment.getIdConsulta(), appointment);
        Repository.getRepository().getCustomers().get(customer.getId()).getAppointments().add(appointment);
        System.out.println("Appointment created successfully!");
        Repository.getRepository().serialize("src\\main\\resources\\data\\data.dat");
    }

}
