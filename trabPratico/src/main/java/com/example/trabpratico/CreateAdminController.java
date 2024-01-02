import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateAdminController {

    @FXML
    private TextField NIFField;

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
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/adminMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) ActionEvent.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Admin");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void registerAdmin(javafx.event.ActionEvent ActionEvent) {
        if(checkUsername(ActionEvent) && checkPassword(ActionEvent) && checkNIF(ActionEvent)) {
            Admin admin = new Admin();

            admin.setFullName(fullNameField.getText());
            admin.setAddress(addressField.getText());
            admin.setPhoneNumber(phoneNumberField.getText());
            admin.setNIF(NIFField.getText());
            admin.setUsername(usernameField.getText());
            admin.setPassword(passwordField.getText());

            AdminBLL.createAdmin(admin);

            try{
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/adminMenu.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) ActionEvent.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Menu Admin");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (checkUsername(ActionEvent) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Por favor, insira um username!");
            alert.show();
        }else if (checkPassword(ActionEvent) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Por favor, insira uma password!");
            alert.show();
        } else if (checkNIF(ActionEvent) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Por favor, insira um NIF!");
            alert.show();
        }
    }

    @FXML
    public boolean checkUsername(javafx.event.ActionEvent actionEvent) {
        Repository repo = Repository.getRepository();
        repo.deserialize("users.repo");
        if (usernameField.getText().isEmpty() || repo.getCustomers().values().equals(usernameField.getText()) || repo.getCompanyOwners().values().equals(usernameField.getText()) || repo.getAdmins().values().equals(usernameField.getText()) || repo.getEmployees().values().equals(usernameField.getText())) {
            usernameField.setStyle("-fx-border-color: red");
            return false;

        } else {
            usernameField.setStyle("-fx-border-color: green");
            return true;
        }
    }

    @FXML
    public boolean checkPassword(javafx.event.ActionEvent actionEvent) {
        if (passwordField.getText().isEmpty()) {
            passwordField.setStyle("-fx-border-color: red");
            return false;

        } else {
            passwordField.setStyle("-fx-border-color: green");
            return true;
        }
    }

    @FXML
    public boolean checkNIF(javafx.event.ActionEvent actionEvent) {
        if (NIFField.getText().isEmpty()) {
            NIFField.setStyle("-fx-border-color: red");
            return false;

        } else {
            NIFField.setStyle("-fx-border-color: green");
            return true;
        }
    }
}
