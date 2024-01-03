import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static javax.accessibility.AccessibleRole.ALERT;


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
    private Label priceField;

    @FXML
    void goBack(javafx.event.ActionEvent ActionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/customerMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) ActionEvent.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Cliente");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void makeAppointment(javafx.event.ActionEvent ActionEvent) {

        try{
            Appointment appointment = new Appointment();
            appointment.setState(AppointmentState.PROCESSADA);
            appointment.setCustomer(SessionData.getLoggedCustomer());

            for (List<Clinic> clinic : Repository.getRepository().getClinicsPerCompanyOwner().values()) {
                for (Clinic clinic1 : clinic) {
                    if (clinic1.getName().equals(clinicChoiceBox.getValue())) {
                        appointment.setClinic(clinic1);
                    }
                }
            }
            appointment.setAppointmentDate(datePicker.getValue());
            appointment.setDescription(descriptionField.getText());
            appointment.setTotalValue(Double.parseDouble(priceField.getText()));

            for (List<Service> services : Repository.getRepository().getServicesClinicMap().values()) {
                for (Service service : services) {
                    if (service.getServiceName().equals(serviceChoiceBox.getValue())) {
                        appointment.setService(service);
                    }
                }
            }

            for (List<Employee> employees : Repository.getRepository().getEmployeesClinicMap().values()) {
                for (Employee employee : employees) {
                    if (employee.getFullName().equals(employeeChoiceBox.getValue())) {
                        appointment.setEmployee(employee);
                    }
                }
            }


            if(clinicChoiceBox.getValue() == null){
                checkClinicSelection(ActionEvent);
                return;
            }
            if(employeeChoiceBox.getValue() == null){
                checkEmployeeSelection(ActionEvent);
                return;
            }
            if(serviceChoiceBox.getValue() == null){
                checkServiceSelection(ActionEvent);
                return;
            }
            if(datePicker.getValue() == null || datePicker.getValue().isBefore(java.time.LocalDate.now())){
                checkDate(ActionEvent);
                datePicker.setValue(null);
                return;
            }




            if (clinicChoiceBox.getValue() != null && employeeChoiceBox.getValue() != null && serviceChoiceBox.getValue() != null) {
                AppointmentBLL.createAppointment(appointment, appointment.getCustomer());
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/customerMenu.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) ActionEvent.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Menu Cliente");
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

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

    @FXML
    public Service getSelectedService() {
        Service service = new Service();
        for (List<Service> serviceList : Repository.getRepository().getServicesClinicMap().values()) {
            for (Service service1 : serviceList) {
                if (service1.getServiceName().equals(serviceChoiceBox.getValue())) {
                    service = service1;
                }
            }
        }
        return service;
    }


    @FXML
    public void updatePriceField() {
        System.out.println("updatePriceField called");
        Service service = getSelectedService();
        if (service != null) {
            priceField.setText(String.valueOf(service.getServicePrice()));
            priceField.setVisible(true);
        } else {
            System.out.println("Selected Service is null.");
        }
    }


    @FXML
    private void updateEmployeeChoiceBox() {
        System.out.println("updateEmployeeChoiceBox called");

        Clinic selectedClinic = getSelectedClinic();
        System.out.println("Selected Clinic: " + selectedClinic);

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
    public void checkDate(javafx.event.ActionEvent ActionEvent){
        if(datePicker.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro");
            alert.setContentText("Selecione uma data");
            alert.showAndWait();
        }

        if(datePicker.getValue().isBefore(java.time.LocalDate.now())){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro");
            alert.setContentText("Selecione uma data valida");
            alert.showAndWait();
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

    @FXML
    private void checkClinicSelection(javafx.event.ActionEvent ActionEvent){
        if(clinicChoiceBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro");
            alert.setContentText("Selecione uma clinica");
            alert.showAndWait();
        }
    }

    @FXML
    private void checkEmployeeSelection(javafx.event.ActionEvent ActionEvent){
        if(employeeChoiceBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro");
            alert.setContentText("Selecione um funcionario");
            alert.showAndWait();
        }
    }

    @FXML
    private void checkServiceSelection(javafx.event.ActionEvent ActionEvent){
        if(serviceChoiceBox.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Erro");
            alert.setContentText("Selecione um servico");
            alert.showAndWait();
        }
    }

}