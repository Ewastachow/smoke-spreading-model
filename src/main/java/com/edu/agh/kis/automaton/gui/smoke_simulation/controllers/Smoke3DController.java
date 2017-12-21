package com.edu.agh.kis.automaton.gui.smoke_simulation.controllers;

import com.edu.agh.kis.automaton.gui.smoke_simulation.views.Smoke3DView;
import javafx.scene.shape.Box;

import java.awt.event.MouseEvent;

public class Smoke3DController extends SmokeController {

    Box[][][] viewTab;

    public Smoke3DController(int x, int y, int z) {
        super(x,y,z);
        setSmokeView(new Smoke3DView());
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
