
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/com/example/trabpratico/login.fxml"));
        Scene scene = new Scene(root, 750, 450);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}