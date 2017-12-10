//package com.edu.agh.kis.automaton.core.neighborhood;
//
//import com.edu.agh.kis.automaton.core.coords.CellCoordinates;
//import com.edu.agh.kis.automaton.core.coords.Coords3D;
//
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//public class MoorNeighborhood3Dim implements CellNeighborhood{
//
//    private int radious;
//    private int height;
//    private int width;
//    private int depth;
//
//    public MoorNeighborhood3Dim(int radious, int height, int width, int depth) {
//        this.radious = radious;
//        this.height = height;
//        this.width = width;
//        this.depth = depth;
//    }
//
//    @Override
//    public Map<CellRelativePosition,Set<CellCoordinates>> cellNeighbors(CellCoordinates cell) {
//
//        Set<CellCoordinates> cellsNeighbors = new HashSet<>();
//
//        for (int i = -radious; i <= radious; i++)
//            for (int j = -radious; j <= radious; j++)
//                for (int k = -radious; k <= radious; k++)
//                    if((i != 0) || (j != 0) || (k != 0))
//                        if(     (((Coords3D) cell).getX() + i >= 0) &&
//                                (((Coords3D) cell).getX() + i < width) &&
//                                (((Coords3D) cell).getY() + j >= 0) &&
//                                (((Coords3D) cell).getY() + j < height) &&
//                                (((Coords3D) cell).getZ() + k >= 0) &&
//                                (((Coords3D) cell).getZ() + k < depth))
//                            cellsNeighbors.add(new Coords3D((((Coords3D) cell).getX() + i),
//                                    (((Coords3D) cell).getY() + j),
//                                    (((Coords3D) cell).getZ() + k)));
//        return cellsNeighbors;
//    }
//}
