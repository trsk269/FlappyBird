# Flappy Bird Java Game

A simple Java Swing version of Flappy Bird. The game launches in a window and uses keyboard input to start and control the bird.

## Screenshot

![Flappy Bird Screenshot](../../SS.png)

## Folder Structure

- `src`: Java source files and game assets
- `lib`: project dependencies (if used)
- `bin`: compiled output files (generated after compilation)

## How to Run

1. Open the project in VS Code or your terminal.
2. Open a terminal in the `src` folder:

```bash
cd /Users/trsk/Downloads/github/FlappyBird/FlappyBird/FlappyBird/src
```

3. Run the game directly if compiled:

```bash
java App
```

4. If compilation is needed, build first:

```bash
javac App.java FlappyBird.java
java App
```

## Start and Controls

- Once the game window opens, press the `Space` bar (the whitespace key) to start the game.
- Use the space bar to make the bird flap and keep it flying between the pipes.

## Notes

- The main entry point is `App.java`.
- The game uses Swing and should run on any system with Java installed.
