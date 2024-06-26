import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CreateClinicController {

    @FXML
    private TextField NIFField;

    @FXML
    private TextField addressField;

    @FXML
    private Button backButton;

    @FXML
    private TextField clinicNameField;

    @FXML
    private ChoiceBox<String> companyField;

    @FXML
    private TextField SpecialtyField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button registerButton;



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

/*
    @FXML
    public void initialize() {
        Repository repo = Repository.getRepository();
        repo.deserialize("userdata.repo");
        CompanyOwner co = new CompanyOwner();
        co = SessionData.loggedCompanyOwner;

        List<String> companiesNames = new ArrayList<>();

        for(Company c : repo.getCompany().values()){
            if(c.getCompanyOwner().getNIF().equals(co.getNIF())){
                companiesNames.add(c.getName());
            }
        }


        List<Company> companies = co.getCompanies();


        for (Company c : companies) {
            companiesNames.add(c.getName());
        }

        ObservableList<String> observableList = FXCollections.observableArrayList(companiesNames);
        companyField.setItems(observableList);

    }*/

    @FXML
    public void initialize() {
        CompanyOwner co = new CompanyOwner();
        co = SessionData.getLoggedCompanyOwner();
        Repository repo = Repository.getRepository();
        repo.deserialize("userdata.repo");
        List<String> companiesNames = new ArrayList<>();


        for (CompanyOwner co1 : repo.getCompanyFromCompanyOwner().keySet()) {
            if (co1.getUsername().equals(co.getUsername())) {
                try {
                    Object value = repo.getCompanyFromCompanyOwner().get(co1);
                    if (value instanceof List) {
                        List<Company> companies = (List<Company>) value;
                        System.out.println(repo.getCompanyFromCompanyOwner().get(co1).size());


                        for (Company c : companies) {
                            companiesNames.add(c.getName());
                        }


                    }

                } catch (ClassCastException e) {
                    e.printStackTrace();
                }
            }
        }
        ObservableList<String> observableList = FXCollections.observableArrayList(companiesNames);
        companyField.setItems(observableList);
    }

    @FXML
    public void registerClinic(javafx.event.ActionEvent event) {
        if (checkName(event) && checkNIF(event) && checkPhoneNumber(event)) {
            Clinic clinic = new Clinic();

            clinic.setName(clinicNameField.getText());
            clinic.setAddress(addressField.getText());
            clinic.setNIF(NIFField.getText());
            clinic.setAppointmentType(SpecialtyField.getText());
            clinic.setPhoneNumber(phoneNumberField.getText());

            String selectedCompanyName = companyField.getValue();

            // Find the selected company by name
            Company selectedCompany = findCompanyByName(selectedCompanyName);

            if (selectedCompany != null) {
                clinic.setCompany(selectedCompany);
                ClinicBLL. createClinic(clinic, clinic.getCompany());

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
            } else {
                System.out.println("Selected company not found.");
            }
        } else {
            return;
        }
    }


    // Helper method to find a company by name
    private Company findCompanyByName(String companyName) {
        CompanyOwner co = SessionData.loggedCompanyOwner;
        List<Company> companies = co.getCompanies();

        for(List<Company> c : Repository.getRepository().getCompanyFromCompanyOwner().values()) {
            for(Company c1 : c) {
                if(c1.getName().equals(companyName)) {
                    return c1;
                }
            }
        }

        /*for (Company c : companies) {
            if (c.getName().equalsIgnoreCase(companyName)) {
                return c;
            }
        }
*/
        return null; // Company not found
    }


    @FXML
    public boolean checkName(javafx.event.ActionEvent event) {
        if(clinicNameField.getText().isEmpty()) {
            clinicNameField.setStyle("-fx-border-color: red");
            return false;
        } else {
            clinicNameField.setStyle("-fx-border-color: green");
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
