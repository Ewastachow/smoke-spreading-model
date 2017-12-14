package com.edu.agh.kis.automaton.gui;


import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.state.CellType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
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

    private AutomatonGUISource currentState;
    private Timeline timeline = new Timeline();
    private int showZ;

    @FXML
    private Slider przekroj;

    @FXML
    private Slider widthSlider;

    @FXML
    private Slider heightSlider;

    @FXML
    private Slider depthSlider;

    @FXML
    private FlowPane board;

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
        if(currentState.cells[x][y][showZ].getCellType().equals(CellType.AIR))
            currentState.cells[x][y][showZ] = new CellState(CellType.BARRIER);
        else if(currentState.cells[x][y][showZ].getCellType().equals(CellType.BARRIER))
            currentState.cells[x][y][showZ] = new CellState(CellType.FIRE_SOURCE);
        else currentState.cells[x][y][showZ] = new CellState(20);
        currentState.putIntoMap();
        createBoard();
    }

    private void resetCells(int w, int h, int d, CellState[][][] tab) {
        // todo wczytywac mape z pliku
        for (int i = 0; i < w; i++)
            for (int j = 0; j < h; j++)
                for (int k = 0; k < d; k++)
                    tab[i][j][k] = new CellState(20);
    }

    private void createBoard() {
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
//TODO ale to zjebane - tu musi być w zależności od typu czerwony dla sourceFIRE, niebieski dla przeszkody,
// todo a jeśli air to od temperatury generujemy jak ciemne bd
                if (currentState.cells[j][i][showZ].getCellType().equals(CellType.FIRE_SOURCE))
                    r.setFill(Paint.valueOf("FF0000"));
                else if (currentState.cells[j][i][showZ].getCellType().equals(CellType.BARRIER))
                    r.setFill(Paint.valueOf("0000FF"));
                else if(currentState.cells[j][i][showZ].getIsSmoked())
                    r.setFill(Paint.valueOf("000000"));
                else r.setFill(Paint.valueOf("FFFFFF"));
//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ do tąd do modyfikacji
                board.getChildren().add(r);
            }
        }
    }

    @FXML
    void initialize() {
        currentState = new AutomatonGUISource();
        showZ = 0;
        przekroj.setMax(currentState.depth);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),
                event -> nextButtonAction(null)));
        setEventsHandlers();
        resetCells(currentState.width, currentState.height, currentState.depth, currentState.cells);
        createBoard();
    }

    private void setEventsHandlers(){
        board.setOnMouseClicked(this::clickedOnCell);
        przekroj.valueProperty().addListener((ObservableValue<? extends Number> observable,
                                              Number oldValue, Number newValue) -> {
            showZ = newValue.intValue()-1;
            createBoard();
        });
        //TODO Naprawić slidery
        widthSlider.valueProperty().addListener((ObservableValue<? extends Number> observable,
                                                 Number oldValue, Number newValue) -> {
            currentState.width = newValue.intValue();
            CellState[][][] tmp = new CellState[currentState.width][currentState.height][currentState.depth];
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
            currentState.height = newValue.intValue();
            CellState[][][] tmp = new CellState[currentState.width][currentState.height][currentState.depth];
            resetCells(currentState.width, currentState.height, currentState.depth, tmp);
            for (int i = 0; i < currentState.width; i++)
                for (int j = 0; j < oldValue.intValue(); j++)
                    for (int k = 0; k < currentState.depth; k++){
                        if (j < currentState.height) tmp[i][j][k] = currentState.cells[i][j][k];
                    }
            currentState.cells = tmp;
            createBoard();
        });
        depthSlider.valueProperty().addListener((ObservableValue<? extends Number> observable,
                                                 Number oldValue, Number newValue) -> {
            currentState.height = newValue.intValue();
            przekroj.setMax(currentState.depth);
            CellState[][][] tmp = new CellState[currentState.width][currentState.height][currentState.depth];
            resetCells(currentState.width, currentState.height, currentState.depth, tmp);
            for (int i = 0; i < currentState.width; i++)
                for (int j = 0; j < currentState.height; j++)
                    for (int k = 0; k < oldValue.intValue(); k++){
                        if (j < currentState.height) tmp[i][j][k] = currentState.cells[i][j][k];
                    }
            currentState.cells = tmp;
            createBoard();
        });
    }
}
