package edu.csupomona.cs480.starbuzz.size;

public class CoffeeBased implements SizeFactor {

	private double cost = 0;
	
	public CoffeeBased(String size) {
		switch(size){
		case "small":
			this.cost = 0.4; break;
		case "medium":
			this.cost = 0.7; break;
		case "large":
			this.cost = 1.0; break;
		default:
			System.out.println("WARN: Size '" + size + "' not found!"); break;	
		}
	}

	@Override
	public double cost() {
		return this.cost;
	}

}
