package com.edu.agh.kis.automaton.gui.views;

import javafx.scene.SubScene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class Smoke2DView extends SmokeView {
    FlowPane root2D;

    public Smoke2DView() {
        super();
        root2D = createBoard();
        Pane pane = createBorderPane();
        pane.getChildren().add(root2D);
        setSubScene(new SubScene(pane,getWeight(),getHeight()));
    }

    private Pane createBorderPane(){
        Pane pane = new Pane();
        //TODO Zaimplmentować padding itp
        return pane;
    }

    private FlowPane createBoard(){
        FlowPane flowPane = new FlowPane();
        //TODO Implement własności
        return flowPane;
    }

    public Rectangle createRectangle(){
        Rectangle rectangle = new Rectangle();
        //TODO: Implement - własnosci poza kolorem, ale mozna w sumie jakiś domyślny walanc
        return rectangle;
    }
}
