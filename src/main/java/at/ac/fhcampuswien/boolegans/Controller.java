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
    private Label question, answerA, answerB, answerC, answerD, playerScore;
    @FXML
    private Button buttonA, buttonB, buttonC, buttonD, nextButton;

    private boolean isATrue, isBTrue, isCTrue, isDTrue;

    private int qCount = 0, score = 0;

    Player player = new Player();

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

    public Controller() throws IOException, URISyntaxException {
        isATrue = false;
        isBTrue = false;
        isCTrue = false;
        isDTrue = false;
    }

    //Das erste Set von Frage und Antworten wird hier initialisiert
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        playerScore.setVisible(false);
        question.setText(questions[qCount].getQuestion());
        answerA.setText(questions[qCount].getA());
        answerB.setText(questions[qCount].getB());
        answerC.setText(questions[qCount].getC());
        answerD.setText(questions[qCount].getD());
        switch (questions[qCount].getAnswer()) {
            case "A" -> isATrue = true;
            case "B" -> isBTrue = true;
            case "C" -> isCTrue = true;
            case "D" -> isDTrue = true;
            default -> System.out.println("ERROR; NO ANSWER SET");
        }
    }

    //Hier sind die jeweiligen Buttons zu finden, die Frabe eines Buttons ändert sich je nachdem ob die Antwort richtig war
    @FXML
    public void buttonAAction() {
        if(isATrue){
            buttonA.setStyle("-fx-background-color: green;");
            score++;
            player.setScore(score);
        }
        else{
            buttonA.setStyle("-fx-background-color: red;");
        }
        buttonA.setDisable(true);
        buttonB.setDisable(true);
        buttonC.setDisable(true);
        buttonD.setDisable(true);

        if(isBTrue){
            buttonB.setStyle("-fx-background-color: green;");
        }
        else if(isCTrue){
            buttonC.setStyle("-fx-background-color: green;");
        }
        else if(isDTrue){
            buttonD.setStyle("-fx-background-color: green;");
        }
    }

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
        else if(isCTrue){
            buttonC.setStyle("-fx-background-color: green;");
        }
    }

    public void buttonNextAction(){
        if(qCount < questions.length - 1){
            qCount++;
            nextQuestion();
        }
        else if(qCount >= questions.length - 1){
            playerScore.setVisible(true);
            playerScore.setText("Game over! Player: " + player.getName() + " scored " + player.getScore() + "/" + questions.length + " points." );
        }
    }

    // Sobald der nextQuestion Button gedrückt wird werden alle Buttons auf die Standard Farben zurückgesetzt,
    // wieder clickbar und die neuen Fragen werden geladen.
    public void nextQuestion(){
        System.out.println(player.getScore() + "");

        buttonA.setStyle(null);
        buttonB.setStyle(null);
        buttonC.setStyle(null);
        buttonD.setStyle(null);
        isATrue = false;
        isBTrue = false;
        isCTrue = false;
        isDTrue = false;
        buttonA.setDisable(false);
        buttonB.setDisable(false);
        buttonC.setDisable(false);
        buttonD.setDisable(false);

        question.setText(questions[qCount].getQuestion());
        answerA.setText(questions[qCount].getA());
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
