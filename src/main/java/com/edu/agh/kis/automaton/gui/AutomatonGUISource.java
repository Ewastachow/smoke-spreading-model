package com.edu.agh.kis.automaton.gui;

import com.edu.agh.kis.automaton.core.Automaton;
import com.edu.agh.kis.automaton.core.Smoke;
import com.edu.agh.kis.automaton.core.coords.CellCoordinates;
import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.neighborhood.VonNeumanNeighborhood3Dim;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.IsSmoked;
import com.edu.agh.kis.automaton.core.state.SmokeState;
import com.edu.agh.kis.automaton.core.stateFactory.GeneralStateFactory;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by EwaStachów on 10/12/2016.
 * GUI
 * @author EwaStachów
 * @version 1.0
 */
public class AutomatonGUISource {

    private Automaton currentAutomaton;

    Class<? extends Automaton> automatonClass;
    String neighborhoodStrategy;
    public String stateFactory;

    int width;
    int height;
    int depth;
    int radious;

    public SmokeState[][][] cells;

    AutomatonGUISource() {
        height = 20;
        width = 20;
        depth = 20;
        radious = 1;
        cells = new SmokeState[width][height][depth];
        automatonClass = Smoke.class;
        neighborhoodStrategy = "VonNeuman";
        currentAutomaton = new Smoke();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                for (int k = 0; k < depth; j++)
                currentAutomaton.setNewCellState(new Coords3D(i, j, k), IsSmoked.CLEAR);
        start();
    }

    void start() {
        Map<CellCoordinates, CellState> tmp = new TreeMap<CellCoordinates, CellState>();
            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++)
                    for (int k = 0; k < depth; j++)
                        tmp.put(new Coords3D(i, j,k), IsSmoked.CLEAR);
        currentAutomaton = new Smoke(tmp,
                    new VonNeumanNeighborhood3Dim(radious, height, width, depth),
                    new GeneralStateFactory(currentAutomaton.getCells()),
                    width, height, depth);
//        if ((automatonClass == GameOfLife.class) && (!quadLife)) {
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    tmp.put(new Coords2D(i, j), BinaryState.DEAD);
//            if (neighborhoodStrategy.equals("MoorNeighborhood"))
//                currentAutomaton = new GameOfLife(tmp,
//                        new MoorNeighborhood(wrapping, radious, width, height),
//                        new GeneralStateFactory(currentAutomaton.getCells()),
//                        width, height, rule1, rule2, rule3, quadLife);
//            else currentAutomaton = new GameOfLife(tmp,
//                    new VonNeumanNeighborhood(wrapping, radious, width, height),
//                    new GeneralStateFactory(currentAutomaton.getCells()),
//                    width, height, rule1, rule2, rule3, quadLife);
//        } else if (automatonClass == WireWorld.class) {
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    tmp.put(new Coords2D(i, j), WireElectronState.VOID);
//            if (neighborhoodStrategy.equals("MoorNeighborhood"))
//                currentAutomaton = new WireWorld(tmp,
//                        new MoorNeighborhood(wrapping, radious, width, height),
//                        new GeneralStateFactory(currentAutomaton.getCells()),
//                        width, height);
//            else currentAutomaton = new WireWorld(tmp,
//                    new VonNeumanNeighborhood(wrapping, radious, width, height),
//                    new GeneralStateFactory(currentAutomaton.getCells()),
//                    width, height);
//        } else if (automatonClass == LangtonAnt.class) {
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    tmp.put(new Coords2D(i, j), new LangtonCell(BinaryState.DEAD, AntState.NONE));
//            if (neighborhoodStrategy.equals("MoorNeighborhood"))
//                currentAutomaton = new LangtonAnt(tmp,
//                        new MoorNeighborhood(wrapping, radious, width, height),
//                        new GeneralStateFactory(currentAutomaton.getCells()),
//                        width, height);
//            else currentAutomaton = new LangtonAnt(tmp,
//                    new VonNeumanNeighborhood(wrapping, radious, width, height),
//                    new GeneralStateFactory(currentAutomaton.getCells()),
//                    width, height);
//        } else if ((automatonClass == GameOfLife.class) && (quadLife)) {
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    tmp.put(new Coords2D(i, j), QuadState.DEAD);
//            if (neighborhoodStrategy.equals("MoorNeighborhood"))
//                currentAutomaton = new GameOfLife(tmp,
//                        new MoorNeighborhood(wrapping, radious, width, height),
//                        new GeneralStateFactory(currentAutomaton.getCells()),
//                        width, height, rule1, rule2, rule3, quadLife);
//            else currentAutomaton = new GameOfLife(tmp,
//                    new VonNeumanNeighborhood(wrapping, radious, width, height),
//                    new GeneralStateFactory(currentAutomaton.getCells()),
//                    width, height, rule1, rule2, rule3, quadLife);
//        } else if (automatonClass == OneDim.class) {
//            for (int i = 0; i < width; i++)
//                tmp.put(new Coords1D(i), BinaryState.DEAD);
//            currentAutomaton = new OneDim(tmp,
//                    new OneDimNeighborhood(wrapping, radious, width),
//                    new GeneralStateFactory(currentAutomaton.getCells()),
//                    width, rule1);
//        }
        putIntoMap();
    }

    void nextState() {
        currentAutomaton = currentAutomaton.nextstate();
//        if (automatonClass == OneDim.class) cells = putLowerOneDim();
        putIntoTable();
    }

    void putIntoMap() {
            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++)
                    for (int k = 0; k < depth; k++)
                        currentAutomaton.setNewCellState(new Coords3D(i, j, k), cells[i][j][k]);
