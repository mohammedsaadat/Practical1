package cs5031;

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
    private String word;
    private int guesses;
    private int wrongGuesses;
    private int remainingHints;

    private ArrayList<Character> guessedLetters;
    private ArrayList<Character> unguessedLetters;

    private Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public GameState(String target, int maxGuesses, int maxHints) {
        this.word = target;
        unguessedLetters = new ArrayList<>();
        guessedLetters = new ArrayList<>();

        for (int i = 0; i < target.length(); ++i) {
            if (!unguessedLetters.contains(Character.toLowerCase(target.charAt(i))))
                unguessedLetters.add(Character.toLowerCase(target.charAt(i)));
        }

        this.guesses = 0;
        wrongGuesses = maxGuesses;
        this.remainingHints = maxHints;
    }

    public String getWord() {
        return word;
    }

    public int getGuesses() {
        return guesses;
    }

    public int getWrongGuesses() {
        return wrongGuesses;
    }

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
                unguessedLetters.clear();
                return true;
            } else return false;
        }

        letter = str.charAt(0);

        if (letter == '?') {
            hint();
            return false;
        }

        for (int i = 0; i < unguessedLetters.size(); ++i) { // Loop over the not got
            if (unguessedLetters.get(i) == letter) {
                unguessedLetters.remove(i);
                guessedLetters.add(letter);
                guesses++;
                return true;
            }
        }

        guesses++; // One more guess
        wrongGuesses--;
        return false;
    }

    boolean won() {
        return unguessedLetters.size() == 0;
    }

    boolean lost() {
        return (unguessedLetters.size() > 0 && wrongGuesses == 0);
    }

    void hint() {
        if (remainingHints == 0) {
            System.out.println("No more hints allowed");
        }

        System.out.print("Try: ");
        System.out.println(unguessedLetters.get((int) (Math.random() * unguessedLetters.size())));
    }


}
