package edu.csupomona.cs480.starbuzz.beverages.coffee;

import edu.csupomona.cs480.starbuzz.beverages.Coffee;
import edu.csupomona.cs480.starbuzz.size.SizeFactor;

public class Espresso extends Coffee {

	SizeFactor sf = null;

	@Override
	public double cost() {
		return 1 + sf.cost();
	}

	@Override
	public String getDescription() {
		return "Espresso";
	}

	@Override
	public void setSizeFactor(SizeFactor sf) {
		this.sf = sf;
	}

}
