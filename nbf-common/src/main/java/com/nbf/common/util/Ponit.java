package com.nbf.common.util;

/**
 * Created by Administrator on 2016/2/27 0027.
 */
public class Ponit {
    private int x;
    private int y;

    public Ponit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Ponit() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Ponit{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ponit ponit = (Ponit) o;

        if (x != ponit.x) return false;
        if (y != ponit.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
