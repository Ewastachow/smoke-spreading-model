package com.edu.agh.kis.automaton.gui.toolbox.controllers;

import com.edu.agh.kis.automaton.gui.AppView;
import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxView;

public abstract class ToolBoxController {
    private ToolBoxView toolBoxView;
    private AppView appView;

    public ToolBoxController(AppView appView) {
        this.appView = appView;
    }

    public AppView getAppView() {
        return appView;
    }

    public ToolBoxView getToolBoxView() {
        return toolBoxView;
    }

    public void setToolBoxView(ToolBoxView toolBoxView) {
        this.toolBoxView = toolBoxView;
    }
}
