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

```
_ _ _ _ _
_ # _ # _
_ _ F _ _
_ # _ # _
_ _ _ _ _
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
            "player": 1,
            "x": 0,
            "y": 0
        },
        {
            "player": 2,
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
