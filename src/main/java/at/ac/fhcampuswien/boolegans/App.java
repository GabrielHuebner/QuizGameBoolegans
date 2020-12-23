package at.ac.fhcampuswien.boolegans;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout.fxml"));  // only looks into /resources folder for files!

        //URL url = new File("src/main/java/at/ac/fhcampuswien/boolegans/layout.fxml").toURI().toURL(); // another way to load fxml file - but not recommended
        //Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root, 300, 275);

        //Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
