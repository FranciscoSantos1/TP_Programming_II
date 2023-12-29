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
import java.util.List;

import static java.lang.Double.parseDouble;

public class CreateServiceController {

    @FXML
    private Button backButton;

    @FXML
    private ChoiceBox<String> clinicChoiceBox;

    @FXML
    private Button createServiceButton;

    @FXML
    private TextField priceField;

    @FXML
    private TextField serviceNameField;

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
        Repository repo = Repository.getRepository();
        repo.deserialize("users.repo");
        CompanyOwner co = SessionData.getLoggedCompanyOwner();

        List<Clinic> clinics = repo.getCompanieClinicsMap().get(co);
        System.out.println("Number of clinics: " + (clinics != null ? clinics.size() : 0));

        List<String> clinicNames = new ArrayList<>();

        if(clinics != null){
            for (Clinic c : clinics) {
                clinicNames.add(c.getName());
            }

            ObservableList<String> clinicNamesList = FXCollections.observableArrayList(clinicNames);
            clinicChoiceBox.setItems(clinicNamesList);
        }

    }

    @FXML
    public void registerService(ActionEvent event) {
        if(checkName(event) && checkPrice(event) && checkClinicSelection(event)) {
            Service service = new Service();

            service.setServiceName(serviceNameField.getText());
            service.setServicePrice(parseDouble(priceField.getText()));
            String clinicName = clinicChoiceBox.getValue();

            Clinic selectedClinic = findClinic(clinicName);
            service.setClinic(selectedClinic);


            double price = parseDouble(priceField.getText());

            if(selectedClinic != null) {
                ServiceBLL.createService(service, selectedClinic);
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
            Repository repo = Repository.getRepository();
            repo.serialize("users.repo");
            System.out.println(repo.getCompanieClinicsMap().get(selectedClinic.getCompany()));
        }

    }

    private Clinic findClinic(String clinicName) {
        String co = SessionData.loggedCompanyOwner.getNIF();
        Repository repo = Repository.getRepository();
        repo.deserialize("users.repo");

        List<Clinic> clinics = repo.getClinicsPerCompanyOner().get(co);

        for(Clinic c : clinics) {
            if(c.getName().equals(clinicName)) {
                return c;
            }
        }
        return null;
    }

    @FXML
    public boolean checkName(ActionEvent event) {
        if(serviceNameField.getText().isEmpty()) {
            serviceNameField.setStyle("-fx-border-color: red");
            return false;
        } else {
            serviceNameField.setStyle("-fx-border-color: green");
            return true;
        }
    }

    @FXML
    public boolean checkPrice(ActionEvent event) {
        Double price = parseDouble(priceField.getText());
        if(priceField.getText().isEmpty() || price < 0 ){
            priceField.setStyle("-fx-border-color: red");
            return false;
        } else {
            priceField.setStyle("-fx-border-color: green");
            return true;
        }
    }

    @FXML
    public boolean checkClinicSelection(ActionEvent event) {
        if(clinicChoiceBox.getValue().isEmpty()) {
            clinicChoiceBox.setStyle("-fx-border-color: red");
            return false;
        } else {
            clinicChoiceBox.setStyle("-fx-border-color: green");
            return true;
        }
    }
}
