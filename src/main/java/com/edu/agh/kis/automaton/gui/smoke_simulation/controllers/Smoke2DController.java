package com.edu.agh.kis.automaton.gui.smoke_simulation.controllers;

import com.edu.agh.kis.automaton.gui.smoke_simulation.views.Smoke2DView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Smoke2DController extends SmokeController {

    Rectangle[][] viewTab;

    public Smoke2DController(int x, int y, int z) {
        super(x, y, z);
        setSmokeView(new Smoke2DView());
    }

    @Override
    public void createBoard() {

    }

    @Override
    public void clickOnCell(MouseEvent event) {

    }

    @Override
    public void putTabIntoMap() {

    }

    @Override
    public void putMapIntoTab() {

    }
}
