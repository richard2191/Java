/* Student: Richard The
 * Instructor: Dr. Daisy F. Sang
 * CS311 - Project 2 (Lexical Analyzer)
 * File: lexical_analyzer.java
 * Date: 5/4/12
 */

import java.io.*;
import java.util.Scanner;

public class lexical_analyzer {
   
   static String[] tokens = {"double", "else", "if", "int", "return", "void", "while", "+", "-", "*", "/", "<", "<=", ">", ">=", "==", "!=", "=", ";", ",", ".", "(", ")", "[", "]", "{", "}", "id", "num"};
   static String[] names = {"DOUBLE", "ELSE", "IF", "INT", "RETURN", "VOID", "WHILE", "plus", "minus", "multiplication", "division", "less", "lessequal", "greater", "greaterequal", "equal", "notequal", "assignop", "semicolon", "comma", "period", "leftparen", "rightparen", "leftbracket", "rightbracket", "leftbrace", "rightbrace", "id", "num", "ERROR"};
   static String print = ""; // a string for printing out token names
   
   // a linear search function to give the corresponding index of a token in the array
   private static int search(String key){
      for(int i=0; i<tokens.length; i++)
         if(key.equals(tokens[i])) return i;
      return -1; // key not found
   }
   
   // a function to determine if an identifier is valid
   private static boolean isIdentifier(String input){
      // it should not start with a digit (0...9)
      if(isNumber(Character.toString(input.charAt(0)))) return false;
      for(int i=0; i<input.length(); i++){
         // contains something that is neither letters, digits, nor underscore, return false
         int a = input.charAt(i);
         if((a != 95) && !(a>64 && a<91) && !(a>96 && a<123)) return false;
      }
      // none of the above, it's a valid identifier
      return true;
   }
   
   // a function to determine if the token is either an integer/float
   private static boolean isNumber(String input){
      try {
         // only parseFloat is checked because integer is its subset
         Float.parseFloat(input);
         return true;
      }
      catch(NumberFormatException nFE){
         return false;
      } 
   }
   
   // an iterative function to analyze tokens that are not separated by whitespace
   private static void analyze(String input){
      String s = "";
      
      for(int i=0; i<input.length(); i++){
         
         char a = input.charAt(i);
         String str = Character.toString(a);
         
         // a known symbol is found
         if((search(str)>6 && search(str)<20) || (search(str)>20 && search(str)<27) || str.equals("!")){
            
            // for handling <=, >=, ==, and !=
            if(str.equals("<") || str.equals(">") || str.equals("=") || str.equals("!")){
               if(i < input.length()-1){
                  str += Character.toString(input.charAt(i+1));
                  if(search(str) != -1) i++;
                  else str = Character.toString(a);
               }
            }
            
            if(search(s) != -1) { // the token before known symbol is also a known token
               // print the known token followed by the symbol
               System.out.print(search(s) + " ");
               print += (search(s) + " ");
            }
            else { // the token before known symbol is an unknown token (not in the array)
               if(!s.equals("")){
                  if(isNumber(s)) { System.out.print("28 "); print += "28 "; }
                  else if(isIdentifier(s)) { System.out.print("27 "); print += "27 "; }
                  else { System.out.print("ERROR "); print += "29 "; }
               }
            }
            
            System.out.print(search(str) + " ");
            print += (search(str) + " ");
            s = "";
         }
         // reaching the end of the current string being processed
         else if (i == (input.length()-1)){
            s += a;
            if(search(s) != -1) { System.out.print(search(s) + " "); print += (search(s) + " "); }
            else {
               if(isNumber(s)) { System.out.print("28 "); print += "28 "; }
               else if(isIdentifier(s)) { System.out.print("27 "); print += "27 "; }
               else { System.out.print("ERROR "); print += "29 "; }
            }
         }
         else s += a;
      }
   }

   public static void main(String[] args) {
      
      // prompt the user to select the file
      System.out.println("Which input file would you like to analyze?");
      System.out.print("Pick one by entering a number (1-5) : ");
      Scanner inOpt = new Scanner(System.in);
      String inFile = "input" + inOpt.nextInt() + ".txt";
      System.out.println();
      
      try {
         
         Scanner in = new Scanner(new File(inFile)); // read the input from a file
         boolean nextLine = true; // to determine whether or not a new line is to be printed
         
         while(in.hasNextLine()){ // scan the input line by line
            
            // firstly, the line is split into tokens separated by whitespace
            String[] s = in.nextLine().trim().split("\\s+");
            
            // new line is not needed for the following conditions
            if(s[0].equals("") || s[0].equals("//") || s[0].equals("/*")) nextLine = false;
            
            for(int i=0; i<s.length; i++){
               if(s[i].equals("/*")) { // scan the string until */ is found
                  i++;
                  while(!s[i].equals("*/")) {
                     if(i<s.length-1) i++; // scan entire line
                     else { // still not found, go to the next line
                        s = in.nextLine().trim().split("\\s+");
                        i = 0;
                     }
                  }
                  break;
               } 
               else if(s[i].equals("//")) break; // simply skip the rest of the line once // presents
               else analyze(s[i]); // otherwise, pass it to 'analyze' function for further processing
            }
            
            if(nextLine) { // printing out the name of the tokens
               System.out.println();
               String print_names[] = print.trim().split("\\s+");
               for(int i=0; i<print_names.length; i++)
                  System.out.print(names[Integer.parseInt(print_names[i])] + " ");
               System.out.println();
            }
            nextLine = true;
            print = "";
         }
         
      }
      catch (FileNotFoundException e) {
         System.out.println("File not found!");
      }
   }
}