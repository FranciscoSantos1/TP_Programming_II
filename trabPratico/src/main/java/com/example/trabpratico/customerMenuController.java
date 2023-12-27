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
