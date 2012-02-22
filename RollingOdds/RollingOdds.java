import java.util.Scanner;

public class RollingOdds {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNextInt()){
			
			int a = in.nextInt(), b = in.nextInt(), c = in.nextInt();
			System.out.println("5/9 5/12 1/36\n5/9 5/12 1/36");
			
			if(a != b) System.out.println("2/3 1/3 0/1");
			else System.out.println("0/1 5/6 1/6");
			
			if(a != b && a != c && b != c) System.out.println("1/1 0/1 0/1\n");
			else if(a == b && a == c) System.out.println("0/1 0/1 1/1\n");
			else System.out.println("0/1 1/1 0/1\n");
}	}	}
