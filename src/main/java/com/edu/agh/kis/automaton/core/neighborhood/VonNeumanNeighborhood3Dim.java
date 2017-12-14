package com.edu.agh.kis.automaton.core.neighborhood;

import com.edu.agh.kis.automaton.core.coords.Coords3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VonNeumanNeighborhood3Dim{
    //TODO wywalić radious, albo narazie chociaż z konstruktora

    private int radious;
    private int height;
    private int width;
    private int depth;

    public VonNeumanNeighborhood3Dim(int radious, int height, int width, int depth) {
        this.radious = radious;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public Map<CellRelativePosition,Set<Coords3D>> cellNeighbors(Coords3D cell) {

        Map<CellRelativePosition, Set<Coords3D>> result = new HashMap<>();
        Set<Coords3D> cellsNeighborsUp = new HashSet<>();
        Set<Coords3D> cellsNeighborsSide = new HashSet<>();
        Set<Coords3D> cellsNeighborsDown = new HashSet<>();
        Set<Coords3D> cellsNeighborsUpSide = new HashSet<>();
        Set<Coords3D> cellsNeighborsDownSide = new HashSet<>();

        for (int i = -radious; i <= radious; i++)
            for (int j = -radious; j <= radious; j++)
                for (int k = -radious; k <= radious; k++)
                    if((i != 0) || (j != 0) || (k != 0))
                        if(((i != 0) && (j == 0) && (k == 0)) ||
                                ((i == 0) && (j != 0) && (k == 0)) ||
                                ((i == 0) && (j == 0) && (k != 0))){
                            if((cell.getX() + i >= 0) &&
                                    (cell.getX() + i < width) &&
                                    (cell.getY() + j >= 0) &&
                                    (cell.getY() + j < height) &&
                                    (cell.getZ() + k >= 0) &&
                                    (cell.getZ() + k < depth)){
                                if(j == 0 && (i != 0 || k != 0))
                                    cellsNeighborsSide.add(new Coords3D((cell.getX() + i),
                                            (cell.getY() + j),
                                            (cell.getZ() + k)));
                                else if(j < 0 && i == 0 && k == 0)
                                    cellsNeighborsUp.add(new Coords3D((cell.getX() + i),
                                            (cell.getY() + j),
                                            (cell.getZ() + k)));
                                else  cellsNeighborsDown.add(new Coords3D((cell.getX() + i),
                                            (cell.getY() + j),
                                            (cell.getZ() + k)));
                            }
                        }else if(j < 0){
                            if((cell.getX() + i >= 0) &&
                                    (cell.getX() + i < width) &&
                                    (cell.getY() + j >= 0) &&
                                    (cell.getY() + j < height) &&
                                    (cell.getZ() + k >= 0) &&
                                    (cell.getZ() + k < depth)){
                                cellsNeighborsUpSide.add(new Coords3D((cell.getX() + i),
                                        (cell.getY() + j),
                                        (cell.getZ() + k)));
                            }
                        }else if(j > 0){
                            if((cell.getX() + i >= 0) &&
                                    (cell.getX() + i < width) &&
                                    (cell.getY() + j >= 0) &&
                                    (cell.getY() + j < height) &&
                                    (cell.getZ() + k >= 0) &&
                                    (cell.getZ() + k < depth)){
                                cellsNeighborsDownSide.add(new Coords3D((cell.getX() + i),
                                        (cell.getY() + j),
                                        (cell.getZ() + k)));
                            }
                        }
        result.put(CellRelativePosition.UP, cellsNeighborsUp);
        result.put(CellRelativePosition.SIDE, cellsNeighborsSide);
        result.put(CellRelativePosition.DOWN, cellsNeighborsDown);
        result.put(CellRelativePosition.UP_SIDE, cellsNeighborsUpSide);
        result.put(CellRelativePosition.DOWN_SIDE, cellsNeighborsDownSide);
        return result;
    }
}
