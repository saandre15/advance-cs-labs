package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.util.Collections;
import java.util.List;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */
public class GameTree
{


	private class Node
	{
		// left - yes | right - no
		private Node left, right;
		private String val;
		private boolean isDebug;
		
		public Node(String s, boolean isDebug)
		{
			
				this.isDebug = isDebug;
				String[] list = s.split("\n");
				this.val = list[0];
				if(list[0].contains("?")) {
					this.set(Choice.No, Arrays.copyOfRange(list, 1, list.length));
					String next = left.getMax();
					int nextIndex = -1;
					for(int i = 0 ; i < list.length ; i++) {
						String cur = list[i];
						if(cur.equals(next)) nextIndex = i;
					}
					if(nextIndex == -1) throw new IndexOutOfBoundsException("Failed to find the right tree node properly.");
					nextIndex+=1;
					this.set(Choice.Yes, Arrays.copyOfRange(list, nextIndex, list.length));
				}
			
		}

		private void set(Choice choice, String[] nodes) {
			if(nodes[0].contains("?")) set(choice, IntStream.range(0, nodes.length)
				.mapToObj(index -> nodes[index]+"\n")
				.reduce((prev, cur) -> prev + cur)
				.orElseThrow(RuntimeException::new));
			else set(choice, nodes[0]);
		} 

		public void set(Choice choice, String val) {
			if(choice.equals(Choice.Yes)) this.right = new Node(val, isDebug);
			else this.left = new Node(val, isDebug);
		}



		public void replace(String noAnswer, String question, String yesAnswer)  {

				traverse((parent, child) -> {
					if(child.val.equals(noAnswer)) {
						Node n = new Node(question + "\n" + yesAnswer + "\n" + noAnswer + "\n", isDebug);
						if(parent.left != null && parent.left.val.equals(noAnswer) ) {
							parent.left = n;
							System.out.println("parent left" + parent.left.left.right);
							current = parent.left;
							if(isDebug) System.out.println("Parent has replaced left child node." );
						}
						if(parent.right != null && parent.right.val.equals(noAnswer)) {
							parent.right = n;
							current = parent.right;
							if(isDebug) System.out.println("Parent has replaced right child node." );
						}
					}
				});
		}


		/**
		 * traverse from bottom to root 
		 * @param fn <ParentNode, ChildNode>
		 */
		private void traverse(BiConsumer<Node, Node>  fn ) {
			if(left != null) {
				left.traverse(fn);
				fn.accept(this, left);
			}
			if(right != null) {
				right.traverse(fn);
				fn.accept(this, right);
			}
		}

		public boolean isAnswer() {
			System.out.println(val);
			return left == null && right == null;
		}

		public String getMax() {
			if(this.right != null) return this.right.getMax();
			else return this.val;
		}

		@Override
		public String toString() {
			return toString(0);
		}
 
		public String toString(int level) {
			String temp = "";
			temp += getNodeString(level);
			return temp;
		}

		private String getNodeString(int level) {
			if(level == 0) return val;
			return IntStream.range(0, level)
			.mapToObj(index -> "- ")
			.reduce((prev, cur) -> prev + cur)
			.orElseThrow(RuntimeException::new) + val + "\n";
		}

		private String getSaveableOutput() {
			String output = "";
			output += val;
			if(left != null) output += left.getSaveableOutput();
			if(right != null) output = right.getSaveableOutput();
			return output;
		}
		


	}

	private Node root;
	private Node current;
	private boolean isDebug;

	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
	public GameTree(String fileName, boolean isDebug)
	{
		this.isDebug = true;
		try(Scanner s = new Scanner(new File(fileName))) {
			String temp = "";
			while(s.hasNextLine()) { temp += s.nextLine() + "\n"; }
			this.root = new Node(temp, isDebug);
			this.current = this.root;
			
		} catch (FileNotFoundException e) {
			System.err.println("Game Tree has an invalid filename");
			System.exit(0);
		}
	}

	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */
	public void add(String newQ, String newA)
	{
		if(current.isAnswer()) root.replace(current.val, newQ, newA);
	}

	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer()
	{
		return current.isAnswer();
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent()
	{
		return current.toString();
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo)
	{
		if(yesOrNo.equals(Choice.No)) current = current.right;
		else current = current.left;
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart()
	{
		current = root;
	}

	@Override
	public String toString()
	{
		return "Current:\n" 
		+ current.getNodeString(0) + "\n"
		+ "Entire Tree:\n"
		+ root.toString();
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame()
	{
		String outputFileName = "output.data";
		try(PrintWriter file = new PrintWriter(new File(outputFileName))) {
			file.write(root.getSaveableOutput());
		} catch(IOException e) {
			System.out.println("Could not create file: " + outputFileName);
		}
	}
}
