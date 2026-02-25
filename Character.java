/**
 * Programmers: Andy, Anita, Emily
 * Date: June 9th 2025
 * Purpose: This class represents a character in the Guess Who? game. Each character has a unique combination
 * of traits that are used to distinguish them from others. The class also handles character elimination 
 * during gameplay based on trait comparisons.
 */
package FinalProject;

/**
 * creates a character object with specified attributes
 * 
 * @param name - the name of the character
 * @param geneder - gender of character
 * @param eyeColor - eye color of character 
 * @param skinTone - the skin tone of the character
 * @param hairColor - hair color of character 
 * @param skinTone - skin tone  of character 
 * @param hasFacialHair - facial hair of character 
 * @param wearsGlasses - if character has glasses
 * @param teethVisible - teeth visibility of character 
 * @param wearsHat - if character is wearing hat
 * @param hairLength -hair length character 
 * @param hasPiercing - if character has piercing
 * @param eliminated- status of character 
 */
public class Character {
	private String name;
    private String gender;
    private String eyeColor;
    private String hairColor;
    private String skinTone;
    private boolean hasFacialHair;
    private boolean wearsGlasses;
    private boolean teethVisible;
    private boolean wearsHat;
    private String hairLength;
    private boolean hasPiercing;
    private boolean eliminated;
    
    public Character(String name, String gender, String eyeColor, String skinTone, String hairColor, boolean hasFacialHair, boolean wearsGlasses, boolean teethVisible, boolean wearsHat, String hairLength, boolean hasPiercing) {
		this.name = name;
		this.gender = gender;
		this.eyeColor = eyeColor;
		this.skinTone = skinTone;
		this.hairColor = hairColor;
		this.hasFacialHair = hasFacialHair;
		this.wearsGlasses = wearsGlasses;
		this.teethVisible = teethVisible;
		this.wearsHat = wearsHat;
		this.hairLength = hairLength;
		this.hasPiercing = hasPiercing;
		this.eliminated = false;
	}
    /**
     * checks if character has a trait matching the given trait name and value
     * 
     * @param trait - name of trait
     * @param value - value to against character's traits 
     * @return true if character's trait matches the value, false if it doesnt 
     */
    public boolean hasTrait(String trait, String value) {
        switch (trait.toLowerCase()) {
            case "gender": 
            	return gender.equals(value);
            case "eyecolor": 
            	return eyeColor.equals(value);
            case "haircolor": 
            	return hairColor.equals(value);
            case "skintone": 
            	return skinTone.equals(value);
            case "hasfacialhair": 
            	return Boolean.toString(hasFacialHair).equals(value);
            case "wearsglasses": 
            	return Boolean.toString(wearsGlasses).equals(value);
            case "teethvisible": 
            	return Boolean.toString(teethVisible).equals(value);
            case "wearshat": 
            	return Boolean.toString(wearsHat).equals(value);
            case "hairlength": 
            	return hairLength.equals(value);
            case "haspiercing": 
            	return Boolean.toString(hasPiercing).equals(value);
            default: 
            	return false;
        }
    }
/*
 * marks character as eliminated 
 */
    public void eliminate() { 
    	eliminated = true; 
    	}
    /* 
     * resets the characters eliminated status to false 
     */
    public void reset() { 
    	eliminated = false; 
    	}
    /*
     * checks wheter the character has been eliminated 
     * 
     * @return true if the character is eliminated; false if not
     */
    public boolean isEliminated() { 
    	return eliminated; 
    	}
    /*
     *getter method 
     *returns characters name
     */
    public String getName() {
        return name;
    }
    /*
     *getter method 
     *returns characters gender
     */
    public String getGender() {
        return gender;
    }
    /*
     *getter method 
     *returns characters eyecolor
     */
    public String getEyeColor() {
        return eyeColor;
    }
    /*
     *getter method 
     *returns characters skin tone
     */
    public String getSkinTone() {
        return skinTone;
    }

    /*
     *getter method 
     *returns characters hair color
     */
    public String getHairColor() {
        return hairColor;
    }

    /*
     *getter method 
     *returns true if character has facial hair, false if not 
     */
    public boolean hasFacialHair() {
        return hasFacialHair;
    }
    
    /*
     *
     *getter method 
     *returns true if character has glasses, false if not 
     */
    public boolean wearsGlasses() {
        return wearsGlasses;
    }
    /*
     * getter method 
     * returns true if character has teeth visible, false if not 
     */
    public boolean hasTeethVisible() {
        return teethVisible;
    }
    /*
     * getter method 
     * returns true if character has hat visible, false if not 
     */
    public boolean wearsHat() {
        return wearsHat;
    }
    
    /*
     * getter method 
     * returns characters hair length
     */
    public String getHairLength() {
        return hairLength;
    }
    
    /*
     * getter method 
     * returns true if character has piercings visible, false if not 
     */
    public boolean hasPiercing() {
        return hasPiercing;
    }
}