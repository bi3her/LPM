package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;


public class Controller extends Application implements ChangeListener {

    Stage window;
    Label score = new Label();
    Label chosenChar = new Label();
     TextField charField = new TextField();
    StackPane gamee = new StackPane();
    Scene game = new Scene(gamee, 150, 200);
    int scoreInt = 0;
    char randomChar;
    double time;

    @Override
        public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setScene(game);
        window.setResizable(false);
        gamee.getChildren().add(charField);
        gamee.getChildren().add(chosenChar);
        gamee.getChildren().add(score);
        score.setTranslateY(-50);
        score.setText("Score: " + scoreInt);
        randomChar = randomChar();
        chosenChar.setText("Type: "+randomChar);
        charField.setTranslateY(50);
        charField.setMaxWidth(30);

        charField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(scoreInt == 1){
                time = System.currentTimeMillis();
            }
            if(!charField.getText().equals("")) {

                if(newValue.charAt(0) == randomChar) {
                    scoreInt += 1;
                    randomChar = randomChar();
                    score.setText("Score: " + scoreInt);
                    chosenChar.setText("Type: "+randomChar);
                }

                charField.setText("");

                if(scoreInt >= 30){
                    double scoree = (((scoreInt * 1000)/(System.currentTimeMillis() - time)) *60);
                    score.setText((int)scoree + " Letter per minute");
                }
            }
        });

        window.setTitle("Random Char");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {

    }

    public char randomChar(){
        String abc = "abcdefghklmnopqrstuvwxyz";
        return abc.charAt((int)(Math.random()*26));
    }
}
