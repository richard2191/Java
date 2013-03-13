package edu.csupomona.cs480.starbuzz.beverages.coffee;

import edu.csupomona.cs480.starbuzz.beverages.Coffee;
import edu.csupomona.cs480.starbuzz.size.SizeFactor;

public class Decaf extends Coffee {
	
	SizeFactor sf = null;

	@Override
	public double cost() {
		return 0.5 + sf.cost();
	}

	@Override
	public String getDescription() {
		return "Decaf";
	}

	@Override
	public void setSizeFactor(SizeFactor sf) {
		this.sf = sf;
	}

}
