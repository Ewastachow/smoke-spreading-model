package com.edu.agh.kis.automaton.gui.toolbox.controllers;

import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxView;

public abstract class ToolBoxController {
    private ToolBoxView toolBoxView;

    public ToolBoxView getToolBoxView() {
        return toolBoxView;
    }

    public void setToolBoxView(ToolBoxView toolBoxView) {
        this.toolBoxView = toolBoxView;
    }
}
