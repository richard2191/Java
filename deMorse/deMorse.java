import java.util.*;

public class deMorse {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		HashMap<String, String> hash = new HashMap<String, String>();
		String[] alpha = {"0000000", "10111", "111010101", "11101011101", "1110101", "1", "101011101", "111011101", "1010101", "101", "1011101110111", "111010111", "101110101", "1110111", "11101", "11101110111", "10111011101", "1110111010111", "1011101", "10101", "111", "1010111", "101010111", "101110111", "11101010111", "1110101110111", "11101110101"};
		
		for(int i = 1; i < alpha.length; i++){
			hash.put(alpha[i], Character.toString((char) (96+i)));
		}
		
		while(in.hasNext()){
			String s = in.next(), output = "", break_word, break_letter, temp = "";
			int i = 0;
			while(i < s.length()){
				
				if(i+7 < s.length()){
					break_letter = Character.toString(s.charAt(i+1)) + Character.toString(s.charAt(i+2)) + Character.toString(s.charAt(i+3));
					break_word = break_letter + Character.toString(s.charAt(i+4)) + Character.toString(s.charAt(i+5)) + Character.toString(s.charAt(i+6)) + Character.toString(s.charAt(i+7));
				}
				else if(i+3 < s.length()){
					break_letter = Character.toString(s.charAt(i+1)) + Character.toString(s.charAt(i+2)) + Character.toString(s.charAt(i+3));
					break_word = "";
				}
				else { break_letter = ""; break_word = ""; }
				
				if(break_word.equals(alpha[0])){ // break between words
					temp += s.charAt(i);
					output += hash.get(temp) + " ";
					temp = ""; i += 8;
				}
				else if(break_letter.equals("000")){ // break between letters
					temp += s.charAt(i);
					output += hash.get(temp);
					temp = ""; i += 4;
				}
				else { // break between tones
					temp += s.charAt(i);
					i++;
			}	}
			output += hash.get(temp);
			System.out.println(output);
}	}	}
