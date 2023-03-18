
//20 questions
//File:	QuestionTree.java
/* Purpose: This class creates the tree
 * 			that is traversed and kept for 
 * 			rounds of 20 questions, being updated
 * 			as the user plays and the computer
 * 			loses. Being able to read in trees from
 * 			a file, or being able to save a game
 * 			to a file to be loaded later.
 * 		
*/
import java.io.*;
import java.util.*;
public class QuestionTree {
	
	public QuestionNode rootNode;
	public int games;
	public int wins;
	public int height;
	public UserInterface nui;
	
	// constructor to initialize the tree
	public QuestionTree(UserInterface ui) {
		rootNode = new QuestionNode("Computer");
		nui = ui;
		games = 0;
		wins = 0;
		height = 1;
	}// end of QuestionTree method
	
	// method to call the recursive version
	// to be able to traverse
	// all the questions of the tree and find
	// an answer
	public void play() {
		rootNode = playRecursive(rootNode);
	}// end of play method
	
	// method to recursively traverse the tree
	private QuestionNode playRecursive(QuestionNode current) {
		String newItem;
		String newQuestion;
		String newAnswer;
		QuestionNode newNode;
		boolean response;
		QuestionNode newLeft;
		QuestionNode newRight;
		// if the current node is a leaf node
		// then it will print out the answer
		// and the user will decide if it 
		// is right or not
		if(current.left == null && current.right ==null) {
			nui.println("Would your object happen to be "+current.data+"?");
			// if the computer guessed right
			// then the computer wins
			if(nui.nextBoolean()) {
				nui.println("I win!");
				wins++;
			}
			// else the computer lost, and will update
			// the tree with the new nodes and information 
			// the user inputs
			else {
				nui.println("I lose. What is your object?" );
				newItem = nui.nextLine();
				nui.println("Type a yes/no question to distignuish your"
						+ " item from "+ current.data);
				newQuestion = nui.nextLine();
				nui.println("And what is the answer for your object?" );
				newAnswer = nui.nextLine();
				// if the user said yes to their new question
				// then it will update the nodes properly
				if(newAnswer.trim().toLowerCase().startsWith("y")) {
					newLeft = new QuestionNode(newItem);
					newRight = new QuestionNode(current.data);
					newNode = new QuestionNode(newQuestion, newLeft, newRight );
				}
				// and if it's no will swap the left and right nodes
				// updating properly
				else {
					newLeft = new QuestionNode(current.data);
					newRight = new QuestionNode(newItem);
					newNode = new QuestionNode(newQuestion, newLeft, newRight);
				}// end of if/else
				
				
				current = newNode;
				
				
			}// end of if/else
			games++;
		}
		// if it is not a leaf node, then will
		// recursively go through the function
		// traversing the current node's children
		else {
			// checks the user response to the question
			// and will traverse either left node
			// for yes, or right node for no
			nui.print(current.data);
			response = nui.nextBoolean();
			if(response) {
				current.left = playRecursive(current.left);
			}
			else {
				current.right = playRecursive(current.right);
			}// end of if/else
		}// end of if/else
		
		return current;
		
	}// end of playRecursive method
	
	// method to throw an exception
	// and calls the recursive function
	// to be able to print the entire
	// tree to a file
	public void save(PrintStream output) {
		// checks for the exception
		if(output == null) {
			throw new IllegalArgumentException();
		}// end of if
		saveRecursion(rootNode, output);
		
	}// end of save method
	
	// method to recursively store the current tree
	// to a file
	public void saveRecursion(QuestionNode root, PrintStream output) {
		// if the line is a leaf node and
		// begins with an answer
		// writes it out as such
		if(root.left == null && root.right == null) {
			output.print("A: ");
			output.println(root.data);
		}
		// otherwise it is a question node
		// that has two children nodes
		// then calling on each of those nodes recursively
		// to print out the tree in a 
		// preorder traversal
		else {
			output.print("Q: ");
			output.println(root.data);
			saveRecursion(root.left, output);
			saveRecursion(root.right, output);
		}// end of if/else
	}// end of saveRecursion method
	
	// method to read from a scanner
	// that reads from a file 
	// until it is empty
	public void load(Scanner input) {
		// while loop to run through the whole
		// file
		while(input.hasNextLine()) {
			rootNode = loadRecursion(input); 
		}// end of while
	}// end of load method
	
	// method to recursively load a tree from a txt file
	public QuestionNode loadRecursion(Scanner input) {
		// two strings created to store whether
		// it is an answer or a question, and 
		// a string storing the contents of the 
		// node
		String QA = input.next();
		String data = input.nextLine();
		
		QuestionNode newRoot = new QuestionNode(data);
		// if the line is a question, then 
		// it will recursively call this function
		// to do the left and right node now
		// once it gets to a leaf node, it will
		// return it
		if(QA.equals("Q:")) {
			newRoot.left = loadRecursion(input);
			newRoot.right = loadRecursion(input);
		}// end of if
		
		return newRoot;
	}// end of loadRecursion method
	
	// method that returns how many games were played
	public int totalGames() {
		return games;
	}// end of totalGames method
	
	// method that returns how many times the computer won
	public int gamesWon() {
		return wins;
	}// end of gamesWon method
	
	

}
