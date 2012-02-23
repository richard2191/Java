//Student: Richard The
//Instructor: Alex Vondrak
//CS 240 (Fall 2011)
//Filename: RPN.java

import java.util.*;

class RPN implements REPL {
	
	ArrayStack s1 = new ArrayStack();
	
	public String[] read(Scanner in) {
		System.out.print("rpn> "); //asks the user to enter an input
		String input = in.nextLine(); //reads the line of input
		String[] s = input.split("\\s+"); //split the input separated by whitespaces
		return s;
	}

	public boolean isInteger(String intTest){ //checks if the input is a valid integer
		try {
		    Integer.parseInt(intTest);
		    return true;
		}
		catch(NumberFormatException nFE) {
		    return false;
		}
	}
	
	public void eval(String expr) throws StackUnderflowException {
		
		int a, b, c;
		char operator = '+'; //initializes operator to any random char so that we can determined if it's a known operator later
		
		for(int l=0; l < expr.length(); l++){
			
			operator = expr.charAt(l);
			
			switch(operator){
			
			case '!':{ //an input for clearing the stack
				for(int j = s1.size(); j > 0; j--){
					s1.pop();
				}
				break;
			}
				
			case '.':{ //an input for popping an element off of the stack
				System.out.println("The following element has just been deleted: " + s1.pop());
				break;
			}
			
			case '+':{
				if(s1.size() < 2) throw new StackUnderflowException();
				b = s1.pop(); a = s1.pop();
				c = a + b; s1.push(c);
				break;
			}
			
			case '-':{
				if(s1.size() < 2) throw new StackUnderflowException();
				b = s1.pop(); a = s1.pop();
				c = a - b; s1.push(c);
				break;
			}
			
			case '*':{
				if(s1.size() < 2) throw new StackUnderflowException();
				b = s1.pop(); a = s1.pop();
				c = a * b; s1.push(c);
				break;
			}
			
			case '/':{
				if(s1.size() < 2) throw new StackUnderflowException();
				if(s1.top() == 0){
					System.out.println("Divided by zero!");
					break;
				}
				else {
					b = s1.pop(); a = s1.pop();
					c = a / b; s1.push(c);
				}
				break;
			}
			}
		}
		
		if(isInteger(expr)){
			int y = Integer.parseInt(expr); //converts the string input into integer
			s1.push(y); //pushes an integer into the stack 
		}
		else if((operator == '!') || (operator == '.') || (operator == '+') || (operator == '-') || (operator == '*') || (operator == '/')){
		} //does nothing if the operator is known and goes to the next line
		else System.out.println("Unknown operator: " + expr); //prints out bad input
		
	}

	public void eval(String[] exprs) {
		for(int i=0; i < exprs.length; i++){ //assigns each of String[] elements to String 
			try {
				eval(exprs[i]);
			} catch (StackUnderflowException e) {
				System.out.println("Data stack underflow");
			}
		}
	}

	public void print() {
		System.out.println("--- Data stack:");
		System.out.println(s1.toString()); //prints out the current elements of the stack 
	}

	public void loop() throws StackUnderflowException {
		while(true){
			Scanner kb = new Scanner(System.in);
			eval(read(kb)); //passes the returned string from kb to eval()
			print(); //calls print method
		}
	}
}