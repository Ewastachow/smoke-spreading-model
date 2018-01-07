package com.edu.agh.kis.automaton.gui.smoke_simulation.controllers;

import com.edu.agh.kis.automaton.core.Automaton;

import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.CellType;
import com.edu.agh.kis.automaton.gui.smoke_simulation.views.Smoke2DView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Map;


public class Smoke2DController extends SmokeController {

    int showZ;

    public void setShowZ(int showZ) {
        this.showZ = showZ;
    }

    public Smoke2DController(int x, int y, int z, Map<Coords3D,CellState> cells) {
        super(x,y,z,cells);
        setSmokeView(new Smoke2DView());
        showZ = 0;
        drawBoard();
        setFlowPaneOnClick(((Smoke2DView)getSmokeView()).getRoot2D());
    }

    public void drawBoard(){
        FlowPane pane = ((Smoke2DView)getSmokeView()).getRoot2D();
        pane.getChildren().clear();
        for(int i=0; i<getxAmong(); i++)
            for(int j=0; j<getyAmong(); j++){
                CellState cellState = getCells().get(new Coords3D(j,i,showZ));
                Paint paint;
                if(cellState.getCellType().equals(CellType.BARRIER))
                    paint = Paint.valueOf("0000FF");
                else if(cellState.getCellType().equals(CellType.FIRE_SOURCE))
                    paint = Paint.valueOf("FF0000");
                else if(cellState.getTemp() > 280)
                    paint = Paint.valueOf("000000");
                else if(cellState.getTemp() > 240)
                    paint = Paint.valueOf("202020");
                else if(cellState.getTemp() > 200)
                    paint = Paint.valueOf("606060");
                else if(cellState.getTemp() > 160)
                    paint = Paint.valueOf("808080");
                else if(cellState.getTemp() > 120)
                    paint = Paint.valueOf("A0A0A0");
                else if(cellState.getTemp() > 70)
                    paint = Paint.valueOf("C0C0C0");
                else paint = Paint.valueOf("FFFFFF");
                Rectangle rectangle =  ((Smoke2DView)getSmokeView()).createRectangle(paint,getxAmong(),getyAmong());
                pane.getChildren().add(rectangle);
            }
    }

    private void setFlowPaneOnClick(FlowPane flowPane){
        flowPane.setOnMouseClicked(e -> {
            double posX = e.getX();
            double posY = e.getY();
            double w = ((Smoke2DView)getSmokeView()).getRoot2D().getPrefWidth()/getxAmong();
            double h = ((Smoke2DView)getSmokeView()).getRoot2D().getPrefHeight()/getyAmong();
            int x = (int) Math.floor(posX/w);
            int y = (int) Math.floor(posY/h);
            CellState currentCellState = getCells().get(new Coords3D(x,y,showZ));
            if(currentCellState.getCellType().equals(CellType.BARRIER))
                getCells().put(new Coords3D(x,y,showZ), new CellState(20));
            else if(currentCellState.getCellType().equals(CellType.AIR))
                getCells().put(new Coords3D(x,y,showZ), new CellState(CellType.FIRE_SOURCE));
            else
                getCells().put(new Coords3D(x,y,showZ), new CellState(CellType.BARRIER));
            drawBoard();

        });
    }
}
