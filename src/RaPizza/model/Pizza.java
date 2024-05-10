package RaPizza.model;

public class Pizza {
	public CataloguedPizza pizza;
	public Size size;
	public boolean hot_sauce;
	public int count;
	
	enum Size { Dwarf, Human, Ogress }

	public Pizza(CataloguedPizza pizza, int size, int count, boolean hot_sauce) {
		this.pizza = pizza;
		this.size = size == 0 ? Size.Dwarf : size == 1 ? Size.Human : Size.Ogress;
		this.hot_sauce = hot_sauce;
		this.count = count;
	}
}
