// Student: Richard The (009012796)
// Instructor: Dr. Daisy F. Sang
// Class : CS311 (Spring 2012)
// Project #1 - Universal Finite State Automaton
// Date : 4/23/2012

import java.io.*;
import java.util.*;

public class fsa {
   
   // the following two variables are declared globally so that they don't have to be passed every time a function is called
   static String alphabet[];
   static int states[][];
   
   private static int get_index(String symbol){
      for(int i=0; i<alphabet.length; i++)
         if(alphabet[i].equals(symbol)) return i;
      return -1; // indicating that the symbol is not in the alphabet
   }
   
   private static int next_state(int state, String symbol) {
      return states[state][get_index(symbol)];
   }
   
   // the symbols are numbered internally, but I create this function to return the actual symbol
   private static String next_symbol(String s, int i) {
      if(i < s.length()) return Character.toString(s.charAt(i));
      return "";
   }
   
   private static boolean machine(String s, boolean FINAL[], int dead_end) {
      int state = 0, string_index = 0;
      boolean exit = false;
      while(!exit) {
         String symbol = next_symbol(s, string_index++);
         
         if(get_index(symbol) != -1) { // symbol is in alphabet
            state = next_state(state, symbol);
            if(state == dead_end){
               exit = true;
               return false;
            }
         }
         
         else {
            exit = true;
            if(!next_symbol(s, string_index).equals("")) return false; // symbol is not end-marker
            else if(FINAL[state]) return true;
            else return false;
         }
         
      }
      return true;
   }

   public static void main(String[] args) {
      try {
         
         int count = 1; // gives number to the machines
         Scanner in = new Scanner(new File("input.txt"));
         
         while(in.hasNextLine()) {
            String[] temp_FINAL; // temporary array for reading input
            
            int N = in.nextInt(); // The # of states
            boolean FINAL[] = new boolean[N]; // The set of final states
            
            System.out.println("\nFinite State Automaton #" + count); count++;
            System.out.println("(1) number of states: " + N);
            
            in.nextLine(); // moves on to the next line
            
            temp_FINAL = in.nextLine().split("\\s+"); // indicates which states are the final states
            for(int i=0; i<temp_FINAL.length; i++) FINAL[Integer.parseInt(temp_FINAL[i])] = true;
            
            System.out.print("(2) final states: ");
            for(int i=0; i<FINAL.length; i++) if(FINAL[i]) System.out.print(i + " ");
            
            String alpha = in.nextLine();
            alphabet = alpha.split("\\s+"); // stores input alphabet in an array
            
            states = new int[N][alphabet.length]; // state table
            for(int i=0; i<N; i++)
               for(int j=0; j<alphabet.length; j++)
                  states[i][j] = N; // set every element on the table initially as the trap state
            
            System.out.print("\n(3) alphabet: ");
            for(int i=0; i<alphabet.length; i++) System.out.print(alphabet[i] + " ");
            
            System.out.println("\n(4) transitions: (trap state = " + N + ")");
            
            String temp1 = in.nextLine();
            while(!temp1.equals("") && temp1.charAt(0) == '('){
               String temp2 = "";
               for(int i=1; i<temp1.length()-1; i++) temp2 += temp1.charAt(i); // truncate the parentheses
               String[] s_arr = temp2.split("\\s+");
               states[Integer.parseInt(s_arr[0])][get_index(s_arr[1])] = Integer.parseInt(s_arr[2]);
               temp1 = in.nextLine();
            }
            
            // print the state table
            System.out.print("       | " + alpha + "\n");
            System.out.print("     --+-");
            for(int i=0; i<alpha.length(); i++) System.out.print("-");
            for(int i=0; i<N; i++){
               System.out.print("\n     " + i + " | ");
               for(int j=0; j<alphabet.length; j++)
                  System.out.print(states[i][j] + " ");
            }
            
            System.out.println("\n(5) strings:");
            
            while(!temp1.equals("NEXT")){
               System.out.print("     " + temp1 + " = ");
               if(machine(temp1, FINAL, N)) System.out.print("Accept\n");
               else System.out.print("Reject\n");
               if(in.hasNextLine()) temp1 = in.nextLine();
               else return;
            }
            
         }
         
      } catch (FileNotFoundException e) {
         System.out.println("File not found!");
      }
   }
}
