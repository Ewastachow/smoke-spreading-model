package com.edu.agh.kis.automaton.gui.toolbox.views;

import javafx.scene.layout.Pane;

public abstract class ToolBoxView {
    private Pane toolBoxPane;

    public Pane getToolBoxPane() {
        return toolBoxPane;
    }

    public void setToolBoxPane(Pane toolBoxPane) {
        this.toolBoxPane = toolBoxPane;
    }

    public abstract Pane createToolBoxPane();
}
