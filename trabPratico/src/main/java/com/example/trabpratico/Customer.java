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

    public void ListAppointments(){
        for(Appointment ap: this.appointments){
            System.out.println("\n\n");
            System.out.println("Numero consulta: " + ap.getIdConsulta());
            System.out.println("Clinica: " + ap.getClinic().getName());
            System.out.println("Data:" + ap.getAppointmentDate());
            System.out.println("Servicos: " + ap.getService());
            System.out.println("Estado: " + ap.getState());
            System.out.println("Valor: " + ap.getTotalValue());
            System.out.println("Funcionario: " + ap.getEmployee());
            System.out.println("Cliente: " + ap.getCustomer().getFullName());
            System.out.println("\n\n");

        }
    }
}
