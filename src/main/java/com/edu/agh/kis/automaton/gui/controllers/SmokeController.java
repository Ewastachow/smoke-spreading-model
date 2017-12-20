package com.edu.agh.kis.automaton.gui.controllers;

import com.edu.agh.kis.automaton.gui.AutomatonGUISource;
import com.edu.agh.kis.automaton.gui.views.SmokeView;
import javafx.scene.layout.Pane;

import java.awt.event.MouseEvent;

public abstract class SmokeController{

    private SmokeView smokeView;
    private AutomatonGUISource smokeAutomatonSource;

    public SmokeView getSmokeView() {
        return smokeView;
    }

    public void setSmokeView(SmokeView smokeView) {
        this.smokeView = smokeView;
    }

    public AutomatonGUISource getSmokeAutomatonSource() {
        return smokeAutomatonSource;
    }

    public void setSmokeAutomatonSource(AutomatonGUISource smokeAutomatonSource) {
        this.smokeAutomatonSource = smokeAutomatonSource;
    }

    public SmokeController() {
        smokeAutomatonSource = new AutomatonGUISource();
    }
    // przydal by się obiekt przechowujący boxy / rectangle żeby je tylko modyfikować a nie od nowa rysować
    // clickOnBoard czy clickOnRectangle????
    // czy tworzenie tablicy i przepisywanie tablicy do mapy nie zrobić tu i na rectangle juz ???
    // i wtedy create board byłoby tylko narusowaniem rectangle / boxes z tablicy

    public abstract void createBoard();
    public abstract void clickOnCell(MouseEvent event); //TODO czy to wg potrzebne? skoro nie ma w 3D
    public abstract void putTabIntoMap();
    public abstract void putMapIntoTab();
//    public abstract void addSubSceneToPane(Pane pane); // w smokeView mamy obiekt group

}
