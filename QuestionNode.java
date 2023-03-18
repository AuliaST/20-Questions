
//20 questions
//File:	QuestionNode.java
/* Purpose: This class is to be able 
 * 			to create a node for questions
 * 			having a left and right child
 * 		
*/
public class QuestionNode {
	public String data;
	public QuestionNode left;
	public QuestionNode right;
	
	// constructor to create a leaf node
	public QuestionNode(String data) {
		this(data,null,null);
	}// end of QuestionNode metehod
	
	// method to create a parent node with children
	public QuestionNode (String data, QuestionNode left, QuestionNode right){
		this.data = data;
		this.left = left;
		this.right = right;
	}// end of QuestionNode method

}
