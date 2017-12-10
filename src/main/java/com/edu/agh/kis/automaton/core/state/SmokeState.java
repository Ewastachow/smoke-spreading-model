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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmokeState that = (SmokeState) o;

        if (isSmoked != that.isSmoked) return false;
        return temperature != null ? temperature.equals(that.temperature) : that.temperature == null;
    }

    @Override
    public int hashCode() {
        int result = isSmoked != null ? isSmoked.hashCode() : 0;
        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
        return result;
    }
}


