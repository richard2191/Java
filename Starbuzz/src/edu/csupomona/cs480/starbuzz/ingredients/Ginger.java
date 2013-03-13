package edu.csupomona.cs480.starbuzz.ingredients;

import edu.csupomona.cs480.starbuzz.beverages.Beverage;

public class Ginger extends Ingredient {
	
	String ingredients[] = this.getDescription().split("\\s+");
	
	public Ginger(Beverage decoratedBeverage) {
		super(decoratedBeverage);
	}
	
	@Override
	public double cost() {
		return super.cost() + 0.6;
	}

	@Override
	public String getDescription() {
		return super.getDescription() + " Ginger";
	}
	
	@Override
	public boolean hasMilk() {
		for(String ingredient : this.ingredients) if (ingredient.equals("Milk")) return true;
		return false;
	}
	
	@Override
	public boolean isCoffee() {
		String coffee_beverages[] = {"Decaf", "Espresso", "HouseBlend"};
		for(String coffee : coffee_beverages) if (coffee.equals(this.ingredients[0])) return true;
		return false;
		
	}
}
