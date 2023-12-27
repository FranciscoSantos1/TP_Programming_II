import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javax.security.auth.login.LoginException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private Button newAccount;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void register(javafx.event.ActionEvent ActionEvent) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/register.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) ActionEvent.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Registar");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void loginButtonOnAction(javafx.event.ActionEvent actionEvent) throws IOException {

        try {
            String user = usernameField.getText();
            String password = passwordField.getText();
            boolean found = false;

            Repository repo = Repository.getRepository();

            for (Customer c : repo.getCustomers().values()) {
                if (user.equals(c.getUsername()) && password.equals(c.getPassword())) {
                    found = true;
                    Parent root;
                    root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/customerMenu.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Customer Menu");
                    stage.show();
                }
            }

            for (CompanyOwner co : repo.getCompanyOwners().values()) {
                if (usernameField.getText().equalsIgnoreCase(co.getUsername()) && passwordField.getText().equals(co.getPassword())) {
                    found = true;
                    System.out.println("Login com Sucesso!");
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/companyOwnerMenu.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Menu Dono Empresa");
                    stage.show();
                }
            }
            for (Admin a : repo.getAdmins().values()) {
                if (usernameField.getText().equalsIgnoreCase(a.getUsername()) && passwordField.getText().equals(a.getPassword())) {
                    found = true;
                    System.out.println("Login com Sucesso!");
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/adminMenu.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Menu Admin");
                    stage.show();
                }
            }
            for (Employee e : repo.getEmployees().values()) {
                if (usernameField.getText().equalsIgnoreCase(e.getUsername()) && passwordField.getText().equals(e.getPassword())) {
                    found = true;
                    System.out.println("Login com Sucesso!");
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/EmployeeMenu.fxml"));
                    Scene regCena = new Scene(root);
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Menu Funcionario");
                    stage.show();
                }
            }
            if (!found) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro");
                alert.setHeaderText("Utilizador/Password Errada!");
                alert.show();

            }
        } catch (IOException e) {
            System.out.println("Erro na funcao verifyLogin! \n" + e);
        }
    }

    @FXML
    public void exit(javafx.event.ActionEvent actionEvent) {
        System.exit(0);

    }
}
