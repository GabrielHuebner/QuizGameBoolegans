package at.ac.fhcampuswien.boolegans;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private Label question, answerA, answerB, answerC, answerD;
    @FXML
    private Button buttonA, buttonB, buttonC, buttonD;

    private boolean isATrue, isBTrue, isCTrue, isDTrue;

    public Controller(){
        isATrue = false;
        isBTrue = false;
        isCTrue = false;
        isDTrue = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        question.setText("Wo befindet sich der Kilimanjaro?");
        answerA.setText("A: Amerika");
        answerB.setText("B: Asien");
        answerC.setText("C: Afrika");
        answerD.setText("D: Europa");
        isCTrue = true;
    }

    @FXML
    public void buttonAAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Apressed");
        if(isATrue){
            buttonA.setStyle("-fx-background-color: green;");
        }
    }

    @FXML
    public void buttonBAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Bpressed");
        if(isBTrue){
            buttonB.setStyle("-fx-background-color: green;");
        }
    }

    @FXML
    public void buttonCAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Cpressed");
        if(isCTrue){
            buttonC.setStyle("-fx-background-color: green;");
        }
    }

    @FXML
    public void buttonDAction(javafx.event.ActionEvent actionEvent) {
        System.out.print("Dpressed");
        if(isDTrue){
            buttonD.setStyle("-fx-background-color: green;");
        }
    }
}
