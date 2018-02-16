package cs5031;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandOptsTest {

	@Test
	public void optionsTest() {
		String[] args = { "--guesses", "2", "--hints", "4", "words.txt" };
		CommandOpts opts = new CommandOpts(args);
		assertEquals(opts.getMaxguesses(), 2);
		assertEquals(opts.getMaxhints(), 4);
		assertEquals(opts.getWordsource(), "words.txt");
	}

}
