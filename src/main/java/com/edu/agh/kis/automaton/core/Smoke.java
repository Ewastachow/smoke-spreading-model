package com.edu.agh.kis.automaton.core;

import com.edu.agh.kis.automaton.core.coords.CellCoordinates;
import com.edu.agh.kis.automaton.core.neighborhood.CellNeighborhood;
import com.edu.agh.kis.automaton.core.neighborhood.CellRelativePosition;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.IsSmoked;
import com.edu.agh.kis.automaton.core.state.SmokeState;
import com.edu.agh.kis.automaton.core.stateFactory.CellStateFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Smoke extends Automaton3Dim{

    public Smoke() {
        super();
    }




    public Smoke(Map<CellCoordinates, CellState> cells, CellNeighborhood neighborhoodStrategy, CellStateFactory stateFactory, int width, int height, int depth) {
        super(cells, neighborhoodStrategy, stateFactory, width, height, depth);
    }

    @Override
    protected Automaton newInstance(CellStateFactory cellSF, CellNeighborhood cellN) {
        return new Smoke(super.getCells(), cellN, cellSF, getWidth(), getHeight(), getDepth());
    }


    @Override
    protected CellState nextCellState(Cell currentState, Map<CellRelativePosition, Set<Cell>> neighborsStates) {
        //todo Implement
        SmokeState result = new SmokeState(IsSmoked.CLEAR, 20);

        if ((currentState.state).equals(IsSmoked.SOURCE_OF_FIRE)) {
            return new SmokeState(IsSmoked.SOURCE_OF_FIRE, 300);
        }else if ((currentState.state).equals((IsSmoked.SMOKED))){
            return new SmokeState(IsSmoked.SMOKED, 300);
        }else{

            if(strategy == Strategy.TO_UP) {
                boolean downSmoked = false;
                for (Cell i : neighborsStates.get(CellRelativePosition.DOWN))
                    if (((SmokeState) i.state).getIsSmoked() == IsSmoked.SMOKED)
                        downSmoked = true;
                if (downSmoked) //todo zmienic temperatura od poprzedniego
                    return new SmokeState(IsSmoked.SMOKED, 300);
                else return new SmokeState(IsSmoked.CLEAR, 20);
            }
            return new SmokeState(IsSmoked.CLEAR, 20);
        }
    }
}
