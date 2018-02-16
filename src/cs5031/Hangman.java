package cs5031;

import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameState game;
        CommandOpts opts;
        boolean correct;
        opts = new CommandOpts(args);
        if (opts.getWordsource() == "") {
            System.out.println("  1. Counties");
            System.out.println("  2. Countries");
            System.out.println("  3. Cities");

            System.out.print("Pick a category:");

            game = new GameState(Words.randomWord(sc.nextInt()),
                    opts.getMaxguesses(), opts.getMaxhints());
        } else {
            game = new GameState(Words.randomWord(opts.getWordsource()),
                    opts.getMaxguesses(), opts.getMaxhints());
        }
        while (!game.won() && !game.lost()) {
            game.showWord();
            System.out.println("Guesses remaining: " + game.getWrong());
            correct = game.guessLetter();
            if (correct)
                System.out.println("Good guess!");
            if (!correct)
                System.out.println("Wrong guess!");
        }
        if (game.won()) {
            System.out.println("Well done!");
            System.out.println("You took " + game.getG() + " guesses");
        } else {
            System.out.println("You lost! The word was " + game.getWrong());
        }
    }
}