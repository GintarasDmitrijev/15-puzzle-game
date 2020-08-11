package com.avalanche.gintaras.puzzle.model;

import javafx.geometry.Pos;

/**
 * Holds all needed data for tile in a game.
 *
 * @author gintaras
 */
public class Tile {

    /**
     * The value of the tile.
     */
    private Integer value;

    /**
     * Tile position info.
     */
    private Position position;

    public Tile() {
    }

    public Tile(Integer value, Position position) {
        this.value = value;
        this.position = position;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
