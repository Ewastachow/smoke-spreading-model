package com.edu.agh.kis.automaton.gui.toolbox.views;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class ToolBoxControlView extends ToolBoxView{

    Button nextButton;
    Button startButton;
    Button stopButton;
    Button resetButton;
    //TODO Toolbox control controller musi miec obiekt smoke - chyba te 3 z appcontroller i tam dodamy view przez referencje tu


    public ToolBoxControlView() {
        setToolBoxPane(createToolBoxPane());
    }

    @Override
    public GridPane createToolBoxPane() {
        GridPane pane = createGridPane();
        setupButtons(pane);
        //TODO Implement Kontrola działania automatu
        return pane;
    }

    private void setupButtons(GridPane pane){
        nextButton = createButton("Next");
        pane.add(nextButton,0,0);
        startButton = createButton("Start");
        pane.add(startButton,1,0);
        stopButton = createButton("Stop");
        pane.add(stopButton,2,0);
        resetButton = createButton("Reset");
        pane.add(resetButton,3,0);
    }
}
