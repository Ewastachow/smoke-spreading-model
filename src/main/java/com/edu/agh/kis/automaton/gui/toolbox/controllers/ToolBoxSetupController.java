package com.edu.agh.kis.automaton.gui.toolbox.controllers;

import com.edu.agh.kis.automaton.gui.AppView;
import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxControlView;
import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxSetupView;
import javafx.scene.control.Button;

public class ToolBoxSetupController extends ToolBoxController{
    public ToolBoxSetupController(AppView appView) {
        super(appView);
        setToolBoxView(new ToolBoxSetupView());
//        setBeginButtonAction(((ToolBoxSetupView)getToolBoxView()).getAcceptButton());
    }

//
//
//    private void setSetupButtonAction(){
//        ToolBoxSetupView view = ((ToolBoxSetupView)getToolBoxView());
//        view.getAcceptButton().setOnAction(e -> {
//            double x,y,z;
//            try{
//                x = Double.parseDouble(view.getxAmongField().toString());
//                y = Double.parseDouble(view.getyAmongField().toString());
//                z = Double.parseDouble(view.getzAmongField().toString());
//            }catch (Exception ex){
//                view.getAcceptButton().setText("Not right value inside");
//                //TODO wywalić to, dać return; -> wymaga naprawy tego w bloku try bo nie rzutuje
//                x=1;
//                y=1;
//                z=1;
//            }
//            int xAmong = (new Double(10*x)).intValue();
//            int yAmong = (new Double(10*y)).intValue();
//            int zAmong = (new Double(10*z)).intValue();
//            toolBox = new ToolBoxControlController(xAmong, yAmong, zAmong);
//            setToolBox();
//            setSubScene();
//            setChangeSubSceneButtonAction((ToolBoxControlView)toolBox.getToolBoxView());
//        });
//    }
//
//    private void setToolBox(){
//        appView.borderPane.setLeft(toolBox.getToolBoxView().getToolBoxPane());
//    }
//
//    private void setSubScene(){
//        //TODO Czy to jest poprawcne??
//        appView.borderPane.getChildren().clear();
//        setToolBox();
//        appView.borderPane.setRight(((ToolBoxControlController)toolBox).getSmoke().getSmokeView().getSubScene());
//    }
//
//    private void setChangeSubSceneButtonAction(ToolBoxControlView toolBoxControlView){
//        toolBoxControlView.getChangeSubScene().setOnAction(e ->{
//            ((ToolBoxControlController)toolBox).onSubSceneChange();
//            setSubScene();
//        });
//
//    }

//    private void setBeginButtonAction(Button button){
//        button.setOnAction(e ->{
//            //TODO - usunac linie nizej
//            //TODO to powinno byc chyba utworzone w AppController żeby móc podmienić referencje
//            button.setText("Lama");
//            //TODO Implement, pobieramy wartości z pól do wpisywnia i ustawiamy nowy obiekt
//        });
//    }
}
