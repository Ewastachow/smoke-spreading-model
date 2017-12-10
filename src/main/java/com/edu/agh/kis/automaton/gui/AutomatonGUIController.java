package com.edu.agh.kis.automaton.gui;

import com.edu.agh.kis.automaton.core.Smoke;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.IsSmoked;
import com.edu.agh.kis.automaton.core.state.SmokeState;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Observable;

/**
 * Created by EwaStachów on 10/12/2016.
 * GUI
 * @author EwaStachów
 * @version 1.0
 */
public class AutomatonGUIController extends Observable {

    public AutomatonGUISource currentState;

    private Timeline timeline = new Timeline();

    private String structure;

    private int showZ;

    @FXML
    private ToggleButton moorToggleButton;

    @FXML
    private ToggleButton vonNeumanToggleButton;

    @FXML
    private Label widthLabel;

    @FXML
    private Slider widthSlider;

    @FXML
    private Label heightLabel;

    @FXML
    private Slider heightSlider;

    @FXML
    private Label radiousLabel;

    @FXML
    private Slider radiousSlider;

    @FXML
    private ToggleButton wrappingToggleButton;

    @FXML
    private MenuButton structureMenuButton;

    @FXML
    private Label rulesLabel;

    @FXML
    private Spinner<Integer> rule1;

    @FXML
    private Spinner<Integer> rule2;

    @FXML
    private Spinner<Integer> rule3;

    @FXML
    private Label slash;

    @FXML
    private FlowPane board;

    @FXML
    void gameOfLifeToggleButtonAction(ActionEvent event) {
//        currentState.automatonClass = Smoke.class;
//        currentState.quadLife = false;
//        setButton();
//        timeline.stop();
//        resetCells(currentState.width, currentState.height, currentState.cells);
//        currentState.start();
//        createBoard();
    }

    @FXML
    void quadLifeToggleButtonAction(ActionEvent event) {
//        currentState.automatonClass = Smoke.class;
//        currentState.quadLife = true;
//        setButton();
//        timeline.stop();
//        resetCells(currentState.width, currentState.height, currentState.cells);
//        currentState.start();
//        createBoard();
    }

    @FXML
    void langtonAntToggleButtonAction(ActionEvent event) {
//        currentState.automatonClass = Smoke.class;
//        setButton();
//        timeline.stop();
//        resetCells(currentState.width, currentState.height, currentState.cells);
//        currentState.start();
//        createBoard();
    }

    @FXML
    void wireworldToggleButtonAction(ActionEvent event) {
//        currentState.automatonClass = Smoke.class;
//        setButton();
//        timeline.stop();
//        resetCells(currentState.width, currentState.height, currentState.cells);
//        currentState.start();
//        createBoard();

    }

    @FXML
    void oneDimToggleButtonAction(ActionEvent event) {
//        currentState.automatonClass = Smoke.class;
//        setButton();
//        timeline.stop();
//        resetCells(currentState.width, currentState.height, currentState.cells);
//        currentState.start();
//        createBoard();

    }

    @FXML
    void wrappingToggleButtonAction(ActionEvent event) {
//        currentState.wrapping = !currentState.wrapping;
    }

    @FXML
    void nextButtonAction(ActionEvent event) {
        currentState.start();
        currentState.nextState();
        createBoard();
    }

    @FXML
    void resetButtonAction(ActionEvent event) {
        timeline.stop();
        resetCells(currentState.width, currentState.height, currentState.depth, currentState.cells);
        currentState.start();
        currentState.putIntoTable();
        createBoard();
    }

    @FXML
    void startButtonAction(ActionEvent e) throws InterruptedException {
        timeline.play();
    }

    @FXML
    void stopButtonAction(ActionEvent event) {
        timeline.stop();
    }

    @FXML
    void moorToggleButtonAction(ActionEvent event) {
//        currentState.neighborhoodStrategy = "MoorNeighborhood";
    }

    @FXML
    void vonNeumanToggleButtonAction(ActionEvent event) {
//        currentState.neighborhoodStrategy = "VonNeumanNeighborhood";
    }

