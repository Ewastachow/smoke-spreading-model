package com.edu.agh.kis.automaton.core;

import com.edu.agh.kis.automaton.core.coords.CellCoordinates;
import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.neighborhood.CellNeighborhood;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.Strategy;
import com.edu.agh.kis.automaton.core.stateFactory.CellStateFactory;

import java.util.Map;

public abstract class Automaton3Dim extends Automaton {

    private int width;
    private int height;
    private int depth;
    static Strategy strategy = Strategy.TO_UP;

    public Automaton3Dim() {
        super();
        width = 20;
        height = 20;
        depth = 20;
    }

    public Automaton3Dim(Map<CellCoordinates, CellState> cells, CellNeighborhood neighborhoodStrategy, CellStateFactory stateFactory) {
        super(cells, neighborhoodStrategy, stateFactory);
        width = 20;
        height = 20;
        depth = 20;
    }

    public Automaton3Dim(Map<CellCoordinates, CellState> cells, CellNeighborhood neighborhoodStrategy, CellStateFactory stateFactory, int width, int height, int depth) {
        super(cells, neighborhoodStrategy, stateFactory);
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    protected boolean hasNextCoordinates(CellCoordinates cellC) {
        int currentSize = ((Coords3D) cellC).getX() * ((Coords3D) cellC).getY() * ((Coords3D) cellC).getY();
        if (currentSize >= (width - 1) * (height - 1) * (depth - 1)) return false;
        else return true;
    }

    @Override
    protected CellCoordinates initialCoordinates(CellCoordinates cellC) {
        return new Coords3D(0, 0, -1);
    }

    @Override
    protected CellCoordinates nextCoordinates(CellCoordinates cellC) {
        if(((Coords3D) cellC).getZ() < (depth - 1)) {
            if (((Coords3D) cellC).getY() < (height - 1))
                return new Coords3D(((Coords3D) cellC).getX(), ((Coords3D) cellC).getY(), ((Coords3D) cellC).getZ() + 1);
            else if (((Coords3D) cellC).getY() == (height - 1))
                return new Coords3D(((Coords3D) cellC).getX(), 0, ((Coords3D) cellC).getZ() + 1);
        }
        else if(((Coords3D) cellC).getZ() == (depth - 1)) {
            if (((Coords3D) cellC).getY() < (height - 1))
                return new Coords3D(((Coords3D) cellC).getX(), ((Coords3D) cellC).getY() + 1, 0);
            else if (((Coords3D) cellC).getY() == (height - 1))
                return new Coords3D(((Coords3D) cellC).getX() + 1, 0, 0);
        }
        return null;
    }
}
