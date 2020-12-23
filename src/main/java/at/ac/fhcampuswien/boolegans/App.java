package at.ac.fhcampuswien.boolegans;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new URL("file:///C:\\Users\\Gabriel\\IdeaProjects\\QuizGameBoolegans\\src\\main\\java\\at\\ac\\fhcampuswien\\boolegans\\layout.fxml"));
        Pane pane = loader.load();

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
