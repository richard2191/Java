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
		
		String ingredients[] = this.getDescription().split("\\s+");
		String coffee[] = {"Decaf", "Espresso", "HouseBlend"};
		boolean isCoffee = false, hasMilk = false;
		
		for(String beverage : coffee)
			if (beverage.equals(ingredients[0])) {
				isCoffee = true; break;
			}
		
		for(String ingredient : ingredients)
			if (ingredient.equals("Milk")) {
				hasMilk = true; break;
			}
		
		if (hasMilk) {
			if (isCoffee) return (new CoffeeMilkRecipe()).prepare();
			else return (new TeaMilkRecipe()).prepare();
		}
			
		else {
			if (isCoffee) return (new CoffeeRecipe()).prepare();
			else return (new TeaRecipe()).prepare();
		}
	}

	@Override
	public void setSizeFactor(SizeFactor sf) { }
	
	Beverage getBeverage() {
		return this.decoratedBeverage;
	}
	
	void setBeverage(Beverage b) {
		this.decoratedBeverage = b;
	}

}