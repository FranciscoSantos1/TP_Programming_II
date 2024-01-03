import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListEmployeesByCompanyOwnerController {

    @FXML
    private TableColumn<Employee, String> CompanyOwnerNameColumn;

    @FXML
    private TableColumn<Employee, String> NIFColumn;

    @FXML
    private TableColumn<Employee, String> UserNameColumn;

    @FXML
    private TableColumn<Employee, String> addressColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Employee> employeesTable;

    @FXML
    private TableColumn<Employee, String> phoneNumberColumn;



    @FXML
    void goBack(javafx.event.ActionEvent ActionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/companyOwnerMenu.fxml"));
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
    public void initialize() {
        Repository repo = Repository.getRepository();
        repo.deserialize("users.repo");
        SessionData sessionData = new SessionData();
        CompanyOwner companyOwner = sessionData.loggedCompanyOwner;

        Map<Clinic, List<Employee>> employeesMap = repo.getEmployeesClinicMap();
        List<Employee> employees = new ArrayList<>();

        // Filter employees based on the company owner's name
        for (List<Employee> employee : employeesMap.values()) {
            for (Employee employee1 : employee) {
                if (employee1.getClinic().getCompany().getCompanyOwner().getUsername().equals(companyOwner.getUsername())) {
                    employees.add(employee1);
                }
            }
        }

        // Populate the TableView
        ObservableList<Employee> observableList = FXCollections.observableArrayList(employees);

        CompanyOwnerNameColumn.setCellValueFactory(cellData -> {
            Employee employee = cellData.getValue();
            if (employee.getClinic() != null) {
                return new SimpleStringProperty(employee.getClinic().getCompany().getCompanyOwner().getUsername());
            } else {
                return new SimpleStringProperty("");
            }
        });

        UserNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        NIFColumn.setCellValueFactory(new PropertyValueFactory<>("NIF"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


        employeesTable.setItems(observableList);
    }
}
