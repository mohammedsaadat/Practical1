package cs5031;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles all of the Hangman game logic.
 */
public class GameState {
    /**
     * Integer shows that the user is asking for a hint.
     */
    static final int HINT = 1;

    /**
     * Integer shows that the user is taking a guess with
     * a letter.
     */
    static final int LETTER = 2;

    /**
     * Integer shows that the user is taking a guess with
     * a whole word.
     */
    static final int WORD = 3;

    /**
     * Integer shows that the user entered a wrong input.
     */
    static final int WRONG_INPUT = 4;

    /**
     * String that represents the target word that the player
     * has to guess.
     */
    private String word;

    /**
     * Integer that represents the number of guesses that the
     * player has taken.
     */
    private int guesses;

    /**
     * Integer that represents the number of remaining guesses
     * that the player has left.
     */
    private int remainingGuesses;

    /**
     * Integer that represents the number of remaining hints
     * that the player has left.
     */
    private int remainingHints;

    /**
     * ArrayList that holds the letters that the player already
     * guessed from the word.
     */
    private ArrayList<Character> guessedLetters;

    /**
     * ArrayList that holds the letters that the player has not
     * guessed from the word yet.
     */
    private ArrayList<Character> unGuessedLetters;

    /**
     * Scanner used to get user input when the game is ran.
     */
    private Scanner sc = new Scanner(System.in).useDelimiter("\n");

    /**
     * Constructor of GameState that initialises the target,
     * maxGuesses and maxHints variables in the GameState class.
     * @param target String that represents the target word.
     * @param maxGuesses Integer that represents the maximum number of guesses.
     * @param maxHints Integer that represents the maximum number of hints.
     */
    public GameState(String target, int maxGuesses, int maxHints) {
        this.word = target;
        this.guesses = 0;
        this.remainingHints = maxHints;
        remainingGuesses = maxGuesses;
        unGuessedLetters = new ArrayList<>();
        guessedLetters = new ArrayList<>();
    }

    /**
     * Takes in a word and an array list and populates that array list with
     * the word characters without duplicates.
     * @param targetWord String that is the target word.
     * @param wordCharacters ArrayList of string.
     */
    public void initialiseUnGuessedArray(String targetWord,
                                         ArrayList<Character> wordCharacters) {
        for (int i = 0; i < targetWord.length(); ++i) {
            Character character = Character.toLowerCase(targetWord.charAt(i));
            /*
             We don't need to consider spaces as characters, players do
             not need to guess them.
            */
            if (!wordCharacters.contains(character) && character != ' ') {
                wordCharacters.add(character);
            }
        }
    }

    /**
     * Getter that returns the unGuessedLetters ArrayList.
     * @return The unGuessedLetters ArrayList.
     */
    public ArrayList<Character> getUnGuessedLetters() {
        return unGuessedLetters;
    }

    /**
     * Getter that returns the target word.
     * @return String that represents the target word.
     */
    public String getWord() {
        return word;
    }

    /**
     * Getter that returns the number of guesses taken so far.
     * @return Integer that represents the number of guesses taken so far.
     */
    public int getGuesses() {
        return guesses;
    }

    /**
     * Getter that returns the number of remaining guesses.
     * @return Integer that represents the number of remaining guesses.
     */
    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    /**
     * Getter that returns the number of remaining hints.
     * @return Integer that represents the number of remaining hints.
     */
    public int getRemainingHints() {
        return remainingHints;
    }

    /**
     * Generates the string that represents the target word in its
     * current state with what the user has guessed so far.
     * @param targetWord The target word the player has to guess.
     * @param guessed ArrayList containing the letters guessed by the player.
     * @return String that shows the current word with letters guessed only.
     */
    public String createShowWordString(String targetWord,
                                       ArrayList<Character> guessed) {
        // create string builder.
        StringBuilder returnString = new StringBuilder();

        for (int i = 0; i < targetWord.length(); ++i) {
            Character letter = Character.toLowerCase(targetWord.charAt(i));
            if (guessed.contains(letter)) {
                // When printing we need the actual case of the letter.
                returnString.append(targetWord.charAt(i));
            } else if (targetWord.charAt(i) == ' ') {
                returnString.append(" ");
            } else {
                returnString.append("-");
            }
        }
        return returnString.toString();
    }

    /**
     * Prints the word with the guessed letters on displayed only.
     */
    void showWord() {
        String showWord = createShowWordString(word, guessedLetters);
        System.out.println(showWord);
    }

    /**
     * Returns an integer that represents one of the pre-defined
     * input types (LETTER, WORD, HINT and WRONG_INPUT).
     * @param input A string that represents the user input.
     * @return An integer that refers to the type of input.
     */
    public int parseInput(String input) {
        // In case of empty string or null input.
        if (input == null || input.length() == 0) {
            return WRONG_INPUT;
        } else if (input.length() > 1) {
            return WORD;
        }

        // Getting the character.
        Character letter = input.charAt(0);

        if (letter == '?') {
            return HINT;
        } else if (letter >= 'A' && letter <= 'Z' || letter >= 'a' && letter <= 'z') {
            return LETTER;
        } else {
            return WRONG_INPUT;
        }
    }

    boolean guessLetter() {
        char letter;

        System.out.print("Guess a letter or word (? for a hint): ");

        String str = sc.next().toLowerCase();

        if (str.length() > 1) {
            if (str == word) {
                unGuessedLetters.clear();
                return true;
            } else return false;
        }

        letter = str.charAt(0);

        if (letter == '?') {
            hint();
            return false;
        }

        for (int i = 0; i < unGuessedLetters.size(); ++i) { // Loop over the not got
            if (unGuessedLetters.get(i) == letter) {
                unGuessedLetters.remove(i);
                guessedLetters.add(letter);
                guesses++;
                return true;
            }
        }

        guesses++; // One more guess
        remainingGuesses--;
        return false;
    }

    boolean won() {
        return unGuessedLetters.size() == 0;
    }

    boolean lost() {
        return (unGuessedLetters.size() > 0 && remainingGuesses == 0);
    }

    void hint() {
        if (remainingHints == 0) {
            System.out.println("No more hints allowed");
        }

        System.out.print("Try: ");
        System.out.println(unGuessedLetters.get((int) (Math.random() * unGuessedLetters.size())));
    }


}

