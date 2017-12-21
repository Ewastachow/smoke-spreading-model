package com.edu.agh.kis.automaton.gui.smoke_simulation.controllers;

import com.edu.agh.kis.automaton.gui.smoke_simulation.views.Smoke3DView;
import javafx.scene.Group;
import javafx.scene.paint.Color;
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
    public void putTabIntoMap() {
        //TODO chyba zbędne, bo w 3D nie modyfikujemy wartosci

    }

    @Override
    public void putMapIntoTab() {
        //TODO Ustaiwamy odpowiedi material na boxach!!! - nie tworzymy boxów na nowo
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
