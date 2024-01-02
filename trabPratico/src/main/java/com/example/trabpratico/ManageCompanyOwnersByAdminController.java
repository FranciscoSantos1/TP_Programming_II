import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.Iterator;
import java.util.List;

public class ManageCompanyOwnersByAdminController {

    @FXML
    private TableColumn<CompanyOwner, String> CCNumberColumn;

    @FXML
    private TableColumn<CompanyOwner, String> NIFColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableView<CompanyOwner> customersTable;

    @FXML
    private Button deleteCompanyOwnerButton;

    @FXML
    private TableColumn<CompanyOwner, String> clinicsNumberColumn;

    @FXML
    private TableColumn<CompanyOwner, String> fullnameColumn;

    @FXML
    private TableColumn<CompanyOwner, String> phonenumberColumn;

    @FXML
    private TableColumn<CompanyOwner, String> usernameColumn;



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
        Repository repository = Repository.getRepository();
        repository.deserialize("users.repo");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        NIFColumn.setCellValueFactory(new PropertyValueFactory<>("NIF"));
        phonenumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        CCNumberColumn.setCellValueFactory(new PropertyValueFactory<>("CCNumber"));
        clinicsNumberColumn.setCellValueFactory(cellData -> {
            CompanyOwner companyOwner = cellData.getValue();
            List<Clinic> clinics = Repository.getRepository().getClinicsPerCompanyOwner().get(companyOwner.getNIF());

            // Check if clinics is not null before getting the size
            int clinicsNumber = (clinics != null) ? clinics.size() : 0;

            return new SimpleStringProperty(String.valueOf(clinicsNumber));
        });






        List<CompanyOwner> companyOwners = new ArrayList<>();


        // Populate companyOwners list
        for (CompanyOwner companyOwner : repository.getCompanyOwners().values()) {
            companyOwners.add(companyOwner);
        }

        ObservableList<CompanyOwner> companyOwnersList = FXCollections.observableArrayList(companyOwners);
        customersTable.setItems(companyOwnersList);
    }

    @FXML
    void deleteCompanyOwner(javafx.event.ActionEvent event) {
        CompanyOwner selectedCompanyOwner = customersTable.getSelectionModel().getSelectedItem();
        Repository repository = Repository.getRepository();
        repository.deserialize("users.repo");

        if (selectedCompanyOwner != null) {
            repository.getCompanyOwners().remove(selectedCompanyOwner.getNIF());
            repository.serialize("users.repo");

        }

    }
}