    @FXML
    void clickedOnCell(MouseEvent event) {
        double posX = event.getX();
        double posY = event.getY();
        double w, h;
        int x = 0;
        int y = 0;
        w = (board.getPrefWidth() - (board.getPrefWidth() % currentState.width)) / currentState.width;
        h = (600 - (600 % currentState.height)) / currentState.height;
        while (posX > w) {
            x++;
            posX -= w;
        }
        while (posY > h) {
            y++;
            posY -= h;
        }
//        if ((currentState.automatonClass == GameOfLife.class) && (!currentState.quadLife)) {
//            if (structure.equals("None")) {
//                if (currentState.cells[x][y] == 1) currentState.cells[x][y] = 0;
//                else currentState.cells[x][y] = 1;
//            } else createStructure(x,y);
//        } else if (currentState.automatonClass == WireWorld.class) {
//            if (currentState.cells[x][y] == 3) currentState.cells[x][y] = 0;
//            else currentState.cells[x][y] += 1;
//        } else if (currentState.automatonClass == LangtonAnt.class) {
//            if (currentState.cells[x][y] == 9) currentState.cells[x][y] = 0;
//            else currentState.cells[x][y] += 1;
//        } else if ((currentState.automatonClass == GameOfLife.class) && (currentState.quadLife)) {
//            if (structure == "None") {
//                if (currentState.cells[x][y] == 5) currentState.cells[x][y] = 0;
//                else currentState.cells[x][y] += 1;
//            } createStructure(x,y);
//        } else if (currentState.automatonClass == OneDim.class) {
//            if (y == 0) {
//                if (currentState.cells[x][y] == 1) currentState.cells[x][y] = 0;
//                else currentState.cells[x][y] = 1;
//            }
//        }
        currentState.cells[x][y][showZ] = new SmokeState(IsSmoked.SOURCE_OF_FIRE, 300);
        currentState.putIntoMap();
        createBoard();
    }

    public void resetCells(int w, int h, int d, CellState[][][] tab) {
        // todo wczytywac mape z pliku
//        for (int i = 0; i < w; i++)
//            for (int j = 0; j < h; j++)
//                for (int k = 0; k < d; k++)
//                tab[i][j][k] = new SmokeState();
    }

    public void createBoard() {
        board.getChildren().clear();
        board.setMaxWidth(600);
        board.setPrefWidth(600);
        board.setMinWidth(600);
        double w = (600 / currentState.width) - 4;
        if ((currentState.width * (w + 4) + w) < 600) {
            board.setMaxWidth(currentState.width * (w + 4));
            board.setPrefWidth(currentState.width * (w + 4));
            board.setMinWidth(currentState.width * (w + 4));
        }
        double h = (600 / currentState.height) - 4;
        for (int i = 0; i < currentState.height; i++) {
            for (int j = 0; j < currentState.width; j++) {
                Rectangle r = new Rectangle();
                r.setWidth(w);
                r.setHeight(h);


                try {
                    if (currentState.cells[j][i][showZ].getIsSmoked().equals(IsSmoked.SMOKED))
                        r.setFill(Paint.valueOf("000000"));
                    else if (currentState.cells[j][i][showZ].getIsSmoked().equals(IsSmoked.SMOKED))
                        r.setFill(Paint.valueOf("FF0000"));
                    else r.setFill(Paint.valueOf("FFFFFF"));
                }catch(Exception e){
                    System.out.println("Lamolandia");
                }

//                if ((currentState.automatonClass == GameOfLife.class) && (!currentState.quadLife)) {
//                    if (currentState.cells[j][i] == 1) {
//                        r.setFill(Paint.valueOf("000000"));
//                    } else {
//                        r.setFill(Paint.valueOf("FFFFFF"));
//                    }
//                } else if (currentState.automatonClass == WireWorld.class) {
//                    if (currentState.cells[j][i] == 0) {
//                        r.setFill(Paint.valueOf("FFFFFF"));
//                    } else if (currentState.cells[j][i] == 1) {
//                        r.setFill(Paint.valueOf("F5E478"));
//                    } else if (currentState.cells[j][i] == 2) {
//                        r.setFill(Paint.valueOf("78C7F5"));
//                    } else {
//                        r.setFill(Paint.valueOf("F76060"));
//                    }
//                } else if (currentState.automatonClass == LangtonAnt.class) {
//                    //DEAD&NONE
//                    if (currentState.cells[j][i] == 0) {
//                        r.setFill(Paint.valueOf("FFFFFF"));
//                        //ALIVE&NONE
//                    } else if (currentState.cells[j][i] == 1) {
//                        r.setFill(Paint.valueOf("000000"));
//                        //DEAD&NORTH
//                    } else if (currentState.cells[j][i] == 2) {
//                        r.setFill(new ImagePattern(new Image("images/deadNorth.jpg")));
//                        //ALIVE&NORTH
//                    } else if (currentState.cells[j][i] == 3) {
//                        r.setFill(new ImagePattern(new Image("images/aliveNorth.jpg")));
//                        //DEAD&SOUTH
//                    } else if (currentState.cells[j][i] == 4) {
//                        r.setFill(new ImagePattern(new Image("images/deadSouth.jpg")));
//                        //ALIVE&SOUTH
//                    } else if (currentState.cells[j][i] == 5) {
//                        r.setFill(new ImagePattern(new Image("images/aliveSouth.jpg")));
//                        //DEAD&EAST
//                    } else if (currentState.cells[j][i] == 6) {
//                        r.setFill(new ImagePattern(new Image("images/deadEast.jpg")));
//                        //ALIVE&EAST
//                    } else if (currentState.cells[j][i] == 7) {
//                        r.setFill(new ImagePattern(new Image("images/aliveEast.jpg")));
//                        //DEAD&WEST
//                    } else if (currentState.cells[j][i] == 8) {
//                        r.setFill(new ImagePattern(new Image("images/deadWest.jpg")));
//                        //ALIVE&WEST
//                    } else {
//                        r.setFill(new ImagePattern(new Image("images/aliveWest.jpg")));
//                    }
//                } else if ((currentState.automatonClass == GameOfLife.class) && (currentState.quadLife)) {
//                    if (currentState.cells[j][i] == 0) {
//                        r.setFill(Paint.valueOf("FFFFFF"));
//                    } else if (currentState.cells[j][i] == 1) {
//                        r.setFill(Paint.valueOf("87CEE6"));//BLUE
//                    } else if (currentState.cells[j][i] == 2) {
//                        r.setFill(Paint.valueOf("E68787"));//RED
//                    } else if (currentState.cells[j][i] == 3) {
//                        r.setFill(Paint.valueOf("87E694"));//GREEN
//                    } else {
//                        r.setFill(Paint.valueOf("E6D487"));//YELLOW
//                    }
//                } else if (currentState.automatonClass == OneDim.class) {
//                    if (i > 0) {
//                        if (currentState.cells[j][i] == 0) {
//                            r.setFill(Paint.valueOf("BABABA"));
//                        } else {
//                            r.setFill(Paint.valueOf("595959"));
//                        }
//                    } else {
//                        if (currentState.cells[j][i] == 0) {
//                            r.setFill(Paint.valueOf("FFFFFF"));
//                        } else {
//                            r.setFill(Paint.valueOf("000000"));
//                        }
//                    }
//                }
                board.getChildren().add(r);
            }
        }
    }

