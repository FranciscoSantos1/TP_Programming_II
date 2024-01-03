import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class CompanyOwnerMenuController {

    @FXML
    private Button createClinic;

    @FXML
    private Button createCompany;

    @FXML
    private Button createEmployee;

    @FXML
    private Button goBack;

    @FXML
    private Button listCompanies;

    @FXML
    private Button addService;

    @FXML
    private Button createService;

    @FXML
    private Button listClinics;

    @FXML
    private Button listEmployees;

    @FXML
    private Button listServices;

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
    public void createClinic(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/createClinic.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar Clínica");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createCompany(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/createCompany.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar Empresa");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void CreateEmployee(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/createEmployee.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar Funcionário");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addService(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/addService.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Adicionar Serviço");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void listCompanies(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/listCompaniesByCompanyOwner.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Lista de Empresas");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void createService(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/createService.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Criar Serviço");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void listClinics(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/listClinicsByCompanyOwner.fxml"));
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
    void listEmployees(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/listEmployeesByCompanyOwner.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Lista de Funcionários");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void listServices(javafx.event.ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/trabpratico/listServicesByCompanyOwner.fxml"));
            Scene regCena = new Scene (root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(regCena);
            stage.setTitle("Lista de Serviços");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



}
