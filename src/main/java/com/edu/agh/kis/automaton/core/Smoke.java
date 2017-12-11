package com.edu.agh.kis.automaton.core;

import com.edu.agh.kis.automaton.core.coords.CellCoordinates;
import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.neighborhood.CellNeighborhood;
import com.edu.agh.kis.automaton.core.neighborhood.CellRelativePosition;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.IsSmoked;
import com.edu.agh.kis.automaton.core.state.SmokeState;
import com.edu.agh.kis.automaton.core.state.Strategy;
import com.edu.agh.kis.automaton.core.stateFactory.CellStateFactory;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Smoke extends Automaton3Dim{

    public static Strategy strategy = Strategy.TO_UP;

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
//
        if (((SmokeState)(currentState.state)).getIsSmoked().equals(IsSmoked.SOURCE_OF_FIRE)) {
            return new SmokeState(IsSmoked.SOURCE_OF_FIRE, 300);
        }else if (((SmokeState)(currentState.state)).getIsSmoked().equals((IsSmoked.SMOKED))){
            return new SmokeState(IsSmoked.SMOKED, 300);
        }else{
            if(strategy == Strategy.TO_SIDE || strategy == Strategy.TO_DOWN){
                if(strategy == Strategy.TO_DOWN){
                    boolean upSmoked = false;
                    for (Cell i : neighborsStates.get(CellRelativePosition.UP))
                        if (((SmokeState) i.state).getIsSmoked() == IsSmoked.SMOKED || ((SmokeState) i.state).getIsSmoked() == IsSmoked.SOURCE_OF_FIRE  )
                            upSmoked = true;
                    if (upSmoked)
                        return new SmokeState(IsSmoked.SMOKED, 300);
                }
                if(((Coords3D)currentState.coords).getY() == 0){
                    boolean sideSmoked = false;
                    for (Cell i : neighborsStates.get(CellRelativePosition.SIDE))
                        if (((SmokeState) i.state).getIsSmoked() == IsSmoked.SMOKED || ((SmokeState) i.state).getIsSmoked() == IsSmoked.SOURCE_OF_FIRE  )
                            sideSmoked = true;
                    if (sideSmoked)
                        return new SmokeState(IsSmoked.SMOKED, 300);
                }
            }
            boolean downSmoked = false;
            for (Cell i : neighborsStates.get(CellRelativePosition.DOWN))
                if (((SmokeState) i.state).getIsSmoked() == IsSmoked.SMOKED || ((SmokeState) i.state).getIsSmoked() == IsSmoked.SOURCE_OF_FIRE  )
                    downSmoked = true;
            if (downSmoked) //todo zmienic temperatura od poprzedniego
                return new SmokeState(IsSmoked.SMOKED, 300);

//            if(strategy == Strategy.TO_UP) {
//                boolean downSmoked = false;
//                for (Cell i : neighborsStates.get(CellRelativePosition.DOWN))
//                    if (((SmokeState) i.state).getIsSmoked() == IsSmoked.SMOKED || ((SmokeState) i.state).getIsSmoked() == IsSmoked.SOURCE_OF_FIRE  )
//                        downSmoked = true;
//                if (downSmoked) //todo zmienic temperatura od poprzedniego
//                    return new SmokeState(IsSmoked.SMOKED, 300);
//                else return new SmokeState(IsSmoked.CLEAR, 20);
//            }else if (strategy == Strategy.TO_SIDE){
//                if()
//            }else {
//
//            }

            return new SmokeState(IsSmoked.CLEAR, 20);
        }
    }

    @Override
    public void checkStateChange() {
        if(strategy == Strategy.TO_UP){
            for(Map.Entry<CellCoordinates,CellState> i : cells.entrySet())
                if(((Coords3D)i.getKey()).getY() == 0 && ((SmokeState)i.getValue()).getIsSmoked().equals(IsSmoked.SMOKED))
                    strategy = Strategy.TO_SIDE;
        }else if(strategy == Strategy.TO_SIDE){
            int hasSide = 0;
            for(Map.Entry<CellCoordinates,CellState> i : cells.entrySet())
                if(((Coords3D)i.getKey()).getY() == 0 &&
                        (((Coords3D)i.getKey()).getX() == getWidth()-1 ||
                        ((Coords3D)i.getKey()).getX() == 0 ||
                        ((Coords3D)i.getKey()).getZ() == getDepth()-1 ||
                        ((Coords3D)i.getKey()).getZ() == 0) &&
                        ((SmokeState)i.getValue()).getIsSmoked().equals(IsSmoked.SMOKED))
                    hasSide++;
            if(hasSide > getWidth()+getDepth()) strategy = Strategy.TO_DOWN;
        }
    }
}
