// Student: Richard The (BID# 009012796)
// Instructor: Alexander Vondrak
// Class: CS240 (Fall 2011)
// File Name: Unmemoized.java (Project 3)

public class Unmemoized {
	
	public static long fib(int n) {
		
        if (n <= 1) return n;
        return fib(n-1) + fib(n-2);
        
    }
}
