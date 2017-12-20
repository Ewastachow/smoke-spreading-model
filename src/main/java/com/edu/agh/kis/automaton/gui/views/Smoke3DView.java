package com.edu.agh.kis.automaton.gui.views;

import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public class Smoke3DView extends SmokeView {
    Group root3D;
    private final Rotate rotateX = new Rotate(-20, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(-20, Rotate.Y_AXIS);

    public Smoke3DView() {
        super();
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setVerticalFieldOfView(false);
        camera.setNearClip(0.1);
        camera.setFarClip(100000.0);
        camera.getTransforms().addAll (rotateX, rotateY, new Translate(0, 0, -3000));
        setSubScene(new SubScene(root3D,weight,height,true,SceneAntialiasing.BALANCED));
        getSubScene().setFill(Color.WHITE);
        getSubScene().setCamera(camera);


    }
}
