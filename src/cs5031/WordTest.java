package cs5031;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class WordTest {

    @Test
    public void testRandomWordReturnCounties() {
        String returnedWord = Word.randomWord(Word.COUNTIES);
        assertThat(Arrays.asList(Word.getCounties()), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordReturnCountries() {
        String returnedWord = Word.randomWord(Word.COUNTRIES);
        assertThat(Arrays.asList(Word.getCountries()), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordReturnCities() {
        String returnedWord = Word.randomWord(Word.CITIES);
        assertThat(Arrays.asList(Word.getCities()), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordWrongInput() {
        String returnedWord = Word.randomWord(4);
        assertEquals("Wrong Input", returnedWord);
    }
}
