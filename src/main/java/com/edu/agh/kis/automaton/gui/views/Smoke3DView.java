package com.edu.agh.kis.automaton.gui.views;

import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Smoke3DView extends SmokeView {
    Group root3D;

    private final Rotate rotateX = new Rotate(-20, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(-20, Rotate.Y_AXIS);
    private double mousePosX, mousePosY;
    private double mouseOldX, mouseOldY;

    public Smoke3DView() {
        super();
        PerspectiveCamera camera = createCamera();
        root3D = createRoot3D();
        setRoot3DEvents();
        setSubScene(createSubScene(camera,Color.WHITE,root3D));
        addFloor();
        addLight();
    }

    private Group createRoot3D(){
        Group group = new Group();
        //TODO Co tu wg mozna doimplementowac????
        return group;
    }

    private void setRoot3DEvents(){
        //TODO czy nie wyeksportować tego do controllera?
        root3D.setOnMousePressed((MouseEvent me) -> {
            mouseOldX = me.getSceneX();
            mouseOldY = me.getSceneY();
        });
        root3D.setOnMouseDragged((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
            rotateX.setAngle(rotateX.getAngle()-(mousePosY - mouseOldY));
            rotateY.setAngle(rotateY.getAngle()+(mousePosX - mouseOldX));
            mouseOldX = mousePosX;
            mouseOldY = mousePosY;
        });
    }

    private PerspectiveCamera createCamera(){
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setVerticalFieldOfView(false);
        camera.setNearClip(0.1);
        camera.setFarClip(100000.0);
        camera.getTransforms().addAll (rotateX, rotateY, new Translate(0, 0, -3000));
        return camera;
    }

    private SubScene createSubScene(Camera camera, Color color, Group group){
        SubScene subScene = new SubScene(group,getWeight(),getHeight(),true,SceneAntialiasing.BALANCED);
        subScene.setFill(color);
        subScene.setCamera(camera);
        return subScene;
    }

    public void addLight(){
        PointLight light = new PointLight(Color.GAINSBORO);
        root3D.getChildren().add(light);
        root3D.getChildren().add(new AmbientLight(Color.WHITE));
    }

    public void addFloor(){
        Box floor = new Box(1400, 10, 1400);
        floor.setMaterial(new PhongMaterial(Color.GRAY));
        floor.setTranslateY(405);
        root3D.getChildren().add(floor);
    }

    public Box[][][] createBoxesTable(int x, int y, int z){
        Box[][][] boxes = new Box[x][y][z];
        //TODO Implement
        return boxes; //TODO : to bd wywołyać w kontrolerze i do niego przypisywać
    }

    private Box createBox(){
        Box box = new Box();
        //TODO Parametry, mozna pominąć kolor, albo dać domyślny
        return box;
    }
}
