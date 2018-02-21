package hangman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Handles obtaining random words from a file or the pre-defined arrays.
 */
public class WordPicker {
    /**
     * Constant that refers to the counties category.
     */
    public static final int COUNTIES = 1;

    /**
     * Constant that refers to the countries category.
     */
    public static final int COUNTRIES = 2;

    /**
     * Constant that refers to the cities category.
     */
    public static final int CITIES = 3;

    /**
     * Constant string array that contains counties words.
     */
    public static final String[] COUNTIES_WORDS = {"Argyll and Bute",
            "Caithness", "Kingdom of Fife", "East Lothian", "Highland",
            "Dumfries and Galloway", "Renfrewshire", "Scottish Borders",
            "Perth and Kinross"};

    /**
     * Constant string array that contains countries words.
     */
    public static final String[] COUNTRIES_WORDS = {"Scotland", "England",
            "Wales", "Northern Ireland", "Ireland", "France", "Germany",
            "Netherlands", "Spain", "Portugal", "Belgium", "Luxembourg",
            "Switzerland", "Italy", "Greece"};

    /**
     * Constant string array that contains cities words.
     */
    public static final String[] CITIES_WORDS = {"St Andrews", "Edinburgh",
            "Glasgow", "Kirkcaldy", "Perth", "Dundee", "Stirling", "Inverness",
            "Aberdeen", "Falkirk"};

    /**
     * ArrayList holds words from the file.
     */
    private static ArrayList<String> customWords;

    /**
     * Used to generate integers when obtaining random words.
     */
    private static Random random = new Random();

    /**
     * Returns a random word from the chosen predefined category array.
     * @param category Integer refers to the category (Counties = 1,
     *                 Countries = 2, Cities = 3).
     * @return String represents a random word from the selected category.
     * @throws WrongCategoryException This exception is thrown when the input
     * for the category does not exist.
     */
    public static String getRandomWord(final int category)
            throws WrongCategoryException {
        if (category == COUNTIES) {
            return COUNTIES_WORDS[(random.nextInt(COUNTIES_WORDS.length))];
        } else if (category == COUNTRIES) {
            return COUNTRIES_WORDS[random.nextInt(COUNTRIES_WORDS.length)];
        } else if (category == CITIES) {
            return CITIES_WORDS[random.nextInt(CITIES_WORDS.length)];
        } else {
            throw new WrongCategoryException();
        }
    }

    /**
     * Returns a random word from a text file.
     * @param wordSource String the represents the path to the file.
     * @return random string obtained from the file.
     * @throws IllegalArgumentException Thrown when the input file is empty.
     * @throws FileNotFoundException Thrown when the file does not exist.
     */
    public static String getRandomWord(final String wordSource)
            throws IllegalArgumentException, FileNotFoundException {
        String line;
        customWords = new ArrayList<>();
        try {
            InputStreamReader file = new InputStreamReader(
                    new FileInputStream(wordSource), "UTF-8");
            BufferedReader reader = new BufferedReader(file);
            while ((line = reader.readLine()) != null) {
                customWords.add(line);
            }

            reader.close();
            return customWords.get(random.nextInt(customWords.size()));
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();

        } catch (IOException e) {
            System.out.println("IO error");
            return "";
        }
    }
}
