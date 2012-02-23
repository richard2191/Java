// Student: Richard The (BID# 009012796)
// Instructor: Alexander Vondrak
// Class: CS240 (Fall 2011)
// File Name: TimeFibonacci.java (Project 3)

import java.util.Scanner;

public class TimeFibonacci {
	
	public static void time(int N) {
		
		long start, end;
		int sumMemoized=0, sumUnmemoized=0;
		
		for(int i = 1; i <= N; i++) {
			
			start = System.currentTimeMillis();
			Unmemoized.fib(i);
			end = System.currentTimeMillis();
			System.out.print("Unmemoized.fib(" + i + ") = " + Unmemoized.fib(i) + " (took " + (end-start) + " ms); ");
			sumUnmemoized += (end-start);
			
			start = System.currentTimeMillis();
			Memoized.fib(i);
			end = System.currentTimeMillis();
			System.out.print("Memoized.fib(" + i + ") = " + Memoized.fib(i) + " (took " + (end-start) + " ms)\n");
			sumMemoized += (end-start);
		}
		
		System.out.println("\nThe average running time for Unmemoized = " + sumUnmemoized/N + " ms");
		System.out.println("The average running time for Memoized = " + sumMemoized/N + " ms");
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
        while(true){
        	System.out.print("\nFibonacci term> ");
        	int N = in.nextInt();
        	time(N);
        }
    }
}

/*
1. The memoized version clearly runs faster than the unmemoized version.
   The reason is simply because the memoized function takes the previously
   calculated values of Fibonacci number that are stored in the memory (Hashtable)
   instead of repeating the calculation again recursively each time the function
   is called.
   
2. The reason we limit n up to only 45 is probably because the maximum value of
   'int' is 2,147,483,647. It is actually not until n=47 that the value gets
   greater than the limit of 'int', but 45 is close enough. However, I set the return
   type of the function to be 'long' to prevent this from happening.
   This way we have more room to test even wider range of data.

OUTPUT:

Unmemoized.fib(1) = 1 (took 0 ms); Memoized.fib(1) = 1 (took 0 ms);
Unmemoized.fib(2) = 1 (took 0 ms); Memoized.fib(2) = 1 (took 0 ms);
Unmemoized.fib(3) = 2 (took 0 ms); Memoized.fib(3) = 2 (took 0 ms);
Unmemoized.fib(4) = 3 (took 0 ms); Memoized.fib(4) = 3 (took 0 ms);
Unmemoized.fib(5) = 5 (took 0 ms); Memoized.fib(5) = 5 (took 0 ms);
Unmemoized.fib(6) = 8 (took 0 ms); Memoized.fib(6) = 8 (took 0 ms);
Unmemoized.fib(7) = 13 (took 0 ms); Memoized.fib(7) = 13 (took 0 ms);
Unmemoized.fib(8) = 21 (took 0 ms); Memoized.fib(8) = 21 (took 0 ms);
Unmemoized.fib(9) = 34 (took 0 ms); Memoized.fib(9) = 34 (took 0 ms);
Unmemoized.fib(10) = 55 (took 0 ms); Memoized.fib(10) = 55 (took 0 ms);
Unmemoized.fib(11) = 89 (took 0 ms); Memoized.fib(11) = 89 (took 0 ms);
Unmemoized.fib(12) = 144 (took 0 ms); Memoized.fib(12) = 144 (took 0 ms);
Unmemoized.fib(13) = 233 (took 0 ms); Memoized.fib(13) = 233 (took 0 ms);
Unmemoized.fib(14) = 377 (took 0 ms); Memoized.fib(14) = 377 (took 0 ms);
Unmemoized.fib(15) = 610 (took 0 ms); Memoized.fib(15) = 610 (took 0 ms);
Unmemoized.fib(16) = 987 (took 0 ms); Memoized.fib(16) = 987 (took 0 ms);
Unmemoized.fib(17) = 1597 (took 1 ms); Memoized.fib(17) = 1597 (took 0 ms);
Unmemoized.fib(18) = 2584 (took 0 ms); Memoized.fib(18) = 2584 (took 0 ms);
Unmemoized.fib(19) = 4181 (took 0 ms); Memoized.fib(19) = 4181 (took 0 ms);
Unmemoized.fib(20) = 6765 (took 0 ms); Memoized.fib(20) = 6765 (took 0 ms);
Unmemoized.fib(21) = 10946 (took 0 ms); Memoized.fib(21) = 10946 (took 0 ms);
Unmemoized.fib(22) = 17711 (took 1 ms); Memoized.fib(22) = 17711 (took 0 ms);
Unmemoized.fib(23) = 28657 (took 1 ms); Memoized.fib(23) = 28657 (took 0 ms);
Unmemoized.fib(24) = 46368 (took 1 ms); Memoized.fib(24) = 46368 (took 0 ms);
Unmemoized.fib(25) = 75025 (took 2 ms); Memoized.fib(25) = 75025 (took 0 ms);
Unmemoized.fib(26) = 121393 (took 3 ms); Memoized.fib(26) = 121393 (took 0 ms);
Unmemoized.fib(27) = 196418 (took 4 ms); Memoized.fib(27) = 196418 (took 0 ms);
Unmemoized.fib(28) = 317811 (took 5 ms); Memoized.fib(28) = 317811 (took 0 ms);
Unmemoized.fib(29) = 514229 (took 6 ms); Memoized.fib(29) = 514229 (took 0 ms);
Unmemoized.fib(30) = 832040 (took 9 ms); Memoized.fib(30) = 832040 (took 0 ms);
Unmemoized.fib(31) = 1346269 (took 19 ms); Memoized.fib(31) = 1346269 (took 0 ms);
Unmemoized.fib(32) = 2178309 (took 28 ms); Memoized.fib(32) = 2178309 (took 0 ms);
Unmemoized.fib(33) = 3524578 (took 40 ms); Memoized.fib(33) = 3524578 (took 0 ms);
Unmemoized.fib(34) = 5702887 (took 65 ms); Memoized.fib(34) = 5702887 (took 0 ms);
Unmemoized.fib(35) = 9227465 (took 105 ms); Memoized.fib(35) = 9227465 (took 0 ms);
Unmemoized.fib(36) = 14930352 (took 170 ms); Memoized.fib(36) = 14930352 (took 0 ms);
Unmemoized.fib(37) = 24157817 (took 276 ms); Memoized.fib(37) = 24157817 (took 0 ms);
Unmemoized.fib(38) = 39088169 (took 441 ms); Memoized.fib(38) = 39088169 (took 0 ms);
Unmemoized.fib(39) = 63245986 (took 712 ms); Memoized.fib(39) = 63245986 (took 0 ms);
Unmemoized.fib(40) = 102334155 (took 1155 ms); Memoized.fib(40) = 102334155 (took 0 ms);
Unmemoized.fib(41) = 165580141 (took 1871 ms); Memoized.fib(41) = 165580141 (took 0 ms);
Unmemoized.fib(42) = 267914296 (took 3085 ms); Memoized.fib(42) = 267914296 (took 0 ms);
Unmemoized.fib(43) = 433494437 (took 4873 ms); Memoized.fib(43) = 433494437 (took 0 ms);
Unmemoized.fib(44) = 701408733 (took 8049 ms); Memoized.fib(44) = 701408733 (took 0 ms);
Unmemoized.fib(45) = 1134903170 (took 12854 ms); Memoized.fib(45) = 1134903170 (took 0 ms);

The average running time for Unmemoized = 750 ms
The average running time for Memoized = 0 ms

*/