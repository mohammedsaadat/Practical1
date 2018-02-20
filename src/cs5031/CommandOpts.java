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
     * Constructor for CommandOpts class which assigns maxGuesses, maxHints
     * and wordSource to their default values.
     */
    CommandOpts() {
        maxGuesses = MAX_GUESSES;
        maxHints = MAX_HINTS;
        wordSource = "";
    }

    /**
     * Handles parsing the arguments passed to their corresponding variables
     * and assign them to it.
     * @param args String array that is passed from the main method and it contains flags and values
     *             for the number of maximum hints and guesses as well as the path to the file with
     *             the words.
     * @throws NumberFormatException This exception is thrown when the input for the maximum hints/guesses
     *                               number is not an integer.
     */
    public void parseArgument(String[] args) throws NumberFormatException{
        // in  case the argument passed is null.
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

    /**
     * Returns the maximum number of guesses.
     * @return integer that represents the maximum number of guesses
     */
    public int getMaxguesses() {
        return maxGuesses;
    }

    /**
     * Returns the maximum number of hints.
     * @return integer that represents the maximum number of hints.
     */
    public int getMaxhints() {
        return maxHints;
    }

    /**
     * Returns the path to the file that contains the words.
     * @return string the represents the path to the file with words.
     */
    public String getWordsource() {
        return wordSource;
    }
}
