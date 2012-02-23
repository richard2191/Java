//Student: Richard The
//Instructor: Alex Vondrak
//CS 240 (Fall 2011)
//Filename: StackUnderflowException.java

public class StackUnderflowException extends Exception {

	private static final long serialVersionUID = 1L; //not sure what this is, Eclipse generated it automatically to prevent error

	public StackUnderflowException() {
		super("StackUnderflow Exception");
	}
}