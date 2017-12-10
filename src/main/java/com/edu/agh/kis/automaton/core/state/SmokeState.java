package com.edu.agh.kis.automaton.core.state;

import com.edu.agh.kis.automaton.core.neighborhood.SmokeDirection;

public class SmokeState implements CellState{

    private IsSmoked isSmoked;
    private Integer temperature;

    public SmokeState(IsSmoked isSmoked, Integer temperature) {
        this.isSmoked = isSmoked;
        this.temperature = temperature;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public IsSmoked getIsSmoked() {
        return isSmoked;
    }

    public void setIsSmoked(IsSmoked isSmoked) {
        this.isSmoked = isSmoked;
    }


}


