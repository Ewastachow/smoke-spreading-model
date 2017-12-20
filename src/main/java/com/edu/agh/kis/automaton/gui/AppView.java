package com.edu.agh.kis.automaton.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class AppView {

    Scene scene;
    BorderPane borderPane;

    public AppView() {
        borderPane = new BorderPane();
        borderPane.setPrefSize(1200,700);
        borderPane.setPadding(new Insets(50,50,50,50));


        scene = new Scene(borderPane);
    }
}
