import java.util.*;

public class barcode {
	
	static private String toDecimal(String bin){
		String s = Integer.toString(Integer.parseInt(bin, 2),8);
		while(s.length()<8) s = "0" + s;
		return s;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		int item = in.nextInt();
		
		for(int i=0; i<item; i++) hash.put(in.next(), in.nextInt());
		int number=0, pay=0;
		
		while(in.hasNextInt()){
			int numb = in.nextInt();
			String code = in.next();
			if(hash.containsKey(toDecimal(code))){
				number += numb;
				pay += numb * hash.get(toDecimal(code));
			}
			else System.out.println("item " + toDecimal(code) + " not in inventory");
		}
		
		System.out.println("deliver " + number + " items from inventory, total cost = $" + pay);
}	}
