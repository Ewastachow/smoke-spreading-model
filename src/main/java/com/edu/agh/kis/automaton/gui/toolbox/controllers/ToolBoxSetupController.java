package com.edu.agh.kis.automaton.gui.toolbox.controllers;

import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxSetupView;
import javafx.scene.control.Button;

public class ToolBoxSetupController extends ToolBoxController{
    public ToolBoxSetupController() {
        setToolBoxView(new ToolBoxSetupView());
//        setBeginButtonAction(((ToolBoxSetupView)getToolBoxView()).getAcceptButton());
    }

//    private void setBeginButtonAction(Button button){
//        button.setOnAction(e ->{
//            //TODO - usunac linie nizej
//            //TODO to powinno byc chyba utworzone w AppController żeby móc podmienić referencje
//            button.setText("Lama");
//            //TODO Implement, pobieramy wartości z pól do wpisywnia i ustawiamy nowy obiekt
//        });
//    }
}