    @FXML
    void initialize() {
        showZ = 0;
        currentState = new AutomatonGUISource();
//        structure = "None";
//        rule1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 256, 2));
//        rule2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 3));
//        rule3.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 3));
//        rule1.valueProperty().addListener((o, oldValue, newValue) -> {
//            currentState.rule1 = newValue;
//        });
//        rule2.valueProperty().addListener((o, oldValue, newValue) -> {
//            currentState.rule2 = newValue;
//        });
//        rule3.valueProperty().addListener((o, oldValue, newValue) -> {
//            currentState.rule3 = newValue;
//        });
//
//        MenuItem none = new MenuItem("None");
//        MenuItem glider = new MenuItem("Glider");
//        MenuItem spaceship = new MenuItem("Spaceship");
//        MenuItem blinker = new MenuItem("Blinker");
//        MenuItem block = new MenuItem("Block");
//        structureMenuButton.getItems().addAll(none, glider, spaceship, blinker, block);
//        none.setOnAction(event -> {
//            structure = "None";
//        });
//        glider.setOnAction(event -> {
//            structure = "Glider";
//        });
//        spaceship.setOnAction(event -> {
//            structure = "Spaceship";
//        });
//        blinker.setOnAction(event -> {
//            structure = "Blinker";
//        });
//        block.setOnAction(event -> {
//            structure = "Block";
//        });

        resetCells(currentState.width, currentState.height, currentState.depth, currentState.cells);
        createBoard();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),
                event -> nextButtonAction(null)));
        widthSlider.valueProperty().addListener((ObservableValue<? extends Number> observable,
                                                 Number oldValue, Number newValue) -> {
            currentState.width = newValue.intValue();
            SmokeState[][][] tmp = new SmokeState[currentState.width][currentState.height][currentState.depth];
            resetCells(currentState.width, currentState.height, currentState.depth, tmp);
            for (int i = 0; i < oldValue.intValue(); i++)
                for (int j = 0; j < currentState.height; j++)
                    for (int k = 0; k < currentState.depth; k++) {
                    if (i < currentState.width) tmp[i][j][k] = currentState.cells[i][j][k];
                }
            currentState.cells = tmp;
            createBoard();

        });
        heightSlider.valueProperty().addListener((ObservableValue<? extends Number> observable,
                                                  Number oldValue, Number newValue) -> {
            //TODO; JAk bd robic "Z" to trzeba zmienic mozliwe wartosci show Z
            currentState.height = newValue.intValue();
            SmokeState[][][] tmp = new SmokeState[currentState.width][currentState.height][currentState.depth];
            resetCells(currentState.width, currentState.height, currentState.depth, tmp);
            for (int i = 0; i < currentState.width; i++)
                for (int j = 0; j < oldValue.intValue(); j++)
                    for (int k = 0; k < currentState.depth; k++){
                        if (j < currentState.height) tmp[i][j][k] = currentState.cells[i][j][k];
                    }
            currentState.cells = tmp;
            createBoard();
        });
        radiousSlider.valueProperty().addListener((ObservableValue<? extends Number> observable,
                                                   Number oldValue, Number newValue) -> currentState.radious = newValue.intValue());
        board.setOnMouseClicked(event -> clickedOnCell(event));
    }

