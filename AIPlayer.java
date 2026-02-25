/*
 * Programmers: Andy He, Anita Jiang, Emily Wong
 * Date: June 9th 2025
 * Purpose: 
 * the AIPlayer class represents the computer contorlled player in the guess who game 
 * it maintains a list of the remaining characters, keeping track of asked questions, and uses basic elimination strategy to narrow down the possible characters. it also makes a final guess when only one character remains
 */

package FinalProject;

import java.util.ArrayList;

/*
 * represents the AI player, tracking the remaining characters and uses strategy to eliminate and make guesses
 */
public class AIPlayer {
	
	//remaining characters for the AI to consider 
    private ArrayList<Character> remainingCharacters;
    
  //remaining questions for the AI to consider 
    private ArrayList<String> askedQuestions;

    /*
     * constructor with the full character list 
     */
    public AIPlayer(ArrayList<Character> allCharacters) {
        remainingCharacters = new ArrayList<Character>();
        for (int i = 0; i < allCharacters.size(); i++) {
            remainingCharacters.add(allCharacters.get(i));
        }
        askedQuestions = new ArrayList<String>();
    }

    /*
     * chooses the best question based off the traits of the remaining characters 
     * 
     * @return the name of the trait that has the most balanced splits 
     */
    public String chooseQuestion() {
        String[] traits = { "hairColor", "eyeColor", "gender", "glasses", "hat" };
        String bestQuestion = "";
        int bestSplit = 1000;

        for (int i = 0; i < traits.length; i++) {
            String trait = traits[i];

            if (!alreadyAsked(trait)) {
                int countYes = countYesAnswers(trait);
                int diff = Math.abs(countYes - remainingCharacters.size() / 2);
                if (diff < bestSplit) {
                    bestSplit = diff;
                    bestQuestion = trait;
                }
            }
        }

        askedQuestions.add(bestQuestion);
        return bestQuestion;
    }

    /*
     * filters the remaining characters based on the the answer to the most recent question
     * 
     * @param trait - that was asked about
     * @param userSaidYes - if the user said yes to the trait 
     */
    public void filterCharacters(String trait, boolean userSaidYes) {
        ArrayList<Character> updated = new ArrayList<Character>();

        for (int i = 0; i < remainingCharacters.size(); i++) {
            Character c = remainingCharacters.get(i);
            boolean hasTrait = getTraitAnswer(c, trait).equals("yes");

            if ((userSaidYes && hasTrait) || (!userSaidYes && !hasTrait)) {
                updated.add(c);
            }
        }

        remainingCharacters = updated;
    }

    /*
     * if only one character remains, return it as the AI's guess
     * @return the character left, null if not ready
     */
    public Character makeGuess() {
        if (remainingCharacters.size() == 1) {
            return remainingCharacters.get(0);
        }
        return null; // AI is not ready to guess
    }

    /*Helper methods if the question has already been asked
    *@param trait - to check
    *@ return true if already asked , false otherwise
    */
    
    private boolean alreadyAsked(String trait) {
        for (int i = 0; i < askedQuestions.size(); i++) {
            if (askedQuestions.get(i).equals(trait)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * counts the remaiing characters with the specified trait
     * @param trait - to evaluate
     * @return number of characters with the trait
     */
    private int countYesAnswers(String trait) {
        int count = 0;
        for (int i = 0; i < remainingCharacters.size(); i++) {
            Character c = remainingCharacters.get(i);
            if (getTraitAnswer(c, trait).equals("yes")) {
                count++;
            }
        }
        return count;
    }

    /*
     * evaluates whether a charater has a specific trait 
     * 
     * @param c the character to evaluate 
     * @param trait - the one asked
     * @return "yes" if the character has the trait, otherwise "no
     */
    private String getTraitAnswer(Character c, String trait) {
        if (trait.equals("hairColor") && c.getHairColor().equals("brown")) return "yes";
        if (trait.equals("eyeColor") && c.getEyeColor().equals("blue")) return "yes";
        if (trait.equals("gender") && c.getGender().equals("female")) return "yes";
        if (trait.equals("glasses") && c.wearsGlasses()) return "yes";
        if (trait.equals("hat") && c.wearsHat()) return "yes";
        return "no";
    }
    /*
     * returns how many characters are still being considered 
     * @return the list size of the remaining characters 
     */
    public int getRemainingCount() {
        return remainingCharacters.size();
    }

    /*
     * returns the current list of remaining characters 
     * @return list of remaining characters 
     */
    public ArrayList<Character> getRemainingCharacters() {
        return remainingCharacters;
    }
}