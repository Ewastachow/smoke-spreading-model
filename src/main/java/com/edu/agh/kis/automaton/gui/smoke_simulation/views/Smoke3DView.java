package com.edu.agh.kis.automaton.gui.smoke_simulation.views;

import javafx.scene.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Smoke3DView extends SmokeView {
    Group root3D;

    private final Rotate rotateX = new Rotate(-20, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(-20, Rotate.Y_AXIS);
    private double mousePosX, mousePosY;
    private double mouseOldX, mouseOldY;

    private double sizePerCell;
    //TODO ustawic zmiane

    public Smoke3DView(int xAmong, int yAmong, int zAmong) {
        super();
        sizePerCell = findSizePerCell(xAmong,yAmong,zAmong);
        PerspectiveCamera camera = createCamera();
        root3D = createRoot3D();
        setRoot3DEvents();
        setSubScene(createSubScene(camera,Color.WHITE,root3D));
        addFloor();
        addLight();
    }

    public Group getRoot3D() {
        return root3D;
    }

    private Group createRoot3D(){
        Group group = new Group();
        //TODO Co tu wg mozna doimplementowac????
        return group;
    }

    private double findSizePerCell(int x, int y, int z){
        List<Integer> findMaxList = new ArrayList<>();
        findMaxList.add(x);
        findMaxList.add(y);
        findMaxList.add(z);
        return 1400/(Collections.max(findMaxList));
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

    public Box createBox(Material material, int xAmong, int yAmong, int zAmong){
        Box box = new Box();
        box.setMaterial(material);
        //TODO Co zamiast 1400????
        box.setWidth(1000/xAmong);
        box.setHeight(1000/yAmong);
        box.setDepth(1000/zAmong);
        //TODO Parametry, mozna pominąć kolor, albo dać domyślny
        return box;
    }
}
