package Test;

import hangman.WordPicker;
import hangman.WrongCategoryException;
import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class WordPickerTest {

    @Test
    public void testRandomWordReturnCounties() throws WrongCategoryException {
        String returnedWord = WordPicker.getRandomWord(WordPicker.COUNTIES);
        assertThat(Arrays.asList(WordPicker.COUNTIES_WORDS), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordReturnCountries() throws WrongCategoryException {
        String returnedWord = WordPicker.getRandomWord(WordPicker.COUNTRIES);
        assertThat(Arrays.asList(WordPicker.COUNTRIES_WORDS), hasItem(returnedWord));
    }

    @Test
    public void testRandomWordReturnCities() throws WrongCategoryException {
        String returnedWord = WordPicker.getRandomWord(WordPicker.CITIES);
        assertThat(Arrays.asList(WordPicker.CITIES_WORDS), hasItem(returnedWord));
    }

    @Test(expected = WrongCategoryException.class)
    public void testRandomWordWrongInput() throws WrongCategoryException {
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

    @Test
    public void testRandomWordFileInputMultipleWords() {
        String[] fileWords = {"Hello", "Bye", "Dubai", "May"};
        try {
            File file = File.createTempFile("temp", ".txt", new File("."));
            file.deleteOnExit();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            for (String word:fileWords) {
                bufferedWriter.write(word+"\n");
            }
            bufferedWriter.close();

            String returnedWord = WordPicker.getRandomWord(file.getAbsolutePath());
            assertThat(Arrays.asList(fileWords), hasItem(returnedWord));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRandomWordEmptyFile() {
        try {
            File file = File.createTempFile("temp", ".txt", new File("."));
            file.deleteOnExit();

            String returnedWord = WordPicker.getRandomWord(file.getAbsolutePath());
            assertEquals("", returnedWord);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = FileNotFoundException.class)
    public void testRandomWordFileDoesNotExist() throws FileNotFoundException {
        String returnedWord = WordPicker.getRandomWord("herro.txt");
    }
}
