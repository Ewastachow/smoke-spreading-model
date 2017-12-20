package com.edu.agh.kis.automaton.gui.views;

import javafx.scene.SubScene;
import javafx.scene.layout.Pane;

public abstract class SmokeView {

    private SubScene subScene;

    int weight;
    int height;

    public SubScene getSubScene() {
        return subScene;
    }

    public void setSubScene(SubScene subScene) {
        this.subScene = subScene;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public SmokeView() {
        weight = 700;
        height = 700;
    }
}
