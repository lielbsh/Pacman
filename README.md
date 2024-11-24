# PacMan Project 🎮  

Welcome to the **PacMan Project**, a fun, interactive recreation of the classic arcade game built with modern technologies. This project combines a Java-powered backend focused on Object-Oriented Programming (OOP) principles and a dynamic React frontend for a seamless gaming experience.  

![Pacman GIF](https://i.gifer.com/T7i.gif)  

---

## 🎯 Project Overview  

The **PacMan Project** is a fully functional PacMan game with an emphasis on clean architecture and efficient design.  
- **Backend**: Built with Java, employing OOP concepts to organize the game logic into modular, reusable classes.  
- **Frontend**: Developed using React, communicating with the backend via RESTful APIs.  

---

## 📊 Game Board Design  

The game board is represented as a **2D array**, with each number indicating a specific game element:  

| Number | Element         |  
|--------|-----------------|  
| `0`    | Empty Space      |  
| `1`    | Block            |  
| `2`    | Coin             |  
| `4`    | Food             |  
| `8`    | PacMan           |  
| `16`   | Ghost1           |  
| `32`   | Ghost2           |  
| `64`   | Ghost3           |  
| `128`  | Power-Up Coin    |  

This binary-based system ensures simplicity and efficient updates during gameplay.  

---

## 🚀 How to Play  

### Prerequisites  
1. Install the backend and frontend dependencies.  
2. Ensure you have Java installed for the backend and Node.js for the frontend.  

### Steps  
1. Clone the repositories for both the backend and frontend.  
2. Start the backend server (Java) and frontend development server (React).  
3. Open the game in your browser via `http://localhost:3000`.  

---

## 📷 Screenshots  

### Game in Action  
![Game Screenshot 1](https://imgur.com/mnub7gH)  
![Game Screenshot 2](https://imgur.com/cJdfmt3)  

### Gameplay Demo  
[Watch Video](https://media.canva.com/v2/files/uri:ifs%3A%2F%2FV%2FaHYrjM65hWnY2LvqSz6xyermTrOFtaJKzwPXkCrW29Y.mp4?csig=AAAAAAAAAAAAAAAAAAAAAGqsXLaJAcFaiKTDWWP4Nvv7HCfNUUl4_KCDvXr23Tpq&exp=1732456860&

---
### Class Diagram
                     +---------------------------+
                     |     PacManApplication     |
                     |---------------------------|
                     | + main(args: String[])    |
                     +---------------------------+
                               ▲
                               |
               Composition (◆) |
                               |
                               v
                     +---------------------------+
                     |           Game            |
                     |---------------------------|
                     | Attributes:               |
                     | +gamestate- enum          |
                     | +cuurentstate- gamestate  |
                     | Methods:                  |
                     | + win()                   |
                     | + gameOver()              |
                     +---------------------------+
                               ▲
                               |
               Composition (◆) |
                               |
                               v
                     +------------------------------+
                     |           Board              |
                     |------------------------------|
                     |  Attributes:                 |
                     | +numscoin -int               |
                     | +score-int                   |
                     | +step -int                   |
                     | +boardArray-int[][]          |
                     | +oldcoordinate-<int,int[]>Map|
                     | Methods:                     |
                     | + score()                    |
                     | + eat()                      |
                     | + deleteFromBoard()          |
                     | + addToBoard()               |
                     | + updateBoard()              |
                     +-------------------------------+
                                      ▲
                                      |     Composition (◆) 
              +----------------------+----------------------+           
              |                      |                      |
              |                      |                      |
              |                      |                      |
              v                      v                      v
        +----------------------+ ---------------------+ -------------------- +
        |        Food          |         Ghost        |        Pacman        |
        |----------------------| ---------------------| ---------------------|
        | Attributes:          | Attributes:          | Attributes:          |
        | + boardIndex: int    | + predator: boolean  | + predator: boolean  |
        | + coordinatesList[]  | + direction: char    | + coordinates: int[] |
        | + currentIndex: int  | Methods:             | Methods:             |
        | + isThere: boolean   | + ghost()            | + constructor()      |
        | Methods:             | + setSmartDirection()| + move()             |
        | + getCoordinates()   | + chaseHorizontal()  | + predator()         |
        | + getIsThere()       | + chaseVertical()    | + die()              |
        | + eaten()            |                      | + getCoordinates()   |
        +--------------------+ +----------------------| + isMovePossible()   |
                                          ^           | + setCoordinates()   |
                                          |           | + predator()         |
                                          |           | + die()              |
                                          |           |+ life()              |
                                          |           +----------------------+
                                          |                     ^
                                          |                     |
                                          +---------------------+
                                                      |
                                            Generalization (△)
                                                      |
                                                      ▼
                                            +-------------------------+
                                            |         Figure          |
                                            |-------------------------|
                                            | Attributes:             |
                                            | + coordinates: int[]    |
                                            | + predator: boolean    |
                                            | Methods:               |
                                            | + setCoordinates()     |
                                            | + isMovePossible(): boolean |
                                            | + predator(): boolean  |
                                            | + die()                |
                                            +-------------------------+
