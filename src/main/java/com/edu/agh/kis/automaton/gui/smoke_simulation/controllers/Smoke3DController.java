package com.edu.agh.kis.automaton.gui.smoke_simulation.controllers;

import com.edu.agh.kis.automaton.core.Automaton;
import com.edu.agh.kis.automaton.core.Smoke;
import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.CellType;
import com.edu.agh.kis.automaton.gui.smoke_simulation.views.Smoke3DView;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

public class Smoke3DController extends SmokeController {

    Box[][][] viewTab;

    public Smoke3DController(int x, int y, int z) {
        super(x,y,z);
        setSmokeView(new Smoke3DView());
        viewTab = createBoxTable();
        createBoard();
    }

    @Override
    public void createBoard() {
        Group root3D = ((Smoke3DView)getSmokeView()).getRoot3D();
        root3D.getChildren().clear();
        ((Smoke3DView)getSmokeView()).addFloor();
        ((Smoke3DView)getSmokeView()).addLight();
        //TODO metoda przyjmujaca boxa i jego połozenie w tablicy i ustawiajaca Translate
        Box box = ((Smoke3DView)getSmokeView()).createBox(new PhongMaterial(new Color(0,0,1,0.3)),2,2,2);
        root3D.getChildren().add(box);

    }

//    @Override
//    public void clickOnCell(MouseEvent event) {
//
//    }

    @Override
    public void putMapIntoTab(Automaton automaton) {
        //TODO Ustaiwamy odpowiedi material na boxach!!! - nie tworzymy boxów na nowo
        for(int i=0; i<getxAmong(); i++)
            for(int j=0; j<getyAmong(); j++)
                for(int k=0; k<getzAmong(); k++){
                    CellState cellState = automaton.cells.get(new Coords3D(i,j,k));
                    if(cellState.getCellType().equals(CellType.BARRIER))
                        viewTab[i][j][k].setMaterial(new PhongMaterial(new Color(0,0,1,1)));
                    else if(cellState.getCellType().equals(CellType.FIRE_SOURCE))
                        viewTab[i][j][k].setMaterial(new PhongMaterial(new Color(1,0,0,1)));
                    else if(cellState.getTemp() > 280)
                        viewTab[i][j][k].setMaterial(new PhongMaterial(new Color(0,0,0,0.9)));
                    else if(cellState.getTemp() > 240)
                        viewTab[i][j][k].setMaterial(new PhongMaterial(new Color(0,0,0,0.7)));
                    else if(cellState.getTemp() > 200)
                        viewTab[i][j][k].setMaterial(new PhongMaterial(new Color(0,0,0,0.5)));
                    else if(cellState.getTemp() > 160)
                        viewTab[i][j][k].setMaterial(new PhongMaterial(new Color(0,0,0,0.4)));
                    else if(cellState.getTemp() > 120)
                        viewTab[i][j][k].setMaterial(new PhongMaterial(new Color(0,0,0,0.3)));
                    else if(cellState.getTemp() > 70)
                        viewTab[i][j][k].setMaterial(new PhongMaterial(new Color(0,0,0,0.2)));
                    else viewTab[i][j][k].setMaterial(new PhongMaterial(new Color(0,0,0,0)));
//                    viewTab[i][j][k].setFill(rectangleCellStateColorMap.get(automaton.cells.get(new Coords3D(i,j,k))));
                }
    }

    private Box[][][] createBoxTable(){
        Box[][][] rectangleTab = new Box[getxAmong()][getyAmong()][getzAmong()];
        for(int i=0; i<getxAmong(); i++)
            for(int j=0; j<getyAmong(); j++)
                for(int k=0; k<getzAmong(); k++)
                    rectangleTab[i][j][k] = ((Smoke3DView)getSmokeView()).createBox( //TODO Zmienić domyślny kolor na przexroczysty
                            new PhongMaterial(new Color(0,0,1,1)),getxAmong(),getyAmong(),getzAmong());
        return rectangleTab;
    }
}
