package com.edu.agh.kis.automaton.core.neighborhood;

import com.edu.agh.kis.automaton.core.Smoke;
import com.edu.agh.kis.automaton.core.coords.CellCoordinates;
import com.edu.agh.kis.automaton.core.coords.Coords3D;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VonNeumanNeighborhood3Dim implements CellNeighborhood{

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

    @Override
    public Map<CellRelativePosition,Set<CellCoordinates>> cellNeighbors(CellCoordinates cell) {

        Map<CellRelativePosition, Set<CellCoordinates>> result = new HashMap<>();
        Set<CellCoordinates> cellsNeighborsUp = new HashSet<>();
        Set<CellCoordinates> cellsNeighborsSide = new HashSet<>();
        Set<CellCoordinates> cellsNeighborsDown = new HashSet<>();

        for (int i = -radious; i <= radious; i++)
            for (int j = -radious; j <= radious; j++)
                for (int k = -radious; k <= radious; k++)
                    if((i != 0) || (j != 0) || (k != 0))
                        if((((Coords3D) cell).getX() + i >= 0) &&
                            (((Coords3D) cell).getX() + i < width) &&
                            (((Coords3D) cell).getY() + j >= 0) &&
                            (((Coords3D) cell).getY() + j < height) &&
                            (((Coords3D) cell).getZ() + k >= 0) &&
                            (((Coords3D) cell).getZ() + k < depth)){
                            if(k ==((Coords3D) cell).getZ() && (i !=((Coords3D) cell).getX() || j !=((Coords3D) cell).getY()))
                                cellsNeighborsSide.add(new Coords3D((((Coords3D) cell).getX() + i),
                                        (((Coords3D) cell).getY() + j),
                                        (((Coords3D) cell).getZ() + k)));
                            if(k > ((Coords3D) cell).getZ() && i ==((Coords3D) cell).getX() && j ==((Coords3D) cell).getY())
                                cellsNeighborsUp.add(new Coords3D((((Coords3D) cell).getX() + i),
                                        (((Coords3D) cell).getY() + j),
                                        (((Coords3D) cell).getZ() + k)));
                            if(k < ((Coords3D) cell).getZ() && i ==((Coords3D) cell).getX() && j==((Coords3D) cell).getY())
                                cellsNeighborsDown.add(new Coords3D((((Coords3D) cell).getX() + i),
                                        (((Coords3D) cell).getY() + j),
                                        (((Coords3D) cell).getZ() + k)));
                }
        result.put(CellRelativePosition.UP, cellsNeighborsUp);
        result.put(CellRelativePosition.SIDE, cellsNeighborsSide);
        result.put(CellRelativePosition.DOWN, cellsNeighborsDown);
        return result;
    }
}
