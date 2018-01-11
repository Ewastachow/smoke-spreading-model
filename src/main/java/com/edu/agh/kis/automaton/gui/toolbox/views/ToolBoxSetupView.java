package com.edu.agh.kis.automaton.gui.toolbox.views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ToolBoxSetupView extends ToolBoxView{

    private Label xAmongLabel;
    private TextField xAmongField;

    private Label yAmongLabel;
    private TextField yAmongField;

    private Label zAmongLabel;
    private TextField zAmongField;

    private Button acceptButton;

    public ToolBoxSetupView() {
        setToolBoxPane(createToolBoxPane());
    }

    public TextField getxAmongField() {
        return xAmongField;
    }

    public TextField getyAmongField() {
        return yAmongField;
    }

    public TextField getzAmongField() {
        return zAmongField;
    }

    public Button getAcceptButton() {
        return acceptButton;
    }

    public GridPane createToolBoxPane() {
        GridPane pane = createGridPane();
        setupLabels(pane);
        setupTextFields(pane);
        setupButton(pane);
        return pane;
    }

    private void setupLabels(GridPane pane){
        xAmongLabel = createLabel("Długość w metrach");
        yAmongLabel = createLabel("Wysokość w metrach");
        zAmongLabel = createLabel("Szerokość w metrach");
        pane.add(xAmongLabel,0,0);
        pane.add(yAmongLabel,0,1);
        pane.add(zAmongLabel,0,2);
    }

    private void setupTextFields(GridPane pane){
        xAmongField = createTextField();
        yAmongField = createTextField();
        zAmongField = createTextField();
        pane.add(xAmongField,1,0);
        pane.add(yAmongField,1,1);
        pane.add(zAmongField,1,2);
    }

    private void setupButton(GridPane pane){
        acceptButton = createButton("Begin");
        pane.add(acceptButton,1,3);
    }


}
