import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.security.auth.login.LoginException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Label newAccount;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    public void registerButtonOnAction(MouseEvent event){


    }
    @FXML
    public void loginButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {
        String user = usernameField.getText();
        String password = passwordField.getText();
        boolean found = false;

        Repository repo = Repository.getRepository();

        for(Customer c : repo.getCustomers().values()){
            if(user.equals(c.getUsername()) && password.equals(c.getPassword())){
                found = true;Parent root;
                root = FXMLLoader.load(getClass().getResource("customerMenu.fxml"));
                Scene regCena = new Scene(root);
                Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                stage.setScene(regCena);
                stage.setTitle("Customer Menu");
                stage.show();
            }
        }
    }
    @FXML
    public void exit(javafx.event.ActionEvent actionEvent) {
        System.exit(0);

    }
}
