//Student: Richard The
//Instructor: Alex Vondrak
//CS 240 (Fall 2011)
//Filename: Stack.java

public interface Stack {
	
	public void push(int item);
	
	public int pop()
		throws StackUnderflowException ;
	
	public int top()
		throws StackUnderflowException ;
	
	public boolean isEmpty();
	
	public int size();
	
	public String toString(); //for printing out the stack's elements
	
}