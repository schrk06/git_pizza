package RaPizza.model;

import java.util.ArrayList;

public class CataloguedPizza {
  public final long id;
	public final String name;
	public final Ingredient[] ingredients;
	public final float base_price;

	public CataloguedPizza(String name, ArrayList<Ingredient> ingredients, float price, long id) {
		this.name = name;
		this.ingredients = ingredients.toArray(new Ingredient[0]);
		this.base_price = price;
    this.id = id;
	}

}
