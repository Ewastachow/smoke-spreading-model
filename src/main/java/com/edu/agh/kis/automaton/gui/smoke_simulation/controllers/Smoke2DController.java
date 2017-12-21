package com.edu.agh.kis.automaton.gui.smoke_simulation.controllers;

import com.edu.agh.kis.automaton.gui.smoke_simulation.views.Smoke2DView;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Smoke2DController extends SmokeController {

    Rectangle[][][] viewTab;
    int showZ;

    public Smoke2DController(int x, int y, int z) {
        super(x,y,z);
        setSmokeView(new Smoke2DView());
        viewTab = createRectangleTable();
        showZ = 0;
        createBoard();
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

    @Override
    public void putTabIntoMap() {
        //TODO Na podstawie kolorów rextangle ustawiamy CellState w automacie
    }

    @Override
    public void putMapIntoTab() {
        //TODO Ustawiamy kolory rectangle

    }

    private Rectangle[][][] createRectangleTable(){
        Rectangle[][][] rectangleTab = new Rectangle[getxAmong()][getyAmong()][getzAmong()];
        Paint paint = Paint.valueOf("0000FF");
        for(int i=0; i<getxAmong(); i++)
            for(int j=0; j<getyAmong(); j++)
                for(int k=0; k<getzAmong(); k++){
                    Rectangle r = ((Smoke2DView)getSmokeView()).createRectangle(paint,getxAmong(),getyAmong());
                    setRactangleOnClick(r);
                    rectangleTab[i][j][k] = r;
                }
        return rectangleTab;
    }

    private void setRactangleOnClick(Rectangle ractangle){
        //TODO Zmienić wartości miedzy sb
        ractangle.setOnMouseClicked(e -> {
            Paint previosPaint = ractangle.getFill();
            if(previosPaint.equals(Paint.valueOf("FF0000")))
                ractangle.setFill(Paint.valueOf("00FF00"));
            else if(previosPaint.equals(Paint.valueOf("00FF00")))
                ractangle.setFill(Paint.valueOf("0000FF"));
            else if(previosPaint.equals(Paint.valueOf("0000FF")))
            ractangle.setFill(Paint.valueOf("FF0000"));
        });

    }
}
