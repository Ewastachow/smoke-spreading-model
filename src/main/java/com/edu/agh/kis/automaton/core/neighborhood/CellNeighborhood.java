package com.edu.agh.kis.automaton.core.neighborhood;

import com.edu.agh.kis.automaton.core.coords.CellCoordinates;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public interface CellNeighborhood {
    Map<CellRelativePosition,Set<CellCoordinates>> cellNeighbors(CellCoordinates cell);
}