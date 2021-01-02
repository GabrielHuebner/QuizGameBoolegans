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
    private Label question, answerA, answerB, answerC, answerD, timer;
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
    public void buttonAAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Apressed");
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
    public void buttonBAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Bpressed");
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
    public void buttonCAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Cpressed");
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
        System.out.print("Dpressed");
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
        nextQuestion();
    }

    // Sobald ein Button gedrückt wird, wird nach einem delay die nächste Frage geladen und die Buttons zurückgesetzt
    public void nextQuestion(){
        if(qCount < questions.length - 1){
            qCount++;
        }
        if(qCount >= questions.length - 1){
            timer.setText("GAME FINISHED!");
        }

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
            default -> System.out.println("ERROR; NO ANSWER SET");
        }
    }
}
