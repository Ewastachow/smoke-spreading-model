package com.edu.agh.kis.automaton.core;


import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.neighborhood.CellRelativePosition;
import com.edu.agh.kis.automaton.core.neighborhood.VonNeumanNeighborhood3Dim;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.CellType;
import com.edu.agh.kis.automaton.core.stateFactory.GeneralStateFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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


//        Cell downCell = null;
//        for(Cell i: neighborsStates.get(CellRelativePosition.DOWN))
//            if(currentState.state.getTemp() < i.state.getTemp())
//                downCell = i;
//        Cell upCell = null;
//        for(Cell i: neighborsStates.get(CellRelativePosition.UP))
//            if(currentState.state.getTemp() < i.state.getTemp())
//                upCell = i;
//        boolean hasBarrier = false;
//        List<Cell> sideCellList = new ArrayList<>();
//        for(Cell i: neighborsStates.get(CellRelativePosition.SIDE))
//            if(currentState.state.getTemp() < i.state.getTemp())
//                sideCellList.add(i);
//        int hasSide = (sideCellList.isEmpty()) ? 0 : 1;

//        if(downCell!=null && upCell!=null && !sideCellList.isEmpty() && !hasBarrier){
//            double sideAver = 0;
//            for(Cell i: sideCellList)
//                sideAver += i.state.getTemp();
//            sideAver = sideAver/sideCellList.size();
//            double newTemp = currentState.state.getTemp() + 5*(downCell.state.getTemp()) + 2*(sideAver) + 0.1*(upCell.state.getTemp());
//            newTemp = newTemp/(1+5+2+0.1);
//            return new CellState(newTemp);
//        }else if()

        if(currentState.state.getCellType().equals(CellType.BARRIER) ||
                currentState.state.getCellType().equals(CellType.FIRE_SOURCE))
            return currentState.state;

        double upSum = 0;
        double downSum = 0;
        double sideSum = 0;
        for(Cell i: neighborsStates.get(CellRelativePosition.DOWN))
            downSum += i.state.getTemp();
        for(Cell i: neighborsStates.get(CellRelativePosition.UP))
            upSum += i.state.getTemp();
        for(Cell i: neighborsStates.get(CellRelativePosition.SIDE))
            sideSum += i.state.getTemp();
        double newTemp = 3*currentState.state.getTemp() +
                1*(neighborsStates.get(CellRelativePosition.UP).isEmpty() ? 0 : upSum/neighborsStates.get(CellRelativePosition.UP).size()) +
                8*(neighborsStates.get(CellRelativePosition.DOWN).isEmpty() ? 0 : downSum/neighborsStates.get(CellRelativePosition.DOWN).size()) +
                2*(neighborsStates.get(CellRelativePosition.SIDE).isEmpty() ? 0 : sideSum/neighborsStates.get(CellRelativePosition.SIDE).size());
        return new CellState(newTemp/(3+(neighborsStates.get(CellRelativePosition.UP).isEmpty() ? 0 : 1)+
                (neighborsStates.get(CellRelativePosition.DOWN).isEmpty() ? 0 : 8)+
                (neighborsStates.get(CellRelativePosition.SIDE).isEmpty() ? 0 : 2)));

//        if(!neighborsStates.get(CellRelativePosition.UP).isEmpty() &&
//                !neighborsStates.get(CellRelativePosition.DOWN).isEmpty() &&
//                neighborsStates.get(CellRelativePosition.SIDE).size()==4){
//            double sideAver = 0;
//            for(Cell i: sideCellList)
//                sideAver += i.state.getTemp();
//            sideAver = sideAver/sideCellList.size();
//            if(downCell!=null && upCell!=null)
//                return new CellState((currentState.state.getTemp() + 5*(downCell.state.getTemp()) + 2*(sideAver) + 0.1*(upCell.state.getTemp()))/(1+5+2*hasSide+0.1));
//            else if(downCell!=null)
//                return new CellState((currentState.state.getTemp() + 5*(downCell.state.getTemp()) + 2*(sideAver))/(1+5+2*hasSide));
//            else if (upCell!=null)
//                return new CellState((currentState.state.getTemp() + 2*(sideAver) + 0.1*(upCell.state.getTemp()))/(1+2*hasSide+0.1));
//            else
//                return new CellState((currentState.state.getTemp() + 2*(sideAver))/(1+2*hasSide));
//        }else if(!neighborsStates.get(CellRelativePosition.UP).isEmpty() &&
//                !neighborsStates.get(CellRelativePosition.DOWN).isEmpty() &&
//                neighborsStates.get(CellRelativePosition.SIDE).size()<4){
//            double sideAver = 0;
//            for(Cell i: sideCellList)
//                sideAver += i.state.getTemp();
//            sideAver = sideAver/sideCellList.size();
//            if(downCell!=null && upCell!=null)
//                return new CellState((currentState.state.getTemp() + 5*(downCell.state.getTemp()) + 3*(sideAver) + 2*(upCell.state.getTemp()))/(1+5+3*hasSide+2));
//            else if(downCell!=null)
//                return new CellState((currentState.state.getTemp() + 5*(downCell.state.getTemp()) + 3*(sideAver))/(1+5+3*hasSide));
//            else if (upCell!=null)
//                return new CellState((currentState.state.getTemp() + 3*(sideAver) + 2*(upCell.state.getTemp()))/(1+3*hasSide+2));
//            else
//                return new CellState((currentState.state.getTemp() + 3*(sideAver))/(1+3*hasSide));
//        }else if(neighborsStates.get(CellRelativePosition.UP).isEmpty()){
//            double sideAver = 0;
//            for(Cell i: sideCellList)
//                sideAver += i.state.getTemp();
//            sideAver = sideAver/sideCellList.size();
//            if(downCell!=null && upCell!=null)
//                return new CellState((currentState.state.getTemp() + 5*(downCell.state.getTemp()) + 3*(sideAver) + 2*(upCell.state.getTemp()))/(1+5+3*hasSide+2));
//            else if(downCell!=null)
//                return new CellState((currentState.state.getTemp() + 5*(downCell.state.getTemp()) + 3*(sideAver))/(1+5+3*hasSide));
//            else if (upCell!=null)
//                return new CellState((currentState.state.getTemp() + 3*(sideAver) + 2*(upCell.state.getTemp()))/(1+3*hasSide+2));
//            else
//                return new CellState((currentState.state.getTemp() + 3*(sideAver))/(1+3*hasSide));
//
//        }
//
//
//        return currentState.state;
    }
}
