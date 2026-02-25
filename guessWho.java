/*
 * Programmers: Andy He, Anita Jiang, Emily Wong
 * Date: June 9th 2025
 * Purpose: 
 *  This is the main class for the guess who game
 *  this class sets up the GUI and manages things like the game logic, player and AI turns, filtering characters based on question answers, and handling the guesses
 * */

package FinalProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;


public class guessWho extends JFrame {
	
	//creates array of all the characters 
	String characters[] = {
			"Al", "Amy", "Ben", "Carmen", "Daniel", "David", "Emma", "Eric", "Farah", "Gabe", "Joe", "Jordan",
			"Katie","Laura", "Leo", "Lily", "Liz", "Mia", "Mike", "Nick", "Olivia", "Rachel", "Sam", "Sofia"
		
	};
	
	// creates an array of the character images 
	String charFile [] = {
			"al.png", "amy.png", "ben.png", "carmen.png", "daniel.png", "david.png", "emma.png", "eric.png",
			"farah.png", "gabe.png", "joe.png", "jordan.png", "katie.png", "laura.png", "leo.png", "lily.png",
			"liz.png", "mia.png", "mike.png", "nick.png", "olivia.png", "rachel.png", "sam.png", "sofia.png"
	};
	
	//character chosen by the AI
	private Character aiCharacter;
	
	//buttons used for each character in the GUI
	private JButton[] characterButtons = new JButton[24];

	//keeps track whos turn it is 
	private boolean isUserTurn = true;
	
	// tracks if the user has made their final guess
	private boolean hasUserGuessed = false;
	// tracks if the ai has made their final guess
	
	private ArrayList<Character> aiRemainingCharacters;
	
	private boolean hasAIGuessed = false;
	
	//remaining characters left in play 
	private ArrayList<Character> remainingCharacters;

	// list of previously asked questions
    private ArrayList<String> askedQuestions;
    
    //full list of all characters in game 
    private ArrayList<Character> allCharacters = initialiseCharacters();

    //button to start game 
    JButton playButton = new JButton("Play");
    
    //button to access rules
	JButton rulesButton = new JButton("Rules");
	
	// welcome label displayed on screen
	JLabel titleLabel = new JLabel("Welcome to Guess Who?", SwingConstants.CENTER);
	
	//instructions label 
	JLabel rulesLabel = new JLabel("\nClick 'Rules' to learn how to play!", SwingConstants.CENTER);
	
	//panel that holds welcome screen together
	JPanel panel1 = new JPanel(new GridLayout(4, 1, 10, 10));
	
	private Timer turnTimer;
	private int timeLeft;
	private JLabel timerLabel = new JLabel("Time left: 15s");
	
	boolean lastGuessWrong = false;

	/**
	 * This constructor method to initialize game with custom character list 
	 * @param allCharacters - takes all the array list of characters
	 */
    public guessWho(ArrayList<Character> allCharacters) {
        remainingCharacters = new ArrayList<Character>();
        for (int i = 0; i < allCharacters.size(); i++) {
            remainingCharacters.add(allCharacters.get(i));
        }
        askedQuestions = new ArrayList<String>();
    }
	
