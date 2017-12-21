package com.edu.agh.kis.automaton.gui;

import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.Smoke2DController;
import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.Smoke3DController;
import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.SmokeController;
import com.edu.agh.kis.automaton.gui.toolbox.controllers.ToolBoxControlController;
import com.edu.agh.kis.automaton.gui.toolbox.controllers.ToolBoxController;
import com.edu.agh.kis.automaton.gui.toolbox.controllers.ToolBoxSetupController;
import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxSetupView;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AppController extends Application{

    AppView appView;
    ToolBoxController toolBox;
    // Gdzie dać atomaton GUI source????


    @Override
    public void start(Stage primaryStage) throws Exception {
        appView = new AppView();
        toolBox = new ToolBoxSetupController();
        //TODO te 3 niżej trzeba zrobić przy zmianie toolboxa w toolbox chyba - np w konstruktorze tego nowego??? nw ;//
        //smoke2D = new Smoke2DController();
        //smoke3D = new Smoke3DController();
        //smoke = smoke2D;
        setToolBox();
        setSetupButtonAction((ToolBoxSetupView)toolBox.getToolBoxView());

//        appView.borderPane.setRight(smoke.getSmokeView().getSubScene());

        // toolbox -> 400x600
        // smoke -> 600x600

        //TODO Ogólnie to zrobić coś takiego, że na początku mamy sam toolBox z ustalaniem wymiarów pomieszczenia, np. w "m"
        //TODO Zczytujemy to i na tej podstawie dobiero tworzymy 2D i 3D view i kontrolery
        //TODO i czyścimy toolbox i dodajemy nowe rzeczy już do obsługi dla podanych wymiarów


        primaryStage.setTitle("Smoke Spreading Model");
        primaryStage.setScene(appView.scene);
        primaryStage.show();
    }

    private void setSetupButtonAction(ToolBoxSetupView toolBoxSetupView){
        toolBoxSetupView.getAcceptButton().setOnAction(e -> {
            String xString = toolBoxSetupView.getxAmongField().toString();
            String yString = toolBoxSetupView.getyAmongField().toString();
            String zString = toolBoxSetupView.getzAmongField().toString();
            double x,y,z;
            try{
                x = Double.parseDouble(xString);
                y = Double.parseDouble(yString);
                z = Double.parseDouble(zString);
            }catch (Exception ex){
                toolBoxSetupView.getAcceptButton().setText("Not right value inside");
                //TODO wywalić to, dać return;
                x=10;
                y=10;
                z=10;
            }
            int xAmong = (new Double(10*x)).intValue();
            int yAmong = (new Double(10*y)).intValue();
            int zAmong = (new Double(10*z)).intValue();
            smoke2D = new Smoke2DController(xAmong, yAmong, zAmong);
            smoke3D = new Smoke3DController(xAmong, yAmong, zAmong);
            smoke = smoke2D;
            toolBox = new ToolBoxControlController();
            setToolBox();
            setSmoke();

        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setToolBox(){
        appView.borderPane.setLeft(toolBox.getToolBoxView().getToolBoxPane());
    }

    private void setSmoke(){
        appView.borderPane.getChildren().clear();
        setToolBox();
        appView.borderPane.setRight(smoke.getSmokeView().getSubScene());
    }
}
