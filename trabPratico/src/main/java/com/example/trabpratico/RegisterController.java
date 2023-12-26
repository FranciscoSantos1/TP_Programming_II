import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController {

    @FXML
    private PasswordField NIFField;

    @FXML
    private TextField accountType;

    @FXML
    private TextField addressField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField fullNameField;

    @FXML
    private Button goBack;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField phoneNumberField;

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

/*
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        accountType.getItems().addAll("Cliente", "Empresario");
    }*/

}
