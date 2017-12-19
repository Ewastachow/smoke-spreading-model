package com.edu.agh.kis.automaton.gui;

import com.edu.agh.kis.automaton.core.Automaton;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.CellType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AppGUI extends Application {
    private double mousePosX, mousePosY;
    private double mouseOldX, mouseOldY;
    private final Rotate rotateX = new Rotate(-20, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(-20, Rotate.Y_AXIS);

    AutomatonGUISource currentState;
    private Timeline timeline = new Timeline();
    private int showZ;

    private Group root3D;
    private VBox toolBox;
    private FlowPane model2D;

    Slider przekrojSlider;


    @Override
    public void start(Stage primaryStage) throws Exception {

        // 3D
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setVerticalFieldOfView(false);
        camera.setNearClip(0.1);
        camera.setFarClip(100000.0);
        camera.getTransforms().addAll (rotateX, rotateY, new Translate(0, 0, -3000));
        root3D = new Group(camera);
        SubScene subScene = new SubScene(root3D, 550, 700, true, SceneAntialiasing.BALANCED);
        subScene.setFill(Color.WHITE);
        subScene.setCamera(camera);

        // 2D
        BorderPane pane = new BorderPane();
        pane.setRight(subScene);
        pane.setPrefSize(1300, 700);

//        Button button = new Button("Reset");
//        button.setOnAction(e->{
//            rotateX.setAngle(-20);
//            rotateY.setAngle(-20);
//        });
//
//        CheckBox checkBox = new CheckBox("Line");
//        checkBox.setOnAction(e->{
//            floor.setDrawMode(checkBox.isSelected()? DrawMode.LINE:DrawMode.FILL);
//        });

//
//        ToolBar toolBar = new ToolBar(button, checkBox);
//        toolBar.setOrientation(Orientation.VERTICAL);
//        toolBar.setPrefSize(200,700);

//        pane.setLeft(toolBar);

        Pane root2D = new Pane();
        root2D.setPrefSize(750,500);
        pane.setLeft(root2D);

        toolBox = new VBox();
        toolBox.setPrefSize(200,700);
        root2D.getChildren().add(toolBox);

        model2D = new FlowPane();
        model2D.setPrefSize(550,700);
        root2D.getChildren().add(model2D);

        initial();

        Scene scene = new Scene(pane);

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

        primaryStage.setScene(scene);
        primaryStage.setTitle("Smoke Spreading Model");
        primaryStage.show();
    }

    private void create2DBoard(){
        model2D.getChildren().clear();
        model2D.setPadding(new Insets(2,2,2,2));
        model2D.setHgap(100);
        model2D.setVgap(4);
        double w = (550 / currentState.width) - 4;
        if ((currentState.width * (w + 4) + w) < 550) {
            model2D.setMaxWidth(currentState.width * (w + 4));
            model2D.setPrefWidth(currentState.width * (w + 4));
            model2D.setMinWidth(currentState.width * (w + 4));
        }
        double h = (550 / currentState.height) - 4;
        for (int i = 0; i < currentState.height; i++) {
            for (int j = 0; j < currentState.width; j++) {
                Rectangle r = new Rectangle();
                r.setWidth(w);
                r.setHeight(h);
                if (currentState.cells[j][i][showZ].getCellType().equals(CellType.FIRE_SOURCE))
                    r.setFill(Paint.valueOf("FF0000"));
                else if (currentState.cells[j][i][showZ].getCellType().equals(CellType.BARRIER))
                    r.setFill(Paint.valueOf("0000FF"));
                else if(currentState.cells[j][i][showZ].getIsSmoked())
                    if(currentState.cells[j][i][showZ].getTemp() > 280)
                        r.setFill(Paint.valueOf("000000"));
                    else if(currentState.cells[j][i][showZ].getTemp() > 240)
                        r.setFill(Paint.valueOf("202020"));
                    else if(currentState.cells[j][i][showZ].getTemp() > 200)
                        r.setFill(Paint.valueOf("606060"));
                    else if(currentState.cells[j][i][showZ].getTemp() > 160)
                        r.setFill(Paint.valueOf("808080"));
                    else if(currentState.cells[j][i][showZ].getTemp() > 120)
                        r.setFill(Paint.valueOf("A0A0A0"));
                    else r.setFill(Paint.valueOf("C0C0C0"));
                else r.setFill(Paint.valueOf("FFFFFF"));
                model2D.getChildren().add(r);
            }
        }

    }

    private void create3DBoard(){
        root3D.getChildren().clear();

        PointLight light = new PointLight(Color.GAINSBORO);
        root3D.getChildren().add(light);
        root3D.getChildren().add(new AmbientLight(Color.WHITE));

        Box floor = new Box(1400, 10, 1400);
        floor.setMaterial(new PhongMaterial(Color.GRAY));
        floor.setTranslateY(405);
        root3D.getChildren().add(floor);

        Material fireSourceMaterial = new PhongMaterial(new Color(1,0,0,1));
        Material barrierMaterial = new PhongMaterial(new Color(0,0,1,1));
        Material smoke1Material = new PhongMaterial(new Color(0,0,0,0.9));
        Material smoke2Material = new PhongMaterial(new Color(0,0,0,0.8));
        Material smoke3Material = new PhongMaterial(new Color(0,0,0,0.7));
        Material smoke4Material = new PhongMaterial(new Color(0,0,0,0.6));
        Material smoke5Material = new PhongMaterial(new Color(0,0,0,0.5));
        Material smoke6Material = new PhongMaterial(new Color(0,0,0,0.4));
        Material smoke7Material = new PhongMaterial(new Color(0,0,0,0.3));
        Material smoke8Material = new PhongMaterial(new Color(0,0,0,0.2));
        Material smoke9Material = new PhongMaterial(new Color(0,0,0,0.1));
        Material airMaterial = new PhongMaterial(new Color(0,0,0,0));

        double w = (1400 / currentState.width);
        double d = (1400 / currentState.depth);
        double h = (1400 / currentState.height);

        for (int i = 0; i < currentState.width; i++)
            for (int j = 0; j < currentState.height; j++)
                for (int k = 0; k < currentState.depth; k++){
                    Box box = new Box(w,h,d);
                    box.setTranslateY(700 - j*h);
                    box.setTranslateX(700 - i*w);
                    box.setTranslateZ(700 - k*d);
                    if (currentState.cells[j][i][showZ].getCellType().equals(CellType.FIRE_SOURCE))
                        box.setMaterial(fireSourceMaterial);
                    else if (currentState.cells[j][i][showZ].getCellType().equals(CellType.BARRIER))
                        box.setMaterial(barrierMaterial);
                    else if(currentState.cells[j][i][showZ].getIsSmoked())
                        if(currentState.cells[j][i][showZ].getTemp() > 280)
                            box.setMaterial(smoke4Material);
                        else if(currentState.cells[j][i][showZ].getTemp() > 240)
                            box.setMaterial(smoke5Material);
                        else if(currentState.cells[j][i][showZ].getTemp() > 200)
                            box.setMaterial(smoke6Material);
                        else if(currentState.cells[j][i][showZ].getTemp() > 160)
                            box.setMaterial(smoke7Material);
                        else if(currentState.cells[j][i][showZ].getTemp() > 120)
                            box.setMaterial(smoke8Material);
                        else box.setMaterial(smoke9Material);
                    else box.setMaterial(airMaterial);
                    root3D.getChildren().add(box);
                }
    }

    private void createToolBox(){
        HBox controlBox = new HBox();

        Button startButton = new Button("Start");
        controlBox.getChildren().add(startButton);
        startButton.setOnAction(e -> timeline.play());

        Button stopButton = new Button("Stop");
        controlBox.getChildren().add(stopButton);
        stopButton.setOnAction(e -> timeline.stop());

        Button nextButton = new Button("Next");
        controlBox.getChildren().add(nextButton);
        nextButton.setOnAction(e -> nextAction());

        Button resetButton = new Button("Reset");
        controlBox.getChildren().add(resetButton);
        resetButton.setOnAction(e -> {
            timeline.stop();
            resetCells(currentState.width, currentState.height, currentState.depth, currentState.cells);
            currentState.start();
            currentState.putIntoTable();
            create2DBoard();
            create3DBoard();
        });

        toolBox.getChildren().add(controlBox);

        Label przekrojLabel = new Label("Przekroj");
        toolBox.getChildren().add(przekrojLabel);
        przekrojSlider = new Slider(1,80,10);
    }

    private void initial(){
        currentState = new AutomatonGUISource();
        showZ = 0;
        createToolBox();
        przekrojSlider.setMax(currentState.depth);
        przekrojSlider.valueProperty().addListener((ObservableValue<? extends Number> observable,
                                              Number oldValue, Number newValue) -> {
            showZ = newValue.intValue()-1;
            create2DBoard();
            create3DBoard();
        });
        model2D.setOnMouseClicked(this::clickedOnCell);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),
                event -> nextAction()));
        resetCells(currentState.width, currentState.height, currentState.depth, currentState.cells);
        create2DBoard();
        create3DBoard();
    }

    private void nextAction(){
        currentState.start();
        currentState.nextState();
        create2DBoard();
        create3DBoard();
    }

    private void resetCells(int w, int h, int d, CellState[][][] tab) {
        // todo wczytywac mape z pliku
        for (int i = 0; i < w; i++)
            for (int j = 0; j < h; j++)
                for (int k = 0; k < d; k++)
                    tab[i][j][k] = new CellState(20);
    }

    void clickedOnCell(MouseEvent event) {
        double posX = event.getX();
        double posY = event.getY();
        double w, h;
        int x = 0;
        int y = 0;
        w = (model2D.getPrefWidth() - (model2D.getPrefWidth() % currentState.width)) / currentState.width;
        h = (600 - (600 % currentState.height)) / currentState.height;
        while (posX > w) {
            x++;
            posX -= w;
        }
        while (posY > h) {
            y++;
            posY -= h;
        }
        if(currentState.cells[x][y][showZ].getCellType().equals(CellType.AIR))
            currentState.cells[x][y][showZ] = new CellState(CellType.BARRIER);
        else if(currentState.cells[x][y][showZ].getCellType().equals(CellType.BARRIER))
            currentState.cells[x][y][showZ] = new CellState(CellType.FIRE_SOURCE);
        else currentState.cells[x][y][showZ] = new CellState(20);
        currentState.putIntoMap();
        create2DBoard();
        create3DBoard();
    }

}
