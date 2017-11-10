package com.edu.agh.kis.automaton.core;

import com.edu.agh.kis.automaton.core.coords.CellCoordinates;
import com.edu.agh.kis.automaton.core.neighborhood.CellNeighborhood;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.IsSmoked;
import com.edu.agh.kis.automaton.core.stateFactory.CellStateFactory;

import java.util.Map;
import java.util.Set;

public class Smoke extends Automaton3Dim{

    public Smoke(Map<CellCoordinates, CellState> cells, CellNeighborhood neighborhoodStrategy, CellStateFactory stateFactory, int width, int height, int depth) {
        super(cells, neighborhoodStrategy, stateFactory, width, height, depth);
    }

    @Override
    protected Automaton newInstance(CellStateFactory cellSF, CellNeighborhood cellN) {
        return new Smoke(super.getCells(), cellN, cellSF, getWidth(), getHeight(), getDepth());
    }

    @Override
    protected CellState nextCellState(Cell currentState, Set<Cell> neighborsStates) {
        //todo Implement
        // Temporary implementation
        if((currentState.state).equals(IsSmoked.SMOKED)) return IsSmoked.SMOKED;

        boolean isSmokeInNeighborhood = false;
        for(Cell i: neighborsStates)
            if(i.state.equals(IsSmoked.SMOKED))
                isSmokeInNeighborhood = true;
        if(isSmokeInNeighborhood)
            return IsSmoked.SMOKED;
        return IsSmoked.CLEAR;
    }
}
