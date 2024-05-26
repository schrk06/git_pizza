package RaPizza.model;

public class Pizza {
	public CataloguedPizza pizza;
	public Size size;
	public int count;

	enum Size { Dwarf, Human, Ogress }

	public Pizza(CataloguedPizza pizza, int size, int count) {
		this.pizza = pizza;
		this.size = size == 0 ? Size.Dwarf : size == 1 ? Size.Human : Size.Ogress;
		this.count = count;
	}
}
