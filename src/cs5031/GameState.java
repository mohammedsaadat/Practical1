package cs5031;

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
    private String word;
    private int guesses;
    private int wrongGuesses;
    private int remainingHints;

    private ArrayList<Character> got;
    private ArrayList<Character> not;

    private Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public GameState(String target, int maxGuesses, int maxHints) {
        this.word = target;
        not = new ArrayList<Character>();
        got = new ArrayList<Character>();

        for (int i = 0; i < target.length(); ++i) {
            if (!not.contains(Character.toLowerCase(target.charAt(i))))
                not.add(Character.toLowerCase(target.charAt(i)));
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
            if (got.contains(word.charAt(i))) {
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
                not.clear();
                return true;
            } else return false;
        }

        letter = str.charAt(0);

        if (letter == '?') {
            hint();
            return false;
        }

        for (int i = 0; i < not.size(); ++i) { // Loop over the not got
            if (not.get(i) == letter) {
                not.remove(i);
                got.add(letter);
                guesses++;
                return true;
            }
        }

        guesses++; // One more guess
        wrongGuesses--;
        return false;
    }

    boolean won() {
        return not.size() == 0;
    }

    boolean lost() {
        return (not.size() > 0 && wrongGuesses == 0);
    }

    void hint() {
        if (remainingHints == 0) {
            System.out.println("No more hints allowed");
        }

        System.out.print("Try: ");
        System.out.println(not.get((int) (Math.random() * not.size())));
    }


}
