package edu.csupomona.cs480.starbuzz.beverages;

import edu.csupomona.cs480.starbuzz.recipes.TeaRecipe;
import edu.csupomona.cs480.starbuzz.size.SizeFactor;

public abstract class Tea implements Beverage {

	public abstract String getDescription();
	
	public abstract double cost();

	public abstract void setSizeFactor(SizeFactor sf);
	
	@Override
	public String prepare() {
		return (new TeaRecipe()).prepare();
	}

}
