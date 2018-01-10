//package com.edu.agh.kis.automaton.core;
//
//import com.edu.agh.kis.automaton.core.coords.Coords3D;
//import com.edu.agh.kis.automaton.core.state.CellState;
//import com.edu.agh.kis.automaton.core.state.CellType;
//import org.junit.Assert;
//import org.junit.Test;
//
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.Assert.*;
//
//public class SmokeTest {
//    @Test
//    public void removeFireSourceAndBarrierFromMapTest() throws Exception {
//
//    }
//
//    @Test
//    public void removeFireSourceAndBarrierFromSetTest() throws Exception {
//        Smoke automat = new Smoke();
//        Set<Cell> startedSet = new HashSet<>();
//        startedSet.add(new Cell(new Coords3D(2,4,4),new CellState(20)));
//        startedSet.add(new Cell(new Coords3D(6,4,4),new CellState(50)));
//        startedSet.add(new Cell(new Coords3D(6,4,4),new CellState(CellType.FIRE_SOURCE)));
//        startedSet.add(new Cell(new Coords3D(7,4,4),new CellState(CellType.BARRIER)));
//        startedSet.add(new Cell(new Coords3D(8,4,4),new CellState(40)));
//        Set<Cell> expectedSet = new HashSet<>();
//        expectedSet.add(new Cell(new Coords3D(2,4,4),new CellState(20)));
//        expectedSet.add(new Cell(new Coords3D(6,4,4),new CellState(50)));
//        expectedSet.add(new Cell(new Coords3D(8,4,4),new CellState(40)));
//
//        Assert.assertEquals("IDK", automat.removeFireSourceAndBarrierFromSet(startedSet), expectedSet);
//
//    }
//
//}