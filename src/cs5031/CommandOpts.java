package cs5031;

public class CommandOpts {
    private int maxGuesses;
    private int maxHints;

    private String wordSource;

    private static final int MAX_GUESSES = 10;
    private static final int MAX_HINTS = 2;

    CommandOpts(String[] args) {
        maxGuesses = MAX_GUESSES;
        maxHints = MAX_HINTS;
        wordSource = "";
        for (int i = 0; i < args.length; ++i) {
            if (args[i].equals("--guesses")) {
                maxGuesses = Integer.parseInt(args[i + 1]);
                i++;
            } else if (args[i].equals("--hints")) {
                maxHints = Integer.parseInt(args[i + 1]);
                i++;
            } else {
                wordSource = args[i];
            }
        }
    }

    public int getMaxguesses() {
        return maxGuesses;
    }

    public int getMaxhints() {
        return maxHints;
    }

    public String getWordsource() {
        return wordSource;
    }
}
