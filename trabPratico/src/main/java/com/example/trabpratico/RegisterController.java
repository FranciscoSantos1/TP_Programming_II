import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController {

    @FXML
    ObservableList<String> UserTypeList = FXCollections.observableArrayList("Customer", "CompanyOwner");

    @FXML
    private TextField NIFField;

    @FXML
    private ChoiceBox<String> UserTypeChoiceBox;

    @FXML
    private TextField addressField;

    @FXML
    private Button backButton;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fullNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private Button registerButton2;

    @FXML
    private TextField usernameField;


    @FXML
    void goBack(javafx.event.ActionEvent ActionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/login.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) ActionEvent.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Login");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        UserTypeChoiceBox.setItems(UserTypeList);
    }

    @FXML
    void register(javafx.event.ActionEvent event) {
        if (checkNIF(event) && checkPhoneNumber(event) && checkUsername(event) && checkPassword(event) && checkUserType(event)) {
            if (UserTypeChoiceBox.getValue().equals("Customer")) {
                User c1 = new Customer();
                c1.setAddress(addressField.getText());
                c1.setEmail(emailField.getText());
                c1.setFullName(fullNameField.getText());
                c1.setNIF(NIFField.getText());
                c1.setPhoneNumber(phoneNumberField.getText());
                c1.setUsername(usernameField.getText());
                c1.setPassword(passwordField.getText());

                CustomerBLL.createCustomer((Customer) c1);

            } else if (UserTypeChoiceBox.getValue().equals("CompanyOwner")){
                User co1 = new CompanyOwner();
                co1.setAddress(addressField.getText());
                co1.setEmail(emailField.getText());
                co1.setFullName(fullNameField.getText());
                co1.setNIF(NIFField.getText());
                co1.setPhoneNumber(phoneNumberField.getText());
                co1.setUsername(usernameField.getText());
                co1.setPassword(passwordField.getText());

                CompanyOwnerBLL.createCompanyOwner((CompanyOwner) co1);

            }

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/login.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Login");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (checkUsername(event) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Por favor, insira um username!");
            alert.show();
        }else if (checkPassword(event) == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Por favor, insira uma password!");
                alert.show();
        }else if (checkUserType(event) == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Por favor, insira um tipo de utilizador!");
            alert.show();
        }else if (checkNIF(event) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("NIF errado. Por favor, insira novamente!");
            alert.show();
        }else if (checkPhoneNumber(event) == false){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Numero de telemovel errado. Por favor, insira novamente!");
            alert.show();

        }

    }

    @FXML
    public boolean checkUserType(javafx.event.ActionEvent actionEvent) {
        if (UserTypeChoiceBox.getValue().isEmpty()) {
            UserTypeChoiceBox.setStyle("-fx-border-color: red");
            return false;
        } else {
            UserTypeChoiceBox.setStyle("-fx-border-color: green");
            return true;
        }
    }

    @FXML
    public boolean checkUsername(javafx.event.ActionEvent actionEvent) {
        if (usernameField.getText().isEmpty()) {
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
        if (NIFField.getText().length() == 9) {
            NIFField.setStyle("-fx-border-color: green");
            return true;
        } else {
            NIFField.setStyle("-fx-border-color: red");
            return false;
        }
    }

    public boolean checkPhoneNumber(javafx.event.ActionEvent actionEvent) {
        if (phoneNumberField.getText().length() == 9) {
            phoneNumberField.setStyle("-fx-border-color: green");
            return true;
        } else {
            phoneNumberField.setStyle("-fx-border-color: red");
            return false;
        }
    }


}
