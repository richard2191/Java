package edu.csupomona.cs480.starbuzz.ingredients;

import edu.csupomona.cs480.starbuzz.beverages.Beverage;
import edu.csupomona.cs480.starbuzz.recipes.*;
import edu.csupomona.cs480.starbuzz.size.SizeFactor;

public abstract class Ingredient implements Beverage {
	
	protected Beverage decoratedBeverage;
	
	public Ingredient(Beverage decoratedBeverage) {
		this.decoratedBeverage = decoratedBeverage;
	}

	@Override
	public String getDescription() {
		return decoratedBeverage.getDescription();
	}

	@Override
	public double cost() {
		return decoratedBeverage.cost();
	}

	@Override
	public String prepare() {
		if(hasMilk())
			if(isCoffee()) return (new CoffeeMilkRecipe()).prepare();
			else return (new TeaMilkRecipe()).prepare();
		else
			if(isCoffee()) return (new CoffeeRecipe()).prepare();
			else return (new TeaRecipe()).prepare();
	}

	@Override
	public void setSizeFactor(SizeFactor sf) { }
	
	Beverage getBeverage() {
		return this.decoratedBeverage;
	}
	
	void setBeverage(Beverage b) {
		this.decoratedBeverage = b;
	}
	
	public abstract boolean hasMilk();
	
	public abstract boolean isCoffee();

}