package com.edu.agh.kis.automaton.core;


import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.neighborhood.CellRelativePosition;
import com.edu.agh.kis.automaton.core.neighborhood.VonNeumanNeighborhood3Dim;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.CellType;
import com.edu.agh.kis.automaton.core.stateFactory.GeneralStateFactory;

import java.util.*;

public class Smoke extends Automaton3Dim{
    //TODO Scalić Automaton, Automaton3Dim i Automaton w jedną klasę

    public Smoke() {
        super();
    }

    public Smoke(Map<Coords3D, CellState> cells, VonNeumanNeighborhood3Dim neighborhoodStrategy, GeneralStateFactory stateFactory, int width, int height, int depth) {
        super(cells, neighborhoodStrategy, stateFactory, width, height, depth);
    }

    @Override
    protected Automaton newInstance(GeneralStateFactory cellSF, VonNeumanNeighborhood3Dim cellN) {
        return new Smoke(super.getCells(), cellN, cellSF, getWidth(), getHeight(), getDepth());
    }

    //TODO: Implement w zależności od TEMP -> potem dodać w zależności od ułożenia BARRIER
    @Override
    protected CellState nextCellState(Cell currentState, Map<CellRelativePosition, Set<Cell>> neighborsStates) {

        // Jeżeli komorka jest babierą lub źródłem ognia to zwracamy ten stan

        if(currentState.state.getCellType().equals(CellType.BARRIER) ||
                currentState.state.getCellType().equals(CellType.FIRE_SOURCE))
            return currentState.state;

        // Jeżeli pod jest źródło ognia to zwraca na podstawie źródła ognia
        for(Cell i: neighborsStates.get(CellRelativePosition.DOWN))
            if(i.state.getCellType().equals(CellType.FIRE_SOURCE))
                return new CellState((6*i.state.getTemp() + currentState.state.getTemp())/7);

        // Usuwam zbędne elementy z setów
        neighborsStates = removeFireSourceAndBarrierFromMap(neighborsStates);

        // Jeżeli ma nad sobą sufit

        if(neighborsStates.get(CellRelativePosition.UP).size() + neighborsStates.get(CellRelativePosition.UP_SIDE).size() == 0){
            return nextCellStateHelper(currentState, neighborsStates, 0, 5, 1);
        }

        // Jeżeli ma ścianę obok
        if(neighborsStates.get(CellRelativePosition.SIDE).size() < 4 &&
                neighborsStates.get(CellRelativePosition.DOWN_SIDE).size() < 5 &&
                neighborsStates.get(CellRelativePosition.UP_SIDE).size() < 5){
            return nextCellStateHelper(currentState, neighborsStates, 3, 5, 0.1);
        }

        return nextCellStateHelper(currentState, neighborsStates, 0, 3, 0.03);
    }

    private CellState nextCellStateHelper(Cell currentState, Map<CellRelativePosition, Set<Cell>> neighborsStates, double upVal, double downVal, double sideVal){
        if(upVal + downVal + sideVal == 0) return currentState.state;
        int sideCounter = 0;
        double aver = 0;
        for(Cell i: neighborsStates.get(CellRelativePosition.SIDE))
            if(i.state.getTemp() > currentState.state.getTemp()){
                aver += i.state.getTemp();
                sideCounter++;
            }
        Cell downCell = null;
        for(Cell i: neighborsStates.get(CellRelativePosition.DOWN))
            if(i.state.getTemp() > currentState.state.getTemp())
                downCell = i;
        Cell upCell = null;
        for(Cell i: neighborsStates.get(CellRelativePosition.UP))
            if(i.state.getTemp() > currentState.state.getTemp())
                upCell = i;
//        if(downCell != null && upCell != null)
//            return new CellState(currentState.state.getTemp() +
//                    (upVal*upCell.state.getTemp() + sideVal*sideCounter*aver + downVal*downCell.state.getTemp())/
//                            (1 + upVal + sideVal + downVal));
//        else if(downCell != null && upCell == null)
//        return new CellState(currentState.state.getTemp() +
//                (sideVal*sideCounter*aver + downVal*downCell.state.getTemp())/
//                        (1 + upVal + sideVal + downVal));
//        else if(downCell == null && upCell != null)
//            return new CellState(currentState.state.getTemp() +
//                    (upVal*upCell.state.getTemp() + sideVal*sideCounter*aver)/
//                            (1 + upVal + sideVal + downVal));
//        else return new CellState(currentState.state.getTemp() +
//                    (sideVal*sideCounter*aver)/
//                            (1 + upVal + sideVal + downVal));

        if(downCell != null && upCell != null)
            return new CellState((currentState.state.getTemp() + upVal*upCell.state.getTemp() + sideVal*sideCounter*aver + downVal*downCell.state.getTemp())/
                            (1 + upVal + sideVal*sideCounter + downVal));
        else if(downCell != null && upCell == null)
        return new CellState((currentState.state.getTemp() + sideVal*sideCounter*aver + downVal*downCell.state.getTemp())/
                        (1 + sideVal*sideCounter + downVal));
        else if(downCell == null && upCell != null)
            return new CellState((currentState.state.getTemp() + upVal*upCell.state.getTemp() + sideVal*sideCounter*aver)/
                            (1 + upVal + sideVal*sideCounter));
        else return new CellState((currentState.state.getTemp() + sideVal*sideCounter*aver)/
                            (1 + sideVal*sideCounter));

//        double newTemp = sideVal*sideCounter*aver +
//                downVal*((downCell == null) ? 0 : downCell.state.getTemp()) +
//                upVal*((upCell == null) ? 0 : upCell.state.getTemp());
//        return new CellState(currentState.state.getTemp() + (newTemp/(sideVal*sideCounter + ((downCell == null) ? 0 : downVal) + ((upCell == null) ? 0 : upVal))));


//        double newTemp = currentState.state.getTemp() +
//                sideVal*sideCounter*aver +
//                downVal*((downCell == null) ? 0 : downCell.state.getTemp()) +
//                upVal*((upCell == null) ? 0 : upCell.state.getTemp());
//        return new CellState((newTemp/(1 + sideVal*sideCounter + ((downCell == null) ? 0 : downVal) + ((upCell == null) ? 0 : upVal))));
    }

    public Map<CellRelativePosition, Set<Cell>> removeFireSourceAndBarrierFromMap(Map<CellRelativePosition, Set<Cell>> neighborsStates){
        for(Map.Entry<CellRelativePosition, Set<Cell>> entry : neighborsStates.entrySet())
            removeFireSourceAndBarrierFromSet(entry.getValue());
        return neighborsStates;
    }

    public Set<Cell> removeFireSourceAndBarrierFromSet( Set<Cell> neighborsStates){
        Set<Cell> cellsToRemove = new HashSet<>();
        for(Cell i: neighborsStates)
            if (i.state.getCellType().equals(CellType.FIRE_SOURCE) || i.state.getCellType().equals(CellType.BARRIER))
                cellsToRemove.add(i);
        for(Cell i: cellsToRemove)
            neighborsStates.remove(i);
        return neighborsStates;
    }
}
