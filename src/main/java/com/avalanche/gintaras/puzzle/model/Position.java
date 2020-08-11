package com.avalanche.gintaras.puzzle.model;

import java.util.Objects;

/**
 * Holds position information for the particular tile.
 *
 * @created 06/08/2020 - 20:46
 * @author gintaras
 */
public class Position {

    private Integer xPos;
    private Integer yPos;

    public Position() {
    }

    public Position(Integer xPos, Integer yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Integer getxPos() {
        return xPos;
    }

    public void setxPos(Integer xPos) {
        this.xPos = xPos;
    }

    public Integer getyPos() {
        return yPos;
    }

    public void setyPos(Integer yPos) {
        this.yPos = yPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return xPos.equals(position.xPos) &&
                yPos.equals(position.yPos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xPos, yPos);
    }
}