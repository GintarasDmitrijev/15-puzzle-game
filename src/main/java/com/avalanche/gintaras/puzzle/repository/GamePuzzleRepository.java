package com.avalanche.gintaras.puzzle.repository;

import com.avalanche.gintaras.puzzle.model.Move;
import com.avalanche.gintaras.puzzle.model.Position;
import com.avalanche.gintaras.puzzle.model.Tile;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Puzzle game repository. Due to no any storage is needed, it servers both as repository and service.
 *
 * @created 06/08/2020 - 21:23
 * @author gintaras
 */

public class GamePuzzleRepository {

    public static Map<String, AtomicInteger> countersMap = new HashMap<>();
    public static Map<String, List<List<Tile>>> playersMap = new HashMap<>();

    public static void startGame(int numberOfPlayers) {
        for (int i=0; i < numberOfPlayers; i++) {
            String key = "player" + (i+1);
            playersMap.put(key, shuffleSquareList(initPlayerSquareList()));
            countersMap.put(key, new AtomicInteger(0));
        }
    }

    public static void handleMoves(Map<String, Move> movesMap) {
        for (String player: movesMap.keySet()) {
            handleMove(player, movesMap.get(player));
        }
    }

    private static List<List<Tile>> initPlayerSquareList() {
        List<List<Tile>> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            result.add(new ArrayList());
        }

        int value = 1;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++, value++) {
                result.get(i).add(j, new Tile(value, new Position(i + 1, j + 1)));
            }
        }
        return result;
    }

    private static List<List<Tile>> shuffleSquareList(List<List<Tile>> puzzleSquareList) {
        puzzleSquareList.stream().forEach(tiles -> Collections.shuffle(tiles));
        Collections.shuffle(puzzleSquareList);

        recalculatePosition(puzzleSquareList);

        return puzzleSquareList;
    }

    private static void handleMove(String player, Move move) {
        List<List<Tile>> playersPuzzleSquareList = playersMap.get(player);
        validateMove(move);
        // this logic could be optimized: just find two tiles by their values and swap positions
        Tile oldTileOnNewPosition = playersPuzzleSquareList.stream()
                .flatMap(Collection::stream)
                .filter(tile1 ->  tile1.getPosition().equals(move.getNewPosition()))
                .findAny()
                .orElse(null);

        Tile targetValueToMove = playersPuzzleSquareList.stream()
                .flatMap(Collection::stream)
                .filter(tile1 ->  tile1.getValue().equals(move.getTile().getValue()))
                .findAny()
                .orElse(null);

        playersPuzzleSquareList.get(targetValueToMove.getPosition().getxPos() - 1)
                .add(targetValueToMove.getPosition().getyPos(), oldTileOnNewPosition);
        playersPuzzleSquareList.get(targetValueToMove.getPosition().getxPos() - 1).remove(targetValueToMove);


        playersPuzzleSquareList.get(oldTileOnNewPosition.getPosition().getxPos() -1)
                .add(oldTileOnNewPosition.getPosition().getyPos() - 1, move.getTile());
        playersPuzzleSquareList.get(oldTileOnNewPosition.getPosition().getxPos() -1).remove(oldTileOnNewPosition);

        recalculatePosition(playersPuzzleSquareList);

        countersMap.get(player).getAndIncrement();
    }

    private static void recalculatePosition(List<List<Tile>> puzzleSquareList) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                puzzleSquareList.get(i).get(j).setPosition(new Position(i + 1, j + 1));
            }
        }
    }

    private static void validateMove(Move move) {
        Position position = move.getNewPosition();
        if (IntStream.rangeClosed(1, 4).noneMatch(x -> x == position.getxPos())
                || IntStream.rangeClosed(1, 4).noneMatch(x -> x == position.getyPos())) {

            throw new PuzzleMoveException("New position is not a valid position!");
        }
    }

    public static Map<String, AtomicInteger> getCounter() {
        return countersMap;
    }
}
