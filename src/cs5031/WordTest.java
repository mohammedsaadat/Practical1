package cs5031;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class WordTest {

    @Test
    public void testRandomWordReturnCounties() {
        String returnedWord = Word.randomWord(Word.COUNTIES);
        assertThat(Arrays.asList(Word.COUNTIES_WORDS), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordReturnCountries() {
        String returnedWord = Word.randomWord(Word.COUNTRIES);
        assertThat(Arrays.asList(Word.COUNTRIES_WORDS), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordReturnCities() {
        String returnedWord = Word.randomWord(Word.CITIES);
        assertThat(Arrays.asList(Word.CITIES_WORDS), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordWrongInput() {
        String returnedWord = Word.randomWord(4);
        assertEquals("Wrong Input", returnedWord);
    }

    @Test
    public void testRandomWordFileInput() {
        String fileWord = "Hello";
        try {
            File file = File.createTempFile("temp", ".txt", new File("."));
            file.deleteOnExit();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(fileWord);
            bufferedWriter.close();

            String returnedWord = Word.randomWord(file.getAbsolutePath());
            assertEquals(fileWord, returnedWord);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
