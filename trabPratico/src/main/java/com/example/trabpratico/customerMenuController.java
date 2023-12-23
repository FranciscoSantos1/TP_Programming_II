import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class customerMenuController {

    @FXML
    void logout(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação de término de sessão");
            alert.setHeaderText("Confirma sair?");
            alert.setContentText("Deseja terminar sessão?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
                    Scene regCena = new Scene (root);
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(regCena);
                    stage.setTitle("Iniciar Sessão");
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ClinicsList(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("clinicsList.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Lista de Clínicas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void customerPayments(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("customerPayments.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Lista de Consultas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