//        if ((automatonClass == GameOfLife.class) && (!quadLife)) {
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    if (cells[i][j] == 1) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), BinaryState.ALIVE);
//                    } else {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), BinaryState.DEAD);
//                    }
//        } else if (automatonClass == WireWorld.class) {
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    if (cells[i][j] == 0) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), WireElectronState.VOID);
//                    } else if (cells[i][j] == 1) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), WireElectronState.WIRE);
//                    } else if (cells[i][j] == 2) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), WireElectronState.ELECTRON_HEAD);
//                    } else {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), WireElectronState.ELECTRON_TAIL);
//                    }
//        } else if (automatonClass == LangtonAnt.class) {
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    if (cells[i][j] == 0) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), new LangtonCell(BinaryState.DEAD, AntState.NONE));
//                    } else if (cells[i][j] == 1) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), new LangtonCell(BinaryState.ALIVE, AntState.NONE));
//                    } else if (cells[i][j] == 2) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), new LangtonCell(BinaryState.DEAD, AntState.NORTH));
//                    } else if (cells[i][j] == 3) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), new LangtonCell(BinaryState.ALIVE, AntState.NORTH));
//                    } else if (cells[i][j] == 4) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), new LangtonCell(BinaryState.DEAD, AntState.SOUTH));
//                    } else if (cells[i][j] == 5) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), new LangtonCell(BinaryState.ALIVE, AntState.SOUTH));
//                    } else if (cells[i][j] == 6) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), new LangtonCell(BinaryState.DEAD, AntState.EAST));
//                    } else if (cells[i][j] == 7) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), new LangtonCell(BinaryState.ALIVE, AntState.EAST));
//                    } else if (cells[i][j] == 8) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), new LangtonCell(BinaryState.DEAD, AntState.WEST));
//                    } else {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), new LangtonCell(BinaryState.ALIVE, AntState.WEST));
//                    }
//        } else if ((automatonClass == GameOfLife.class) && (quadLife)) {
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    if (cells[i][j] == 0) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), QuadState.DEAD);
//                    } else if (cells[i][j] == 1) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), QuadState.BLUE);
//                    } else if (cells[i][j] == 2) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), QuadState.RED);
//                    } else if (cells[i][j] == 3) {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), QuadState.GREEN);
//                    } else {
//                        currentAutomaton.setNewCellState(new Coords2D(i, j), QuadState.YELLOW);
//                    }
//        } else if (automatonClass == OneDim.class) {
//            for (int i = 0; i < width; i++) {
//                if (cells[i][0] == 0) {
//                    currentAutomaton.setNewCellState(new Coords1D(i), BinaryState.DEAD);
//                } else {
//                    currentAutomaton.setNewCellState(new Coords1D(i), BinaryState.ALIVE);
//                }
//            }
//        }
    }

    void putIntoTable() {
            cells = new SmokeState[width][height][depth];
            for (int i = 0; i < width; i++)
                    for (int j = 0; j < height; j++)
                        for (int k = 0; k < height; k++)
                            cells[i][j][k] = (SmokeState)currentAutomaton.getStateOfCoords(new Coords3D(i, j, k));
        //        if ((automatonClass == GameOfLife.class) && (!quadLife)) {
//            cells = new int[width][height];
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)) == BinaryState.ALIVE) {
//                        cells[i][j] = 1;
//                    } else {
//                        cells[i][j] = 0;
//                    }
//        } else if (automatonClass == WireWorld.class) {
//            cells = new int[width][height];
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)) == WireElectronState.VOID) {
//                        cells[i][j] = 0;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)) == WireElectronState.WIRE) {
//                        cells[i][j] = 1;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)) == WireElectronState.ELECTRON_HEAD) {
//                        cells[i][j] = 2;
//                    } else {
//                        cells[i][j] = 3;
//                    }
//        } else if (automatonClass == LangtonAnt.class) {
//            cells = new int[width][height];
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)).equals(new LangtonCell(BinaryState.DEAD, AntState.NONE))) {
//                        cells[i][j] = 0;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)).equals(new LangtonCell(BinaryState.ALIVE, AntState.NONE))) {
//                        cells[i][j] = 1;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)).equals(new LangtonCell(BinaryState.DEAD, AntState.NORTH))) {
//                        cells[i][j] = 2;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)).equals(new LangtonCell(BinaryState.ALIVE, AntState.NORTH))) {
//                        cells[i][j] = 3;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)).equals(new LangtonCell(BinaryState.DEAD, AntState.SOUTH))) {
//                        cells[i][j] = 4;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)).equals(new LangtonCell(BinaryState.ALIVE, AntState.SOUTH))) {
//                        cells[i][j] = 5;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)).equals(new LangtonCell(BinaryState.DEAD, AntState.EAST))) {
//                        cells[i][j] = 6;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)).equals(new LangtonCell(BinaryState.ALIVE, AntState.EAST))) {
//                        cells[i][j] = 7;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)).equals(new LangtonCell(BinaryState.DEAD, AntState.WEST))) {
//                        cells[i][j] = 8;
//                    } else {
//                        cells[i][j] = 9;
//                    }
//        } else if ((automatonClass == GameOfLife.class) && (quadLife)) {
//            cells = new int[width][height];
//            for (int i = 0; i < width; i++)
//                for (int j = 0; j < height; j++)
//                    if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)) == QuadState.DEAD) {
//                        cells[i][j] = 0;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)) == QuadState.BLUE) {
//                        cells[i][j] = 1;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)) == QuadState.RED) {
//                        cells[i][j] = 2;
//                    } else if (currentAutomaton.getStateOfCoords(new Coords2D(i, j)) == QuadState.GREEN) {
//                        cells[i][j] = 3;
//                    } else {
//                        cells[i][j] = 4;
//                    }
//        } else if (automatonClass == OneDim.class) {
//            for (int i = 0; i < width; i++)
//                if (currentAutomaton.getStateOfCoords(new Coords1D(i)) == BinaryState.DEAD) {
//                    cells[i][0] = 0;
//                } else {
//                    cells[i][0] = 1;
//                }
//        }
    }
}
