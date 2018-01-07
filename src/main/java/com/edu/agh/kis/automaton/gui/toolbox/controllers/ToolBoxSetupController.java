package com.edu.agh.kis.automaton.gui.toolbox.controllers;

import com.edu.agh.kis.automaton.gui.AppView;
import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxSetupView;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextFormatter;
import javafx.util.StringConverter;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class ToolBoxSetupController extends ToolBoxController{
//    public ToolBoxSetupController(AppView appView) {
//        super(appView);
//        setToolBoxView(new ToolBoxSetupView());
//    }
    public ToolBoxSetupController(){
        setToolBoxView(new ToolBoxSetupView());
    }

    private void setupTextFieldsFormatted(){
        ToolBoxSetupView tbsv = (ToolBoxSetupView)getToolBoxView();


        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?");

        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c ;
            } else {
                return null ;
            }
        };

        StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0 ;
                } else {
                    return Double.valueOf(s);
                }
            }


            @Override
            public String toString(Double d) {
                return d.toString();
            }
        };

        TextFormatter<Double> textFormatter = new TextFormatter<>(converter, 0.0, filter);
        tbsv.getxAmongField().setTextFormatter(textFormatter);
        tbsv.getyAmongField().setTextFormatter(textFormatter);
        tbsv.getzAmongField().setTextFormatter(textFormatter);

        textFormatter.valueProperty().addListener((ObservableValue<? extends Double> obs, Double oldValue, Double newValue) -> {
            System.out.println("User entered value: "+newValue.doubleValue());
        });

    }
}
