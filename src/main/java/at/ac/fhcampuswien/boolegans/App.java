package at.ac.fhcampuswien.boolegans;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class App extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("layout.fxml")));  // only looks into /resources folder for files!

        //URL url = new File("src/main/java/at/ac/fhcampuswien/boolegans/layout.fxml").toURI().toURL(); // another way to load fxml file - but not recommended
        //Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        //Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
