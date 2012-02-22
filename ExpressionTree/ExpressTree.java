// Richard The (009012796)
// CS241, section 01
// Project 2: Create and evaluate a binary expression tree
// 11 February 2012

import java.io.*;

public class ExpressTree {
	
   public static void main(String[] args){
      BTNode<String> root = null;
      BTree<String> tree = new BTree<String>(root);
      int[] vars = new int[26]; // variable for storing the operands
      
      try {
         BufferedReader in = new BufferedReader(new FileReader("input.dat"));
    	 String str = in.readLine();
    	 
    	 while(true){
    		 
    		String[] s = str.split("\\s+"); // split the input separated by whitespaces
    		vars[s[0].charAt(0) - 65] = Integer.parseInt(s[1]); // store variable in its corresponding index in vars
    		str = in.readLine();
    		
    		if(str.equals("")){
    			
               str = in.readLine();
    		   s = str.trim().split("\\s+");

    		   tree = (tree.buildTree(s)); // building the expression tree
    		   
    		   System.out.println("In Order : ");
    		   tree.inOrder();
    		   System.out.println("\nPre Order : ");
    		   tree.preOrder();
    		   System.out.println("\nPost Order : ");
    		   tree.postOrder();
    		   
    		   // evaluating the binary tree and handling division by zero
    		   try {
    			  System.out.println("\nEval result = " + tree.evalTree(tree.getRoot(), vars)); // evaluating the expression tree
               }
 			   catch(ArithmeticException e){
 				  System.out.println("\nError: Division by zero!");
 			   }
 			   
 			   System.out.println("--------------------");
 			   
 			   // reinitialize all operands to 0
 			   for(int i=0; i<vars.length; i++){
 				  vars[i] = 0;
 			   }
    			
 			   str = in.readLine();
    			
    		   if(str.equals("<EOF>")) break; // stop reading lines in the file when it reaches <EOF>
    		   else str = in.readLine(); // otherwise continue to the next block of variables
    		}
    	 }
    	 in.close();
    	 
      }
      catch (IOException e) {
		 System.out.println("Error: File not found!");
	  }
   }
}