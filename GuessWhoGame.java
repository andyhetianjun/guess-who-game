package FinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GuessWhoGame extends JFrame {
 private JLabel questionLabel;
 private JButton yesButton, noButton;
 private JTextArea aiStatusArea;
 private AIPlayer ai;
 private String currentTrait;

 public GuessWhoGame() {

     // setup characters
     ArrayList<Character> characters = createCharacters();
     ai = new AIPlayer(characters);
 }


     //create array list of characters
 private ArrayList<Character> createCharacters() {
     ArrayList<Character> characters = new ArrayList<Character>();
     characters.add(new Character("Sam", "Male", "Green", "Dark Skin", "Black", false, false, false, true, "Short", false));
     characters.add(new Character("Nick", "Male", "Brown", "Light Skin", "Blonde", false, false, false, false, "Short", true));
     characters.add(new Character("David", "Male", "Brown", "Light Skin", "Blonde", true, false, true, true, "Short", false));
     characters.add(new Character("Leo", "Male", "Brown", "Light Skin", "Blonde", true, false, true, false, "Short", false));
     characters.add(new Character("Daniel", "Male", "Green", "Light Skin", "Ginger", true, false, false, false, "Tied", false));
     characters.add(new Character("Ben", "Male", "Brown", "Light Skin", "Brown", false, true, false, false, "Short", true));
     characters.add(new Character("Al", "Male", "Green", "Dark Skin", "White", true, true, false, false, "Bald", false));
     characters.add(new Character("Mike", "Male", "Brown", "Light Skin", "Black", false, false, true, true, "Short", false));
     characters.add(new Character("Gabe", "Male", "Brown", "Dark Skin", "Black", false, false, false, false, "Short", false));
     characters.add(new Character("Jordan", "Male", "Brown", "Dark Skin", "Black", true, false, false, false, "Short", true));
     characters.add(new Character("Eric", "Male", "Blue", "Light Skin", "Black", false, false, false, false, "Short", false));
     characters.add(new Character("Joe", "Male", "Brown", "Dark Skin", "White", true, true, true, false, "Bald", false));
     characters.add(new Character("Olivia", "Female", "Brown", "Dark Skin", "Black", false, false, false, false, "Tied", false));
     characters.add(new Character("Sofia", "Female", "Brown", "Dark Skin", "Brown", false, false, true, false, "Short", true));
     characters.add(new Character("Liz", "Female", "Blue", "Light Skin", "White", false, false, false, false, "Tied", false));
     characters.add(new Character("Lily", "Female", "Green", "Dark Skin", "Brown", false, false, true, true, "Long",false));
     characters.add(new Character("Emma", "Female", "Brown", "Light Skin", "Ginger", false, false, false, false, "Tied", false));
     characters.add(new Character("Katie", "Female", "Blue", "Light Skin", "Blonde", false, false, false, true, "Tied", false));
     characters.add(new Character("Amy", "Female", "Brown","Light Skin", "Black", false, true, false, false, "Short", false));
     characters.add(new Character("Farah", "Female", "Blue", "Dark Skin", "Black", false, false, false, false, "Tied", false));
     characters.add(new Character("Laura", "Female", "Green", "Dark Skin", "Black", false, false, true, false, "Long", true));
     characters.add(new Character("Carmen", "Female", "Brown", "Dark Skin", "White", false, false, true, false, "Short", true));
     characters.add(new Character("Rachel", "Female", "Blue", "Light Skin", "Brown", false, true, false, false, "Long", true));
     characters.add(new Character("Mia", "Female", "Brown", "Dark Skin", "Black", false, false, true, false, "Long", false));
     return characters;
 }

 public static void main(String[] args) {
     new GuessWhoGame();
 }
}