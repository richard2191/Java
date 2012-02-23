//Student: Richard The
//Instructor: Alex Vondrak
//CS 240 (Fall 2011)
//Filename: REPL.java

import java.util.*;

public interface REPL {
	
	public String[] read(Scanner in);

	public boolean isInteger(String expr);
	
	public void eval(String expr) throws StackUnderflowException;
	
	public void eval(String[] exprs);
	
	public void print() throws StackUnderflowException;
	
	public void loop() throws StackUnderflowException;
		
}