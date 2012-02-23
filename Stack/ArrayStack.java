//Student: Richard The
//Instructor: Alex Vondrak
//CS 240 (Fall 2011)
//Filename: ArrayStack.java

class ArrayStack implements Stack {

	private int[] data ;
	private int top;

	public ArrayStack () {
		final int CAPACITY = 10;
		top = -1;
		data = new int[ CAPACITY ];
	}

	private void grow() {
		final int CAPACITY = 2 * data.length + 1;
		int[] biggerArray = new int[ CAPACITY ];
		for (int i = 0; i < data.length ; i++)
		biggerArray [i] = data [i];
		data = biggerArray ;
	}

	public void push (int value) {
		if ( size () == data.length ) grow();
		data [++ top] = value;
	}

	public int pop ()
		throws StackUnderflowException  {
			if ( isEmpty ()) throw new StackUnderflowException();
			int result = top();
			top--;
			return result;
		}

	public int top()
		throws StackUnderflowException {
			if ( isEmpty ()) throw new StackUnderflowException();
			return data[top];
		}

	public boolean isEmpty () {
		return (size() == 0);
	}

	public int size () {
		return top + 1;
	}
	
	public String toString(){
		String toPrint = "";
		for (int i = 0; i < top+1 ; i++){
			toPrint += (Integer.toString(data[i]) + '\n'); //read current stack's elements as a string
		}
		return toPrint;
	}
	
}