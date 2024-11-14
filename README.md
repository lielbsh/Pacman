# PacMan Project

This is a Java-based PacMan game project. The project includes multiple classes to represent the game logic, board, PacMan, ghosts, and other game components. The game can be started, stopped, and tracks the player's score and remaining lives. This README explains the classes, methods, and attributes used in the project.

## Classes Overview

### 1. **PacManApplication**
   - **Purpose**: The main application class where the game starts.
   - **Main Method**: 
     - `public static void main(String[] args)`: Initializes and launches the game by creating instances of the `Game` and other necessary components.

### 2. **Game**
   - **Purpose**: Manages the game state, player lives, and score.
   - **Methods**:
     - `set()`: Starts a new game, initializing the board, lives, score, and positions of PacMan and ghosts.
     - `gameOver()`: Ends the game when conditions are met (e.g., no lives left, player wins or loses).
   - **Attributes**:
     - `life` - Tracks the remaining lives of the player.
     - `score` - Tracks the score based on collected coins.
     - `state` - Enum type representing the game state with possible values: `ON`, `OFF`, `WIN`, and `LOSS`.

### 3. **Board**
   - **Purpose**: Represents the game board and manages objects' positions on the board.
   - **Methods**:
     - To be implemented (such as rendering the board, checking for collisions, etc.).
   - **Attributes**:
     - `boardArray` - A 2D array representing the game layout:
       - `0` - Empty space
       - `1` - Block
       - `2` - Coin
       - `3` - Food
       - `4` - PacMan
       - `5` - Ghost
     - `nextMove` - Represents the next move for PacMan with possible values: 'R' (Right), 'L' (Left), 'U' (Up), 'D' (Down).
     - `ghost1`, `ghost2`, `ghost3` - Instances of `Ghost`.
     - `pacman` - An instance of `Pacman`.

### 4. **Ghost (implements Figure)**
   - **Purpose**: Represents a ghost character that can move around the board and interact with PacMan.
   - **Methods**:
     - `ghost()`: Initializes or controls ghost behavior.
     - `move()`: Determines the movement of the ghost on the board.
   - **Attributes**:
     - `color` - Represents the color of the ghost, useful for differentiation.

### 5. **Pacman (implements Figure)**
   - **Purpose**: Represents the main character, PacMan, which the player controls.
   - **Methods**:
     - `cot’or()`: Initializes or sets the attributes of PacMan.
     - `move()`: Handles PacMan's movement across the board.
   - **Attributes**:
     - `color` - Represents PacMan's color.

### 6. **Figure (Abstract Class)**
   - **Purpose**: An abstract class implemented by both `Ghost` and `Pacman`, which defines shared behavior for all game figures.
   - **Methods**:
     - `move()`: Abstract method that dictates movement, implemented by subclasses.
     - `preditor()`: Determines if the figure can act as a predator, affecting PacMan.
   - **Attributes**:
     - `preditor` - Boolean attribute that specifies whether the figure is in a predator mode (e.g., when PacMan can eat ghosts).

---

## Game Enum
- `state` Enum within `Game` class:
  - **ON** - Game is active.
  - **OFF** - Game is inactive.
  - **WIN** - Player has won the game.
  - **LOSS** - Player has lost the game.

---

This project provides the framework for a classic PacMan game where players control PacMan, collect coins and food, and avoid ghosts. Additional functionality, such as game rendering, collision detection, and movement logic, will be built on top of these class structures.
