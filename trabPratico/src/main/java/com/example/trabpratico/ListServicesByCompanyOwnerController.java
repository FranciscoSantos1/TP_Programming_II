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

public class ListServicesByCompanyOwnerController {

    @FXML
    private TableColumn<Service, String> ServiceNameColumn;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Service, String> clinicColumn;

    @FXML
    private TableColumn<Service, String> priceColumn;

    @FXML
    private TableView<Service> servicesTable;



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
        repo.deserialize("userdata.repo");
        SessionData sessionData = new SessionData();
        CompanyOwner companyOwner = sessionData.loggedCompanyOwner;

        Map<Clinic, List<Service>> servicesClinicMap = repo.getServicesClinicMap();
        List<Service> servicesList = new ArrayList<>();

        for (List<Service> services : servicesClinicMap.values()) {
            for (Service service : services) {
                if (service.getClinic().getCompany().getCompanyOwner().getNIF().equals(companyOwner.getNIF())) {
                    servicesList.add(service);
                }
            }
        }

        ObservableList<Service> observableList = FXCollections.observableArrayList(servicesList);

        ServiceNameColumn.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        clinicColumn.setCellValueFactory(cellData -> {
            Service service = cellData.getValue();
            if (service.getClinic() != null) {
                return new SimpleStringProperty(service.getClinic().getName());
            } else {
                return new SimpleStringProperty("");
            }
        });
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("servicePrice"));


        servicesTable.setItems(observableList);
    }
}
