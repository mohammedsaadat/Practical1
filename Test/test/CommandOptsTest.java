
import HangmanGame.CommandOpts;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandOptsTest {

	@Test
	public void optionsTest() {
		String[] args = {"--guesses", "2", "--hints", "4", "--source", "words.txt"};
		CommandOpts opts = new CommandOpts();
		opts.parseArgument(args);
		assertEquals(opts.getMaxguesses(), 2);
		assertEquals(opts.getMaxhints(), 4);
		assertEquals(opts.getWordsource(), "words.txt");
	}

	@Test
	public void testOneInputArgs() {
        String[] args = {"--source", "words.txt"};
        CommandOpts opts = new CommandOpts();
        opts.parseArgument(args);
        assertEquals(opts.getMaxguesses(), CommandOpts.MAX_GUESSES);
        assertEquals(opts.getMaxhints(), CommandOpts.MAX_HINTS);
        assertEquals(opts.getWordsource(), "words.txt");
    }

    @Test(expected = NumberFormatException.class)
    public void testWrongArgs() {
        String[] args = {"--guesses", "--hints", "4", "--source", "words.txt"};
        CommandOpts opts = new CommandOpts();
        opts.parseArgument(args);
    }

    @Test
    public void testOptionDifferentOrder() {
        String[] args = {"--hints", "2", "--guesses", "4", "--source", "words.txt"};
        CommandOpts opts = new CommandOpts();
        opts.parseArgument(args);
        assertEquals(opts.getMaxguesses(), 4);
        assertEquals(opts.getMaxhints(), 2);
        assertEquals(opts.getWordsource(), "words.txt");
	}

    @Test
    public void testOptionDifferentOrder2() {
        String[] args = {"--source", "words.txt", "--hints", "4", "--guesses", "2"};
        CommandOpts opts = new CommandOpts();
        opts.parseArgument(args);
        assertEquals(opts.getMaxguesses(), 2);
        assertEquals(opts.getMaxhints(), 4);
        assertEquals(opts.getWordsource(), "words.txt");
    }

    @Test
    public void testOptionWithNoArgument() {
        String[] args = {};
        CommandOpts opts = new CommandOpts();
        opts.parseArgument(args);
        assertEquals(opts.getMaxguesses(), CommandOpts.MAX_GUESSES);
        assertEquals(opts.getMaxhints(), CommandOpts.MAX_HINTS);
        assertEquals(opts.getWordsource(), "");
    }

    @Test
    public void testOptionWithNullArgument() {
        String[] args = null;
        CommandOpts opts = new CommandOpts();
        opts.parseArgument(args);
        assertEquals(opts.getMaxguesses(), CommandOpts.MAX_GUESSES);
        assertEquals(opts.getMaxhints(), CommandOpts.MAX_HINTS);
        assertEquals(opts.getWordsource(), "");
    }

    @Test
    public void testOptionWithWrongArguments() {
        String[] args = {"-source", "words.txt", "-hints", "4", "--bla", "2"};
        CommandOpts opts = new CommandOpts();
        opts.parseArgument(args);
        assertEquals(opts.getMaxguesses(), CommandOpts.MAX_GUESSES);
        assertEquals(opts.getMaxhints(), CommandOpts.MAX_HINTS);
        assertEquals(opts.getWordsource(), "");
    }
}
