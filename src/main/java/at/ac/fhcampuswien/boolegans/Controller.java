package at.ac.fhcampuswien.boolegans;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    // Alle Labels und Buttons die im FXML File enthalten sind werden hier angesprochen
    @FXML
    private Label question, answerA, answerB, answerC, answerD, playerScore;    // muss genauso heißen wie die IDs beim "layout.fxml"
    @FXML
    private Button buttonA, buttonB, buttonC, buttonD, nextButton;              // muss genauso heißen wie die IDs beim "layout.fxml"

    private boolean isATrue, isBTrue, isCTrue, isDTrue;

    private int qCount = 0, score = 0;

    Player player = new Player();       //Name und Score

    //Hier werden die Fragen und Antworten aus dem JSON File importiert
    Gson gson = new Gson();
    URL questionsURL = getClass().getClassLoader().getResource("questions.json");
    File questionsFile;
    {
        assert questionsURL != null;
        questionsFile = Paths.get(questionsURL.toURI()).toFile();
    }
    String absolutePath = questionsFile.getAbsolutePath();
    Path pathQuestions = Paths.get(absolutePath);
    Questions[] questions = gson.fromJson(Files.newBufferedReader(pathQuestions, StandardCharsets.UTF_8), Questions[].class);
    // von hier (Questions[]) werden die Fragen abgerufen

    public Controller() throws IOException, URISyntaxException { // boolean- Wert von Antworten wird auf false gesetzt
        isATrue = false;                                         // um unten die richtige Antwort auf true zu setzen
        isBTrue = false;
        isCTrue = false;
        isDTrue = false;
    }

    //Das erste Set von Frage und Antworten wird hier initialisiert
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerScore.setVisible(false);                      // Score wird (noch) nicht angezeigt; erst zum Schluss bei ButtonNextAction
        question.setText(questions[qCount].getQuestion());
        answerA.setText(questions[qCount].getA());
        answerB.setText(questions[qCount].getB());
        answerC.setText(questions[qCount].getC());
        answerD.setText(questions[qCount].getD());
        switch (questions[qCount].getAnswer()) {            // richtige Antwort Nummer [laufende Nummer] von questions abrufen
            case "A" -> isATrue = true;                     // und die richtige Antwort auf "true" setzen
            case "B" -> isBTrue = true;
            case "C" -> isCTrue = true;
            case "D" -> isDTrue = true;
            default -> System.out.println("ERROR; NO ANSWER SET");
        }
    }

    //Hier sind die jeweiligen Buttons zu finden, die Frabe eines Buttons ändert sich je nachdem ob die Antwort richtig war
    //button A
    @FXML
    public void buttonAAction() {
        if(isATrue){
            buttonA.setStyle("-fx-background-color: green;");       // wenn Button A angeklickt wurde und auch die richtige Antwort ist, dann Button gruen markieren
            score++;                                                // da richtig: kann score um 1 erhöht werden
            player.setScore(score);                                 // aktueller Punktestand (score) wird bei player gesetzt
        }
        else{
            buttonA.setStyle("-fx-background-color: red;");         // wenn A "false" --> button red (unten wird der button mit richtiger Antwort gruen gesetzt)
        }
        buttonA.setDisable(true);
        buttonB.setDisable(true);
        buttonC.setDisable(true);
        buttonD.setDisable(true);

        if(isBTrue){
            buttonB.setStyle("-fx-background-color: green;");       // sollte der falsche Button angeklickt worden sein
        }                                                           // dann den richtigen Button gruen markieren
        else if(isCTrue){
            buttonC.setStyle("-fx-background-color: green;");
        }
        else if(isDTrue){
            buttonD.setStyle("-fx-background-color: green;");
        }
    }

    //button B
    @FXML
    public void buttonBAction() {
        if(isBTrue){
            buttonB.setStyle("-fx-background-color: green;");
            score++;
            player.setScore(score);
        }
        else{
            buttonB.setStyle("-fx-background-color: red;");
        }
        buttonA.setDisable(true);
        buttonB.setDisable(true);
        buttonC.setDisable(true);
        buttonD.setDisable(true);
        if(isATrue){
            buttonA.setStyle("-fx-background-color: green;");
        }
        else if(isCTrue){
            buttonC.setStyle("-fx-background-color: green;");
        }
        else if(isDTrue){
            buttonD.setStyle("-fx-background-color: green;");
        }
    }

    //button C
    @FXML
    public void buttonCAction() {
        if(isCTrue){
            buttonC.setStyle("-fx-background-color: green;");
            score++;
            player.setScore(score);
        }
        else{
            buttonC.setStyle("-fx-background-color: red;");
        }
        buttonA.setDisable(true);
        buttonB.setDisable(true);
        buttonC.setDisable(true);
        buttonD.setDisable(true);
        if(isATrue){
            buttonA.setStyle("-fx-background-color: green;");
        }
        else if(isBTrue){
            buttonB.setStyle("-fx-background-color: green;");
        }
        else if(isDTrue){
            buttonD.setStyle("-fx-background-color: green;");
        }
    }

    //button D
    @FXML
    public void buttonDAction() {
        if(isDTrue){
            buttonD.setStyle("-fx-background-color: green;");
            score++;
            player.setScore(score);
        }
        else{
            buttonD.setStyle("-fx-background-color: red;");
        }
        buttonA.setDisable(true);       // Antwort-Button kann nicht mehr angeklickt werden
        buttonB.setDisable(true);
        buttonC.setDisable(true);
        buttonD.setDisable(true);
        if(isATrue){
            buttonA.setStyle("-fx-background-color: green;");
        }
        else if(isBTrue){
            buttonB.setStyle("-fx-background-color: green;");
        }
        else if(isCTrue){
            buttonC.setStyle("-fx-background-color: green;");
        }
    }


    public void buttonNextAction(){
        if(qCount < questions.length - 1){      // solange die laufende Question-Number kleiner ist als die gesamtanzahl der Fragen -1
            qCount++;                           // soll die laufende Question-Number (qcount) um 1 erhöht werden
            nextQuestion();                     // und die naechste Frage geladen werden
        }
        else if(qCount >= questions.length - 1){    // sollte die laufende Question-Number >= gesamtanzahl der Fragen-1 ist
            playerScore.setVisible(true);           // jzt kann Score angezeigt werden
            playerScore.setText("Game over! Player: " + player.getName() + " scored " + player.getScore() + "/" + questions.length + " points." );
        }// Ausgabe: Player-Name, Score/Gesamtanzahl der Fragen.
    }

    // Sobald der nextQuestion Button gedrückt wird werden alle Buttons auf die Standard Farben zurückgesetzt,
    // wieder clickbar und die neuen Fragen werden geladen.
    public void nextQuestion(){
        System.out.println(player.getScore() + "");

        buttonA.setStyle(null);     // Button-Farben werden wieder ausgegraut
        buttonB.setStyle(null);
        buttonC.setStyle(null);
        buttonD.setStyle(null);
        isATrue = false;            // Boolean isATrue wird wieder auf false gesetzt
        isBTrue = false;
        isCTrue = false;
        isDTrue = false;
        buttonA.setDisable(false);     // Antwort- Button kann wieder ausgewählt/ angeklickt werden
        buttonB.setDisable(false);
        buttonC.setDisable(false);
        buttonD.setDisable(false);

        question.setText(questions[qCount].getQuestion());  // dasselbe wie Zeile 54- 68
        answerA.setText(questions[qCount].getA());          // warum diese Wiederholung?
        answerB.setText(questions[qCount].getB());
        answerC.setText(questions[qCount].getC());
        answerD.setText(questions[qCount].getD());
        switch (questions[qCount].getAnswer()) {
            case "A" -> isATrue = true;
            case "B" -> isBTrue = true;
            case "C" -> isCTrue = true;
            case "D" -> isDTrue = true;
            default -> System.out.println("ERROR" + "; NO ANSWER SET");
        }
    }
}
