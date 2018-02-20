package cs5031;

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
    private String word;
    private int guesses;
    private int remainingGuesses;
    private int remainingHints;

    private ArrayList<Character> guessedLetters;
    private ArrayList<Character> unGuessedLetters;

    private Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public GameState(String target, int maxGuesses, int maxHints) {
        this.word = target;
        this.guesses = 0;
        this.remainingHints = maxHints;
        remainingGuesses = maxGuesses;

        unGuessedLetters = new ArrayList<>();
        guessedLetters = new ArrayList<>();

    }

    public void initialiseUnGuessedArray(String word, ArrayList<Character> unGuessedLetters) {
        for (int i = 0; i < word.length(); ++i) {
            Character character = Character.toLowerCase(word.charAt(i));
            if (!unGuessedLetters.contains(character)) {
                unGuessedLetters.add(character);
            }
        }
    }

    public ArrayList<Character> getUnGuessedLetters() {
        return unGuessedLetters;
    }

    public String getWord() {
        return word;
    }

    public int getGuesses() {
        return guesses;
    }

    public int getRemainingGuesses() {
        return remainingGuesses;
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
