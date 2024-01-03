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

public class ListClinicsByCompanyOwnerController {
    @FXML
    private Button backButton;

    @FXML
    private TableView<Clinic> clinicsTable = new TableView<>();

    @FXML
    private TableColumn<Clinic, String> ClinicNameColumn;

    @FXML
    private TableColumn<Clinic, String> addressColumn;

    @FXML
    private TableColumn<Clinic, String> NIFColumn;

    @FXML
    private TableColumn<Clinic, String> phoneNumberColumn;

    @FXML
    private TableColumn<Clinic, String> CompanyOwnerNameColumn;


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

        ClinicNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        NIFColumn.setCellValueFactory(new PropertyValueFactory<>("NIF"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        CompanyOwnerNameColumn.setCellValueFactory(cellData -> {
            Clinic clinic = cellData.getValue();
            if (clinic.getCompany() != null) {
                return new SimpleStringProperty(clinic.getCompany().getCompanyOwner().getUsername());
            } else {
                return new SimpleStringProperty("");
            }
        });

        List<Clinic> clinicList = new ArrayList<>();

        Map<Company, List<Clinic>> companyClinicsMap = repo.getCompanieClinicsMap();

        for (List<Clinic> clinics : companyClinicsMap.values()) {
            clinicList.addAll(clinics);
        }

        ObservableList<Clinic> observableList = FXCollections.observableArrayList(clinicList);
        clinicsTable.setItems(observableList);
    }

}
