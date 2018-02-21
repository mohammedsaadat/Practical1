package cs5031;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class GameStateTest {

    @Test
    public void testGameStateConstructor() {
        String word = "LOVE";
        GameState gameState = new GameState(word, 20, 20);

        assertEquals(gameState.getWord(), word);
        assertEquals(gameState.getRemainingGuesses(), 20);
        assertEquals(gameState.getGuesses(), 0);
        assertEquals(gameState.getRemainingHints(), 20);
    }

	@Test
	public void testInitialisingUnGuessedArray() {
	    String word = "hero";
        GameState gameState = new GameState(word, 10, 4);
        ArrayList<Character> arrayList = new ArrayList<>();
        ArrayList<Character> expectedArrayList = new ArrayList<>();

        expectedArrayList.add('h');
        expectedArrayList.add('e');
        expectedArrayList.add('r');
        expectedArrayList.add('o');

        gameState.initialiseUnGuessedArray(word, arrayList);

        assertEquals(arrayList, expectedArrayList);
	}

	@Test
    public void testInitialisingUnGuessedArrayRepeatedLetters() {
        String word = "herro";
        GameState gameState = new GameState(word, 10, 4);
        ArrayList<Character> arrayList = new ArrayList<>();
        ArrayList<Character> expectedArrayList = new ArrayList<>();

        expectedArrayList.add('h');
        expectedArrayList.add('e');
        expectedArrayList.add('r');
        expectedArrayList.add('o');

        gameState.initialiseUnGuessedArray(word, arrayList);

        assertEquals(arrayList, expectedArrayList);
    }

    @Test
    public void testInitialisingUnGuessedArrayEmpty() {
        String word = "";
        GameState gameState = new GameState(word, 10, 4);
        ArrayList<Character> arrayList = new ArrayList<>();
        ArrayList<Character> expectedArrayList = new ArrayList<>();

        gameState.initialiseUnGuessedArray(word, arrayList);

        assertEquals(arrayList, expectedArrayList);
    }


    @Test
    public void testInitialisingUnGuessedArrayWithSpacedInput() {
        String word = "Love u";
        GameState gameState = new GameState(word, 10, 4);
        ArrayList<Character> arrayList = new ArrayList<>();
        ArrayList<Character> expectedArrayList = new ArrayList<>();

        expectedArrayList.add('l');
        expectedArrayList.add('o');
        expectedArrayList.add('v');
        expectedArrayList.add('e');
        expectedArrayList.add('u');

        gameState.initialiseUnGuessedArray(word, arrayList);

        assertEquals(arrayList, expectedArrayList);
    }

    @Test
    public void testCreateShowWordStringFullWord() {
        String word = "Love";
        GameState gameState = new GameState(word, 10, 4);
        ArrayList<Character> guessed = new ArrayList<>();
        guessed.add('l');
        guessed.add('o');
        String result = gameState.createShowWordString(word, guessed);
        assertEquals("Lo--", result);
    }

    @Test
    public void testCreateShowWordStringWordWithSpace() {
        String word = "I Love u";
        GameState gameState = new GameState(word, 10, 4);
        ArrayList<Character> guessed = new ArrayList<>();
        guessed.add('l');
        guessed.add('o');
        guessed.add('u');
        String result = gameState.createShowWordString(word, guessed);
        assertEquals("- Lo-- u", result);
    }

    @Test
    public void testCreateShowWordStringWordWithNoGuess() {
        String word = "I Love u";
        GameState gameState = new GameState(word, 10, 4);
        ArrayList<Character> guessed = new ArrayList<>();
        String result = gameState.createShowWordString(word, guessed);
        assertEquals("- ---- -", result);
    }

    @Test
    public void testCreateShowWordStringWordWithAllGuesses() {
        String word = "Love u";
        GameState gameState = new GameState(word, 10, 4);
        ArrayList<Character> guessed = new ArrayList<>();
        guessed.add('l');
        guessed.add('o');
        guessed.add('v');
        guessed.add('e');
        guessed.add('u');
        String result = gameState.createShowWordString(word, guessed);
        assertEquals(word, result);
    }

    @Test
    public void testParseInputWord() {
        GameState gameState = new GameState("Hello", 20, 20);
        // Trying out different lengths.
        int result = gameState.parseInput("Hey");
        int result2 = gameState.parseInput("hi");

        assertEquals(result, GameState.WORD);
        assertEquals(result2, GameState.WORD);
    }

    @Test
    public void testParseInputLetter() {
        GameState gameState = new GameState("Hello", 20, 20);
        // Trying out different cases.
        int result = gameState.parseInput("H");
        int result2 = gameState.parseInput("h");

        assertEquals(result, GameState.LETTER);
        assertEquals(result2, GameState.LETTER);
    }

    @Test
    public void testParseInputHint() {
        GameState gameState = new GameState("Hello", 20, 20);
        int result = gameState.parseInput("?");

        assertEquals(result, GameState.HINT);
    }

    @Test
    public void testParseInputWrongInput() {
        GameState gameState = new GameState("Hello", 20, 20);
        // Testing out different wrong inputs.
        int result = gameState.parseInput("-");
        int result2 = gameState.parseInput("9");
        int result3 = gameState.parseInput("");

        assertEquals(result, GameState.WRONG_INPUT);
        assertEquals(result2, GameState.WRONG_INPUT);
        assertEquals(result3, GameState.WRONG_INPUT);
    }

    @Test
    public void testParseInputWithNullInput() {
        GameState gameState = new GameState("Hello", 20, 20);
        // Testing out different wrong inputs.
        int result = gameState.parseInput(null);

        assertEquals(result, GameState.WRONG_INPUT);
    }

    @Test
    public void testHandleWordInputCorrectGuess() {
        GameState gameState = new GameState("hi", 10, 10);

        String message = gameState.handleWordInput("hi", gameState.getWord());
        int numOfGuesses = gameState.getGuesses();

        assertEquals(message, GameState.CORRECT_GUESS);
        assertEquals(1, numOfGuesses);
        assertEquals(10, gameState.getRemainingGuesses());
        assertEquals(0, gameState.getUnGuessedLetters().size());
    }

    @Test
    public void testHandleWordInputWrongGuess() {
        GameState gameState = new GameState("hi", 10, 10);

        String message = gameState.handleWordInput("hr", gameState.getWord());
        int numOfGuesses = gameState.getGuesses();
        int remainingGuesses = gameState.getRemainingGuesses();

        assertEquals(message, GameState.INCORRECT_GUESS);
        assertEquals(1, numOfGuesses);
        assertEquals(9, remainingGuesses);

    }

    @Test
    public void testGenerateHint() {
        GameState gameState = new GameState("hi", 10, 10);
        gameState.initialiseUnGuessedArray(gameState.getWord(), gameState.getUnGuessedLetters());
        Character hint = gameState.generateHint();

        ArrayList<Character> unGuessedList = gameState.getUnGuessedLetters();

        assertTrue(unGuessedList.contains(hint));
        assertEquals(9, gameState.getRemainingHints());
    }

    @Test
    public void testGenerateHintNoMoreHints() {
        GameState gameState = new GameState("hi", 10, 1);
        gameState.initialiseUnGuessedArray(gameState.getWord(), gameState.getUnGuessedLetters());
        Character hint = gameState.generateHint();
        Character hint2 = gameState.generateHint();

        ArrayList<Character> unGuessedList = gameState.getUnGuessedLetters();

        //For the first hint.
        assertTrue(unGuessedList.contains(hint));

        assertEquals(0, gameState.getRemainingHints());
        assertEquals(GameState.NO_HINTS, hint2);
    }

    @Test
    public void testHandleHintInputNoHint() {
        GameState gameState = new GameState("hi", 10, 0);
        gameState.initialiseUnGuessedArray(gameState.getWord(), gameState.getUnGuessedLetters());
        String message = gameState.handleHintInput();


        assertEquals(GameState.NO_MORE_HINT_MSG, message);
    }
}
