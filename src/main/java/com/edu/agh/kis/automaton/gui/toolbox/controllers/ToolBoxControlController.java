package com.edu.agh.kis.automaton.gui.toolbox.controllers;

import com.edu.agh.kis.automaton.core.Automaton;
import com.edu.agh.kis.automaton.core.Smoke;
import com.edu.agh.kis.automaton.core.coords.Coords3D;
import com.edu.agh.kis.automaton.core.neighborhood.VonNeumanNeighborhood3Dim;
import com.edu.agh.kis.automaton.core.state.CellState;
import com.edu.agh.kis.automaton.core.stateFactory.GeneralStateFactory;
import com.edu.agh.kis.automaton.gui.AppView;
import com.edu.agh.kis.automaton.gui.AutomatonGUISource;
import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.Smoke2DController;
import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.Smoke3DController;
import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.SmokeController;
import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxControlView;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;

import java.util.Map;
import java.util.TreeMap;

public class ToolBoxControlController extends ToolBoxController{

    private SmokeController smoke;
    private Smoke2DController smoke2D;
    private Smoke3DController smoke3D;

    private Automaton automaton;

    private Timeline timeline = new Timeline();

    public SmokeController getSmoke() {
        return smoke;
    }

    public Smoke2DController getSmoke2D() {
        return smoke2D;
    }

    public Smoke3DController getSmoke3D() {
        return smoke3D;
    }

    public ToolBoxControlController(AppView appView, int x, int y, int z) {
        super(appView);
        setToolBoxView(new ToolBoxControlView(z));
        automaton = createAutomaton(x,y,z);
        smoke2D = new Smoke2DController(x,y,z,automaton);
        smoke3D = new Smoke3DController(x,y,z);
        smoke = smoke2D;
        setTimeline();
        setControlPanelOnAction();
        setupSlider();
    }

    public void onSubSceneChange(){
        if(smoke.getClass() == Smoke2DController.class){
            //TODO dezaktywacja slidera, albo nie, zmieniajmy pozycje showZ w smoke2
            smoke = smoke3D;
        }
        else{
            smoke = smoke2D;
            //TODO aktywacja slidera
        }
        //TODO co jeszcze zmienić????
    }

    private void setControlPanelOnAction(){
        ((ToolBoxControlView)getToolBoxView()).getNextButton().setOnAction(e -> nextStep());
        ((ToolBoxControlView)getToolBoxView()).getNextButton().setOnAction(e -> timeline.play());
        ((ToolBoxControlView)getToolBoxView()).getStopButton().setOnAction(e -> timeline.stop());
        ((ToolBoxControlView)getToolBoxView()).getResetButton().setOnAction(e -> resetStep());
    }

    private void nextStep(){
        automaton = automaton.nextstate();
        smoke2D.setAutomaton(automaton);
        smoke.putMapIntoTab(automaton);
    }
    private void resetStep(){
        timeline.stop();
        //TODO RESET CELL -> nowy obiekt automatonu, putToTab Dla smoke2D i smoke3D
    }

    private void setTimeline(){
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(100),
                event -> nextStep()));
    }

    private void setupSlider(){
        ((ToolBoxControlView)getToolBoxView()).getSlider().valueProperty().addListener((ObservableValue<? extends Number> observable,
                                                                                        Number oldValue, Number newValue) -> {
            smoke2D.setShowZ(newValue.intValue()-1);
            smoke2D.putMapIntoTab(automaton);
        });
    }

    private Automaton createAutomaton(int x, int y, int z){
//        Automaton automaton = new Smoke(x,y,z);
//        for (int i = 0; i < x; i++)
//            for (int j = 0; j < y; j++)
//                for (int k = 0; k < z; k++)
//                    automaton.setNewCellState(new Coords3D(i, j, k), new CellState(20));
        Automaton automaton = new Smoke();
        Map<Coords3D, CellState> tmp = new TreeMap<>();
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                for (int k = 0; k < z; k++)
                    tmp.put(new Coords3D(i, j,k), new CellState(20));
        automaton = new Smoke(tmp,
                new VonNeumanNeighborhood3Dim(1, y, x, z),
                new GeneralStateFactory(automaton.getCells()),
                x, y, z);
        //TODO coś jest pojebane z kolejnością x y z
        return automaton;
    }
}
