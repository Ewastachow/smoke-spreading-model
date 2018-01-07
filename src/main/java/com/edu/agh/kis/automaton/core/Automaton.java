package com.edu.agh.kis.automaton.core;

import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.neighborhood.CellRelativePosition;
import com.edu.agh.kis.automaton.core.neighborhood.VonNeumanNeighborhood3Dim;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.stateFactory.GeneralStateFactory;


import java.util.*;

public abstract class Automaton implements Iterable<Cell>, Cloneable {
    public Map<Coords3D, CellState> cells = new TreeMap<>();
    private VonNeumanNeighborhood3Dim neighborhoodStrategy;
    private GeneralStateFactory stateFactory;

    public Automaton() {
    }

    public Automaton(Map<Coords3D, CellState> cells, VonNeumanNeighborhood3Dim neighborhoodStrategy, GeneralStateFactory stateFactory) {
        this.cells = cells;
        this.neighborhoodStrategy = neighborhoodStrategy;
        this.stateFactory = stateFactory;
    }

    public CellState getStateOfCoords(Coords3D cc) {
        return cells.get(cc);
    }

    public void setNewCellState(Coords3D cc, CellState cs) {
        cells.put(cc, cs);
    }

    public Map<Coords3D, CellState> getCells() {
        return cells;
    }

    public void setCells(Map<Coords3D, CellState> cells) {
        this.cells = cells;
    }

    public GeneralStateFactory getStateFactory() {
        return stateFactory;
    }

    public void setStateFactory(GeneralStateFactory stateFactory) {
        this.stateFactory = stateFactory;
    }

    public class CellIterator implements Iterator<Cell> {
        private Coords3D currentState;

        public CellIterator(Coords3D currentCoordinates) {
            currentState = initialCoordinates(currentCoordinates);
        }

        public void setCurrentState(Coords3D currentState) {
            this.currentState = currentState;
        }

        public boolean hasNext() {
            return hasNextCoordinates(currentState);
        }

        public Cell next() {
            if (hasNext()) {
                currentState = nextCoordinates(currentState);
                return new Cell(currentState, cells.get(currentState));
            } else throw new NoSuchElementException();
        }

        public void setState(CellState cellS) {
            cells.put(currentState, cellS);
        }
    }

    public Automaton nextstate() {
        Automaton letGetStartedAgain = newInstance(stateFactory, neighborhoodStrategy);
        Map<Coords3D, CellState> newCells = new TreeMap<>();
        for (Map.Entry<Coords3D, CellState> i : cells.entrySet()) {
            Cell cell = new Cell(i.getKey(), i.getValue());
            newCells.put(cell.coords, this.nextCellState(cell,
                    this.mapCoordinates(neighborhoodStrategy.cellNeighbors(i.getKey()))));
        }
        letGetStartedAgain.setCells(newCells);
        return letGetStartedAgain;
    }

    public void insertStructure(Map<? extends Coords3D, ? extends CellState> strcture) {
        cells.putAll(strcture);
    }

    @Override
    public Iterator<Cell> iterator() {
        return new CellIterator(null);
    }

    protected abstract Automaton newInstance(GeneralStateFactory cellSF, VonNeumanNeighborhood3Dim cellN);

    protected abstract boolean hasNextCoordinates(Coords3D cellC);

    protected abstract Coords3D initialCoordinates(Coords3D cellC);

    protected abstract Coords3D nextCoordinates(Coords3D cellC);

    protected abstract CellState nextCellState(Cell currentState, Map<CellRelativePosition, Set<Cell>> neighborsStates);

    private Map<CellRelativePosition, Set<Cell>> mapCoordinates(Map<CellRelativePosition, Set<Coords3D>> cellsmap) {
        Map<CellRelativePosition, Set<Cell>> newMap = new HashMap<>();


        for (Map.Entry<CellRelativePosition, Set<Coords3D>> entry : cellsmap.entrySet()) {
            Set<Cell> newSetCell = new HashSet<Cell>();

            CellRelativePosition cellRelativePosition = entry.getKey();
            Set<Coords3D> cellCoordinatesSet = entry.getValue();

            for (Coords3D i : cellCoordinatesSet) {
                newSetCell.add(new Cell(i, cells.get(i)));
            }

            newMap.put(cellRelativePosition, newSetCell);
        }

        return newMap;
    }

    @Override
    public String toString() {
        return "Automaton{" +
                "cells=" + cells +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Automaton automaton = (Automaton) o;

        if (cells != null ? !cells.equals(automaton.cells) : automaton.cells != null) return false;
        if (neighborhoodStrategy != null ? !neighborhoodStrategy.equals(automaton.neighborhoodStrategy) : automaton.neighborhoodStrategy != null)
            return false;
        return stateFactory != null ? stateFactory.equals(automaton.stateFactory) : automaton.stateFactory == null;
    }

    @Override
    public int hashCode() {
        int result = cells != null ? cells.hashCode() : 0;
        result = 31 * result + (neighborhoodStrategy != null ? neighborhoodStrategy.hashCode() : 0);
        result = 31 * result + (stateFactory != null ? stateFactory.hashCode() : 0);
        return result;
    }

    @Override
    public Object clone() {
        try {
            super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        Automaton duplicate = newInstance(stateFactory, neighborhoodStrategy);
        duplicate.cells = new TreeMap<>(cells);
        return duplicate;
    }
}

