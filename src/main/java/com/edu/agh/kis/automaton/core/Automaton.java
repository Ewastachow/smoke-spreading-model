package com.edu.agh.kis.automaton.core;

import com.edu.agh.kis.automaton.core.coords.CellCoordinates;
import com.edu.agh.kis.automaton.core.neighborhood.CellNeighborhood;
import com.edu.agh.kis.automaton.core.neighborhood.CellRelativePosition;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.SmokeState;
import com.edu.agh.kis.automaton.core.stateFactory.CellStateFactory;

import java.util.*;

public abstract class Automaton implements Iterable<Cell>, Cloneable {
    private Map<CellCoordinates, CellState> cells = new TreeMap<>();
    private CellNeighborhood neighborhoodStrategy;
    private CellStateFactory stateFactory;

    public Automaton() {
    }

    public Automaton(Map<CellCoordinates, CellState> cells, CellNeighborhood neighborhoodStrategy, CellStateFactory stateFactory) {
        this.cells = cells;
        this.neighborhoodStrategy = neighborhoodStrategy;
        this.stateFactory = stateFactory;
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

    public CellState getStateOfCoords(CellCoordinates cc) {
        return cells.get(cc);
    }

    public void setNewCellState(CellCoordinates cc, CellState cs) {
        cells.put(cc, cs);
    }

    public Map<CellCoordinates, CellState> getCells() {
        return cells;
    }

    public void setCells(Map<CellCoordinates, CellState> cells) {
        this.cells = cells;
    }

    public CellStateFactory getStateFactory() {
        return stateFactory;
    }

    public void setStateFactory(CellStateFactory stateFactory) {
        this.stateFactory = stateFactory;
    }

    @Override
    public String toString() {
        return "Automaton{" +
                "cells=" + cells +
                ", neighborhoodStrategy=" + neighborhoodStrategy +
                ", stateFactory=" + stateFactory +
                '}';
    }

    public class CellIterator implements Iterator<Cell> {
        private CellCoordinates currentState;

        public CellIterator(CellCoordinates currentCoordinates) {
            currentState = initialCoordinates(currentCoordinates);
        }

        public void setCurrentState(CellCoordinates currentState) {
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
        Map<CellCoordinates, CellState> newCells = new TreeMap<>();
        for (Map.Entry<CellCoordinates, CellState> i : cells.entrySet()) {
            Cell cell = new Cell(i.getKey(), i.getValue());
            newCells.put(cell.coords, this.nextCellState(cell,
                    this.mapCoordinates(neighborhoodStrategy.cellNeighbors(i.getKey()))));
        }
        letGetStartedAgain.setCells(newCells);
        return letGetStartedAgain;
    }

    public void insertStructure(Map<? extends CellCoordinates, ? extends CellState> strcture) {
        cells.putAll(strcture);
    }

    @Override
    public Iterator<Cell> iterator() {
        return new CellIterator(null);
    }

    protected abstract Automaton newInstance(CellStateFactory cellSF, CellNeighborhood cellN);

    protected abstract boolean hasNextCoordinates(CellCoordinates cellC);

    protected abstract CellCoordinates initialCoordinates(CellCoordinates cellC);

    protected abstract CellCoordinates nextCoordinates(CellCoordinates cellC);

    protected abstract CellState nextCellState(Cell currentState, Set<Cell> neighborsStates);



    private Map<CellRelativePosition, Set<Cell>> mapCoordinates(Map<CellRelativePosition, Set<CellCoordinates>> cellsmap) {
        Map<CellRelativePosition, Set<Cell>> newMap = new HashMap<>();


        for (Map.Entry<CellRelativePosition, Set<CellCoordinates>> entry : cellsmap.entrySet()) {
            Set<Cell> newSetCell = new HashSet<Cell>();

            CellRelativePosition cellRelativePosition = entry.getKey();
            Set<CellCoordinates> cellCoordinatesSet = entry.getValue();

            for (CellCoordinates i : cellCoordinatesSet) {
                newSetCell.add(new Cell(i, cells.get(i)));
            }

            newMap.put(cellRelativePosition, newSetCell);
        }

        return newMap;
    }
}

