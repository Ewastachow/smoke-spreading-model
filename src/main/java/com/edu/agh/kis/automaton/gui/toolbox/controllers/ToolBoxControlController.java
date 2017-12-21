package com.edu.agh.kis.automaton.gui.toolbox.controllers;

import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.Smoke2DController;
import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.Smoke3DController;
import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.SmokeController;
import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxControlView;

public class ToolBoxControlController extends ToolBoxController{

    SmokeController smoke;
    Smoke2DController smoke2D;
    Smoke3DController smoke3D;

    public ToolBoxControlController() {
        setToolBoxView(new ToolBoxControlView());
    }
}
