import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ManageAppointmentsByAdminController {

    @FXML
    private TableColumn<Appointment, String> AppointmentIdColumn;

    @FXML
    private TableColumn<Appointment, String> AppointmentStateColumn;

    @FXML
    private TableColumn<Appointment, String> ClinicColumn;

    @FXML
    private TableColumn<Appointment, String> DateColumn;


    @FXML
    private TableColumn<Appointment, String> TotalValueColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Appointment> appointmentsTable;

    @FXML
    private TableColumn<Appointment, String> EmployeeColumn;

    @FXML
    private TableColumn<Appointment, String> serviceColumn;


    @FXML
    private Button payButton;

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
    public void initialize() {
        AppointmentBLL.updateAppointmentStates();
        Repository repo = Repository.getRepository();
        repo.deserialize("users.repo");
        Employee employee = SessionData.getLoggedEmployee();

        AppointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("idConsulta"));
        AppointmentStateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        ClinicColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClinic().getName()));
        EmployeeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmployee().getFullName()));
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        TotalValueColumn.setCellValueFactory(new PropertyValueFactory<>("totalValue"));
        serviceColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getService().getServiceName()));

        List<Appointment> appointments = new ArrayList<>();


        for(List<Appointment> apps : Repository.getRepository().getAppointments().values()){
            for(Appointment aux : apps) {
                appointments.add(aux);
            }
        }

        ObservableList<Appointment> observableList = FXCollections.observableArrayList(appointments);
        appointmentsTable.setItems(observableList);
    }

    @FXML
    void cancelAppointment(javafx.event.ActionEvent ActionEvent) {
        Appointment selectedAppointment = appointmentsTable.getSelectionModel().getSelectedItem();
        Repository repository = Repository.getRepository();
        repository.deserialize("users.repo");

        if (selectedAppointment != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION, "Apos clicar sim, a consulta selecionada vai para o estado cancelada. Quer continuar?", ButtonType.YES, ButtonType.NO);
            confirmationAlert.showAndWait();
            Alert alert = new Alert(Alert.AlertType.WARNING, "A consulta selecionada ja esta cancelada", ButtonType.OK);

            if (confirmationAlert.getResult() == ButtonType.YES) {
                if(selectedAppointment.getState().equals(AppointmentState.CANCELADA)) {
                    alert.showAndWait();
                    return;
                }
                selectedAppointment.setState(AppointmentState.CANCELADA);
                appointmentsTable.getItems().remove(selectedAppointment);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("Consulta nao selecionada");
            alert.setContentText("Por favor, selecione uma consulta para pagar");
            alert.showAndWait();
        }
    }
}
