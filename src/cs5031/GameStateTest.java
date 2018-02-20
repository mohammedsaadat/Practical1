package cs5031;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;

public class GameStateTest {

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

}
