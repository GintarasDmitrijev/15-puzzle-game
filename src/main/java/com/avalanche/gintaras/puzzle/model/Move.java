package com.avalanche.gintaras.puzzle.model;

/**
 * Represents move of the tile in a puzzle game.
 *
 * @author gintaras
 */
public class Move {

    /**
     * Tile to move.
     */
    private Tile tile;

    /**
     * The new position of the tile.
     */
    private Position newPosition;

    public Move() {
    }

    public Move(Tile tile, Position newPosition) {
        this.tile = tile;
        this.newPosition = newPosition;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Position getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Position newPosition) {
        this.newPosition = newPosition;
    }
}
