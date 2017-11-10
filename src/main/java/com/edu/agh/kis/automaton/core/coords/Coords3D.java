package com.edu.agh.kis.automaton.core.coords;

public class Coords3D implements CellCoordinates, Comparable<Coords3D>  {

    private int x;
    private int y;
    private int z;

    public Coords3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coords3D coords3D = (Coords3D) o;

        if (x != coords3D.x) return false;
        if (y != coords3D.y) return false;
        return z == coords3D.z;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + z;
        return result;
    }

    @Override
    public String toString() {
        return "Coords3D{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public int compareTo(Coords3D c) {
        if (this.x == c.x && this.y == c.y && this.z == c.z)
            return 0;
        else if (this.x > c.x)
            return 1;
        else if (this.x == c.x && this.y > c.y)
            return 1;
        else if (this.x == c.x && this.y == c.y && this.z > c.z)
            return 1;
        else
            return -1;
    }
}
