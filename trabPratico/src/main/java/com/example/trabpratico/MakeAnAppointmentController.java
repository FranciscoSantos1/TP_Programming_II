import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;


public class MakeAnAppointmentController {
    @FXML
    private ChoiceBox<String> clinicChoiceBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField descriptionField;

    @FXML
    private ChoiceBox<String> employeeChoiceBox;

    @FXML
    private Button goBack;

    @FXML
    private Button makeAppointmentButton;

    @FXML
    private ChoiceBox<String> serviceChoiceBox;

    @FXML
    void goBack(javafx.event.ActionEvent ActionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/customerMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) ActionEvent.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Empresario");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void makeAppointment(javafx.event.ActionEvent ActionEvent) {
        Appointment appointment = new Appointment();
        for (List<Clinic> clinic : Repository.getRepository().getClinicsPerCompanyOwner().values()) {
            for (Clinic clinic1 : clinic) {
                if (clinic1.getName().equals(clinicChoiceBox.getValue())) {
                    appointment.setClinic(clinic1);
                }
            }
        }
        appointment.setAppointmentDate(datePicker.getValue());
        appointment.setDescription(descriptionField.getText());

    }

    @FXML
    public void initialize() {
        Repository repo = Repository.getRepository();
        repo.deserialize("users.repo");
        List<String> clinicsNames = new ArrayList<>();
        List<String> employeesNames = new ArrayList<>();

        for (List<Clinic> clinic : repo.getClinicsPerCompanyOwner().values()) {
            for (Clinic clinic1 : clinic) {
                clinicsNames.add(clinic1.getName());
            }
        }

        ObservableList<String> clinics = FXCollections.observableArrayList(clinicsNames);
        clinicChoiceBox.setItems(clinics);



        /*for(Employee : getSelectedClinic().getEmployees()){
            employeesNames.add(employee.getFullName());
        }

        ObservableList<String> employees = FXCollections.observableArrayList(employeesNames);
        employeeChoiceBox.setItems(employees);*/


    }

    @FXML
    public Clinic getSelectedClinic() {
        Clinic clinic = new Clinic();
        for (List<Clinic> clinicList : Repository.getRepository().getClinicsPerCompanyOwner().values()) {
            for (Clinic clinic1 : clinicList) {
                if (clinic1.getName().equals(clinicChoiceBox.getValue())) {
                    clinic = clinic1;
                }
            }
        }
        return clinic;
    }

    /*@FXML
    private void updateEmployeeChoiceBox() {
        System.out.println("updateEmployeeChoiceBox called"); // Check if this line gets printed

        Clinic selectedClinic = getSelectedClinic();
        System.out.println(selectedClinic.getName());

        if (selectedClinic != null) {
            List<String> employeesNames = new ArrayList<>();
            for (List<Clinic> clinic : Repository.getRepository().getClinicsPerCompanyOwner().values()) {
                for (Clinic clinic1 : clinic) {
                    if(clinic1.getName().equals(selectedClinic.getName())){
                        for(Employee employee : clinic1.getEmployees()){
                            employeesNames.add(employee.getFullName());
                            System.out.println(employee.getFullName());
                        }

                    }
                }
            }



*//*
            for (Employee employee : selectedClinic.getEmployees()) {
                employeesNames.add(employee.getFullName());
                System.out.println(employee.getEmployeeType()); // Check if this line gets printed
            }*//*

            ObservableList<String> employees = FXCollections.observableArrayList(employeesNames);
            employeeChoiceBox.setItems(employees);
        }
    }

}
*/
    @FXML
    private void updateEmployeeChoiceBox() {
        System.out.println("updateEmployeeChoiceBox called"); // Check if this line gets printed

        Clinic selectedClinic = getSelectedClinic();
        System.out.println("Selected Clinic: " + selectedClinic); // Print selected clinic information

        if (selectedClinic != null) {
            List<String> employeesNames = new ArrayList<>();

            for(List<Clinic> clinics : Repository.getRepository().getClinicsPerCompanyOwner().values()){
                for(Clinic clinic : clinics){
                    if(clinic.getName().equals(selectedClinic.getName())){
                        System.out.println("Clinic: " + clinic.getName()); // Print clinic information
                        for(List<Employee> employees : Repository.getRepository().getEmployeesClinicMap().values()){
                            for(Employee employee : employees){
                                if(employee.getClinic().getName().equals(clinic.getName())){
                                    employeesNames.add(employee.getFullName());
                                    System.out.println("EmployeeName: " + employee.getUsername());
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("Employees Names List: " + employeesNames); // Print employee names list

            ObservableList<String> employees = FXCollections.observableArrayList(employeesNames);
            employeeChoiceBox.setItems(employees);
        }
    }

    @FXML
    private void updateServiceChoiceBox() {
        System.out.println("updateServiceChoiceBox called"); // Check if this line gets printed

        Clinic selectedClinic = getSelectedClinic();
        System.out.println("Selected Clinic: " + selectedClinic); // Print selected clinic information

        if(selectedClinic != null){
            List<String> servicesNames = new ArrayList<>();

            for(List<Clinic> clinics : Repository.getRepository().getClinicsPerCompanyOwner().values()){
                for(Clinic clinic : clinics){
                    if(clinic.getName().equals(selectedClinic.getName())){
                        System.out.println("Clinic: " + clinic.getName()); // Print clinic information
                        for(List<Service> services : Repository.getRepository().getServicesClinicMap().values()){
                            for(Service service : services){
                                if(service.getClinic().getName().equals(selectedClinic.getName())){
                                    servicesNames.add(service.getServiceName());
                                    System.out.println("ServiceName: " + service.getServiceName());
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("Services Names List: " + servicesNames); // Print services names list

            ObservableList<String> services = FXCollections.observableArrayList(servicesNames);
            serviceChoiceBox.setItems(services);
        }
    }

    @FXML
    private void handleChoixeBoxAction(javafx.event.ActionEvent ActionEvent){
        updateEmployeeChoiceBox();
        updateServiceChoiceBox();
    }

}