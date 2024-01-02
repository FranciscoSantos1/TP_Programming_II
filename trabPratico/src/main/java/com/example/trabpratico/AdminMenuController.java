import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class AdminMenuController {


    @FXML
    private Button createAdmins;

    @FXML
    private Button logout;

    @FXML
    private Button manageAppointments;

    @FXML
    private Button manageCustomers;

    @FXML
    private Button mangeCompanyOwners;


    @FXML
    void goBack(javafx.event.ActionEvent ActionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        alert.setTitle("Closing warning");
        alert.setHeaderText("Tem a certeza que deseja sair?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get().equals(ButtonType.YES)){
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
        } else {
            return;
        }
    }

    @FXML
    void createAdmins(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/createAdmins.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar Administrador");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void manageCustomers(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/manageCustomersByAdmin.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Gerir Clientes");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mangeCompanyOwners(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/manageCompanyOwnersByAdmin.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Gerir Clientes");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void manageAppointments(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/manageAppointmentsByAdmin.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Gerir Marcações");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