    /**
     * This is the default constructor that sets up game GUI, character list, AI character, and action listeners. 
     */
	public guessWho() {
		
		playBackgroundMusic("guessWhoMusic.wav");
		
	    // initialize characters and remaining characters list
	    ArrayList<Character> allCharacters = initialiseCharacters();
	    remainingCharacters = new ArrayList<>(allCharacters);
		aiRemainingCharacters = new ArrayList<>(allCharacters);

	    // randomly assign the AI character
	    assignRandomCharacter();

	    //GUI setup
	    setTitle("Guess Who?");
	    setSize(800, 600);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLayout(new BorderLayout());
	    JOptionPane.showMessageDialog(this, "Welcome to Guess Who?\nClick 'OK' to proceed!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
	    titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 24));
	    rulesLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    panel1.setBorder(BorderFactory.createEmptyBorder(100, 200, 100, 200));
	    panel1.add(titleLabel);
	    panel1.add(rulesLabel);
	    panel1.add(playButton);
	    panel1.add(rulesButton);
	    add(panel1, BorderLayout.CENTER);
	    rulesButton.addActionListener(e -> showRules());
	    playButton.addActionListener(e -> {
	    	this.dispose();
	        showGameBoard();
	    });
	    setVisible(true);
	}
	
	/**
	 * This method randomly selects one character from the remainingCharacters list and assigns it to the computer.
	 */

	public void assignRandomCharacter() {
	    Random rand = new Random();
	    int index = rand.nextInt(remainingCharacters.size());
	    aiCharacter = remainingCharacters.get(index);
	    System.out.println("AI character: " + aiCharacter.getName());
	
	}
	
	/**
	 * This method checks if the character matches the described trait in the question. 
	 * @param c the character to check
	 * @param question - string containing a trait condition 
	 * @return true if condition matches, false if not 
	 */
	public boolean checkTraitAnswer(Character c, String question) {
	    if (question.toLowerCase().contains("is this person male")) 
	    	return c.getGender().equalsIgnoreCase("Male");
	    if (question.toLowerCase().contains("is this person female")) 
	    	return c.getGender().equalsIgnoreCase("Female");
	    if (question.contains("eye colour brown")) 
	    	return c.getEyeColor().equalsIgnoreCase("Brown");
	    if (question.contains("eye colour green")) 
	    	return c.getEyeColor().equalsIgnoreCase("Green");
	    if (question.contains("eye colour blue")) 
	    	return c.getEyeColor().equalsIgnoreCase("Blue");
	    if (question.contains("light skin")) 
	    	return c.getSkinTone().toLowerCase().contains("light");
	    if (question.contains("hair colour black")) 
	    	return c.getHairColor().equalsIgnoreCase("Black");
	    if (question.contains("hair colour brown")) 
	    	return c.getHairColor().equalsIgnoreCase("Brown");
	    if (question.contains("hair colour ginger")) 
	    	return c.getHairColor().equalsIgnoreCase("Ginger");
	    if (question.contains("hair colour blonde")) 
	    	return c.getHairColor().equalsIgnoreCase("Blonde");
	    if (question.contains("hair colour white")) 
	    	return c.getHairColor().equalsIgnoreCase("White");
	    if (question.contains("facial hair")) 
	    	return c.hasFacialHair();
	    if (question.contains("glasses")) 
	    	return c.wearsGlasses();
	    if (question.contains("teeth")) 
	    	return c.hasTeethVisible();
	    if (question.contains("hat")) 
	    	return c.wearsHat();
	    if (question.contains("short hair")) 
	    	return c.getHairLength().equalsIgnoreCase("Short");
	    if (question.contains("tied")) 
	    	return c.getHairLength().equalsIgnoreCase("Tied");
	    if (question.contains("long hair")) 
	    	return c.getHairLength().equalsIgnoreCase("Long");
	    if (question.contains("bald")) 
	    	return c.getHairLength().equalsIgnoreCase("Bald");
	    if (question.contains("piercing")) 
	    	return c.hasPiercing();
	    return false;
	}
	
	/**
	 * This method filters remaining characters based on user's answer to a question. 
	 * if answer is 'yes', characters matching trait is kept 
	 * if answer is 'no', characters not matching are kept 
	 * 
	 * @param question - question asked
	 * @param answerYes - user's response to question -true if yes, false if not 
	 */
	public void filterCharacters(String question, boolean answerYes) {
	    ArrayList<Character> filtered = new ArrayList<>();

	    for (Character c : remainingCharacters) {
	        boolean match = checkTraitAnswer(c, question);
	        if (match == answerYes) {
	            filtered.add(c);
	        }
	    }

	    // Disable buttons for characters no longer in filtered list
	    for (int i = 0; i < allCharacters.size(); i++) {
	        Character fullChar = allCharacters.get(i);
	        boolean stillIn = false;

	        for (Character c : filtered) {
	            if (c.getName().equals(fullChar.getName())) {
	                stillIn = true;
	                break;
	            }
	        }

	        if (!stillIn && characterButtons[i] != null) {
	            characterButtons[i].setEnabled(false);
	            characterButtons[i].setBackground(Color.LIGHT_GRAY);
	        }
	    }

	    remainingCharacters = filtered;
	}

    
	/**
	 * This method initializes a full list of 24 characters with pre-defined traits 
	 * each character with assigned attributes. 
	 * 
	 * @return an arraylist containing all the character objects to be used in the game 
	 */
    private ArrayList<Character> initialiseCharacters() {
		ArrayList<Character> characters = new ArrayList<>();
	     characters.add(new Character("Al", "Male", "Green", "Dark Skin", "White", true, true, false, false, "Bald", false));
	     characters.add(new Character("Amy", "Female", "Brown","Light Skin", "Black", false, true, false, false, "Short", false));
	     characters.add(new Character("Ben", "Male", "Brown", "Light Skin", "Brown", false, true, false, false, "Short", true));
	     characters.add(new Character("Carmen", "Female", "Brown", "Dark Skin", "White", false, false, true, false, "Short", true));
	     characters.add(new Character("Daniel", "Male", "Green", "Light Skin", "Ginger", true, false, false, false, "Tied", false));
	     characters.add(new Character("David", "Male", "Brown", "Light Skin", "Blonde", true, false, true, true, "Short", false));
	     characters.add(new Character("Emma", "Female", "Brown", "Light Skin", "Ginger", false, false, false, false, "Tied", false));
	     characters.add(new Character("Eric", "Male", "Blue", "Light Skin", "Black", false, false, false, false, "Short", false));
	     characters.add(new Character("Farah", "Female", "Blue", "Dark Skin", "Black", false, false, false, false, "Tied", false));
	     characters.add(new Character("Gabe", "Male", "Brown", "Dark Skin", "Black", false, false, false, false, "Short", false));
	     characters.add(new Character("Joe", "Male", "Brown", "Dark Skin", "White", true, true, true, false, "Bald", false));
	     characters.add(new Character("Jordan", "Male", "Brown", "Dark Skin", "Black", true, false, false, false, "Short", true));
	     characters.add(new Character("Katie", "Female", "Blue", "Light Skin", "Blonde", false, false, false, true, "Tied", false));
	     characters.add(new Character("Laura", "Female", "Green", "Dark Skin", "Black", false, false, true, false, "Long", true));
	     characters.add(new Character("Leo", "Male", "Brown", "Dark Skin", "White", true, false, true, false, "Short", false));
	     characters.add(new Character("Lily", "Female", "Green", "Dark Skin", "Brown", false, false, true, true, "Long",false));
	     characters.add(new Character("Liz", "Female", "Blue", "Light Skin", "White", false, true, true, false, "Long", false));
	     characters.add(new Character("Mia", "Female", "Brown", "Dark Skin", "Black", false, false, true, false, "Long", false));
	     characters.add(new Character("Mike", "Male", "Brown", "Light Skin", "Black", false, false, true, true, "Short", false));
	     characters.add(new Character("Nick", "Male", "Brown", "Light Skin", "Blonde", false, false, false, false, "Short", true));
	     characters.add(new Character("Olivia", "Female", "Brown", "Dark Skin", "Black", false, false, false, false, "Tied", false));
	     characters.add(new Character("Rachel", "Female", "Blue", "Light Skin", "Brown", false, true, false, false, "Long", true));
	     characters.add(new Character("Sam", "Male", "Green", "Dark Skin", "Black", false, false, false, true, "Short", false));
	     characters.add(new Character("Sofia", "Female", "Green", "Dark Skin", "Brown", false, false, true, false, "Short", true));
	     return characters;
	}
	
    /**	
     * This method shows a pop up message showing the rules for the guess who game 
     * explains objectives and steps of game play. 
     */
	public void showRules() {
		String rules = """
		HOW TO PLAY GUESS WHO?
		1. Each player has a board with all 24 characters.
		2. Players silently pick one character.
		3. Ask YES/NO questions to eliminate options.
		4. Eliminate characters that do not match answer.
		5. First to gu	ess correctly wins! Players only get one chance...
				""";
		
		JOptionPane.showMessageDialog(this, rules, "Game Rules", JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * This method initializes and displays the GUI game board with all character buttons and images
	 * sets up action listeners for asking questions and making a guess.
	 */
	public void showGameBoard() {
	    JFrame gameFrame = new JFrame("Guess Who - Game Board");
	    gameFrame.setSize(1000, 800);
	    gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    JPanel gridPanel = new JPanel(new GridLayout(4, 6, 10, 10));
	    gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

	    for (int i = 0; i < characters.length; i++) {
	        JButton charButton = new JButton(characters[i]);
	        charButton.setHorizontalTextPosition(SwingConstants.CENTER);
	        charButton.setVerticalTextPosition(SwingConstants.BOTTOM);
	        charButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));

	        try {
	            ImageIcon icon = new ImageIcon(charFile[i]);
	            Image scaledImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	            charButton.setIcon(new ImageIcon(scaledImage));
	        } catch (Exception ex) {
	            System.out.println("Image not found for " + characters[i]);
	        }

	        characterButtons[i] = charButton; // store reference
	        gridPanel.add(charButton);
	    }

	    gameFrame.add(new JScrollPane(gridPanel), BorderLayout.CENTER);

	    JButton qbButton = new JButton("Question Bank");
	    qbButton.setPreferredSize(new Dimension(150, 40));
	    qbButton.addActionListener(e -> showQuestionBank(gameFrame));
	    JButton guessButton = new JButton("Guess Character");
	    guessButton.setPreferredSize(new Dimension(150, 40));
	    guessButton.addActionListener(e -> makeGuess(gameFrame));
	    JPanel panel2 = new JPanel();
	    panel2.add(qbButton);
	    panel2.add(guessButton);
	    panel2.add(timerLabel);
	    gameFrame.add(panel2, BorderLayout.SOUTH);

	    gameFrame.setVisible(true);
	}	

	/**
	 * This method displays a pop up question bank which the user can use to select a question to ask the computer AI. 
	 * @param gameFrame - the main game frame the position the question bank
	 */
	public void showQuestionBank(JFrame gameFrame) {
		if (!isUserTurn) {
			JOptionPane.showMessageDialog(gameFrame, "Wait for your turn! The AI is thinking.");
			return;
		}
		
		String questionBank [] = {
				"1. Is this person male?" ,
				"2. Is this person female?",
				"3. Is the eye colour brown?",
				"4. Is the eye colour green?",
				"5. Is the eye colour blue?",
				"6. Does the person have a light skin tone?",
				"7. Is the hair colour black?",
				"8. Is the hair colour brown?",
				"9. Is the hair colour ginger?",
				"10. Is the hair colour blonde?",
				"11. Is the hair colour white?",
				"12. Does the person have facial hair?",
				"13. Is the person wearing glasses?",
				"14. Does the person have visible teeth?",
				"15. Is the person wearing a hat?",
				"16. Does the person have short hair?",
				"17. Does the person have their hair tied up?",
				"18. Does the person have long hair?",
				"19. Is the person bald?",
				"20. Does the person have an ear piercing?"
		};
		
		JFrame qbFrame = new JFrame("Guess Who - Question Bank");
		qbFrame.setSize(500, 150);
		qbFrame.setLocationRelativeTo(gameFrame);
		qbFrame.setLayout(new BorderLayout());
		
		startTimer(() -> {
			qbFrame.dispose();
			JOptionPane.showMessageDialog(gameFrame, "Times up! You missed your turn.");
			isUserTurn = false;
			aiTurn(gameFrame);
		});
		
		JComboBox <String> questionBox = new JComboBox<>(questionBank);
		JButton askButton = new JButton("Ask");
		JLabel qbLabel = new JLabel("Questions", SwingConstants.LEFT);
		qbFrame.add(new JLabel("Pick a question: "), BorderLayout.NORTH);
		qbFrame.add(questionBox, BorderLayout.CENTER);
		qbFrame.add(askButton, BorderLayout.SOUTH);
		
		askButton.addActionListener(e -> {
			if (turnTimer != null && turnTimer.isRunning()) turnTimer.stop();
			
		    String question = (String) questionBox.getSelectedItem();
		    boolean answerYes = checkTraitAnswer(aiCharacter, question);
		    String answerString = answerYes ? "Yes" : "No" ;
		 
		    System.out.println("AI Character: " + aiCharacter.getName());
		    System.out.println("Hair: " + aiCharacter.getHairColor() + ", Eyes: " + aiCharacter.getEyeColor() + ", Glasses: " + aiCharacter.wearsGlasses());

		    JOptionPane.showMessageDialog(qbFrame, "You asked\n" + question + "\nThe answer is " + answerString);
		    
		    filterCharacters(question, answerYes);
		    qbFrame.dispose();
		    isUserTurn = false;
		    aiTurn(gameFrame);
		});
  
		qbFrame.setVisible(true);
	}	
	
	/**
	 * This method simulates the AI's turn by randomly selecting a question and asking the user to answer yes or no
	 * if a few characters remain, the AI attempts to ask a question. 
	 * @param parent - the JFrame used for pop-up positioning 
	 */
	private void aiTurn(JFrame parent) {
		if (isUserTurn || hasAIGuessed) return;
		String possibleQuestions [] = {
				"Is this person male?" ,
				"Is this person female?",
				"Is the eye colour brown?",
				"Is the eye colour green?",
				"Is the eye colour blue?",
				"Does the person have a light skin tone?",
				"Is the hair colour black?",
				"Is the hair colour brown?",
				"Is the hair colour ginger?",
				"Is the hair colour blonde?",
				"Is the hair colour white?",
				"Does the person have facial hair?",
				"Is the person wearing glasses?",
				"Does the person have visible teeth?",
				"Is the person wearing a hat?",
				"Does the person have short hair?",
				"Does the person have their hair tied up?",
				"Does the person have long hair?",
				"Is the person bald?",
				"Does the person have an ear piercing?"
		};
		
		Random rand = new Random();
		String question = possibleQuestions[rand.nextInt(possibleQuestions.length)];
		int result = JOptionPane.showConfirmDialog(parent,
			"Ai asks:\n" + question + "\nAnswer YES or NO",
			"AI Turn",
			JOptionPane.YES_NO_OPTION
		);
		
		if (result == JOptionPane.CLOSED_OPTION) {
			JOptionPane.showMessageDialog(parent, "You didn't answer! AI missed it's turn");
			isUserTurn = true;
			return;
		}
		boolean userAnswerYes = (result == JOptionPane.YES_OPTION);
		
		ArrayList<Character> filteredAI = new ArrayList<>();
		for (Character c : aiRemainingCharacters) {
		    boolean match = checkTraitAnswer(c, question);
		    if (match == userAnswerYes) {
		        filteredAI.add(c);
		    }
		}
		aiRemainingCharacters = filteredAI;
		isUserTurn = true;
		
		if (!hasAIGuessed && aiRemainingCharacters.size() < 2) {
			hasAIGuessed = true;
			Character guess = aiRemainingCharacters.get(rand.nextInt(aiRemainingCharacters.size()));
			int confirm = JOptionPane.showConfirmDialog(parent,
				"AI wants to guess: " + guess.getName() + "\nIs that your character?",
				"AI Guess",
				JOptionPane.YES_NO_OPTION 
			);
			if(confirm == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(parent, "AI guessed correctly, you lose!");
			    String saveResult = "AI guessed: " + guess.getName() + " correctly";
			    saveResultToFile(saveResult);
			}
			else {
				JOptionPane.showMessageDialog(parent, "AI guessed wrong. YOU WIN!!!");
				 String saveResult = "AI guessed: " + guess.getName() + " incorrectly";
				 saveResultToFile(saveResult);
			}
			showThankYouScreen();
		}
	}
	
	/**
	 * This method allows the user to make a single guess to who the AI's character is.  
	 * if the guess is correct, the user wins. 
	 * @param gameFrame - to be disposed when restarting or exiting game 
	 */
	private void makeGuess(JFrame gameFrame) {
		if (hasUserGuessed) {
			JOptionPane.showMessageDialog(this, "You have already used your one guess.");
			return;
		}

		String[] options = characters;
		String guess = (String) JOptionPane.showInputDialog(
			this,
			"Who do you think the AI's character is?",
			"Make a Guess!",
			JOptionPane.QUESTION_MESSAGE,
			null,
			options,
			options[0]
		);

		if (guess == null) return;

		hasUserGuessed = true;

		if (guess.equalsIgnoreCase(aiCharacter.getName())) {
			int response = JOptionPane.showConfirmDialog(
				this,
				"CORRECT!!! You guessed the AI's character. You win!\nDo you want to play again?",
				"Game Over",
				JOptionPane.YES_NO_OPTION
			);

			if (response == JOptionPane.YES_OPTION) {
				this.dispose();         // close the outer frame
				gameFrame.dispose();    // close the game board
				new guessWho();         // start new game
			} else {
				showThankYouScreen();
			}
		} else {
			int response = JOptionPane.showConfirmDialog(
				this,
				"WRONG... GAME OVER. The correct answer was " + aiCharacter.getName() + ".\nDo you want to play again?",
				"Game Over",
				JOptionPane.YES_NO_OPTION
			);

			if (response == JOptionPane.YES_OPTION) {
				this.dispose();         // close the outer frame
				gameFrame.dispose();    // close the game board
				new guessWho();         // start new game
			} else {
				showThankYouScreen();
			}
		}
	}
	
	/**
	 * This method sets a timer that counts down the game time from 15 seconds.
	 * @param onTimeout
	 */
	public void startTimer(Runnable onTimeout) {
		if (turnTimer != null && turnTimer.isRunning()) {
			turnTimer.stop();
		}
		
		timeLeft = 15; //set a timer for 15 seconds
		timerLabel.setText("Time left: 15s");
		
		turnTimer = new Timer(1000, e -> {
			timeLeft--;
			timerLabel.setText("Time left: " + timeLeft + "s");
			
			if (timeLeft <= 0) {
				turnTimer.stop();
				onTimeout.run();
			}
		});
		
		turnTimer.start();
	}
	
	/**
	 * This method plays background music for the guess who game. 
	 * @param filePath - the file for the music 
	 */
	public void playBackgroundMusic(String filePath) {
		try {
			File musicPath = new File(filePath);
			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				Clip clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				clip.start();
			}
			else {
				System.out.println("Music file not found at: " + filePath);
			}
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * This method saves the AIs guess results to a file.
	 * @param resultText
	 */
	public void saveResultToFile(String resultText) {
	    try {
	        FileWriter fileWriter = new FileWriter("game_results.txt", true); 
	        PrintWriter writer = new PrintWriter(fileWriter);
	        writer.println(resultText);
	        writer.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	/* 
	* void method createAndShowWelcomeScreen creates and displays the welcome screen with 3 beautiful people with a start button
	*/
	public static void createAndShowWelcomeScreen() {
	    JFrame frame = new JFrame("Guess Who - Welcome");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(1000, 800);
	    frame.setLayout(new BorderLayout());

	    JLabel welcomeLabel = new JLabel("Welcome to Andy, Anita, and Emily's Guess Who Game!", SwingConstants.CENTER);
	    welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));

	    JButton startButton = new JButton("Click to Start!");
	    startButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));

	    // Create a panel to hold the images
	    JPanel imagePanel = new JPanel();
	    imagePanel.setLayout(new GridLayout(1, 3)); // Display 3 images in 1 row

	    // Load the images
	    ImageIcon andy = new ImageIcon("andy.jpeg");
	    ImageIcon anita = new ImageIcon("anita.jpeg");
	    ImageIcon emily = new ImageIcon("emily.jpeg");

	    JLabel andyLabel = new JLabel(andy);
	    JLabel anitaLabel = new JLabel(anita);
	    JLabel emilyLabel = new JLabel(emily);

	    imagePanel.add(andyLabel);
	    imagePanel.add(anitaLabel);
	    imagePanel.add(emilyLabel);

	    frame.add(welcomeLabel, BorderLayout.NORTH);
	    frame.add(imagePanel, BorderLayout.CENTER);
	    frame.add(startButton, BorderLayout.SOUTH);

	    frame.setVisible(true);

	    startButton.addActionListener(e -> {
	    	//close welcome screen 
	        frame.dispose();
	        
	        // Start the actual game
	        new guessWho(); 
	    });
	}
	
	public static void showThankYouScreen() {
	    JFrame frame = new JFrame("Goodbye!");
	    frame.setSize(800, 600);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setLayout(new BorderLayout());

	    JLabel thankYouLabel = new JLabel("Thanks for playing Guess Who!", SwingConstants.CENTER);
	    thankYouLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));

	  	ImageIcon thankYouImage = new ImageIcon("mra.png"); 
	    JLabel imageLabel = new JLabel(thankYouImage, SwingConstants.CENTER);

	    JButton closeButton = new JButton("Close");
	    closeButton.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	    closeButton.addActionListener(e -> System.exit(0));

	    frame.add(thankYouLabel, BorderLayout.NORTH);
	    frame.add(imageLabel, BorderLayout.CENTER);
	    frame.add(closeButton, BorderLayout.SOUTH);

	    frame.setVisible(true);
	}
	

	/**
	 * This method main method launches the welcome screen
	 */
	public static void main(String[] args) {
	    SwingUtilities.invokeLater(() -> createAndShowWelcomeScreen());
	}
}