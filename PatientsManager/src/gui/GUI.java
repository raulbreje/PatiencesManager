package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Created by Raul Breje on 03/24/2016.
 */
public class GUI extends Application {

    @Override
    public void start(Stage primaryStage) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fgui.xml"));
            Parent root = loader.load();
            Label message = new Label("Hello World!");
            message.setFont(new Font(50));
            primaryStage.setScene(new Scene(message));
            primaryStage.setTitle("Hello");
            primaryStage.show();
        } catch (Exception e){
            System.err.print("dont know yet");
        }

    }

    public static void main(String... args) {
        launch();
    }
}
