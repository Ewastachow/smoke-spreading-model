package com.edu.agh.kis.automaton.gui;

import com.edu.agh.kis.automaton.core.Automaton;
import com.edu.agh.kis.automaton.core.Smoke;
import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.neighborhood.VonNeumanNeighborhood3Dim;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.stateFactory.GeneralStateFactory;

import java.util.Map;
import java.util.TreeMap;

public class AutomatonGUISource {

    private Automaton currentAutomaton;

    private Class<? extends Automaton> automatonClass;
    private String neighborhoodStrategy;

    int width;
    int height;
    int depth;
    private int radious;

    private int sizeBoard = 20;

    CellState[][][] cells;

    public AutomatonGUISource() {
        height = sizeBoard;
        width = sizeBoard;
        depth = sizeBoard;
        radious = 1;
        cells = new CellState[width][height][depth];
        automatonClass = Smoke.class;
        neighborhoodStrategy = "VonNeuman";
        currentAutomaton = new Smoke();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                for (int k = 0; k < depth; k++)
                currentAutomaton.setNewCellState(new Coords3D(i, j, k), new CellState(20));
        start();
    }

    void start() {
        Map<Coords3D, CellState> tmp = new TreeMap<>();
            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++)
                    for (int k = 0; k < depth; k++)
                        tmp.put(new Coords3D(i, j,k), new CellState(20));
        currentAutomaton = new Smoke(tmp,
                    new VonNeumanNeighborhood3Dim(radious, height, width, depth),
                    new GeneralStateFactory(currentAutomaton.getCells()),
                    width, height, depth);
        putIntoMap();
    }

    void nextState() {
        currentAutomaton = currentAutomaton.nextstate();
        putIntoTable();
    }

    void putIntoMap() {
            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++)
                    for (int k = 0; k < depth; k++)
                        currentAutomaton.setNewCellState(new Coords3D(i, j, k), cells[i][j][k]);
    }

    void putIntoTable() {
            cells = new CellState[width][height][depth];
            for (int i = 0; i < width; i++)
                    for (int j = 0; j < height; j++)
                        for (int k = 0; k < height; k++)
                            cells[i][j][k] = (CellState)currentAutomaton.getStateOfCoords(new Coords3D(i, j, k));
    }
}
