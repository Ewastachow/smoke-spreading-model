package com.edu.agh.kis.automaton.gui.controllers;

import com.edu.agh.kis.automaton.gui.AutomatonGUISource;
import com.edu.agh.kis.automaton.gui.views.SmokeViewInterface;

import java.awt.event.MouseEvent;

public abstract class SmokeController{
    SmokeViewInterface smokeView;
    AutomatonGUISource smokeAutomatonSource;

    public SmokeController() {
        smokeAutomatonSource = new AutomatonGUISource();
    }
    // przydal by się obiekt przechowujący boxy / rectangle żeby je tylko modyfikować a nie od nowa rysować
    // clickOnBoard czy clickOnRectangle????
    // czy tworzenie tablicy i przepisywanie tablicy do mapy nie zrobić tu i na rectangle juz ???
    // i wtedy create board byłoby tylko narusowaniem rectangle / boxes z tablicy

    public abstract void createBoard();
    public abstract void clickOnCell(MouseEvent event);
    public abstract void putTabIntoMap();
    public abstract void putMapIntoTab();

}
