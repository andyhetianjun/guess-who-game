/**
 * Programmers: Andy He, Anita Jiang, Emily Wong
 * Date: June 9th 2025
 * Purpose: the purpose of this class is to handle the random selection of a hidden character for the AI player to play as in the "Guess Who" game. 
 * It stores a list of 24 possible characters to play from and randomly assigns one of them 
 */

package FinalProject;

import java.util.Random;

/*
 * class used to randomly assign one of the 24 characters to the AI player
 */
public class assignCharacter {
	
	// creates a static integer of the total number of characters
    private static final int totalCharacters = 24;
	
    //array of all character names that can be selected
	private  String characters[] = {
			"Al", "Amy", "Ben", "Carmen", "Daniel", "David", "Emma", "Eric", "Farah", "Gabe", "Joe", "Jordan",
			"Katie","Laura", "Leo", "Lily", "Liz", "Mia", "Mike", "Nick", "Olivia", "Rachel", "Sam", "Sofia"
	};
	
	// variable name for the character randomly selected for the AI
    private String aiCharacter;
    

    // Method to randomly assign a character to the AI
    public void assignRandomCharacter() {
        Random rand = new Random();
        int index = rand.nextInt(totalCharacters); // random int from 0 to 23
        aiCharacter = characters[index];
    }

    // Getter for AI character
    // @return the name of the AI's selected character 
    public String getAICharacter() {
        return aiCharacter;
    }



}