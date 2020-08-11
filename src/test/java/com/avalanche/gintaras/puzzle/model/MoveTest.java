package com.avalanche.gintaras.puzzle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * JUnit test for {@link com.avalanche.gintaras.puzzle.model.Move}.
 *
 * @created 10/08/2020 - 13:41
 * @author gintaras
 */

public class MoveTest {

    private static final int VALUE_15 = 15;
    private static final int POSITION_1 = 1;
    private static final int POSITION_2 = 2;


    @Test
    void testConstructor() {
        Move move = new Move(new Tile(VALUE_15, new Position(POSITION_1,POSITION_1)),
                new Position(POSITION_2,POSITION_2));
        assertNotNull(move.getTile(), "Tile should not be null");
        assertNotNull(move.getNewPosition(), "New position should not be null");
        assertEquals(VALUE_15, move.getTile().getValue(), "Value of tile should match");
        assertEquals(POSITION_1, move.getTile().getPosition().getxPos(), "Position X of tile should match");
        assertEquals(POSITION_1, move.getTile().getPosition().getyPos(), "Position Y of tile should match");
        assertEquals(POSITION_2, move.getNewPosition().getxPos(), "New Position X of tile should match");
        assertEquals(POSITION_2, move.getNewPosition().getyPos(), "New Position Y of tile should match");

    }
}