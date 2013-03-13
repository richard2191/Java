package edu.csupomona.cs480.starbuzz.beverages.tea;

import edu.csupomona.cs480.starbuzz.beverages.Tea;
import edu.csupomona.cs480.starbuzz.size.SizeFactor;

public class GreenTea extends Tea {
	
	SizeFactor sf = null;

	@Override
	public double cost() {
		return 1 + sf.cost();
	}

	@Override
	public String getDescription() {
		return "GreenTea";
	}

	@Override
	public void setSizeFactor(SizeFactor sf) {
		this.sf = sf;
	}

}
