package fr.creator_craft.RaPizza;

public class Ingredient {
	final String name;
	final boolean allergen;
//	NutriPackage nutri;
	
	public Ingredient(String name, boolean allergen) {
		this.name = name;
		this.allergen = allergen;
	}
}
