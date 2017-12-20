package com.edu.agh.kis.automaton.gui.views;

import javafx.scene.SubScene;
import javafx.scene.layout.FlowPane;

public class Smoke2DView extends SmokeView {
    FlowPane root2D;

    public Smoke2DView() {
        super();
        root2D = new FlowPane();

        setSubScene(new SubScene(root2D,weight,height));

    }
}
