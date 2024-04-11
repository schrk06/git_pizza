package fr.creator_craft.RaPizza;

public class Pizza {
	public CataloguedPizza pizza;
	public Size size;
	public boolean hot_sauce;
	
	enum Size { Dwarf, Human, Ogress }

	public Pizza(CataloguedPizza pizza, Size size, boolean hot_sauce) {
		this.pizza = pizza;
		this.size = size;
		this.hot_sauce = hot_sauce;
	}
}
