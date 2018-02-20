package cs5031;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Word {

    static final int COUNTIES = 1;
    static final int COUNTRIES = 2;
    static final int CITIES = 3;


    private static String[] counties = {"Argyll and Bute", "Caithness", "Kingdom of Fife",
            "East Lothian", "Highland", "Dumfries and Galloway",
            "Renfrewshire", "Scottish Borders", "Perth and Kinross"};
    private static String[] countries = {"Scotland", "England", "Wales", "Northern Ireland", "Ireland",
            "France", "Germany", "Netherlands", "Spain", "Portugal",
            "Belgium", "Luxembourg", "Switzerland", "Italy", "Greece"};
    private static String[] cities = {"St Andrews", "Edinburgh", "Glasgow", "Kirkcaldy", "Perth",
            "Dundee", "Stirling", "Inverness", "Aberdeen", "Falkirk"};

    private static ArrayList<String> customWords;
    private static Random random = new Random();

    public static String randomWord(int category) {
        if (category == COUNTIES)
            return counties[(random.nextInt(9))];
        else if (category == COUNTRIES)
            return countries[random.nextInt(15)];
        else if (category == CITIES)
            return cities[random.nextInt(10)];
        else
            return "Wrong Input";
    }

    public static String randomWord(String wordsource) {
        String line;
        customWords = new ArrayList<String>();
        try {
            FileReader file = new FileReader(wordsource);
            BufferedReader reader = new BufferedReader(file);
            while ((line = reader.readLine()) != null) {
                customWords.add(line);
            }

            reader.close();
            return customWords.get((int) (Math.random() * customWords.size()));
        } catch (FileNotFoundException e) {
            System.out.println("File error");
            return "";
        } catch (IOException e) {
            System.out.println("IO error");
            return "";
        }
    }

    public static String[] getCounties() {
        return counties;
    }

    public static String[] getCountries() {
        return countries;
    }

    public static String[] getCities() {
        return cities;
    }
}
