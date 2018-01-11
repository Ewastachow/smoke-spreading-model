package com.edu.agh.kis.automaton.gui.toolbox.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public abstract class ToolBoxView {
    private GridPane toolBoxPane;

    public GridPane getToolBoxPane() {
        return toolBoxPane;
    }

    public void setToolBoxPane(GridPane toolBoxPane) {
        this.toolBoxPane = toolBoxPane;
    }

    public Button createButton(String title){
        Button button = new Button(title);
        return button;
    }

    public TextField createTextField(){
        TextField textField = new TextField();
        return textField;
    }

    public GridPane createGridPane(){
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setVgap(10);
        return pane;
    }

    public Slider createSlider(){
        Slider slider = new Slider();
        return slider;
    }

    public Label createLabel(String title){
        Label label = new Label(title);
        return label;
    }
}
