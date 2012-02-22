import java.util.Scanner;

public class change {
	
	static int money;
	
	static private void eval(int cent){
		int a = money/cent;
		money -= a*cent;
		if(a != 0) System.out.println(a + " x " + cent + " cents");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(in.hasNext()){
			money = in.nextInt();
			System.out.println("\n" + money + " cents:");
			
			eval(25); eval(10);
			eval(5); eval(1);
}	}	}
