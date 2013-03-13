package edu.csupomona.cs480.starbuzz.beverages;

import edu.csupomona.cs480.starbuzz.size.SizeFactor;

public interface Beverage {
	
	String getDescription();
	
	double cost();
	
	String prepare();
	
	void setSizeFactor(SizeFactor sf);

}
