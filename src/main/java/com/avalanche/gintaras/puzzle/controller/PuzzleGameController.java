package com.avalanche.gintaras.puzzle.controller;

import com.avalanche.gintaras.puzzle.model.Move;
import com.avalanche.gintaras.puzzle.repository.GamePuzzleRepository;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller that serves api requests.
 *
 * @author gintaras
 */
@Controller
@RequestMapping("puzzle")
public class PuzzleGameController {

    private Gson gson = new Gson();

    @GetMapping(value = "/start", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> startGame(@RequestParam(name = "players") int numberOfPlayers) {
        GamePuzzleRepository.startGame(numberOfPlayers);

        return new ResponseEntity<>(gson.toJson(GamePuzzleRepository.playersMap), HttpStatus.OK);
    }

    @PostMapping(value = "/move",
            consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8")
    public ResponseEntity<String> handleMove(@RequestBody Map<String, Move> movesMap) {

        GamePuzzleRepository.handleMoves(movesMap);

        return new ResponseEntity<>(gson.toJson(GamePuzzleRepository.playersMap), HttpStatus.CREATED);
    }

    @GetMapping(value = "/counter", produces = "application/json; charset=utf-8")
    @ResponseBody
    public ResponseEntity<String> getCounter() {
        return new ResponseEntity<>(gson.toJson(GamePuzzleRepository.getCounter()), HttpStatus.OK);
    }

}

