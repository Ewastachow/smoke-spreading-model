package com.edu.agh.kis.automaton.gui.toolbox.controllers;

import com.edu.agh.kis.automaton.core.Smoke;
import com.edu.agh.kis.automaton.gui.AutomatonGUISource;
import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.Smoke2DController;
import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.Smoke3DController;
import com.edu.agh.kis.automaton.gui.smoke_simulation.controllers.SmokeController;
import com.edu.agh.kis.automaton.gui.toolbox.views.ToolBoxControlView;

public class ToolBoxControlController extends ToolBoxController{

    private SmokeController smoke;
    private Smoke2DController smoke2D;
    private Smoke3DController smoke3D;

    private Smoke automaton;

    public SmokeController getSmoke() {
        return smoke;
    }

    public Smoke2DController getSmoke2D() {
        return smoke2D;
    }

    public Smoke3DController getSmoke3D() {
        return smoke3D;
    }

    public ToolBoxControlController(int x, int y, int z) {
        setToolBoxView(new ToolBoxControlView(z));
        automaton = new Smoke(x,y,z);
        smoke2D = new Smoke2DController(x,y,z);
        smoke3D = new Smoke3DController(x,y,z);
        smoke = smoke2D;
    }

    public void onSubSceneChange(){
        if(smoke.getClass() == Smoke2DController.class){
            //TODO dezaktywacja slidera
            smoke = smoke3D;
        }
        else{
            smoke = smoke2D;
            //TODO aktywacja slidera
        }
        //TODO co jeszcze zmienić????
    }
}
