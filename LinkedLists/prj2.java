// Student: Richard The (BID# 009012796)
// Instructor: Alexander Vondrak
// Class: CS240 (Fall 2011)
// File Name: prj2.java

import java.util.Scanner;
import java.util.Stack;

class EvalException extends Exception {
	private static final long serialVersionUID = 1L;
	public EvalException(String msg) { super(msg); }
}

interface Expr {
   public int eval() throws EvalException;
}

class Atom implements Expr {
   String value;

   public Atom(String value) { this.value = value; }

   public String toString() { return value; }

   public int eval() throws EvalException {
      /* IMPLEMENT ME
       * We'll assume Atoms represent integers when we eval them, so try to
       * parse the value as an int.  If this can't be done, we'll want to throw
       * an EvalException with the proper error message.
       */
	   int x;
	   try{
		   x = Integer.parseInt(value);
	   }
	   catch(NumberFormatException e){
		   throw new EvalException("Could not eval atom to an int: " + value);
	   }
	   return x;
   }
}

class List<E> implements Expr {
   private Node<E> head;

   public List(E... elements) {
      head = null;

      for(int i = elements.length - 1; i >= 0; i--) {
         head = new Node<E>(elements[i], head);
      }
   }

   public void append(E element) {
      if (head == null) {
         head = new Node<E>(element, null);
      }
      else {
         Node<E> last = head;
         while (last.link != null) last = last.link;
         last.link = new Node<E>(element, null);
      }
   }

   public String toString() {
      String s = "( ";
      Node<E> elements = head;
      while (elements != null) {
         s += elements.data + " ";
         elements = elements.link;
      }
      return s + ")";
   }

@SuppressWarnings("unchecked")
public void evalAndPrintEach() {
      Node<Expr> current = (Node<Expr>) head;
      while (current != null) {
         try {
            System.out.println( ((Expr) current.data).eval() );
         }
         catch (EvalException e) {
            System.out.println(e);
         }
         current = current.link;
      }
   }

   private Node<Integer> evalArgs(Node<Expr> xs) throws EvalException {
      if (xs == null) return null;
      return new Node<Integer>(xs.data.eval(), evalArgs(xs.link));
   }
   
@SuppressWarnings("unchecked")
public int eval() throws EvalException {
      Node<Expr> elts = (Node<Expr>) head;
      if (elts == null) throw new EvalException("Empty list.");
      String op = ((Atom) elts.data).value; //this line reads the first element (supposedly an operator)
      
      /* IMPLEMENT ME.
       * To evaluate a (non-empty) List<Expr>, we look at the first element
       * (which we assume is an Atom), evaluate each of the remaining elements
       * (using evalArgs), then apply the appropriate operator to the results.
       * See the examples in the handout.
       *
       * Attempting to evaluate an empty list results in an error.  If op isn't
       * one of "+", "-", "*", "/", then this too is an error.
       */
      
      elts = elts.link; //increment the element in the list by 1 because the first one is supposed to be an operator
      
      if (op.equals("+")) return add(evalArgs(elts));
      else if (op.equals("-")){
    	  if(elts == null || elts.link == null) return subHelp(evalArgs(elts)); //while there is < 2 argument
    	  int accum = evalArgs(elts).data;
    	  elts = elts.link;
    	  return sub(evalArgs(elts), accum); //while there are >= 2 arguments, accum = the first element
      }
      else if (op.equals("*")) return mul(evalArgs(elts), 1);
      else if (op.equals("/")){
    	  if(elts == null || elts.link == null) throw new EvalException("/ expects at least 2 arguments");
    	  int accum = evalArgs(elts).data;
    	  elts = elts.link;
    	  return div(evalArgs(elts), accum);
      }
      else throw new EvalException("Unknown operator: " + op); //if it's not an operator
   }

   private int add(Node<Integer> xs) throws EvalException {
      /* IMPLEMENT ME
       * Find the sum of xs using a right fold.
       */
	   if(xs == null) return 0;
	   return xs.data + add(xs.link);			   
   }
   
   private int mul(Node<Integer> xs, int accum) throws EvalException {
      /* IMPLEMENT ME
       * Find the product of xs using a left fold.
       */
	   if(xs == null) return accum;
	   return mul(xs.link, accum * xs.data);
   }
   
   private int subHelp(Node<Integer> xs){
	   if (xs == null) return 0;
	   return -xs.data;
   }
   
   private int sub(Node<Integer> xs, int accum) throws EvalException {
      /* IMPLEMENT ME
       * Calculate the result by using a tail-recursive helper method.
       */
	   if(xs == null) return accum;
	   return sub(xs.link, accum - xs.data);
   }

   private int div(Node<Integer> xs, int accum) throws EvalException {
      /* IMPLEMENT ME
       * Calculate the result iteratively.  If xs doesn't have at least 2
       * elements, throw an Exception.
       */
	   //exception for having not enough element is thrown outside of the method
	   while(xs != null){
		   accum /= xs.data;
		   xs = xs.link;
	   }
	   return accum;
   }

@SuppressWarnings("hiding")
private class Node<E> {
      E data;
      Node<E> link;

      public Node (E data, Node<E> link) {
         this.data = data;
         this.link = link;
      }
}

static class UnclosedListException extends Exception {
	private static final long serialVersionUID = 1L;
}

static class Lisp {
   public static List<Expr> parse(String[] tokens) throws Exception {
      Stack<List<Expr>> stack = new Stack<List<Expr>>();

      stack.push(new List<Expr>());

      for (String token : tokens) {
         if (token.equals("(")) {
            stack.push(new List<Expr>());
         }

         else if (token.equals(")")) {
            List<Expr> toAppend = stack.pop();
            stack.peek().append(toAppend);
         }

         else {
            stack.peek().append(new Atom(token));
         }
      }

      if (stack.size() > 1)
         throw new UnclosedListException();

      return stack.pop();
   }

   public static void main(String[] args) throws Exception {
      Scanner in = new Scanner(System.in);

      while(true) {
         System.out.print("lisp> ");
         String line = in.nextLine();
         try {
            parse(line.split("\\s+")).evalAndPrintEach();
         }
         catch (Exception e) {
            System.out.println("Parsing error.");
         }
      }
   }
}
}
