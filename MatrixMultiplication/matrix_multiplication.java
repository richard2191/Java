// Student: Richard The (009012796)
// Instructor: Dr. Gilbert Young
// Class : CS331 (Spring 2012)
// Project #1 - Comparing different algorithms for matrix multiplication
// Date : 5/10/2012

import java.util.*;

public class matrix_multiplication {
	
	// function for adding two matrices
	private static int[][] add(int a[][], int b[][]) {
		int n = a.length, c[][] = new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				c[i][j] = a[i][j] + b[i][j];
		return c;
	}
	
	// function for subtracting two matrices
	private static int[][] subtract(int a[][], int b[][]) {
		int n = a.length, c[][] = new int[n][n];
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				c[i][j] = a[i][j] - b[i][j];
		return c;
	}
	
	// Classical Matrix Multiplication
	private static void classic(int a[][], int b[][], int c[][]) {
		int n = a.length;
		for(int i=0; i<n; i++)
			for(int j=0; j<n; j++)
				for(int k=0; k<n; k++)
					c[i][j] += a[i][k] * b[k][j];
	}
	
	// Divide-and-Conquer Matrix Multiplication
	private static void div_con(int a[][], int b[][], int c[][]) {
		
		int n = a.length;
		
		if(n == 2) { // base case 1
			c[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
			c[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
			c[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
			c[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
		}
		
		else if(n == 1) c[0][0] = a[0][0] * b[0][0]; // base case 2
		
		else {
			
			int h = (int) Math.ceil(n/2.0); // the new size of the sub-matrices
			
			int a11[][] = new int[h][h], a12[][] = new int[h][h], a21[][] = new int[h][h], a22[][] = new int[h][h];
			int b11[][] = new int[h][h], b12[][] = new int[h][h], b21[][] = new int[h][h], b22[][] = new int[h][h];
			int c11[][], c12[][], c21[][], c22[][]; // arrays for containing temporary results
			
			// breaks the matrix into 4 sub-matrices
			for(int i=0; i<h; i++)
				for(int j=0; j<h; j++){
					a11[i][j] = a[i][j];
					b11[i][j] = b[i][j];
				}
			
			for(int i=0; i<h; i++)
				for(int j=h; j<n; j++){
					a12[i][j-h] = a[i][j];
					b12[i][j-h] = b[i][j];
				}
				
			for(int i=h; i<n; i++)
				for(int j=0; j<h; j++){
					a21[i-h][j] = a[i][j];
					b21[i-h][j] = b[i][j];
				}
			
			for(int i=h; i<n; i++)
				for(int j=h; j<n; j++){
					a22[i-h][j-h] = a[i][j];
					b22[i-h][j-h] = b[i][j];
				}
			
			int P[][] = new int[h][h], Q[][] = new int[h][h], R[][] = new int[h][h], S[][] = new int[h][h], T[][] = new int[h][h], U[][] = new int[h][h], V[][] = new int[h][h], W[][] = new int[h][h];
			
			div_con(a11, b11, P); div_con(a12, b21, Q);
			div_con(a11, b12, R); div_con(a12, b22, S);
			div_con(a21, b11, T); div_con(a22, b21, U);
			div_con(a21, b12, V); div_con(a22, b22, W);
			
			c11 = add(P, Q); c12 = add(R, S);
         c21 = add(T, U); c22 = add(V, W);
         
         // merges the sub-matrices
         for(int i=0; i<h; i++)
            for(int j=0; j<h; j++) c[i][j] = c11[i][j];
         for(int i=0; i<h; i++)
            for(int j=h; j<n; j++) c[i][j] = c12[i][j-h];
         for(int i=h; i<n; i++)
            for(int j=0; j<h; j++) c[i][j] = c21[i-h][j];
         for(int i=h; i<n; i++)
            for(int j=h; j<n; j++) c[i][j] = c22[i-h][j-h];
		}
	}
	
	// Strassen's Matrix Multiplication
	private static void strassen(int a[][], int b[][], int c[][]) {
		
		int n = a.length;
		
		if(n == 2) { // base case 1
			c[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
			c[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
			c[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
			c[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
		}
		
		else if(n == 1) c[0][0] = a[0][0] * b[0][0]; // base case 2
		
		else {
			
			int h = (int) Math.ceil(n/2.0); // the new size of the sub-matrices
			
			int a11[][] = new int[h][h], a12[][] = new int[h][h], a21[][] = new int[h][h], a22[][] = new int[h][h];
			int b11[][] = new int[h][h], b12[][] = new int[h][h], b21[][] = new int[h][h], b22[][] = new int[h][h];
			int c11[][], c12[][], c21[][], c22[][]; // arrays for containing temporary results
			
			// breaks the matrix into 4 sub-matrices
			for(int i=0; i<h; i++)
				for(int j=0; j<h; j++){
					a11[i][j] = a[i][j];
					b11[i][j] = b[i][j];
				}
			for(int i=0; i<h; i++)
				for(int j=h; j<n; j++){
					a12[i][j-h] = a[i][j];
					b12[i][j-h] = b[i][j];
				}
			for(int i=h; i<n; i++)
				for(int j=0; j<h; j++){
					a21[i-h][j] = a[i][j];
					b21[i-h][j] = b[i][j];
				}
			for(int i=h; i<n; i++)
				for(int j=h; j<n; j++){
					a22[i-h][j-h] = a[i][j];
					b22[i-h][j-h] = b[i][j];
				}
			
			int P[][] = new int[h][h], Q[][] = new int[h][h], R[][] = new int[h][h], S[][] = new int[h][h], T[][] = new int[h][h], U[][] = new int[h][h], V[][] = new int[h][h];
			
			strassen(add(a11, a22), add(b11, b22), P);
			strassen(add(a21, a22), b11, Q);
			strassen(a11, subtract(b12, b22), R);
			strassen(a22, subtract(b21, b11), S);
			strassen(add(a11, a12), b22, T);
			strassen(subtract(a21, a11), add(b11, b12), U);
			strassen(subtract(a12, a22), add(b21, b22), V);
			
			c11 = add(subtract(add(P, S), T), V);
			c12 = add(R, T);
			c21 = add(Q, S);
			c22 = add(subtract(add(P, R), Q), U);
			
			// merges the sub-matrices
			for(int i=0; i<h; i++)
            for(int j=0; j<h; j++) c[i][j] = c11[i][j];
         for(int i=0; i<h; i++)
            for(int j=h; j<n; j++) c[i][j] = c12[i][j-h];
         for(int i=h; i<n; i++)
            for(int j=0; j<h; j++) c[i][j] = c21[i-h][j];
         for(int i=h; i<n; i++)
            for(int j=h; j<n; j++) c[i][j] = c22[i-h][j-h];
			
		}
	}

	public static void main(String[] args) {
		
		while(true){
			
			long t; // a variable for storing the execution time
			
			System.out.print("\nEnter the size of the matrix (>0) : ");
			Scanner in = new Scanner(System.in); // prompt the user to input the size
			int N = in.nextInt(); // a variable for holding the size
			if(N <= 0) return; // condition to exit the program
			
			int c = 0;
			while(c<2){
			   c++;
			   
			   int A[][] = new int[N][N], B[][] = new int[N][N], C[][] = new int[N][N]; // a 2d array for containing the result
	         
	         // generates the elements of A and B randomly (0-9)
	         Random r = new Random();
	         for(int i=0; i<N; i++){
	            for(int j=0; j<N; j++){
	               A[i][j] = r.nextInt(9);
	               B[i][j] = r.nextInt(9);
	            }
	         }
	         
	         t = System.currentTimeMillis();
	         classic(A, B, C);
	         t = System.currentTimeMillis() - t;
	         
	         System.out.println("\nClassical : " + t + " ms");
	         
	         C = new int[N][N]; // re-initializes all elements of C to 0
	         t = System.currentTimeMillis();
	         div_con(A, B, C);
	         t = System.currentTimeMillis() - t;
	         
	         System.out.println("Divide and Conquer : " + t + " ms");
	         
	         
	         C = new int[N][N]; // re-initializes all elements of C to 0
	         t = System.currentTimeMillis();
	         strassen(A, B, C);
	         t = System.currentTimeMillis() - t;
	         
	         System.out.println("Strassen's : " + t + " ms");
	         
			}
			
		}
	}
}
