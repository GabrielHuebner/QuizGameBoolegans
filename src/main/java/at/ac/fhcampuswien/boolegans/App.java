package at.ac.fhcampuswien.boolegans;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Objects;

public class App extends Application {

    Scene sceneStart, sceneGame;
    Player player = new Player();

    public static void main(String[] args){
        launch(args);
    } // here is the main- function

    @Override
    public void start(Stage primaryStage) throws Exception{

        //All of this is the first scene before you really start the game
        //Here we add all Buttons, Labels that are in the first Scene in code, the second Scene is done with an fxml file
        Label labelPName = new Label("Enter your Name here:");      // Text: Aufforderung Namen einzugeben
        labelPName.setAlignment(Pos.CENTER);        // Position soll zentral sein
        TextField playerName = new TextField();     // Textfeld fuer Namenseingabe
        Button buttonSubmit = new Button("Submit"); // Button-Befehl für submit
        buttonSubmit.setOnAction(e -> player.setName(playerName.getText())); // Name des Players wird eingelesen und gespeichert
        Label labelWelcome= new Label("Welcome to the Quiz Game");      // Text
        Button buttonStart = new Button("Start Quiz Game");         // Button-Benennung fuer Quizstart
        buttonStart.setOnAction(e -> primaryStage.setScene(sceneGame)); // wenn Button gedrueckt wird soll Quiz starten

        //VBox for the first scene
        VBox layoutStart = new VBox((20));                              // spacing, Abstände
        layoutStart.getChildren().addAll(labelPName,playerName, buttonSubmit, labelWelcome, buttonStart);  // alles was auf dem Start-Layout angezeigt werden soll
        sceneStart = new Scene(layoutStart,200,200);    //Maße: 200x200

        //Here the next scene starts with the fxml file
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("layout.fxml")));  // only looks into /resources folder for files!
        sceneGame = new Scene(root);

        //Here the App starts with the first scene and the title Quiz Game
        primaryStage.setScene(sceneStart);
        primaryStage.setTitle("Quiz Game");
        primaryStage.show();
    }
}
