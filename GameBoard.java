package FinalProject;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private ArrayList<Character> characters;

    public GameBoard(ArrayList<Character> characterList) {
        // Create a deep copy so each board has its own characters
        characters = new ArrayList<>();
        for (Character c : characterList) {
            characters.add(c); // Could clone if Character had a copy constructor
        }
    }

    public ArrayList<Character> getCharacters() {
        return characters;
    }

    public ArrayList<Character> getRemainingCharacters() {
        ArrayList<Character> remaining = new ArrayList<>();
        for (Character c : characters) {
            if (!c.isEliminated()) {
                remaining.add(c);
            }
        }
        return remaining;
    }

    public void eliminateByTrait(String trait, String value, boolean keepMatches) {
        for (Character c : characters) {
            if (c.isEliminated()) continue;

            boolean matches = c.hasTrait(trait, value);

            if ((keepMatches && !matches) || (!keepMatches && matches)) {
                c.eliminate();
            }
        }
    }

    public void resetBoard() {
        for (Character c : characters) {
            c.reset();
        }
    }

    public int countRemaining() {
        int count = 0;
        for (Character c : characters) {
            if (!c.isEliminated()) {
                count++;
            }
        }
        return count;
    }
}
