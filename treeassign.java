package oop3;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class treeassign {
	
	boolean answer= true;

	public static void main(String[] args) 
	{
		// Create a tree
		System.out.println("Constructing a test tree ...");
		BinaryTree<String> testTree = new BinaryTree<String>();
		createTree1(testTree); //passes tree to function which creates initial tree
		Scanner user_input = new Scanner(System.in); //allows program to accept user input
		String ans; //String to hold the yes/no answer provided by user
		File file = new File("test1.txt"); //creating path to file which tree will be stored in
		//testingTree(testTree);
		Scanner userScan = new Scanner(System.in); //using a scanner to read user input
		while(true) {
			BinaryNodeInterface<String> currentNode = testTree.getRootNode(); //gets root of tree
			while(! currentNode.isLeaf()) { //performs operation when the current node is not a leaf, i.e. has no children
				String msg = currentNode.getData(); //data of current node held in variable msg
				System.out.println(msg); //the data is printed to the user
				ans = userScan.nextLine(); //they provide yes or no answer which is stored in variable ans
				if (ans.equalsIgnoreCase("yes")) { //is ans == yes
					currentNode = currentNode.getLeftChild(); //proceed to the left child of the current node and make this the current node
					
				}
				else if (ans.equalsIgnoreCase("no")) { // if ans == no
					currentNode = currentNode.getRightChild(); //proceed to right child of current node and make this = currentNode
				}
				else { //if something other than "yes" or "no" is entered, ask user to enter either yes or no
					System.out.println("Enter 'yes' or 'no'");
				}
			
			}
			//code to handle when node is a leaf
			
			String guess = currentNode.getData(); //the data in the leaf node is held in string guess
			System.out.println(guess); //Present this to the user as the guess
			ans = user_input.next(); // User determines whether the guess is correct
			if (ans.equalsIgnoreCase("yes")) { //if guess is correct and user says it is correct
				System.out.println("Wow, I got it right! \n");
				System.out.println("Would you like to: \n");
				System.out.println("(1) Play again? \n");
				System.out.println("(2) Save the tree? \n");
				System.out.println("(3) Load another tree? \n");
				System.out.println("(4) Quit? \n");
				ans = user_input.next(); //accepts user input to determine what to do next
				if (ans.equalsIgnoreCase("1")) { //if they choose to play again, the program continues
					continue;
				}
				else if (ans.equalsIgnoreCase("2")) { //if they choose to save the tree
		
					BinaryNodeInterface<String> currentNode2 = testTree.getRootNode(); //get root node of tree and store it in currentNode2
					
					ArrayList<String> serialized = new ArrayList<String>(); //ArrayList to hold the serialized tree
					serialize(currentNode2, serialized); //passes the root node and the array into the serialize function
					//System.out.print(serialized.get(0));
					BufferedWriter writer;
					try {
						writer = new BufferedWriter( new FileWriter(".\\test1.txt"));
						for (int i = 0; i < serialized.size(); i++) {
							
							writer.write(serialized.get(i) + ":"); //writes each part of the ArrayList to the file //":" is a delimiter
							//writer.write("TEST" + i);
					
					}
						writer.close();
					
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
				
				
				else if (ans.equalsIgnoreCase("3")) {
					
					//This doesn't create the binary tree using the file, it only reads in the data from the file
					
					ArrayList<String> readingFile = new ArrayList<String>(); //arrayList to hold each line from the text file
					String[] nodes = {""}; //array to hold data of each node (data obtained from text file
					String data = ""; //string to hold data read from file
					try {
						Scanner scan = new Scanner(file); //read from the file
						
						while (scan.hasNextLine()) //while the file has another line
							 data = scan.nextLine(); //assign the line to data - coudln't figure out how to spearate them onto new line so turns out there is only one line in the file
							 nodes = data.split(":"); //split this line according to the delimiter which is ":" and store in array nodes
							 for (int i = 0; i < nodes.length; i++){ //sort through array
								 System.out.println(nodes[i]); //printing each node
							 }
						     
						scan.close();
					} catch (FileNotFoundException e1) {
						System.out.print("file not found"); //if program can't find the file, it informs user
						e1.printStackTrace();
					}
					
					//this code doesn't work - this is rough workings of the direction I should go in
					BinaryTree<String> aTree = new BinaryTree<String>(); //new BinaryTree created
					for (int i = 0; i < nodes.length ; i++) {
					while(nodes[i] != "-1") {
					aTree.setRootData(nodes[i]); //sets root data of this tree to be first element in array
					BinaryNodeInterface<String> currentNode3 = aTree.getRootNode(); //sets currentNode3 = root of tree
					BinaryNode leftChild1 = new BinaryNode(nodes[1]); //creates a left child of this node containing the second field in the array
					currentNode3 = leftChild1; //the left child is now the current node
					//BinaryNode leftChild2 = new BinaryNode(nodes[2]); //creating left child of current node
					
					
					}
					BinaryNode leftChild1 = new BinaryNode(null);
					}
					testingTree(aTree);
					//because mine is stored in preorder traverse, i can't figure out a way to create the tree from this data properly
					
					
				}
							
				
				else if (ans.equalsIgnoreCase("4")) {//if they choose quit, application is quit
					System.out.println("you have quit the application");
					break;
				}
			}
			
			else if (ans.equalsIgnoreCase("no")) { //if they answer no to the guess
				Scanner scan = new Scanner(System.in);
				System.out.println("I don't know, what is the correct answer?"); //program asks for correct answer
				String animalName = "Am I a " + scan.nextLine()+"?"; //stores answer in variable animalName
				System.out.println("Come up with a question that will help me figure that out in future!");//asks for question to help figure out correct answer
				String clue = scan.nextLine(); //stores the question in clue
				BinaryNode leftnode = new BinaryNode(animalName); //creates new node with the correct answer
				BinaryNode rightnode = new BinaryNode(currentNode.getData()); //creates new node with the guess previously stored in the node
				currentNode.setData(clue); //sets the data of the currentNode to equals the question they provided 
				//ans = user_input.next();
				currentNode.setLeftChild(leftnode);
				currentNode.setRightChild(rightnode);
				System.out.print(currentNode.getData());
				System.out.print(currentNode.getLeftChild().getData());
				System.out.print(currentNode.getRightChild().getData());
				System.out.print("Thank you!"); //thanks user for their input
				break; //quits application
				
			}
			}
		
		
	} // end of main
	public static void serialize(BinaryNodeInterface node, ArrayList<String> array) { //function to serialize tree
		//traverses the array in pre-order
		if(node == null) { //if node is empty
			array.add("-1"); //-1 added to array
			return;
		}
		
		array.add((String) node.getData()); //if node is not empty, the node's data is added to array
		serialize(node.getLeftChild(), array);//calls serialize function, passing leftchild of current node in, and the array in
		serialize(node.getRightChild(), array);//calls serialize function, passing rightchild of current node in, and the array in
	}
	
	public static void testingTree(BinaryTree<String> tree) {
		//receives tree and prints out its contents in text
		System.out.println("\nPrinting contents of tree");
		tree.inorderTraverse();
	}
	
	
	public static void createTree1(BinaryTree<String> tree)
	{
		// To create a tree, build it up from the bottom:
		// create subtree for each leaf, then create subtrees linking them,
		// until we reach the root.
		
	  	System.out.println("\nCreating a tree that looks like this:\n");
	  	System.out.println("              Think of an animal, is it a mammal?            ");
	    System.out.println("             /                                  \\   "); // '\\' is the escape character for backslash
	    System.out.println("  		Is it a marsupial?                Is it a reptile?   ");
	    System.out.println("       /                \\               /               \\");
	    System.out.println("Is it a kangaroo?  Is it large?  Is it a crocodile?  Is it an amphibian?");
	    System.out.println("                  /         \\                        /              \\");
	    System.out.println("   Is it an elephant?  Is it a mouse?            Is it a frog?    Is it a fish?");
	    System.out.println();

	    // First the leaves
	    BinaryTree<String> zTree = new BinaryTree<String>("Is it an elephant?");
		BinaryTree<String> yTree = new BinaryTree<String>("Is it a mouse?");
		
		BinaryTree<String> iTree = new BinaryTree<String>("Is it a frog?");
		BinaryTree<String> hTree = new BinaryTree<String>("Is it a fish?");
		
		BinaryTree<String> dTree = new BinaryTree<String>();
		dTree.setTree("Is it a kangaroo?");
		// neater to use the constructor the initialisation ...
		BinaryTree<String> eTree = new BinaryTree<String>("Is it large?", zTree, yTree);
		BinaryTree<String> fTree = new BinaryTree<String>("Is it a crocodile?");
		BinaryTree<String> gTree = new BinaryTree<String>("Is it an amphibian?", iTree, hTree);
		
		// Now the subtrees joining leaves:
		BinaryTree<String> bTree = new BinaryTree<String>("Is it a marsupial?", dTree, eTree);
		BinaryTree<String> cTree = new BinaryTree<String>("Is it a reptile?", fTree, gTree);

		// Now the root
		tree.setTree("Think of an animal, is it a mammal?", bTree, cTree);
	} // end createTree1

}
