package cs5031;

/**
 * Handles retrieving information passed to the program from the arguments.
 */
public class CommandOpts {
    /**
     * Represents the maximum number of guesses that can be taken.
     */
    private int maxGuesses;

    /**
     * Represents the maximum number of hints available to the player.
     */
    private int maxHints;

    /**
     * Represents the path to the file that contains word source.
     */
    private String wordSource;

    /**
     * Default maximum number of guesses.
     */
    static final int MAX_GUESSES = 10;

    /**
     * Default maximum number of hints.
     */
    static final int MAX_HINTS = 2;

    /**
     * Constructor for CommandOpts class which assigns the arguments to the
     * proper variables.
     * @param args The argument array passed from the main method.
     * @throws NumberFormatException This is thrown whenever we have an element that can't be
     * parsed to an integer in the argument array.
     */
    CommandOpts(String[] args) throws NumberFormatException{
        maxGuesses = MAX_GUESSES;
        maxHints = MAX_HINTS;
        wordSource = "";

        if (args == null) {
            return;
        }

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
