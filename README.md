# PacMan Project ![Pacman GIF](https://i.gifer.com/T7i.gif)  

This is a Java-based PacMan game project that includes multiple classes representing the game logic, board, PacMan, ghosts, and other essential components. The game allows starting, stopping, and tracking the player's score and remaining lives. This README explains the classes, methods, and attributes used in the project.

---

## Classes Overview

### 1. **PacManApplication**

- **Purpose**: The main application class where the game starts.
- **Main Method**:
  - `public static void main(String[] args)`: Initializes and launches the game by creating instances of the `Game` class and other necessary components.

---

### 2. **Game**

- **Purpose**: Manages the overall game state and controls the board.
- **Methods**:
  - `Game()`: Initializes a new game, setting up the board, lives, score, and starting positions of PacMan and the ghosts.
  - `gameOver()`: Ends the game based on specific conditions, such as losing all lives or meeting win/lose criteria.
- **Attributes**:
  - `state` - Enum type representing the game state with possible values: `ON`, `OFF`, `WIN`, `LOSS`.
  - `board` - An instance of the `Board` class that manages the layout and interactions.

---

### 3. **Board**

- **Purpose**: Represents the game board and handles object positions and interactions.
- **Methods**:
  - `life()`: Tracks the remaining lives of the player.
  - `score()`: Updates the score based on collected items like coins.
  - Additional methods for rendering the board, collision detection, and managing objects will be implemented.
- **Attributes**:
  - `boardArray` - A 2D array representing the game layout:
    - `0` - Empty space
    - `1` - Block
    - `2` - Coin
    - `4` - Food
    - `8` - PacMan
    - `16` - Ghost1
    - `32` - Ghost2
    - `64` - Ghost3
    - `128` - power-up coin
  - `nextMove` - Maps PacMan's next move (e.g., `'R'` for Right, `'L'` for Left).
  - `ghost1`, `ghost2`, `ghost3` - Instances of the `Ghost` class.
  - `pacman` - An instance of the `Pacman` class.
  - `eat`
  - `deleteFromBoard`
  - `addToBoard`
  - `updateboard`

---

### 4. **Ghost (implements Figure)**

- **Purpose**: Represents a ghost character that moves around the board and interacts with PacMan.
- **Methods**:
  - `ghost()`: Initializes or updates the ghost's behavior.
  - `setSmartDirection()`: Determines the ghost's movement on the board and changes the nexstStep.
  - `chaseHorizontal` and `chaseVertical`: helper's functions.
- **Attributes**:
  - `predator` - Boolean indicating if the ghost is in predator mode (i.e., can harm PacMan).
  - `direction` - char

---

### 5. **Pacman (implements Figure)**

- **Purpose**: Represents the player-controlled character, PacMan.
- **Methods**:
  - `constructor()`: Initializes PacMan's attributes.
  - `move()`: Handles PacMan's movement based on player input.
- **Attributes**:
  - `predator` - Boolean indicating if PacMan is in predator mode (e.g., when powered-up to eat ghosts).

---

### 6. **Figure (Abstract Class)**

- **Purpose**: Abstract class for shared behavior between `Ghost` and `Pacman`.
- **Methods**:
  - `setCordinates()`: Abstract method defining movement, implemented in subclasses.
  - `isMovePossible()`: boolean
  - `predator()`: Determines if the figure acts as a predator in the current state.
  - `die`
- **Attributes**:
  - `coordinates`
  - `predator` - Boolean indicating the predator state of the figure.

---

## Game Enum

- **Purpose**: Defines the possible states of the game.
- **Values**:
  - `ON` - The game is active.
  - `OFF` - The game is inactive.
  - `WIN` - The player has won.
  - `LOSS` - The player has lost.

---

## Next Steps

Future development will focus on implementing:

- Game rendering and graphical output.
- Detailed movement logic for PacMan and ghosts.
- Collision detection and interactions.
- Sound effects and animations.
