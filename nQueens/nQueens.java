// Student: Richard The
// CS241, section 01
// Project 1: Use backtracking to solve the n-queens problem
// Date: January 23, 2012

public class nQueens {
	
	static int count = 0; // variable for counting the possible queen positions

	private static boolean place(int k, int[] x){ // a function to check if position is valid
		for(int i = 0; i < k; i++){
			if((x[i] == x[k]) || (Math.abs(x[i]-x[k]) == Math.abs(i-k))) return false;
		}
		return true;
	}
	
	private static void rNqueens(int k, int[] x){
		for(x[k] = 1; x[k] <= x.length; x[k]++){
			if(place(k, x)){
				if(k+1 == x.length){
					if(x.length <= 8){ // condition to print the solution vectors
						System.out.print("   " + (count + 1) + " = ( ");
						for(int i = 0; i < x.length; i++){
							System.out.print(x[i] + " ");
						}
						System.out.print(")\n");
					}
					count++;
				}
				else rNqueens(k+1, x);
			}
		}
	}
	
	private static void iNqueens(int[] x){
		
		int k = 0; x[k] = 0;
		
		while(k >= 0){
			x[k]++;
			while((x[k] <= x.length) && !place(k, x)) x[k]++;
			if(x[k] <= x.length){
				if(k+1 == x.length){
					if(x.length <= 8){ // condition to print the solution vectors
						System.out.print("   " + (count + 1) + " = ( ");
						for(int i = 0; i < x.length; i++){
							System.out.print(x[i] + " ");
						}
						System.out.print(")\n");
					}
					count++;
				}
				else { k++; x[k] = 0; }
			}
			else k--; // backtrack
		}
	}
	
	public static void main(String[] args) {
	
		for(int i = 1; i <= 15; i++){
				
			int[] a = new int[i];
			
			System.out.println("\nn = " + i + "\n\nrecursion...");
			rNqueens(0, a); // call recursive method
			System.out.println("   There are " + count + " solution vectors");
			count = 0; // reset count to 0
				
			System.out.println("\niteration...");
			iNqueens(a); // call iterative method
			System.out.println("   There are " + count + " solution vectors\n");
			count = 0; // reset count to 0
		}
	}
}
