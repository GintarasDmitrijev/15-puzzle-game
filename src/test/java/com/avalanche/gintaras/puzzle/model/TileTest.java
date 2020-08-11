package com.avalanche.gintaras.puzzle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JUnit test for {@link com.avalanche.gintaras.puzzle.model.Tile}.
 *
 * @created 11/08/2020 - 16:32
 * @author gintaras
 */

class TileTest {

    private static final int VALUE_15 = 15;
    private static final int POSITION_1 = 1;

    @Test
    void testConstructor() {
        Tile tile = new Tile(VALUE_15, new Position(POSITION_1,POSITION_1));
        assertNotNull(tile, "Tile should not be null");
        assertNotNull(tile.getPosition(), "Position should not be null");
        assertEquals(VALUE_15, tile.getValue(), "Value of tile should match");
        assertEquals(POSITION_1, tile.getPosition().getxPos(), "Position X of tile should match");
        assertEquals(POSITION_1, tile.getPosition().getyPos(), "Position Y of tile should match");

    }

}