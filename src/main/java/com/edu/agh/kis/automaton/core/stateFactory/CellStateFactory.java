package com.edu.agh.kis.automaton.core.stateFactory;

import com.edu.agh.kis.automaton.core.coords.CellCoordinates;
import com.edu.agh.kis.automaton.core.state.CellState;

public interface CellStateFactory {
    CellState initialState(CellCoordinates cellCoords);
}
