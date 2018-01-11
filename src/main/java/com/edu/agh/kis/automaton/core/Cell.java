package com.edu.agh.kis.automaton.core;

import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.state.CellState;

public class Cell {
    public CellState state;
    public Coords3D coords;

    @Override
    public String toString() {
        return "Cell{" +
                "state=" + state +
                ", coords=" + coords +
                '}';
    }

    public Cell(Coords3D coords, CellState state) {
        this.state = state;
        this.coords = coords;
    }

    @Override
    public boolean equals(Object c) {
        return ((state == ((Cell) c).state) && (coords.equals(((Cell) c).coords)));
    }
}
