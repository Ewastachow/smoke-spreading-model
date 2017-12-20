package com.edu.agh.kis.automaton.gui.smoke_simulation.views;

import javafx.scene.SubScene;

public abstract class SmokeView {

    private SubScene subScene;

    private int weight;
    private int height;
    private int depth;

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
