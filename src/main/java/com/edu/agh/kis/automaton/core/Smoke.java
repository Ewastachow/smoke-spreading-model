package com.edu.agh.kis.automaton.core;


import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.neighborhood.CellRelativePosition;
import com.edu.agh.kis.automaton.core.neighborhood.VonNeumanNeighborhood3Dim;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.stateFactory.GeneralStateFactory;

import java.util.Map;
import java.util.Set;

public class Smoke extends Automaton3Dim{

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
//        if (((CellState)(currentState.state)).getIsSmoked().equals(IsSmoked.SOURCE_OF_FIRE)) {
//            return new CellState(IsSmoked.SOURCE_OF_FIRE, 300);
//        }else if (((CellState)(currentState.state)).getIsSmoked().equals((IsSmoked.SMOKED))){
//            return new CellState(IsSmoked.SMOKED, 300);
//        }else{
//            if(strategy == Strategy.TO_SIDE || strategy == Strategy.TO_DOWN){
//                if(strategy == Strategy.TO_DOWN){
//                    boolean upSmoked = false;
//                    for (Cell i : neighborsStates.get(CellRelativePosition.UP))
//                        if (((CellState) i.state).getIsSmoked() == IsSmoked.SMOKED || ((CellState) i.state).getIsSmoked() == IsSmoked.SOURCE_OF_FIRE  )
//                            upSmoked = true;
//                    if (upSmoked)
//                        return new CellState(IsSmoked.SMOKED, 300);
//                }
//                if(((Coords3D)currentState.coords).getY() == 0){
//                    boolean sideSmoked = false;
//                    for (Cell i : neighborsStates.get(CellRelativePosition.SIDE))
//                        if (((CellState) i.state).getIsSmoked() == IsSmoked.SMOKED || ((CellState) i.state).getIsSmoked() == IsSmoked.SOURCE_OF_FIRE  )
//                            sideSmoked = true;
//                    if (sideSmoked)
//                        return new CellState(IsSmoked.SMOKED, 300);
//                }
//            }
//            boolean downSmoked = false;
//            for (Cell i : neighborsStates.get(CellRelativePosition.DOWN))
//                if (((CellState) i.state).getIsSmoked() == IsSmoked.SMOKED || ((CellState) i.state).getIsSmoked() == IsSmoked.SOURCE_OF_FIRE  )
//                    downSmoked = true;
//            if (downSmoked) //zmienic temperatura od poprzedniego
//                return new CellState(IsSmoked.SMOKED, 300);
//
////            if(strategy == Strategy.TO_UP) {
////                boolean downSmoked = false;
////                for (Cell i : neighborsStates.get(CellRelativePosition.DOWN))
////                    if (((CellState) i.state).getIsSmoked() == IsSmoked.SMOKED || ((CellState) i.state).getIsSmoked() == IsSmoked.SOURCE_OF_FIRE  )
////                        downSmoked = true;
////                if (downSmoked) // zmienic temperatura od poprzedniego
////                    return new CellState(IsSmoked.SMOKED, 300);
////                else return new CellState(IsSmoked.CLEAR, 20);
////            }else if (strategy == Strategy.TO_SIDE){
////                if()
////            }else {
////
////            }
//            return new CellState(IsSmoked.CLEAR, 20);

        return currentState.state;
    }
}
