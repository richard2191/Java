import java.text.DecimalFormat;
import java.util.Scanner;

public class TruePricing {

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("#.##");
		Scanner in = new Scanner(System.in);
		String str; String[] s;
		int t; float tax = 0;
		
		while(in.hasNextLine()){
			str = in.nextLine();
			s = str.split("\\s+");
			t = Integer.parseInt(s[0]);
			
			for(int i=1; i<s.length; i++) tax += Float.parseFloat(s[i]);
			
			System.out.println(df.format((float) (t/(1.0+tax/100.0))));
			tax = 0;
}	}	}
