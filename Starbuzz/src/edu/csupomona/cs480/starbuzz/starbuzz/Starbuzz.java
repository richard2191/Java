package edu.csupomona.cs480.starbuzz.starbuzz;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import edu.csupomona.cs480.starbuzz.beverages.Beverage;
import edu.csupomona.cs480.starbuzz.beverages.coffee.*;
import edu.csupomona.cs480.starbuzz.beverages.tea.*;
import edu.csupomona.cs480.starbuzz.ingredients.*;
import edu.csupomona.cs480.starbuzz.size.*;

public class Starbuzz {
	
	// function for adding ingredients using decorator pattern
	private static Beverage addIngredients(Beverage c, ArrayList<String> ingredients) {

		for (String ingredient : ingredients) {
			switch (ingredient) {
			case "milk":
				c = new Milk(c);
				break;
			case "whipcream":
				c = new WhipCream(c);
				break;
			case "chocolate":
				c = new Chocolate(c);
				break;
			case "jasmine":
				c = new Jasmine(c);
				break;
			case "ginger":
				c = new Ginger(c);
				break;
			default:
				System.out.println("WARN: Ingredient '" + ingredient + "' not found!");
				break;
			}
		}

		return c;
	}

	// function for printing the output
	private static void printOutput(Beverage b) {
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println("The ordered beverage is " + b.getDescription());
		System.out.println("The total cost of your order is: $" + df.format(b.cost()));
		System.out.println("The beverage is prepared as follows:\n" + b.prepare());
	}

	public static void main(String[] args) {
		
		boolean run = true;

		while (run) {
			
			// prompt the user to enter the input
			System.out.println("\nPlease enter the beverage order in the following format:");
			System.out.println("<beverage name>  <size>  [<ingredient 1 ingredient 2 ingredient 3...>]");

			Scanner in = new Scanner(System.in);
			String input = in.nextLine();

			// parse the input string
			String order[] = input.toLowerCase().split("\\s+");
			String size = "medium"; // set the default size to 'medium' if not specified
			
			try {
				size = order[1];
			} catch (ArrayIndexOutOfBoundsException e) {
				if(!order[0].equals("exit")) System.out.println("Beverage size is not specified, using 'medium' as default.");
			}
			
			ArrayList<String> ingredients = new ArrayList<String>();

			for (int i = 2; i < order.length; i++)
				ingredients.add(order[i]);

			// create the beverage object according to the input
			switch (order[0]) {

			case "decaf":
				Beverage _decaf = new Decaf();
				_decaf.setSizeFactor(new CoffeeBased(size));
				_decaf = addIngredients(_decaf, ingredients);
				printOutput(_decaf);
				break;

			case "houseblend":
				Beverage _houseblend = new HouseBlend();
				_houseblend.setSizeFactor(new CoffeeBased(size));
				_houseblend = addIngredients(_houseblend, ingredients);
				printOutput(_houseblend);
				break;

			case "espresso":
				Beverage _espresso = new Espresso();
				_espresso.setSizeFactor(new CoffeeBased(size));
				_espresso = addIngredients(_espresso, ingredients);
				printOutput(_espresso);
				break;
				
			case "mocha":
				_espresso = new Espresso(); // reuse espresso object
				_espresso.setSizeFactor(new CoffeeBased(size));
				_espresso = new Chocolate(_espresso); // modify
				_espresso = addIngredients(_espresso, ingredients);
				printOutput(_espresso);
				break;
				
			case "latte":
				_espresso = new Espresso(); // reuse espresso object
				_espresso.setSizeFactor(new CoffeeBased(size));
				_espresso = new Milk(_espresso); // modify
				_espresso = addIngredients(_espresso, ingredients);
				printOutput(_espresso);
				break;
				
			case "cappuccino":
				_espresso = new Espresso(); // reuse espresso object
				_espresso.setSizeFactor(new CoffeeBased(size));
				_espresso = new WhipCream(_espresso); // modify
				_espresso = addIngredients(_espresso, ingredients);
				printOutput(_espresso);
				break;
				
			case "decafmocha":
				_decaf = new Decaf(); // reuse decaf object
				_decaf.setSizeFactor(new CoffeeBased(size));
				_decaf = new Chocolate(_decaf); // modify
				_decaf = addIngredients(_decaf, ingredients);
				printOutput(_decaf);
				break;
				
			case "decaflatte":
				_decaf = new Decaf(); // reuse decaf  object
				_decaf.setSizeFactor(new CoffeeBased(size));
				_decaf = new Milk(_decaf); // modify
				_decaf = addIngredients(_decaf, ingredients);
				printOutput(_decaf);
				break;
				
			case "decafcappuccino":
				_decaf = new Decaf(); // reuse decaf object
				_decaf.setSizeFactor(new CoffeeBased(size));
				_decaf = new WhipCream(_decaf); // modify
				_decaf = addIngredients(_decaf, ingredients);
				printOutput(_decaf);
				break;

			case "redtea":
				Beverage _redtea = new RedTea();
				_redtea.setSizeFactor(new TeaBased(size));
				_redtea = addIngredients(_redtea, ingredients);
				printOutput(_redtea);
				break;

			case "whitetea":
				Beverage _whitetea = new WhiteTea();
				_whitetea.setSizeFactor(new TeaBased(size));
				_whitetea = addIngredients(_whitetea, ingredients);
				printOutput(_whitetea);
				break;

			case "greentea":
				Beverage _greentea = new GreenTea();
				_greentea.setSizeFactor(new TeaBased(size));
				_greentea = addIngredients(_greentea, ingredients);
				printOutput(_greentea);
				break;
				
			case "flowertea":
				_greentea = new GreenTea();
				_greentea.setSizeFactor(new TeaBased(size));
				_greentea = new Jasmine(_greentea);
				_greentea = addIngredients(_greentea, ingredients);
				printOutput(_greentea);
				break;
				
			case "gingertea":
				_greentea = new GreenTea();
				_greentea.setSizeFactor(new TeaBased(size));
				_greentea = new Ginger(_greentea);
				_greentea = addIngredients(_greentea, ingredients);
				printOutput(_greentea);
				break;
				
			case "tealatte":
				_redtea = new RedTea();
				_redtea.setSizeFactor(new TeaBased(size));
				_redtea = new Milk(_redtea);
				_redtea = addIngredients(_redtea, ingredients);
				printOutput(_redtea);
				break;
				
			case "exit":
				run = false;
				break;

			default:
				System.out.println("ERROR: Beverage '" + order[0] + "' not found!");
				break;
			}
		}

	}
}
