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

public class WordTest {

    @Test
    public void testRandomWordReturnCounties() {
        String returnedWord = WordPicker.getRandomWord(WordPicker.COUNTIES);
        assertThat(Arrays.asList(WordPicker.COUNTIES_WORDS), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordReturnCountries() {
        String returnedWord = WordPicker.getRandomWord(WordPicker.COUNTRIES);
        assertThat(Arrays.asList(WordPicker.COUNTRIES_WORDS), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordReturnCities() {
        String returnedWord = WordPicker.getRandomWord(WordPicker.CITIES);
        assertThat(Arrays.asList(WordPicker.CITIES_WORDS), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordWrongInput() {
        String returnedWord = WordPicker.getRandomWord(4);
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

            String returnedWord = WordPicker.getRandomWord(file.getAbsolutePath());
            assertEquals(fileWord, returnedWord);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
