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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class listCompaniesByCompanyOwnerController {


    @FXML
    private Button backButton;

    @FXML
    private TableView<Company> companiesTable = new TableView<>();

    @FXML
    private TableColumn<Company, String> CompanyNameColumn;

    @FXML
    private TableColumn<Company, String> addressColumn;

    @FXML
    private TableColumn<Company, String> NIFColumn;

    @FXML
    private TableColumn<Company, String> phoneNumberColumn;

    @FXML
    private TableColumn<Company, String> CompanyOwnerNameColumn;


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
        CompanyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        NIFColumn.setCellValueFactory(new PropertyValueFactory<>("NIF"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        CompanyOwnerNameColumn.setCellValueFactory(new PropertyValueFactory<>("companyOwnerName"));

        List<Company> companyList = new ArrayList<>();

        CompanyOwner companyOwner = SessionData.getLoggedCompanyOwner();

        Repository repo = Repository.getRepository();
        repo.deserialize("users.repo");

        for (CompanyOwner co : repo.getCompanyFromCompanyOwner().keySet()) {
            if (companyOwner.getUsername().equals(co.getUsername())) {
                try {
                    Object value = repo.getCompanyFromCompanyOwner().get(co);

                    if (value instanceof List) {
                        List<Company> companies = (List<Company>) value;
                        System.out.println(companyOwner.getCompanies().size());

                        for (Company c : companies) {
                            companyList.add(c);
                        }

                    }

                    ObservableList<Company> observableList = FXCollections.observableArrayList(companyList);

                    companiesTable.setItems(observableList);
                } catch (ClassCastException e) {
                    // Handle the exception (e.g., log it or take appropriate action)
                    e.printStackTrace();
                }
            }
        }
    }

}
