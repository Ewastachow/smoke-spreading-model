package com.edu.agh.kis.automaton.gui.toolbox.views;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


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

    @Override
    public GridPane createToolBoxPane() {
        GridPane pane = createGridPane();
        setupLabels(pane);
        setupTextFields(pane);
        setupButton(pane);
        //TODO Implement Panel ustawiający początkowy panel, wybieramy tam wymiary automatu, i klikamy przycisk akceptacji
        //TODO Po kliknieciu przyciku potmieniamy w kontrolerze konstruktor toolBoxView na te z control i
        //TODO ustawiamy controllery smoke dla wybranych rozmiarow automatu
        return pane;
    }

    private Label createLabel(String title){
        Label label = new Label(title);
        //TODO Jak ma wyglądać label
        return label;
    }

    //czy te create nie powinny byc w abstrakcyjnej żeby ujednolicić???

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
