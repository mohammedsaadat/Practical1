package cs5031;

import java.util.ArrayList;
import java.util.Scanner;

public class GameState {
	private String word;
	private int g;
	private int wrong;
	private int h;
	
	private ArrayList<Character> got;
	private ArrayList<Character> not;
	
	private Scanner sc = new Scanner(System.in).useDelimiter("\n");
	
	public GameState(String target, int g, int h) {
	    this.word = target;
	    not = new ArrayList<Character>();
	    got = new ArrayList<Character>();
		
		for(int i = 0; i < target.length(); ++i) {
			if (!not.contains(Character.toLowerCase(target.charAt(i))))
			not.add(Character.toLowerCase(target.charAt(i)));
		}

		this.g = 0;
		wrong = g;
		this.h = h;
	}

    public String getWord() {
        return word;
    }
    public int getG() {
        return g;
    }
    public int getWrong() {
        return wrong;
    }
    public int getH() {
        return h;
    }

    void showWord() {
		for (int i = 0; i < word.length(); ++i) {
			if (got.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
			} else {
				System.out.print("-");
			}
		}
		System.out.println("");
	}
	
	boolean guessLetter() {
		int i;
		char letter;
		
		System.out.print("Guess a letter or word (? for a hint): ");
		
		String str = sc.next().toLowerCase();
		
		if (str.length() > 1) {
			if (str==word) {
				not.clear();
				return true;
			} else return false;
		}
		
		letter = str.charAt(0);
		
		if (letter == '?') {
			hint();
			return false;
		}
		
		for(i = 0; i < not.size(); ++i) { // Loop over the not got
			if (not.get(i) == letter) {
				not.remove(i);
				got.add(letter);
				g++;
				return true;
			}
		}

		g++; // One more guess
		wrong--;
		return false;
	}
	
	boolean won() {
		if (not.size() == 0) return true; else return false;
	}

	boolean lost() {
		if (not.size() > 0 && wrong == 0) return true; else return false;
	}

	void hint() {
		if (h == 0) {
			System.out.println("No more hints allowed");
		}
		
		System.out.print("Try: ");
		System.out.println(not.get((int)(Math.random()*not.size())));
	}


}
