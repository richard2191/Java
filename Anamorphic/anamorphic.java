import java.util.Scanner;

public class anamorphic {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while(in.hasNextLong()){
			long w_in = in.nextLong(), h_in = in.nextLong(), w_dev = in.nextLong(), h_dev = in.nextLong(), w_out, h_out;
			
			if((float) w_in/h_in == (float) w_dev/h_dev){
				w_out = w_dev; h_out = h_dev;
			}
			else if((float) w_in/h_in > (float) w_dev/h_dev){
				w_out = w_dev; h_out = (long) ((float) w_dev/w_in*h_in);
			}
			else { // w_in/h_in < w_dev/h_dev
				h_out = h_dev; w_out = (long) ((float) h_dev/h_in*w_in);
			}
			System.out.println("w x h = " + w_out + " x " + h_out + " pixels");
}	}	}