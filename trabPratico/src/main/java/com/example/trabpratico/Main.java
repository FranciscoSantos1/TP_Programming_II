
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/com/example/trabpratico/login.fxml"));
        Scene scene = new Scene(root, 750, 500);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Repository repo;
        Repository.deserialize("userdata.repo");
        repo = Repository.getRepository();

        if (repo.getAdmins().isEmpty()) {
            Admin a1 = new Admin();
            a1.setUsername("Administrador");
            a1.setAddress("Rua do Administrador");
            a1.setNIF("251320561");
            a1.setPhoneNumber("937707486");
            a1.setUsername("admin");
            a1.setPassword("admin");
            AdminBLL.createAdmin(a1);
        }
        launch();
    }
}


