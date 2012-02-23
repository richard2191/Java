// Student: Richard The (BID# 009012796)
// Instructor: Alexander Vondrak
// Class: CS240 (Fall 2011)
// File Name: Memoized.java (Project 3)

import java.util.Hashtable;

public class Memoized {
	
	static Hashtable<Integer, Long> hash = new Hashtable<Integer, Long>();
	
	public static long fib(int n) {
		
	       if(hash.containsKey(n)) return (Long) hash.get(n);
	       if(n <= 1) return n;
	       hash.put(n, fib(n-1) + fib(n-2));
	       return fib(n-1) + fib(n-2);
	       
	}
}
