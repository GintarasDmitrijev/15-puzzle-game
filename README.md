# Puzzle game restful implementation

This project implements 15 puzzle game as restful application. For the game description see 
[this wiki page](https://en.wikipedia.org/wiki/15_puzzle).

# Getting Started

### How to build and run this application

This project has everything that is needed to build the application. Just issue the following command
from the project root:

```shell script
mvnw clean install
```

After project is built, issue the following command:
```shell script
mvnw spring-boot:run
```

After application is started, the root path is:
```shell script
http://localhost:8080/puzzle
```

### Rest endpoints for the game
This API provides the following endpoints to control the flow:

|Method|Endpoint URL|Parameter  |Description|Example|
|------------|-----------|-----------|-------|-------|
|GET|puzzle/start|players    |Start the game|localhost:8080/puzzle/start?players=2   |
|POST|puzzle/move | -  |Make a move|localhost:8080/puzzle/move|
|GET|puzzle/counter|players    |Get the counter map|localhost:8080/puzzle/counter   |

### The game flow
The game engine is capable to handle multiple players at the same time. Use Postman tool to send the
requests and observe the results. To begin the game the following GET request should be used:
```shell script
localhost:8080/puzzle/start?players=2
```
The mandatory parameter ```players``` specifies the number of players. This request returns the JSON response
with map of randomly shuffled square lists for each player. Each list contains 16 tiles with theirs positions (where the tile 
with value 16 represents the empty space for the tile):
```json
{
    "player1": [
        [
            {
                "value": 8,
                "position": {
                    "xPos": 1,
                    "yPos": 1
                }
            },
            {
                "value": 5,
                "position": {
                    "xPos": 1,
                    "yPos": 2
                }
            },
      ...
     ],
    "player2": [
        [
            {
                "value": 11,
                "position": {
                    "xPos": 1,
                    "yPos": 1
                }
            },
            {
                "value": 9,
                "position": {
                    "xPos": 1,
                    "yPos": 2
                }
            },
        ...
     ]
    }
```

Each player should observe it's current square "board" and decide on the next move. The move itself is performed
by POST request:
```shell script
localhost:8080/puzzle/move
```
with JSON payload that specifies the tile to be moved for each player:
```json
{
    "player1": {
     "tile": {
            "value": 15,
            "position": {
                "xPos": 4,
                "yPos": 3
            }
     },
     "newPosition": {
         "xPos": 4,
         "yPos": 2
     }
    },
     "player2": {
     "tile": {
            "value": 15,
            "position": {
                "xPos": 4,
                "yPos": 3
            }
     },
     "newPosition": {
         "xPos": 4,
         "yPos": 2
     }
    }
}
```
Currently the engine validates only the ranges of positions to be in the range of 1...4. Despite the engine itself 
currently allows to swap any tiles, from the point of view of game logic it's recommended to make moves of tiles that are 
adjasent to empty tile (value 16). Also the game engine doesn't check the whether a player achieved the end state
of the game, so this is a responsibility of the particular player. 

The application provides also the possibility to get the counter values for each player, i.e. the number of moves.
For this purpose this request sould be utilized:
```shell script
localhost:8080/puzzle/counter
```

It returns the map of counter values for each player:
```json
{
    "player1": 1,
    "player2": 1
}
```