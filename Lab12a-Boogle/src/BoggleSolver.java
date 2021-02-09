import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.awt.Point;

public class BoggleSolver
{

	private HashSet<String> dictionary;

	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	public BoggleSolver(String dictionaryName)
	{
		try(Scanner s = new Scanner(new File(dictionaryName))) {
			this.dictionary = new HashSet<>();
			while(s.hasNext()) {
				dictionary.add(s.next());
			}
		} catch(FileNotFoundException e) {
			System.err.println("Invalid File");
			System.exit(-1);
		}
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		ArrayList<String> words = new ArrayList<>();
		for(int r = 0 ; r < board.rows(); r++) {
			for(int c = 0 ; c < board.cols() ; c++) {
				words.addAll(getAllValidWordsStartingAt(board, r, c));
			}
		}
		return words;
	}

	private ArrayList<String> getAllValidWordsStartingAt(BoggleBoard b, int r, int c) {
		Stack<Point> visited = new Stack<>();
		return getAllValidWordsHelper(b, visited, r, c);
	}

	private ArrayList<String> getAllValidWordsHelper(BoggleBoard b, Stack<Point> stack, int r, int c) {


		// Checks if the dice is out of bound and if the the stack already contains the point
		if(r < 0 || r > b.rows() - 1 || c < 0 || c > b.cols() - 1 || stack.contains(new Point(r, c))) {
			return new ArrayList<>();
		}


		ArrayList<String> word = new ArrayList<>();
		stack.push(new Point(r, c));
		String verify = concat(b, stack);
		if(dictionary.contains(verify) && verify.length() > 2) word.add(verify);
		
		// Looks for adjacent dice
		var n = getAllValidWordsHelper(b, stack, r + 1, c);
		// stack.pop();
		var ne = getAllValidWordsHelper(b, stack, r + 1, c + 1);
		// stack.pop();
		var e = getAllValidWordsHelper(b, stack, r, c + 1);
		// stack.pop();
		var se = getAllValidWordsHelper(b, stack, r - 1, c + 1);
		// stack.pop();
		var s = getAllValidWordsHelper(b, stack, r - 1, c);
		// stack.pop();
		var sw = getAllValidWordsHelper(b, stack, r - 1, c - 1);
		// stack.pop();
		var w = getAllValidWordsHelper(b, stack, r, c - 1);
		// stack.pop();
		var nw = getAllValidWordsHelper(b, stack, r + 1, c - 1);
		// stack.pop();

		stack.pop();
		return merge(word, n, ne, e, se, s, sw, w, nw);
		
	} 


	private String concat(BoggleBoard b, Stack<Point> stack) {
		String s = "";
		Stack<Point> copy = (Stack<Point>)stack.clone();
		while(!copy.isEmpty()) {
			Point p = copy.pop();
			char letter = b.getLetter((int)p.getY(), (int)p.getX());
			// Edge case Q -> QU
			s = (letter == 'Q' ? "QU" : letter)  + s;
		}
		return s;
	}

	private ArrayList<String> merge(ArrayList<String>... sources) {
		int counter = 0;
		for(var source: sources) {
			counter += source.size();
		}
		ArrayList<String> target = new ArrayList<>(counter);
		for(var source: sources) {
			target.addAll(source);
		}
		return target;
	}
	


	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word)
	{
		if(dictionary.contains(word)) {
			return switch(word.length()) {
				case 0 -> 0;
				case 1 -> 0;
				case 2 -> 0;
				case 3 -> 1;
				case 4 -> 1;
				case 5 -> 2;
				case 6 -> 3;
				case 7 -> 5;
				default -> 11;
			};
		}
		return 0;
	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		final String PATH   = "./data/";
		BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84

		new BoggleGame(4, 4);
	}

}
