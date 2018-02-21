package cs5031;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles all of the Hangman game logic.
 */
class GameState {
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
     * String holds the message printed to the player on
     * correct input.
     */
    static final String CORRECT_GUESS = "Correct Guess";

    /**
     * String holds the message printed to the player on
     * incorrect input.
     */
    static final String INCORRECT_GUESS = "Incorrect Guess";

    /**
     * Character representing that no more hints are left.
     */
    static final Character NO_HINTS = '.';

    /**
     * String holds the message printed to the player when
     * no more hints are allowed.
     */
    static final String NO_MORE_HINT_MSG = "No more hints allowed";

    /**
     * String shows the message displayed when user asks
     * for a hint.
     */
    static final String HINT_MSG = "Try: ";

    /**
     * String shows the messages displayed to the user on wrong
     * type input.
     */
    static final String WRONG_INPUT_MSG = "Wrong input, try again";


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
    GameState(String target, int maxGuesses, int maxHints) {
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
    void initialiseUnGuessedArray(String targetWord,
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
    ArrayList<Character> getUnGuessedLetters() {
        return unGuessedLetters;
    }

    /**
     * Getter that returns the target word.
     * @return String that represents the target word.
     */
    String getWord() {
        return word;
    }

    /**
     * Getter that returns the number of guesses taken so far.
     * @return Integer that represents the number of guesses taken so far.
     */
    int getGuesses() {
        return guesses;
    }

    /**
     * Getter that returns the number of remaining guesses.
     * @return Integer that represents the number of remaining guesses.
     */
    int getRemainingGuesses() {
        return remainingGuesses;
    }

    /**
     * Getter that returns the number of remaining hints.
     * @return Integer that represents the number of remaining hints.
     */
    int getRemainingHints() {
        return remainingHints;
    }

    /**
     * Generates the string that represents the target word in its
     * current state with what the user has guessed so far.
     * @param targetWord The target word the player has to guess.
     * @param guessed ArrayList containing the letters guessed by the player.
     * @return String that shows the current word with letters guessed only.
     */
    String createShowWordString(String targetWord,
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
    int parseInput(String input) {
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
        } else if (letter >= 'A' && letter <= 'Z'
                || letter >= 'a' && letter <= 'z') {
            return LETTER;
        } else {
            return WRONG_INPUT;
        }
    }

    /**
     * Checks if the word that the user guessed is correct
     * and returns a message to the player ('Correct Guess'
     * in case the user got it right and 'Incorrect Guess'
     * in case the user got it wrong).
     * @param userInput A string represents the user input.
     * @param targetWord A string represents what the user should guess.
     * @return A string that represents a message for the player.
     */
    String handleWordInput(String userInput, String targetWord) {
        // Increase number of guesses taken.
        guesses++;

        if (userInput.equalsIgnoreCase(targetWord)) {
            // clear the unGuessedLetters ArrayList.
            unGuessedLetters.clear();
            return CORRECT_GUESS;
        } else {
            // Subtract the number of remaining guesses.
            remainingGuesses--;
            return INCORRECT_GUESS;
        }
    }

    /**
     * Creates a message that is displayed to the player when
     * requesting for hints. In case no hints are remaining a
     * message stating that will be displayed. Otherwise, the
     * player will receive a message containing the hint.
     * @return A string that represents the message displayed for player.
     */
    String handleHintInput() {
        Character hint = generateHint();
        if (hint == NO_HINTS) {
            return NO_MORE_HINT_MSG;
        } else {
            return HINT_MSG + hint;
        }
    }

    /**
     * Generates a hint by randomly selecting a character from the
     * unGuessedLetters ArrayList. It updates the remaining hints
     * variable as well.
     * @return A character that represents the suggested hint.
     */
    Character generateHint() {
        if (remainingHints == 0) {
            return NO_HINTS;
        }
        // subtract the remaining hints.
        remainingHints--;
        return unGuessedLetters.get((int) (Math.random()
                * unGuessedLetters.size()));
    }

    /**
     * Creates a message that displays whether the guess the
     * player took was correct or incorrect. In addition, all
     * other variables such as the number of guesses and the
     * remaining guesses are updated accordingly.
     * @param character A character that represents the player input.
     * @return A string that represents the message displayed to the player.
     */
    String handleLetterInput(Character character) {
        // Increase number of guesses taken.
        guesses++;

        for (Character letter : unGuessedLetters) {
            /*
             Changed to lower case so that in case the player enters an upper
             case letter.
            */
            if (letter == Character.toLowerCase(character)) {
                // remove the guessed letter from un-guessed list.
                unGuessedLetters.remove(letter);
                // add the removed letter to the guessed list
                guessedLetters.add(letter);
                return CORRECT_GUESS;
            }
        }

        // otherwise the guess was wrong, thus subtract remaining guesses.
        remainingGuesses--;
        return INCORRECT_GUESS;
    }

    /**
     * Creates the message that is returned to the player as per
     * the input the player entered (WORD, LETTER, HINT or WRONG_INPUT).
     * @param userInput String represents user input.
     * @return A string that contains the message to be displayed to the user.
     */
    String handleGuess(String userInput) {
        // Get the type of the input.
        int inputType = parseInput(userInput);
        String message = "";
        // Generate message base on the input.
        switch (inputType) {
            case WORD:
                message = handleWordInput(userInput, word);
                break;

            case LETTER:
                message = handleLetterInput(userInput.charAt(0));
                break;

            case HINT:
                message = handleHintInput();
                break;

            case WRONG_INPUT:
                message = WRONG_INPUT_MSG;
                break;

            default:
                message = "Ops, something went wrong!";
        }

        return message;
    }

    /**
     * Asks the user for an input which is then passed to the handleGuess
     * to be handled. After that a message is returned for the player.
     * @return A string contains a message to be displayed to the player.
     */
    String guess() {
        // Get the user input.
        System.out.print("Guess a letter or word (? for a hint): ");
        // read from the scanner.
        String str = sc.next().toLowerCase();
        return handleGuess(str);
    }

    /**
     * Checks if the player won the game and returns true
     * if the player won.
     * @return Boolean that shows that the player won or not.
     */
    boolean won() {
        // Checks if the player guessed all the letters.
        return unGuessedLetters.size() == 0;
    }

    /**
     * Checks if the player list the game and returns
     * a boolean accordingly.
     * @return Boolean that shows that the player lost or not.
     */
    boolean lost() {
        return (unGuessedLetters.size() > 0 && remainingGuesses == 0);
    }
}

