package com.edu.agh.kis.automaton.core;

import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.neighborhood.VonNeumanNeighborhood3Dim;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.stateFactory.GeneralStateFactory;


import java.util.Map;

public abstract class Automaton3Dim extends Automaton {

    private int width;
    private int height;
    private int depth;

    public Automaton3Dim() {
        super();
        width = 20;
        height = 20;
        depth = 20;
    }

    public Automaton3Dim(int width, int height, int depth){
        super();
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public Automaton3Dim(Map<Coords3D, CellState> cells, VonNeumanNeighborhood3Dim neighborhoodStrategy, GeneralStateFactory stateFactory, int width, int height, int depth) {
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
    protected boolean hasNextCoordinates(Coords3D cellC) {
        int currentSize = cellC.getX() * cellC.getY() * cellC.getY();
        return currentSize < (width - 1) * (height - 1) * (depth - 1);
    }

    @Override
    protected Coords3D initialCoordinates(Coords3D cellC) {
        return new Coords3D(0, 0, -1);
    }

    @Override
    protected Coords3D nextCoordinates(Coords3D cellC) {
        if(cellC.getZ() < (depth - 1)) {
            if (cellC.getY() < (height - 1))
                return new Coords3D(cellC.getX(), cellC.getY(), cellC.getZ() + 1);
            else if (cellC.getY() == (height - 1))
                return new Coords3D(cellC.getX(), 0, cellC.getZ() + 1);
        }
        else if(cellC.getZ() == (depth - 1)) {
            if (cellC.getY() < (height - 1))
                return new Coords3D(cellC.getX(), cellC.getY() + 1, 0);
            else if (cellC.getY() == (height - 1))
                return new Coords3D(cellC.getX() + 1, 0, 0);
        }
        return null;
    }
}
