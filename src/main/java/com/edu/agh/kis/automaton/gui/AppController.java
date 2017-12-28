package com.edu.agh.kis.automaton.gui;

import com.edu.agh.kis.automaton.gui.toolbox.controllers.ToolBoxControlController;
import com.edu.agh.kis.automaton.gui.toolbox.controllers.ToolBoxController;
import com.edu.agh.kis.automaton.gui.toolbox.controllers.ToolBoxSetupController;
import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxControlView;
import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxSetupView;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppController extends Application{

    AppView appView;
    ToolBoxController toolBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        appView = new AppView();
        toolBox = new ToolBoxSetupController();
        setToolBox();
        setSetupButtonAction((ToolBoxSetupView)toolBox.getToolBoxView());

        // toolbox -> 400x600
        // smoke -> 600x600

        primaryStage.setTitle("Smoke Spreading Model");
        primaryStage.setScene(appView.getScene());
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
                //TODO wywalić to, dać return; -> wymaga naprawy tego w bloku try bo nie rzutuje
                x=1;
                y=1;
                z=1;
            }
            int xAmong = (new Double(10*x)).intValue();
            int yAmong = (new Double(10*y)).intValue();
            int zAmong = (new Double(10*z)).intValue();
            toolBox = new ToolBoxControlController(xAmong, yAmong, zAmong);
            setToolBox();
            setSubScene();
            setChangeSubSceneButtonAction((ToolBoxControlView)toolBox.getToolBoxView());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void setToolBox(){
        appView.getBorderPane().setLeft(toolBox.getToolBoxView().getToolBoxPane());
    }

    private void setSubScene(){
        //TODO Czy to jest poprawcne??
        appView.getBorderPane().getChildren().clear();
        setToolBox();
        appView.getBorderPane().setRight(((ToolBoxControlController)toolBox).getSmoke().getSmokeView().getSubScene());
    }

    private void setChangeSubSceneButtonAction(ToolBoxControlView toolBoxControlView){
        toolBoxControlView.getChangeSubScene().setOnAction(e ->{
            ((ToolBoxControlController)toolBox).onSubSceneChange();
            setSubScene();
        });

    }

}
