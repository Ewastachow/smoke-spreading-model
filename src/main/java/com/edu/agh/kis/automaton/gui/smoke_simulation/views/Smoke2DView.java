package com.edu.agh.kis.automaton.gui.smoke_simulation.views;

import javafx.scene.SubScene;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Smoke2DView extends SmokeView {
    private FlowPane root2D;

    public FlowPane getRoot2D() {
        return root2D;
    }

    public Smoke2DView() {
        super();
        root2D = createBoard();
        Pane pane = createBorderPane();
        pane.getChildren().add(root2D);
        setSubScene(new SubScene(pane,getWeight(),getHeight()));
    }

    private Pane createBorderPane(){
        Pane pane = new Pane();
        pane.setPrefSize(700,700);
        return pane;
    }

    private FlowPane createBoard(){
        FlowPane flowPane = new FlowPane();
        flowPane.setPrefSize(600,600);
        return flowPane;
    }

    public Rectangle createRectangle(Paint paint, int xAmong, int yAmong){
        Rectangle rectangle = new Rectangle();
        rectangle.setFill(paint);
        rectangle.setWidth(Math.floor(root2D.getPrefWidth()/xAmong));
        rectangle.setHeight(Math.floor(root2D.getPrefHeight()/yAmong));
        return rectangle;
    }
}
