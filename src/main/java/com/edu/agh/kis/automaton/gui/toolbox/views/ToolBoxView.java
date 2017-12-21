package com.edu.agh.kis.automaton.gui.toolbox.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public abstract class ToolBoxView {
    private GridPane toolBoxPane;

    public GridPane getToolBoxPane() {
        return toolBoxPane;
    }

    public void setToolBoxPane(GridPane toolBoxPane) {
        this.toolBoxPane = toolBoxPane;
    }

    public abstract GridPane createToolBoxPane();


    public Button createButton(String title){
        Button button = new Button(title);
        //TODO Implement
        return button;
    }

    public TextField createTextField(){
        TextField textField = new TextField();
        //TODO Jak ma wyglądać text field
        return textField;
    }

    public GridPane createGridPane(){
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        return pane;
    }
}
