package com.edu.agh.kis.automaton.gui.smoke_simulation.controllers;

import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.gui.smoke_simulation.views.SmokeView;

import java.util.Map;

public abstract class SmokeController{

    private SmokeView smokeView;
    private Map<Coords3D,CellState> cells;

    private int xAmong;
    private int yAmong;
    private int zAmong;

    public SmokeController(int xAmong, int yAmong, int zAmong, Map<Coords3D,CellState> cells) {
        this.cells = cells;
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

    public Map<Coords3D, CellState> getCells() {
        return cells;
    }

    public void setCells(Map<Coords3D, CellState> cells) {
        this.cells = cells;
    }

    public void setSmokeView(SmokeView smokeView) {
        this.smokeView = smokeView;
    }

    public abstract void drawBoard();
}
