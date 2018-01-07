package threadingtest;

import java.util.ArrayList;
import java.util.List;

public class ListNumber {
    List<Integer> cells;

    public void setCells(List<Integer> cells) {
        this.cells = cells;
    }

    public ListNumber(List<Integer> cells) {
        this.cells = cells;
    }

    public ListNumber() {
    }

    public ListNumber nextState(){
        ListNumber listNumber = new ListNumber();
        List<Integer> newList = new ArrayList<>();
        for(Integer i: cells){
            newList.add(i);
        }
        listNumber.setCells(newList);
        return listNumber;
    }

    @Override
    public String toString() {
        return "ListNumber{" +
                "cells=" + cells +
                '}';
    }
}
