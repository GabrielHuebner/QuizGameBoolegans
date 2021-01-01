package at.ac.fhcampuswien.boolegans;

import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private Label question, answerA, answerB, answerC, answerD, timer;
    @FXML
    private Button buttonA, buttonB, buttonC, buttonD;

    private boolean isATrue, isBTrue, isCTrue, isDTrue;
    private int qCount = 1;

    Gson gson = new Gson();
    Path pathQuestions = Paths.get("C:\\Users\\Gabriel\\IdeaProjects\\QuizGameBoolegans\\src\\main\\resources\\questions.json");
    Questions[] questions = gson.fromJson(Files.newBufferedReader(pathQuestions, StandardCharsets.UTF_8), Questions[].class);

    public Controller() throws IOException {
        isATrue = false;
        isBTrue = false;
        isCTrue = false;
        isDTrue = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        question.setText(questions[qCount].getQuestion());
        answerA.setText(questions[qCount].getA());
        answerB.setText(questions[qCount].getB());
        answerC.setText(questions[qCount].getC());
        answerD.setText(questions[qCount].getD());
        timer.setText(questions[qCount].getA());
        switch (questions[qCount].getAnswer()) {
            case "A" -> isATrue = true;
            case "B" -> isBTrue = true;
            case "C" -> isCTrue = true;
            case "D" -> isDTrue = true;
            default -> System.out.println("ERROR; NO ANSWER SET");
        }
    }

    @FXML
    public void buttonAAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Apressed");
        if(isATrue){
            buttonA.setStyle("-fx-background-color: green;");
        }
        else{
            buttonA.setStyle("-fx-background-color: red;");
        }

    }

    @FXML
    public void buttonBAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Bpressed");
        if(isBTrue){
            buttonB.setStyle("-fx-background-color: green;");
        }
        else{
            buttonB.setStyle("-fx-background-color: red;");
        }
    }

    @FXML
    public void buttonCAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Cpressed");
        if(isCTrue){
            buttonC.setStyle("-fx-background-color: green;");
        }
        else{
            buttonC.setStyle("-fx-background-color: red;");
        }
    }

    @FXML
    public void buttonDAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Dpressed");
        if(isDTrue){
            buttonD.setStyle("-fx-background-color: green;");
        }
        else{
            buttonD.setStyle("-fx-background-color: red;");
        }
    }

    public void setQuestionText(String text){
        question.setText(text);
    }

    public void setAText(String text){
        answerA.setText(text);
    }

    public void setBText(String text){
        answerB.setText(text);
    }

    public void setCText(String text){
        answerC.setText(text);
    }

    public void setDText(String text){
        answerD.setText(text);
    }

    public void setTimerText(String text){
        timer.setText(text);
    }
}
