package com.edu.agh.kis.automaton.gui.smoke_simulation.controllers;

import com.edu.agh.kis.automaton.core.Automaton;
import com.edu.agh.kis.automaton.core.Smoke;
import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.CellType;
import com.edu.agh.kis.automaton.gui.smoke_simulation.views.Smoke2DView;
import javafx.scene.control.Slider;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.Map;

public class Smoke2DController extends SmokeController {

    CellState[][][] viewTab;
    int showZ;
    Automaton automaton; //Z tą referencja jest raczej cos nie tak

    public void setAutomaton(Automaton automaton) {
        this.automaton = automaton;
    }

    public void setShowZ(int showZ) {
        this.showZ = showZ;
    }

    public Smoke2DController(int x, int y, int z, Automaton automaton) {
        super(x,y,z);
        this.automaton = automaton;
        setSmokeView(new Smoke2DView());
        viewTab = createRectangleTable();
        showZ = 0;
        createBoard();
        setFlowPaneOnClick(((Smoke2DView)getSmokeView()).getRoot2D());
    }

    @Override
    public void createBoard() {
        FlowPane pane = ((Smoke2DView)getSmokeView()).getRoot2D();
        pane.getChildren().clear();
        for(int i=0; i<getxAmong(); i++)
            for(int j=0; j<getyAmong(); j++)
                pane.getChildren().add(viewTab[i][j][showZ]);
    }
//
//    public void clickOnCell(event) {
//
//    }

    public void putTabIntoMap(Automaton a) {
        automaton = a;
        //TODO Na podstawie kolorów rextangle ustawiamy CellState w automacie
        for(int i=0; i<getxAmong(); i++)
            for(int j=0; j<getyAmong(); j++)
//                for(int k=0; k<getzAmong(); k++)
                {
                    if(viewTab[i][j][showZ].getFill().equals(Paint.valueOf("0000FF")))
                        automaton.cells.put(new Coords3D(i,j,showZ),new CellState(CellType.BARRIER));
                    else if(viewTab[i][j][showZ].getFill().equals(Paint.valueOf("FF0000")))
                        automaton.cells.put(new Coords3D(i,j,showZ),new CellState(CellType.FIRE_SOURCE));
                    else automaton.cells.put(new Coords3D(i,j,showZ),new CellState(CellType.AIR));
                }
//                    automaton.cells.put(new Coords3D(i,j,k),rectangleColorCellStateMap.get(viewTab[i][j][k].getFill()));
    }

    @Override
    public void putMapIntoTab(Automaton a) {
        automaton = a;
        //TODO Ustawiamy kolory rectangle
        for(int i=0; i<getxAmong(); i++)
            for(int j=0; j<getyAmong(); j++)
                for(int k=0; k<getzAmong(); k++){
                    CellState cellState = automaton.cells.get(new Coords3D(i,j,k));
                    if(cellState.getCellType().equals(CellType.BARRIER))
                        viewTab[i][j][k].setFill(Paint.valueOf("0000FF"));
                    else if(cellState.getCellType().equals(CellType.FIRE_SOURCE))
                        viewTab[i][j][k].setFill(Paint.valueOf("FF0000"));
                    else if(cellState.getTemp() > 280)
                        viewTab[i][j][k].setFill(Paint.valueOf("000000"));
                    else if(cellState.getTemp() > 240)
                        viewTab[i][j][k].setFill(Paint.valueOf("202020"));
                    else if(cellState.getTemp() > 200)
                        viewTab[i][j][k].setFill(Paint.valueOf("606060"));
                    else if(cellState.getTemp() > 160)
                        viewTab[i][j][k].setFill(Paint.valueOf("808080"));
                    else if(cellState.getTemp() > 120)
                        viewTab[i][j][k].setFill(Paint.valueOf("A0A0A0"));
                    else if(cellState.getTemp() > 70)
                        viewTab[i][j][k].setFill(Paint.valueOf("C0C0C0"));
                    else viewTab[i][j][k].setFill(Paint.valueOf("FFFFFF"));
//                    viewTab[i][j][k].setFill(rectangleCellStateColorMap.get(automaton.cells.get(new Coords3D(i,j,k))));
                }
    }

    private Rectangle[][][] createRectangleTable(){
        Rectangle[][][] rectangleTab = new Rectangle[getxAmong()][getyAmong()][getzAmong()];
        Paint paint = Paint.valueOf("0000FF");
        for(int i=0; i<getxAmong(); i++)
            for(int j=0; j<getyAmong(); j++)
                for(int k=0; k<getzAmong(); k++){
                    Rectangle r = ((Smoke2DView)getSmokeView()).createRectangle(paint,getxAmong(),getyAmong());
//                    setRactangleOnClick(r);
                    rectangleTab[i][j][k] = r;
                }
        return rectangleTab;
    }

    private void setRactangleOnClick(Rectangle ractangle){
        //TODO Zmienić wartości miedzy sb
        ractangle.setOnMouseClicked(e -> {
            Paint previosPaint = ractangle.getFill();
            if(previosPaint.equals(Paint.valueOf("FF0000")))
                ractangle.setFill(Paint.valueOf("0000FF"));
            else if(previosPaint.equals(Paint.valueOf("0000FF")))
                ractangle.setFill(Paint.valueOf("FFFFFF"));
            else if(previosPaint.equals(Paint.valueOf("FFFFFF")))
            ractangle.setFill(Paint.valueOf("FF0000"));
            putTabIntoMap(automaton);
        });
    }

    private void setFlowPaneOnClick(FlowPane flowPane){
        flowPane.setOnMouseClicked(e -> {
            double posX = e.getX();
            double posY = e.getY();
            double w = ((Smoke2DView)getSmokeView()).getRoot2D().getPrefWidth();
            double h = ((Smoke2DView)getSmokeView()).getRoot2D().getPrefHeight();
            int x = 0;
            while (posX > w){
                x++;
                posX -= w;
            }
            int y = 0;
            while (posY > h) {
                y++;
                posY -= h;
            }
            if(viewTab[x][y][showZ].equals(Paint.valueOf("FF0000")))
                viewTab[x][y][showZ].setFill(Paint.valueOf("0000FF"));
            else if(viewTab[x][y][showZ].equals(Paint.valueOf("0000FF")))
                viewTab[x][y][showZ].setFill(Paint.valueOf("FFFFFF"));
            else if(viewTab[x][y][showZ].equals(Paint.valueOf("FFFFFF")))
                viewTab[x][y][showZ].setFill(Paint.valueOf("FF0000"));

        });
    }
}
