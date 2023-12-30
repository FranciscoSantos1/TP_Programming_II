import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class MakeAnAppointmentController {
    @FXML
    private ChoiceBox<String> clinicChoiceBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField descriptionField;

    @FXML
    private ChoiceBox<String> employeeChoiceBox;

    @FXML
    private Button goBack;

    @FXML
    private Button makeAppointmentButton;

    @FXML
    private ChoiceBox<String> serviceChoiceBox;

    @FXML
    void goBack(javafx.event.ActionEvent ActionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/customerMenu.fxml"));
            Scene regCena = new Scene(root);
            Stage stage = (Stage) ((Node) ActionEvent.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Menu Empresario");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void makeAppointment(javafx.event.ActionEvent ActionEvent) {

    }

    @FXML
    public void initialize() {
        Repository repo = Repository.getRepository();
        repo.deserialize("users.repo");

        ArrayList<String> clinicNames = new ArrayList<>();

        /*for (Clinic clinic : repo.getCompanieClinicsMap().get()) {
            clinicNames.add(clinic.getName());
        }
*/
        clinicChoiceBox.getItems().addAll("Clinica 1", "Clinica 2", "Clinica 3");
        employeeChoiceBox.getItems().addAll("Funcionario 1", "Funcionario 2", "Funcionario 3");
        serviceChoiceBox.getItems().addAll("Servico 1", "Servico 2", "Servico 3");
    }

}
