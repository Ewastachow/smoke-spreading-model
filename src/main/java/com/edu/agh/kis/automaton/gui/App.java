package com.edu.agh.kis.automaton.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(this.getClass().getResource("automatonGUI.fxml"));

        Pane pane = loader.load();

        Scene scene = new Scene(pane);

        primaryStage.setTitle("AutomatonEwaStachow");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
