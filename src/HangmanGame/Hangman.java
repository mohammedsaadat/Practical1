package HangmanGame;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameState game = null;
        CommandOpts opts = new CommandOpts();
        // Parsing the arguments.
        opts.parseArgument(args);
        if (opts.getWordsource().equalsIgnoreCase("")) {
            System.out.println("  1. Counties");
            System.out.println("  2. Countries");
            System.out.println("  3. Cities");

            System.out.print("Pick a category:");
            try {
                game = new GameState(WordPicker.getRandomWord(sc.nextInt()),
                        opts.getMaxguesses(), opts.getMaxhints());
                game.initialiseUnGuessedArray(game.getWord(), game.getUnGuessedLetters());
            } catch (WrongCategoryException e) {
                System.out.println("Wrong Category Choice");
            }

        } else {
            try {
                game = new GameState(WordPicker.getRandomWord(opts.getWordsource()),
                        opts.getMaxguesses(), opts.getMaxhints());
                game.initialiseUnGuessedArray(game.getWord(), game.getUnGuessedLetters());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        while (!game.won() && !game.lost()) {
            game.showWord();
            System.out.println("Guesses remaining: " + game.getRemainingGuesses());
            System.out.println(game.guess());
        }
        if (game.won()) {
            System.out.println("Well done!");
            System.out.println("You took " + game.getGuesses() + " guesses");
        } else {
            System.out.println("You lost! The word was " + game.getWord());
        }
    }
}