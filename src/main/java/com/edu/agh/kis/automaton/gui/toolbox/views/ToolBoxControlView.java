package com.edu.agh.kis.automaton.gui.toolbox.views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ToolBoxControlView extends ToolBoxView{

    Button nextButton;
    Button startButton;
    Button stopButton;
    Button resetButton;

    Button changeSubScene;

    Button howManyWithSmokeButton;

    Label sliderLabel;

    TextField iterateAmong;
    Button iterateButton;

    Slider slider;

    public Button getNextButton() {
        return nextButton;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getStopButton() {
        return stopButton;
    }

    public Button getResetButton() {
        return resetButton;
    }

    public Button getIterateButton() {
        return iterateButton;
    }

    public TextField getIterateAmong() {
        return iterateAmong;
    }

    public Button getChangeSubScene() {
        return changeSubScene;
    }

    public Slider getSlider() {
        return slider;
    }

    public Button getHowManyWithSmokeButton() {
        return howManyWithSmokeButton;
    }

    public ToolBoxControlView(int depth) {
        setToolBoxPane(createToolBoxPane(depth));
    }

    public GridPane createToolBoxPane(int depth) {
        GridPane pane = createGridPane();
        setupButtons(pane);
        setupSlider(pane,depth);
        setupIterate(pane);
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
        changeSubScene = createButton("Change Simulation View");
        pane.add(changeSubScene,2,4);
        howManyWithSmokeButton = createButton("How many with smoke?");
        pane.add(howManyWithSmokeButton,2,5);
    }

    private void setupIterate(GridPane pane){
        iterateAmong = new TextField("Among of minutes");
        pane.add(iterateAmong,1,1);
        iterateButton = createButton("Generate");
        pane.add(iterateButton,3,1);
    }

    private void setupSlider(GridPane pane, int depth){
        sliderLabel = createLabel("Crosses");
        pane.add(sliderLabel,2,2);
        slider = createSlider();
        slider.setMax(depth);
        slider.setMin(1);
        slider.setValue(1);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(50);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(1);
        pane.add(slider,2,3);

    }
}
