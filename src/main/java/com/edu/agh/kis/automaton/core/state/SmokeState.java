package com.edu.agh.kis.automaton.core.state;

import com.edu.agh.kis.automaton.core.neighborhood.SmokeDirection;

public class SmokeState implements CellState{

    private IsSmoked isSmoked;
    private SmokeDirection smokeDirection;
    private Integer temperature;

    public SmokeState(IsSmoked isSmoked, SmokeDirection smokeDirection, Integer temperature) {
        this.isSmoked = isSmoked;
        this.smokeDirection = smokeDirection;
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

    public SmokeDirection getSmokeDirection() {
        return smokeDirection;
    }

    public void setIsSmoked(IsSmoked isSmoked) {
        this.isSmoked = isSmoked;
    }

    public void setSmokeDirection(SmokeDirection smokeDirection) {
        this.smokeDirection = smokeDirection;
    }



}


