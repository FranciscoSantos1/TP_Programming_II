import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateCompanyController {
    @FXML
    private TextField NIFField;

    @FXML
    private TextField addressField;

    @FXML
    private Button backButton;

    @FXML
    private TextField companyNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField locationField;

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

    @FXML
    public void registerCompany(javafx.event.ActionEvent event) {
        if(checkName(event) && checkNIF(event) && checkPhoneNumber(event)) {
            Company company = new Company();

            company.setName(companyNameField.getText());
            company.setAddress(addressField.getText());
            company.setLocation(locationField.getText());
            company.setNIF(NIFField.getText());
            company.setPhoneNumber(phoneNumberField.getText());

            CompanyBLL.createCompany(company, SessionData.loggedCompanyOwner);

            System.out.println(Repository.getRepository().getCompany().get(SessionData.loggedCompanyOwner.getNIF()).getName());



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

    public boolean checkName(javafx.event.ActionEvent event) {
        if(companyNameField.getText().isEmpty()) {
            companyNameField.setStyle("-fx-border-color: red");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkNIF(javafx.event.ActionEvent event) {
        if(NIFField.getText().isEmpty() || NIFField.getText().length() != 9) {
            NIFField.setStyle("-fx-border-color: red");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkPhoneNumber(javafx.event.ActionEvent event) {
        if(phoneNumberField.getText().isEmpty() || phoneNumberField.getText().length() != 9) {
            phoneNumberField.setStyle("-fx-border-color: red");
            return false;
        } else {
            return true;
        }
    }
}
