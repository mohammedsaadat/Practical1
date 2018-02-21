package hangman;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class has the main method.
 */
public class Hangman {

    public static void main(String[] args) {

        try {
            Scanner sc = new Scanner(System.in, "UTF-8");
            CommandOpts opts = new CommandOpts();
            // Parsing the arguments.
            opts.parseArgument(args);
            // Obtaining the arguments
            int maxGuesses = opts.getMaxguesses();
            int maxHints = opts.getMaxhints();
            String targetWord;
            if (opts.getWordsource().equalsIgnoreCase("")) {
                // User selection menu
                printMenu();
                targetWord = WordPicker.getRandomWord(sc.nextInt());
            } else {
                targetWord = WordPicker.getRandomWord(opts.getWordsource());
            }

            GameState game = new GameState(targetWord, maxGuesses, maxHints);
            // initialise the unGuessed array.
            game.initialiseUnGuessedArray(game.getWord(),
                    game.getUnGuessedLetters());

            // while the game not ended yet.
            while (!game.won() && !game.lost()) {
                game.showWord();
                printGameRound(game.getRemainingGuesses(), game.guess());
            }
            if (game.won()) {
                printGameWon(game.getGuesses(), game.getWord());
            } else {
                printGameLost(game.getWord());
            }

            // close scanner.
            sc.close();
        } catch (NumberFormatException e) {
            System.out.println("The arguments passed are incorrect!");
            System.exit(1);
        } catch (WrongCategoryException e) {
            System.out.println("Wrong Category Choice!");
            System.exit(1);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");
            System.exit(1);
        }
    }

    /**
     * Prints out a menu for the player to select from.
     */
    private static void printMenu() {
        System.out.println("  1. Counties");
        System.out.println("  2. Countries");
        System.out.println("  3. Cities");
        System.out.print("Pick a category:");
    }

    /**
     * Prints out a message states that the game is won
     * when the player wins.
     * @param guesses The number of guesses that the player took.
     * @param word The target word that the player was guessing.
     */
    private static void printGameWon(final int guesses, final String word) {
        System.out.println("Well done!");
        System.out.println("You took " + guesses
                + " guesses. The word was " + word);
    }

    /**
     * Prints out that the game is lost when the player
     * losses.
     * @param word The word that the player was meant to guess.
     */
    private static void printGameLost(final String word) {
        System.out.println("You lost! The word was " + word);
    }

    /**
     * Prints out each game play round.
     * @param remainingGuesses The remaining number of guesses that the
     *                         player has.
     * @param message The message that the player gets after taking a guess.
     */
    private static void printGameRound(final int remainingGuesses,
                                       final String message) {
        System.out.println("Guesses remaining: " + remainingGuesses);
        System.out.println(message);
    }
}