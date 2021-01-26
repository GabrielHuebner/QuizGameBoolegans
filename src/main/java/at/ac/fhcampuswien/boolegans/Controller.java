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
        setText();
    }

    public void setText(){
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

    @FXML
    public void buttonActions(boolean a, boolean b, boolean c, boolean d, Button aB, Button bB, Button cB, Button dB){
        if(a){
            aB.setStyle("-fx-background-color: green;");       // wenn Button A angeklickt wurde und auch die richtige Antwort ist, dann Button gruen markieren
            score++;                                                // da richtig: kann score um 1 erhöht werden
            player.setScore(score);                                 // aktueller Punktestand (score) wird bei player gesetzt
        }
        else{
           aB.setStyle("-fx-background-color: red;");         // wenn A "false" --> button red (unten wird der button mit richtiger Antwort gruen gesetzt)
        }
        buttonA.setDisable(true);                             // jzt kann ich voruebergehend keine Buttons mehr klicken
        buttonB.setDisable(true);
        buttonC.setDisable(true);
        buttonD.setDisable(true);

        if(b){
            bB.setStyle("-fx-background-color: green;");       // sollte der falsche Button angeklickt worden sein
        }                                                           // dann den richtigen Button gruen markieren
        else if(c){
            cB.setStyle("-fx-background-color: green;");
        }
        else if(d){
            dB.setStyle("-fx-background-color: green;");
        }
    }

    //Hier werden die Buttons angeklickt
    //button A
    @FXML
    public void buttonAAction() {
        buttonActions(isATrue,isBTrue,isCTrue,isDTrue,buttonA,buttonB,buttonC,buttonD);
    }

    //button B
    @FXML
    public void buttonBAction() {
        buttonActions(isBTrue,isATrue,isCTrue,isCTrue,buttonB,buttonA,buttonC,buttonD);
    }

    //button C
    @FXML
    public void buttonCAction() {
       buttonActions(isCTrue,isATrue,isBTrue,isDTrue,buttonC,buttonA,buttonB,buttonD);
    }

    //button D
    @FXML
    public void buttonDAction() {
       buttonActions(isDTrue,isATrue,isBTrue,isCTrue,buttonD,buttonA,buttonB,buttonC);
    }


    public void buttonNextAction(){
        if(qCount < questions.length - 1){      // solange die laufende Question-Number kleiner ist als die gesamtanzahl der Fragen -1
            qCount++;                           // soll die laufende Question-Number (qcount) um 1 erhöht werden
            nextQuestion();
        }
        else if(qCount >= questions.length - 1){    // sollte die laufende Question-Number >= gesamtanzahl der Fragen-1 ist
            playerScore.setVisible(true);           // jzt kann Score angezeigt werden
            playerScore.setText("Game over! Player: " + player.getName() + " scored " + player.getScore() + "/" + questions.length + " points." );
        }// Ausgabe: Player-Name, Score/Gesamtanzahl der Fragen.
    }

    // Sobald der nextQuestion Button gedrückt wird werden alle Button-Farben auf null zurückgesetzt,
    // wieder klickbar und die neuen Fragen werden geladen.
    public void nextQuestion(){
        buttonA.setStyle(null);     // Button-Farben werden wieder ausgegraut
        buttonB.setStyle(null);
        buttonC.setStyle(null);
        buttonD.setStyle(null);
        isATrue = false;            // Boolean isATrue wird wieder auf false gesetzt
        isBTrue = false;
        isCTrue = false;
        isDTrue = false;
        buttonA.setDisable(false);     // Antwort- Button kann wieder angeklickt werden
        buttonB.setDisable(false);
        buttonC.setDisable(false);
        buttonD.setDisable(false);

        setText();                  // naechste Frage
    }
}
