# Brick Breaker Game

A desktop Brick Breaker game built with **Java Swing**. Move the paddle to keep the ball in play, break bricks to earn points, and advance through increasingly challenging levels.

## Features

- Keyboard-controlled paddle and ball launch
- Ball collisions with walls, the paddle, and bricks
- Score tracking and three lives
- Multiple levels with increasing ball speed
- Game-over display

## Technologies

- Java
- Java Swing (`JFrame`, `JPanel`, `Timer`)
- Java 2D Graphics
- Keyboard event handling

## Run the game

**Requirements:** A Java Development Kit (JDK) and a Java IDE. For VS Code, install the **Extension Pack for Java**.

1. Clone the repository:

   ```bash
   git clone https://github.com/BeingBornn/Brick-Breaker-Game.git
   ```

2. In VS Code, open the inner **`BrickBreakerGame`** folder.
3. Open **`src/view/GameView.java`**.
4. Click **Run** above the `main` method.

The entry point is `view.GameView`.

## Controls

| Key | Action |
| --- | --- |
| Left arrow | Move paddle left |
| Right arrow | Move paddle right |
| Up arrow | Release the ball |

The game ends when all three lives have been lost.

## Project structure

The source code is organized into **model**, **view**, and **controller** packages to separate game objects, rendering, and game logic.

```text
BrickBreakerGame/
└── src/
    ├── model/
    ├── view/
    └── controller/
```

### Class responsibilities

- **`view/GameView.java`** — Application entry point. Extends `JFrame`, configures the fixed-size game window (1024 × 768), creates the `GamePanel`, and makes the window visible.
