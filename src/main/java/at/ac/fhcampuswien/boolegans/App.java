package at.ac.fhcampuswien.boolegans;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Objects;
import java.util.Scanner;

public class App extends Application {

    Scene sceneStart, sceneGame;
    Player player = new Player();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        //All of this is the first scene before you really start the game
        TextField playerName = new TextField();
        player.setName(playerName.getText());
        Label label1 = new Label("Welcome to the Quiz Game");
        Button button1 = new Button("Start Quiz Game");
        button1.setOnAction(e -> primaryStage.setScene(sceneGame));

        VBox layoutStart = new VBox((20));
        layoutStart.getChildren().addAll(playerName,label1, button1);
        sceneStart = new Scene(layoutStart,200,200);

        //Here the next scene starts with the fxml file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("layout.fxml")));  // only looks into /resources folder for files!

        //URL url = new File("src/main/java/at/ac/fhcampuswien/boolegans/layout.fxml").toURI().toURL(); // another way to load fxml file - but not recommended
        //Parent root = FXMLLoader.load(url);
        sceneGame = new Scene(root);

        //Scene scene = new Scene(pane);
        primaryStage.setScene(sceneStart);
        primaryStage.setTitle("Quiz Game");
        primaryStage.show();
    }
}