//    private void createStructure(int x, int y){
//        if (structure.equals("Glider")) {
//            currentState.cells[x][y] = 1;
//            if (x + 1 < currentState.width) currentState.cells[x + 1][y] = 1;
//            if (x + 2 < currentState.width) currentState.cells[x + 2][y] = 1;
//            if ((x + 2 < currentState.width) && (y - 1 >= 0)) currentState.cells[x + 2][y - 1] = 1;
//            if ((x + 1 < currentState.width) && (y - 2 >= 0)) currentState.cells[x + 1][y - 2] = 1;
//            structure = "None";
//        } else if (structure.equals("Spaceship")) {
//            currentState.cells[x][y] = 1;
//            if (y + 2 < currentState.height) currentState.cells[x][y + 2] = 1;
//            if (x + 3 < currentState.width) currentState.cells[x + 3][y] = 1;
//            if ((y + 3 < currentState.height) && (x + 1 < currentState.width)) currentState.cells[x + 1][y + 3] = 1;
//            if ((y + 3 < currentState.height) && (x + 2 < currentState.width)) currentState.cells[x + 2][y + 3] = 1;
//            if ((y + 3 < currentState.height) && (x + 3 < currentState.width)) currentState.cells[x + 3][y + 3] = 1;
//            if ((y + 3 < currentState.height) && (x + 4 < currentState.width)) currentState.cells[x + 4][y + 3] = 1;
//            if ((y + 2 < currentState.height) && (x + 4 < currentState.width)) currentState.cells[x + 4][y + 2] = 1;
//            if ((y + 1 < currentState.height) && (x + 4 < currentState.width)) currentState.cells[x + 4][y + 1] = 1;
//            structure = "None";
//        } else if (structure.equals("Blinker")) {
//            currentState.cells[x][y] = 1;
//            if (x + 1 < currentState.width) currentState.cells[x + 1][y] = 1;
//            if (x + 2 < currentState.width) currentState.cells[x + 2][y] = 1;
//            structure = "None";
//        } else if (structure.equals("Block")) {
//            currentState.cells[x][y] = 1;
//            if (x + 1 < currentState.width) currentState.cells[x + 1][y] = 1;
//            if ((x + 1 < currentState.width) && (y + 1 < currentState.height)) currentState.cells[x + 1][y + 1] = 1;
//            if (y + 1 < currentState.height) currentState.cells[x][y + 1] = 1;
//            structure = "None";
//        }
//
//    }

    private void setButton(){
//        if(currentState.automatonClass==OneDim.class){
//            moorToggleButton.setDisable(true);
//            vonNeumanToggleButton.setDisable(true);
//            widthLabel.setText("Size");
//            heightLabel.setText("History");
//        }else{
//            moorToggleButton.setDisable(false);
//            vonNeumanToggleButton.setDisable(false);
//            widthLabel.setText("Width");
//            heightLabel.setText("Height");
//        }
//        if((currentState.automatonClass==GameOfLife.class)||(currentState.automatonClass==OneDim.class)){
//            radiousLabel.setText("Radious");
//            radiousSlider.setDisable(false);
//
//        }else{
//            radiousLabel.setText("");
//            radiousSlider.setDisable(true);
//        }
//        if(currentState.automatonClass==OneDim.class){
//            wrappingToggleButton.setDisable(true);
//        }else{
//            wrappingToggleButton.setDisable(false);
//        }
//        if(currentState.automatonClass==GameOfLife.class){
//            structureMenuButton.setDisable(false);
//        } else{
//            structureMenuButton.setDisable(true);
//        }
//        if(currentState.automatonClass==GameOfLife.class){
//            rule2.setDisable(false);
//            rule3.setDisable(false);
//            slash.setTextFill(Paint.valueOf("9d9d9d"));
//            slash.setDisable(false);
//        } else{
//            rule2.setDisable(true);
//            rule3.setDisable(true);
//            slash.setTextFill(Paint.valueOf("FFFFFF"));
//            slash.setDisable(true);
//        }
//        if(currentState.automatonClass==GameOfLife.class){
//            rulesLabel.setText("Rules");
//            rule1.setDisable(false);
//            rule1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 15, 2));
//        } else if(currentState.automatonClass==OneDim.class){
//            rulesLabel.setText("Rules");
//            rule1.setDisable(false);
//            rule1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 256, 2));
//        } else{
//            rule1.setDisable(true);
//            rulesLabel.setText("");
//        }
    }

}
