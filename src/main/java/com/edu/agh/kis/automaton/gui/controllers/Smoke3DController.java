package com.edu.agh.kis.automaton.gui.controllers;

import com.edu.agh.kis.automaton.gui.views.Smoke2DView;
import com.edu.agh.kis.automaton.gui.views.Smoke3DView;
import javafx.scene.shape.Box;

import java.awt.event.MouseEvent;

public class Smoke3DController extends SmokeController {

    Box[][][] viewTab;

    public Smoke3DController() {
        super();
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
