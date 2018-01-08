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
            String xString = toolBoxSetupView.getxAmongField().getText();
            String yString = toolBoxSetupView.getyAmongField().getText();
            String zString = toolBoxSetupView.getzAmongField().getText();
            double x,y,z;
            try{
                x = Double.parseDouble(xString)*5;
                y = Double.parseDouble(yString)*5;
                z = Double.parseDouble(zString)*5;
            }catch (Exception ex){
                toolBoxSetupView.getAcceptButton().setText("Not right value inside");
                //TODO wywalić to, dać return; -> wymaga naprawy tego w bloku try bo nie rzutuje
                x=3;
                y=3;
                z=3;
            }
            int xAmong = (new Double(x)).intValue();
            int yAmong = (new Double(y)).intValue();
            int zAmong = (new Double(z)).intValue();
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
