package cs5031;

public class CommandOpts {
    private int maxGuesses;
    private int maxHints;

    private String wordSource;

    static final int MAX_GUESSES = 10;
    static final int MAX_HINTS = 2;

    CommandOpts(String[] args) {
        maxGuesses = MAX_GUESSES;
        maxHints = MAX_HINTS;
        wordSource = "";
        for (int i = 0; i < args.length; ++i) {

            switch (args[i]) {
                case "--guesses":
                    maxGuesses = Integer.parseInt(args[i + 1]);
                    i++;
                    break;

                case "--hints":
                    maxHints = Integer.parseInt(args[i + 1]);
                    i++;
                    break;

                default:
                    wordSource = args[i];
                    break;
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
