package com.edu.agh.kis.automaton.core.state;

public class CellState {
    private double temp;
    private CellType cellType;

    public CellState(double temperature) {
        this.temp = temperature;
        cellType = CellType.AIR;
    }

    public CellState(CellType cellType){
        this.cellType = cellType;
        switch (cellType){
            case AIR: temp = 20;
            case BARRIER: temp = 20;
            case FIRE_SOURCE: temp = 300;
        }
    }

    public double getTemp() {
        return temp;
    }

    public CellType getCellType() {
        return cellType;
    }

    public boolean getIsSmoked() {
        return cellType.equals(CellType.AIR) && temp > 70;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CellState cellState = (CellState) o;

        if (Double.compare(cellState.temp, temp) != 0) return false;
        return cellType == cellState.cellType;
    }

    @Override
    public int hashCode() {
        int result;
        long temp1;
        temp1 = Double.doubleToLongBits(temp);
        result = (int) (temp1 ^ (temp1 >>> 32));
        result = 31 * result + (cellType != null ? cellType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CellState{" +
                "temp=" + temp +
                ", cellType=" + cellType +
                '}';
    }
}


