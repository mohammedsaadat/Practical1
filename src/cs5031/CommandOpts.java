package cs5031;

public class CommandOpts {
	private int maxguesses;
	private int maxhints;
	
	private String wordsource;
	
	CommandOpts(String[] args) {
		maxguesses = 10;
		maxhints = 2;
		
		wordsource = "";
		
		for(int i = 0; i < args.length; ++i) {
			if (args[i].equals("--guesses")) {
				maxguesses = Integer.parseInt(args[i+1]);
				i++;
			}
			else if (args[i].equals("--hints")) {
				maxhints = Integer.parseInt(args[i+1]);
				i++;
			}
			else wordsource = args[i];
		}
	}

    public int getMaxguesses() {
        return maxguesses;
    }

    public int getMaxhints() {
        return maxhints;
    }

    public String getWordsource() {
        return wordsource;
    }
}
