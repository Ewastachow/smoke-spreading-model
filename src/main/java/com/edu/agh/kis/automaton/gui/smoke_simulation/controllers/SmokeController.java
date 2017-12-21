package com.edu.agh.kis.automaton.gui.smoke_simulation.controllers;

import com.edu.agh.kis.automaton.gui.AutomatonGUISource;
import com.edu.agh.kis.automaton.gui.smoke_simulation.views.SmokeView;

import java.awt.event.MouseEvent;

public abstract class SmokeController{

    private SmokeView smokeView;

    //TODO Wymyśleć lepsze nazwy, jest to ilość komórek w danej płaszczyźnie, dodać getery i setery
    private int xAmong;
    private int yAmong;
    private int zAmong;

    public SmokeController(int xAmong, int yAmong, int zAmong) {
        this.xAmong = xAmong;
        this.yAmong = yAmong;
        this.zAmong = zAmong;
    }

    public int getxAmong() {
        return xAmong;
    }

    public int getyAmong() {
        return yAmong;
    }

    public int getzAmong() {
        return zAmong;
    }

    public SmokeView getSmokeView() {
        return smokeView;
    }

    public void setSmokeView(SmokeView smokeView) {
        this.smokeView = smokeView;
    }

    // przydal by się obiekt przechowujący boxy / rectangle żeby je tylko modyfikować a nie od nowa rysować
    // clickOnBoard czy clickOnRectangle????
    // czy tworzenie tablicy i przepisywanie tablicy do mapy nie zrobić tu i na rectangle juz ???
    // i wtedy create board byłoby tylko narusowaniem rectangle / boxes z tablicy


    public abstract void createBoard();
    public abstract void putTabIntoMap();
    public abstract void putMapIntoTab();
//    public abstract void addSubSceneToPane(Pane pane); // w smokeView mamy obiekt group

}
