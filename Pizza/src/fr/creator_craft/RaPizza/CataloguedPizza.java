package fr.creator_craft.RaPizza;

import java.util.ArrayList;

public class CataloguedPizza {
	public final String name;
	public final ArrayList<Ingredient> ingredients;
	public final float base_price;
	
	public CataloguedPizza(String name, ArrayList<Ingredient> ingredients, float price) {
		this.name = name;
		this.ingredients = ingredients;
		this.base_price = price;
	}
	
}
