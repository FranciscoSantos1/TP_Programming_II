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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class CreateEmployeeController {

    @FXML
    private ChoiceBox<String> ClinicChoiceBox;

    ObservableList<EmployeeType> UserTypeList = FXCollections.observableArrayList(EmployeeType.DENTIST, EmployeeType.NURSE, EmployeeType.SECRETARY, EmployeeType.ASSISTANT);

    @FXML
    private TextField NIFField;

    @FXML
    private ChoiceBox<EmployeeType> UserTypeChoiceBox;

    @FXML
    private TextField addressField;

    @FXML
    private Button backButton;

    @FXML
    private TextField fullNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameField;

    @FXML
    public void goBack(javafx.event.ActionEvent ActionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/companyOwnerMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) ActionEvent.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Dono de Empresa");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize(){
        UserTypeChoiceBox.setItems(UserTypeList);

        Repository repo = Repository.getRepository();
        CompanyOwner co = SessionData.getLoggedCompanyOwner();
        repo.deserialize("userdata.repo");
        String coNIF = SessionData.loggedCompanyOwner.getNIF();

        List<Clinic> clinics = repo.getClinicsPerCompanyOwner().get(coNIF);
        List<String> clinicNames = new ArrayList<>();

        if (clinics != null) {
            // Your existing code to populate clinicNames
            for (Clinic c : clinics) {
                clinicNames.add(c.getName());
            }
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(clinicNames);
        ClinicChoiceBox.setItems(observableList);

    }



    @FXML
    void registerEmployee(javafx.event.ActionEvent event) {
        if(checkAllFields(event)) {
            Employee employee = new Employee();

            employee.setFullName(fullNameField.getText());
            employee.setUsername(usernameField.getText());
            employee.setPassword(passwordField.getText());
            employee.setNIF(NIFField.getText());
            employee.setPhoneNumber(phoneNumberField.getText());
            employee.setEmployeeType(UserTypeChoiceBox.getValue());

            String selectedClinicName = ClinicChoiceBox.getValue();
            Clinic selectedClinic = findClinic(selectedClinicName);

            if (selectedClinic != null) {
                selectedClinic.getEmployees().add(employee);
                EmployeeBLL.createEmployee(employee, selectedClinic);

                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/companyOwnerMenu.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Menu Dono de Empresa");
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

    private Clinic findClinic(String clinicName) {
        String co = SessionData.loggedCompanyOwner.getNIF();
        Repository repo = Repository.getRepository();
        repo.deserialize("userdata.repo");

        List<Clinic> clinics = repo.getClinicsPerCompanyOwner().get(co);

        for(Clinic c : clinics) {
            if(c.getName().equals(clinicName)) {
                return c;
            }
        }
        return null;
    }
    @FXML
    public boolean checkAllFields(javafx.event.ActionEvent event) {
        if(checkName(event) && checkPassword(event) && checkNIF(event) && checkPhoneNumber(event) && checkClinic(event) && checkCheckBox(event) && checkFullName(event)) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    public boolean checkFullName(javafx.event.ActionEvent event) {
        if(fullNameField.getText().isEmpty()) {
            fullNameField.setStyle("-fx-border-color: red");
            return false;
        } else {
            fullNameField.setStyle("-fx-border-color: green");
            return true;
        }
    }

    @FXML
    public boolean checkCheckBox(javafx.event.ActionEvent event) {
        if(UserTypeChoiceBox.getValue() == null) {
            UserTypeChoiceBox.setStyle("-fx-border-color: red");
            return false;
        } else {
            UserTypeChoiceBox.setStyle("-fx-border-color: green");
            return true;
        }
    }
    @FXML
    public boolean checkClinic(javafx.event.ActionEvent event) {
        if(ClinicChoiceBox.getValue() == null) {
            ClinicChoiceBox.setStyle("-fx-border-color: red");
            return false;
        } else {
            ClinicChoiceBox.setStyle("-fx-border-color: green");
            return true;
        }
    }

    @FXML
    public boolean checkName(javafx.event.ActionEvent event) {
        if(usernameField.getText().isEmpty()) {
            usernameField.setStyle("-fx-border-color: red");
            return false;
        } else {
            usernameField.setStyle("-fx-border-color: green");
            return true;
        }
    }

    @FXML
    public boolean checkPassword(javafx.event.ActionEvent event) {
        if(passwordField.getText().isEmpty()) {
            passwordField.setStyle("-fx-border-color: red");
            return false;
        } else {
            passwordField.setStyle("-fx-border-color: green");
            return true;
        }
    }

    @FXML
    public boolean checkNIF(javafx.event.ActionEvent event) {
        if(NIFField.getText().isEmpty() || NIFField.getText().length() != 9) {
            NIFField.setStyle("-fx-border-color: red");
            return false;
        } else {
            NIFField.setStyle("-fx-border-color: green");
            return true;
        }
    }

    @FXML
    public boolean checkPhoneNumber(javafx.event.ActionEvent event) {
        if(phoneNumberField.getText().isEmpty() || phoneNumberField.getText().length() != 9) {
            phoneNumberField.setStyle("-fx-border-color: red");
            return false;
        } else {
            phoneNumberField.setStyle("-fx-border-color: green");
            return true;
        }
    }

}

