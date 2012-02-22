import java.util.*;

public class BTree<E> {
   private BTNode<E> root;

   public BTree(BTNode<E> initialRoot) {
      root = initialRoot;
   }
   
   public BTNode<E> getRoot() {
      return root;
   }
   
   public void setRoot(BTNode<E> newRoot) {
      root = newRoot;
   }
   
   // Pre Order Traversal
   public void preOrder()
   {
      preOrder(root);
      System.out.println();
   }
   
   private void preOrder(BTNode<E> n)
   {
      if (n != null)
      {
         System.out.print(n.getData() + " ");
         preOrder(n.getLeft());
         preOrder(n.getRight());
      }
   }
   
   // In Order Traversal
   public void inOrder()
   {
      inOrder(root);
      System.out.println();
   }
   
   private void inOrder(BTNode<E> n)
   {
      if (n != null)
      {
         if(n.isLeaf()) System.out.print(n.getData());
    	 else{
    	    System.out.print("(");
            inOrder(n.getLeft());
            System.out.print(n.getData());
            inOrder(n.getRight());
            System.out.print(")");
    	 }
      }
   }

   // Post Order Traversal
   public void postOrder()
   {
      postOrder(root);
      System.out.println();
   }
   
   private void postOrder(BTNode<E> n)
   {
      if (n != null)
      {
         postOrder(n.getLeft());
         postOrder(n.getRight());
         System.out.print(n.getData() + " ");
      }
   }
   
   // method to check if the input is a variable/operand
   private boolean isOperand(String s){
      return (s.charAt(0) >= 65 && s.charAt(0) <= 90);
   }

   // method to Build an Expression Tree
   @SuppressWarnings({ "unchecked", "rawtypes" })
   public BTree<E> buildTree(String[] s){
      Stack<BTree<E>> stk = new Stack();
	  BTNode<E> n = null;
	  BTree<E> t = new BTree<E>(null), l = null, r = null;
	  for(int i=0; i < s.length; i++){
         if(isOperand(s[i]) || isInteger(s[i])){
            n = new BTNode<E>((E) s[i], null, null);
			t = new BTree<E>(n);
			stk.push(t);
		 }
		 else {
			r = (stk.pop()); l = (stk.pop());
			n = new BTNode<E>((E) s[i], l.getRoot(), r.getRoot());
			t = new BTree<E>(n);
			stk.push(t);
		 }
      }
	  return t;
   }
   
   // method to check if the input is an integer
   public boolean isInteger(String intTest){
      try {
         Integer.parseInt(intTest);
		 return true;
      }
      catch(NumberFormatException nFE) {
         return false;
      }
   }
   
   // method to Evaluate an Expression Tree
   public int evalTree(BTNode<E> node, int[] vars){
      if(isOperand((String) node.getData())) return vars[((String) node.getData()).charAt(0) - 65];
      else {
         char op = ((String) node.getData()).charAt(0);
         switch(op){
         case '+' : return evalTree(node.getLeft(), vars) + evalTree(node.getRight(), vars);
         case '-' : return evalTree(node.getLeft(), vars) - evalTree(node.getRight(), vars);
         case '*' : return evalTree(node.getLeft(), vars) * evalTree(node.getRight(), vars);
         case '/' : return evalTree(node.getLeft(), vars) / evalTree(node.getRight(), vars);
         default : return Integer.parseInt((String) node.getData());
		 }
	  }
   }
}