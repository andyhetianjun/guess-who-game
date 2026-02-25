# Guess Who Game

A Java Swing implementation of the classic Guess Who? board game, built as a final project for ICS4U.

**Collaborators:** Andy He, Anita Jiang, Emily Wong

## How to Play

1. Launch the game — a random character is secretly assigned to the AI
2. On your turn, pick a yes/no question from the question bank to eliminate characters
3. Flip down characters that don't match the answer
4. You have **15 seconds** per turn — miss the timer and your turn is skipped
5. When you're ready, make your one guess. First to guess correctly wins!

## Features

- **Full Swing GUI** — 4×6 character board with images, buttons, and dialogs
- **20-question bank** across 10 traits: gender, eye colour, hair colour/length, skin tone, facial hair, glasses, hat, teeth, and piercings
- **AI opponent** — maintains its own candidate list and eliminates characters based on your answers, guessing when narrowed to 1 candidate
- **15-second turn timer** — auto-skips your turn if time runs out
- **Background music** via Java AudioSystem
- **Game outcome logging** — results saved to `game_results.txt`

## Project Structure

| File | Purpose |
|------|---------|
| `guessWho.java` | Main class — GUI setup, game logic, player/AI turns |
| `Character.java` | Character object with 10 trait attributes |
| `GameBoard.java` | Board state management and trait-based elimination |
| `AIPlayer.java` | AI logic and candidate tracking |
| `assignCharacter.java` | Random character assignment |
| `GuessWhoGame.java` | Entry point |

## Running the Game

Requires Java 11+. Open in Eclipse or compile manually:

```bash
javac -d bin src/FinalProject/*.java
java -cp bin FinalProject.GuessWhoGame
```

Make sure all `.png`, `.jpeg`, and `.wav` files are in the same directory as the compiled classes.