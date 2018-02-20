package cs5031;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles all of the Hangman game logic.
 */
public class GameState {
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

    void showWord() {
        for (int i = 0; i < word.length(); ++i) {
            if (guessedLetters.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("-");
            }
        }
        System.out.println("");
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
