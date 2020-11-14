# Bug Wars Game Engine
The Bug Wars Game Engine contains an Interpreter for the Bug Wars AI Script language.  During gameplay, the Game Engine manages the Program Counter for each bug, executes commands, and processes results.

## Data Models
Description of the data models used in the requests and responses for the Game Engine API.

### Map
Represents the terrain of the arena.  In this example, a 5x5 map is passed.

#### Legend
`#` = wall
`_` = open space
`F` = food
`1` = SpawnPoint for species #1
`2` = SpawnPoint for species #2

```
1 _ _ _ _
_ # _ # _
_ _ F _ _
_ # _ # _
_ _ _ _ 2
```

```json
{
    "rows": [
        ["OPEN", "OPEN", "OPEN", "OPEN", "OPEN"],
        ["OPEN", "WALL", "OPEN", "WALL", "OPEN"],
        ["OPEN", "OPEN", "OPEN", "OPEN", "OPEN"],
        ["OPEN", "WALL", "OPEN", "WALL", "OPEN"],
        ["OPEN", "OPEN", "OPEN", "OPEN", "OPEN"]
    ],
    "spawns": [
        {
            "team": 1,
            "x": 0,
            "y": 0
        },
        {
            "team": 2,
            "x": 4,
            "y": 4
        }
    ],
    "food": [
        {
            "x": 2,
            "y": 2
        }
    ]
}
```
### Game
The Game model has all starting information for the game to be executing.  This includes:
* Map Terrain
* Starting locations of bugs (Spawn Points)
* Object Code used to execute the given bug
* Starting locations of food
```json
{
    "map": {
        "rows": ["(see above)"],
        "spawns": [ { "team": 1, "x":0, "y":0}, {"team": 2, "x":4, "y":4}],
        "food": { "x":"2", "y":"2"}
    },
    "bugs": [
        {
            "team": 1,
            "code": "23 0 1 3 5 0 3 2 4 21 3 4 5 1 2 3 8"
        },
        {
            "team": 2,
            "code": "20 4 5 2 3 5 5 2 1 3 5 21 4 5 2 4"
        }
    ]
}
```

## POST /games
Launch a new game and get an array of GameState.

### Request Body
```json
{
"map": {
board: [],
[],
[],
[],
[]
}
}
```


## GET /games/{id}
View a replay of game with `id`




## GET /games
Get a list of Played Games
