package com.avalanche.gintaras.puzzle.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * JUnit test for {@link com.avalanche.gintaras.puzzle.model.Position}.
 * @created 11/08/2020 - 16:17
 * @author gintaras
 */

class PositionTest {

    private static final int POSITION_1 = 1;
    private static final int POSITION_2 = 2;

    @Test
    void testConstructor() {
        Position position = new Position(POSITION_1,POSITION_1);
        assertNotNull(position, "Position object should not be null");
        assertEquals(POSITION_1, position.getxPos(), "position x value should match");
        assertEquals(POSITION_1, position.getyPos(), "position y value should match");

    }

    @Test
    void testEquals() {
        Position position1 = new Position(POSITION_1,POSITION_1);
        Position position2 = new Position(POSITION_1,POSITION_1);
        Position position3 = new Position(POSITION_2,POSITION_2);
        assertTrue(position1.equals(position2), "Position objects should be equal");
        assertFalse(position1.equals(position3), "Position objects should not be equal");
    }
}