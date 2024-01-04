import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManageCustomersByAdminController {

    @FXML
    private TableColumn<Customer, String> CCNumberColumn;


    @FXML
    private TableColumn<Customer, String> NIFColumn;

    @FXML
    private TableView<Customer> customersTable;

    @FXML
    private Button backButton;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> fullnameColumn;

    @FXML
    private Button deleteCustomerButton;

    @FXML
    private TableColumn<Customer, String> phonenumberColumn;

    @FXML
    private TableColumn<Customer, String> usernameColumn;

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
        repository.deserialize("userdata.repo");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        fullnameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phonenumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        NIFColumn.setCellValueFactory(new PropertyValueFactory<>("NIF"));
        CCNumberColumn.setCellValueFactory(new PropertyValueFactory<>("CCNumber"));

        List<Customer> customers = new ArrayList<>();

        for(Customer aux : Repository.getRepository().getCustomers().values()) {
            customers.add(aux);
            System.out.println(aux.getFullName());

        }

        ObservableList<Customer> customersList = FXCollections.observableArrayList(customers);
        customersTable.setItems(customersList);

    }

    @FXML
    public void deleteCustomer(javafx.event.ActionEvent ActionEvent) {
        Customer customer = customersTable.getSelectionModel().getSelectedItem();

        if(customer != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Tem a certeza que pretende eliminar este cliente?");
            alert.showAndWait();

            if(alert.getResult().getText().equals("OK")) {
                Repository repository = Repository.getRepository();

                repository.getCustomers().remove(customer.getNIF());


                for (List<Appointment> appointments : repository.getAppointments().values()) {
                    Iterator<Appointment> iterator = appointments.iterator();
                    while (iterator.hasNext()) {
                        Appointment appointment = iterator.next();
                        if (appointment.getCustomer().getNIF().equals(customer.getNIF())) {
                            iterator.remove();
                        }
                    }
                }

                repository.serialize("userdata.repo");
                customersTable.getItems().remove(customer);
            }
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Por favor, selecione um cliente!");
            alert.show();
        }
    }

}
