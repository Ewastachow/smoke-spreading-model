//package com.edu.agh.kis.automaton.core.neighborhood;
//
//import com.edu.agh.kis.automaton.core.coords.CellCoordinates;
//import com.edu.agh.kis.automaton.core.coords.Coords3D;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.Assert.*;
//
//public class VonNeumanNeighborhood3DimTest {
//
//    @Test
//    public void cellNeighborsCornerTest() throws Exception {
//        VonNeumanNeighborhood3Dim vnn = new VonNeumanNeighborhood3Dim(1,4,4,4);
//
//        CellCoordinates cellCoordinatesCorner = new Coords3D(0,0,0);
//
//        Set<CellCoordinates> cellNeighborsCoordinatesCorner = new HashSet<>();
//        cellNeighborsCoordinatesCorner.add(new Coords3D(0,0,1));
//        cellNeighborsCoordinatesCorner.add(new Coords3D(0,1,0));
//        cellNeighborsCoordinatesCorner.add(new Coords3D(1,0,0));
//
////        Assert.assertEquals("False Corner", true, false);
//        Assert.assertEquals("Corner", cellNeighborsCoordinatesCorner, vnn.cellNeighbors(cellCoordinatesCorner));
//    }
//
////    @Test
////    public void cellNeighborsInside2RadiousTest() throws Exception {
////        VonNeumanNeighborhood3Dim vnn = new VonNeumanNeighborhood3Dim(2,7,7,7);
////
////        CellCoordinates cellCoordinatesCorner = new Coords3D(3,3,3);
////
////        Set<CellCoordinates> cellNeighborsCoordinatesCorner = new HashSet<>();
////        cellNeighborsCoordinatesCorner.add(new Coords3D(1,3,3));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(2,3,3));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(3,3,1));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(3,3,2));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(3,3,4));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(3,3,5));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(3,1,3));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(3,2,3));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(3,4,3));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(3,5,3));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(4,3,3));
////        cellNeighborsCoordinatesCorner.add(new Coords3D(5,3,3));
////
//////        Assert.assertEquals("False Corner", true, false);
////        Assert.assertEquals("Corner", cellNeighborsCoordinatesCorner, vnn.cellNeighbors(cellCoordinatesCorner));
////    }
//
//}