
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/com/example/trabpratico/login.fxml"));
        Scene scene = new Scene(root, 750, 500);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Repository repo;
        Repository.deserialize("src/main/resources/data/users.repo");
        repo = Repository.getRepository();
        User u = new Customer();

        //DADOS DE TESTE
        u.setUsername("c1");
        u.setPassword("c1");
        u.setNIF("123456789");
        u.setAddress("Rua do customer1");
        u.setPhoneNumber("937707486");
        u.setFullName("customer1");
        CustomerBLL.createCustomer((Customer) u);


        if (repo.getAdmins().isEmpty()) {
            Admin a1 = new Admin();
            a1.setUsername("Administrador");
            a1.setAddress("Rua do Administrador");
            a1.setNIF("251320561");
            a1.setPhoneNumber("937707486");
            a1.setUsername("admin");
            a1.setPassword("admin");
            AdminBLL.createAdmin(a1);
        }
        launch();
        System.out.println("Dados dos Clientes: ");

        for (Customer aux : repo.getCustomers().values()) {
            System.out.println("----------------------------------------");
            System.out.println("Cliente n: " + aux.getId());
            System.out.println("Nome: " + aux.getUsername());
            System.out.println("NIF: " + aux.getNIF());
            System.out.println("Username: " + aux.getUsername());
            System.out.println("Password: " + aux.getPassword());
            System.out.println("Morada: " + aux.getAddress());
            System.out.println("Num Telemovel: " + aux.getPhoneNumber());
            System.out.println("consultas: ");
            aux.ListAppointments();
        }

        System.out.println("--------- FIM DOS CLIENTES -------------");


        System.out.println("\n----------------------------------------");
        System.out.println("------| Dados dos Empresários |---------");

        for (CompanyOwner aux : repo.getCompanyOwners().values()) {
            System.out.println("----------------------------------------");
            System.out.println("Empresário nº: " + aux.getId());
            System.out.println("Nome: " + aux.getFullName());
            System.out.println("NIF: " + aux.getNIF());
            System.out.println("Username: " + aux.getUsername());
            System.out.println("Password: " + aux.getPassword());
            System.out.println("Morada: " + aux.getAddress());
            System.out.println("Numero telemovel: " + aux.getPhoneNumber());
            System.out.println("Empresas: ");
            aux.listCompanies();
        }


        System.out.println("--------- FIM DOS EMPRESÁRIOS ---------");

// Similar loops for Admins, Employees, Services, Appointments

        System.out.println("\n----------------------------------------");
        System.out.println("------| Dados dos ADMINS |---------");

        for (Admin aux : repo.getAdmins().values()) {
            System.out.println("----------------------------------------");
            System.out.println("Admin nº: " + aux.getId());
            System.out.println("Nome: " + aux.getFullName());
            System.out.println("NIF: " + aux.getNIF());
            System.out.println("Username: " + aux.getUsername());
            System.out.println("Password: " + aux.getPassword());
            System.out.println("Morada: " + aux.getAddress());
            System.out.println("Numero telemovel: " + aux.getPhoneNumber());
        }


        System.out.println("--------- FIM DOS ADMINS ---------");


        System.out.println("\n----------------------------------------");
        System.out.println("------| Dados dos FUNCIONARIOS |---------");

        for (Employee aux : repo.getEmployees().values()) {
            System.out.println("----------------------------------------");
            System.out.println("Funcionario nº: " + aux.getId());
            System.out.println("Nome: " + aux.getFullName());
            System.out.println("NIF: " + aux.getNIF());
            System.out.println("userName: " + aux.getUsername());
            System.out.println("password: " + aux.getPassword());
            System.out.println("Morada: " + aux.getAddress());
            System.out.println("Nº Telefone: " + aux.getPhoneNumber());
            System.out.println("tipo: " + aux.getType());
            System.out.println("Empresa: ");
            aux.printClinicData();
        }


        System.out.println("--------- FIM DOS FUNCIONARIOS ---------");

// Similar loop for Services

        System.out.println("\n----------------------------------------");
        System.out.println("------| Dados dos SERVIÇOS |---------");

        for (Service aux : repo.getServices().values()) {
            System.out.println("----------------------------------------");
            System.out.println("Serviço nº: " + aux.getServiceId());
            System.out.println("Nome: " + aux.getServiceName());
            System.out.println("Preço: " + aux.getServicePrice());
            System.out.println("Empresa: " + aux.getClinic().getName());
        }

        System.out.println("--------- FIM DOS SERVIÇOS ---------");

// Similar loop for Appointments

        System.out.println("\n----------------------------------------");
        System.out.println("------| Dados das CONSULTAS |---------");

        for (Appointment aux : repo.getAppointments().values()) {
            System.out.println("----------------------------------------");
            System.out.println("Consulta nº: " + aux.getIdConsulta());
            System.out.println("Clinica: " + aux.getClinicName());
            System.out.println("Data:" + aux.getAppointmentDate());
            System.out.println("Serviços: " + aux.getServices());
            System.out.println("Estado: " + aux.getState());
            System.out.println("Valor: " + aux.getTotalValue());
            System.out.println("Funcionario: " + aux.getEmployee());
            System.out.println("Cliente: " + aux.getCustomer().getFullName());
        }

        System.out.println("--------- FIM DAS CONSULTAS ---------");
    }
    }


