package com.edu.agh.kis.automaton.gui;

import com.edu.agh.kis.automaton.gui.controllers.Smoke2DController;
import com.edu.agh.kis.automaton.gui.controllers.Smoke3DController;
import com.edu.agh.kis.automaton.gui.controllers.SmokeController;
import com.edu.agh.kis.automaton.gui.toolbox.ToolBoxController;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppController extends Application{

    AppView appView;
    ToolBoxController toolBox;
    SmokeController smoke;
    Smoke2DController smoke2D;
    Smoke3DController smoke3D;
    // Gdzie dać atomaton GUI source????

    @Override
    public void start(Stage primaryStage) throws Exception {
        appView = new AppView();
        toolBox = new ToolBoxController();
        smoke2D = new Smoke2DController();
        smoke3D = new Smoke3DController();
        smoke = smoke2D;

        appView.borderPane.setLeft(toolBox.getToolBoxView().getToolBoxPane());
        appView.borderPane.setRight(smoke.getSmokeView().getSubScene());

        // toolbox -> 400x600
        // smoke -> 600x600

        //TODO Ogólnie to zrobić coś takiego, że na początku mamy sam toolBox z ustalaniem wymiarów pomieszczenia, np. w "m"
        //TODO Zczytujemy to i na tej podstawie dobiero tworzymy 2D i 3D view i kontrolery
        //TODO i czyścimy toolbox i dodajemy nowe rzeczy już do obsługi dla podanych wymiarów


        primaryStage.setTitle("Smoke Spreading Model");
        primaryStage.setScene(appView.scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
