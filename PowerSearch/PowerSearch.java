import java.util.Scanner;
import java.math.BigInteger;

public class PowerSearch {
	public static void main(String[] args) {
		BigInteger a, b;
		int pwr = 0;
		Scanner in = new Scanner(System.in);
		while(in.hasNextBigInteger()){
			a = in.nextBigInteger();
			b = in.nextBigInteger();
			while(!(a.compareTo(b.pow(pwr)) <= 0 && b.pow(pwr).compareTo(a.multiply(b)) < 0)){
				pwr++;
			}
			System.out.println(a + " <= " + b + "^" + pwr + " = " + b.pow(pwr) + " < " + a + "*" + b + " = " + a.multiply(b));
			pwr = 0;
}	}	}
