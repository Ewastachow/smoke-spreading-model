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
//public class MoorNeighborhood3DimTest {
//
//    @Test
//    public void cellNeighborsCornetTest() throws Exception {
//        MoorNeighborhood3Dim mn = new MoorNeighborhood3Dim(1,4,4,4);
//
//        CellCoordinates cellCoordinatesCorner = new Coords3D(0,0,0);
//
//        Set<CellCoordinates> cellNeighborsCoordinatesCorner = new HashSet<>();
//        cellNeighborsCoordinatesCorner.add(new Coords3D(0,0,1));
//        cellNeighborsCoordinatesCorner.add(new Coords3D(0,1,0));
//        cellNeighborsCoordinatesCorner.add(new Coords3D(0,1,1));
//        cellNeighborsCoordinatesCorner.add(new Coords3D(1,0,0));
//        cellNeighborsCoordinatesCorner.add(new Coords3D(1,0,1));
//        cellNeighborsCoordinatesCorner.add(new Coords3D(1,1,0));
//        cellNeighborsCoordinatesCorner.add(new Coords3D(1,1,1));
//
////        Assert.assertEquals("False Corner", true, false);
//        Assert.assertEquals("Corner", cellNeighborsCoordinatesCorner, mn.cellNeighbors(cellCoordinatesCorner));
//    }
//
//    @Test
//    public void cellNeighborsInsideTest() throws Exception {
//        MoorNeighborhood3Dim mn = new MoorNeighborhood3Dim(1,5,5,5);
//
//        CellCoordinates cellCoordinatesInside = new Coords3D(2,2,2);
//
//        Set<CellCoordinates> cellNeighborsCoordinatesInside = new HashSet<>();
//        cellNeighborsCoordinatesInside.add(new Coords3D(1,1,1));
//        cellNeighborsCoordinatesInside.add(new Coords3D(1,1,2));
//        cellNeighborsCoordinatesInside.add(new Coords3D(1,1,3));
//        cellNeighborsCoordinatesInside.add(new Coords3D(1,2,1));
//        cellNeighborsCoordinatesInside.add(new Coords3D(1,2,2));
//        cellNeighborsCoordinatesInside.add(new Coords3D(1,2,3));
//        cellNeighborsCoordinatesInside.add(new Coords3D(1,3,1));
//        cellNeighborsCoordinatesInside.add(new Coords3D(1,3,2));
//        cellNeighborsCoordinatesInside.add(new Coords3D(1,3,3));
//        cellNeighborsCoordinatesInside.add(new Coords3D(2,1,1));
//        cellNeighborsCoordinatesInside.add(new Coords3D(2,1,2));
//        cellNeighborsCoordinatesInside.add(new Coords3D(2,1,3));
//        cellNeighborsCoordinatesInside.add(new Coords3D(2,2,1));
//        cellNeighborsCoordinatesInside.add(new Coords3D(2,2,3));
//        cellNeighborsCoordinatesInside.add(new Coords3D(2,3,1));
//        cellNeighborsCoordinatesInside.add(new Coords3D(2,3,2));
//        cellNeighborsCoordinatesInside.add(new Coords3D(2,3,3));
//        cellNeighborsCoordinatesInside.add(new Coords3D(3,1,1));
//        cellNeighborsCoordinatesInside.add(new Coords3D(3,1,2));
//        cellNeighborsCoordinatesInside.add(new Coords3D(3,1,3));
//        cellNeighborsCoordinatesInside.add(new Coords3D(3,2,1));
//        cellNeighborsCoordinatesInside.add(new Coords3D(3,2,2));
//        cellNeighborsCoordinatesInside.add(new Coords3D(3,2,3));
//        cellNeighborsCoordinatesInside.add(new Coords3D(3,3,1));
//        cellNeighborsCoordinatesInside.add(new Coords3D(3,3,2));
//        cellNeighborsCoordinatesInside.add(new Coords3D(3,3,3));
//
////        Assert.assertEquals("False Inside", true, false);
//        Assert.assertEquals("Inside", cellNeighborsCoordinatesInside, mn.cellNeighbors(cellCoordinatesInside));
//    }
//
//}