public class BTNode<E> {
   private E data;
   private BTNode<E> left, right;
   
   public BTNode(E initialData, BTNode<E> initialLeft, BTNode<E> initialRight) {
      data = initialData;
      left = initialLeft;
      right = initialRight;
   }
   
   // Setters
   public void setRight(BTNode<E> r){
	   right = r;
   }
   
   public void setLeft(BTNode<E> l){
	   left = l;
   }
   
   public void setData(E in){
	   this.data = in;
   }
   
   // Getters
   public E getData(){
	   return this.data;
   }
   
   public BTNode<E> getLeft(){
	   return this.left;
   }
   
   public BTNode<E> getRight(){
	   return this.right;
   }
   
   // method to determine if a node is a leaf
   public boolean isLeaf(){
	   return (left==null && right==null);
   }
}