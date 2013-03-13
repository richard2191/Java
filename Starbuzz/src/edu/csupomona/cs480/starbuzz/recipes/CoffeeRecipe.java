package edu.csupomona.cs480.starbuzz.recipes;

public class CoffeeRecipe implements Recipe {

	@Override
	public String prepare() {
		return "- add coffee\n- add water\n- process ingredients\n- add ingredients";
	}

}
