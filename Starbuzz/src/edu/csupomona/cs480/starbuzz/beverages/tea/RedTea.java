package edu.csupomona.cs480.starbuzz.beverages.tea;

import edu.csupomona.cs480.starbuzz.beverages.Tea;
import edu.csupomona.cs480.starbuzz.size.SizeFactor;

public class RedTea extends Tea {
	
	SizeFactor sf = null;

	@Override
	public double cost() {
		return 0.8 + sf.cost();
	}

	@Override
	public String getDescription() {
		return "RedTea";
	}

	@Override
	public void setSizeFactor(SizeFactor sf) {
		this.sf = sf;
	}

}
