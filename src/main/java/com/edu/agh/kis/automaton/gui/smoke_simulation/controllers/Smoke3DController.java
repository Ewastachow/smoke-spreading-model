package com.edu.agh.kis.automaton.gui.smoke_simulation.controllers;

import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.CellType;
import com.edu.agh.kis.automaton.gui.smoke_simulation.views.Smoke3DView;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.Map;

public class Smoke3DController extends SmokeController {

    public Smoke3DController(int x, int y, int z, Map<Coords3D,CellState> cells) {
        super(x,y,z,cells);
        setSmokeView(new Smoke3DView(x,y,z));
        drawBoard();
    }

    @Override
    public void drawBoard() {
        Group root3D = ((Smoke3DView)getSmokeView()).getRoot3D();
        root3D.getChildren().clear();
        ((Smoke3DView)getSmokeView()).addLight();
        for(int i=0; i<getxAmong(); i++)
            for(int j=0; j<getyAmong(); j++)
                for(int k=0; k<getzAmong(); k++){
                    CellState cellState = getCells().get(new Coords3D(i,j,k));
                    Color color;

                    if(cellState.getCellType().equals(CellType.BARRIER))
                        color = new Color(0,0,1,1);
                    else if(cellState.getCellType().equals(CellType.FIRE_SOURCE))
                        color = new Color(1,0,0,1);
                    else if(cellState.getTemp() > 280)
                        color = new Color(0,0,0,0.9);
                    else if(cellState.getTemp() > 240)
                        color = new Color(0,0,0,0.7);
                    else if(cellState.getTemp() > 200)
                        color = new Color(0,0,0,0.5);
                    else if(cellState.getTemp() > 160)
                        color = new Color(0,0,0,0.4);
                    else if(cellState.getTemp() > 120)
                        color = new Color(0,0,0,0.3);
                    else if(cellState.getTemp() > 70)
                        color = new Color(0,0,0,0.2);
                    else color = new Color(0,0,0,0);

                    Smoke3DView smoke3DView = ((Smoke3DView)getSmokeView());

                    Box box = smoke3DView.createBox(new PhongMaterial(color));
                    box.setTranslateX(i*smoke3DView.getSizePerCell()-600);
                    box.setTranslateY(j*smoke3DView.getSizePerCell()-600);
                    box.setTranslateZ(k*smoke3DView.getSizePerCell()-600);
                    root3D.getChildren().add(box);
                }
    }
}
