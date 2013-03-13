package edu.csupomona.cs480.starbuzz.size;

public class TeaBased implements SizeFactor {
	
	double cost = 0;
	
	public TeaBased(String size) {
		switch(size){
		case "small":
			this.cost = 0.2; break;
		case "medium":
			this.cost = 0.5; break;
		case "large":
			this.cost = 0.7; break;
		default:
			System.out.println("WARN: Size '" + size + "' not found!"); break;	
		}
	}

	@Override
	public double cost() {
		return this.cost;
	}

}
