package fr.creator_craft.RaPizza;

import java.util.ArrayList;

public class Order {
	private ArrayList<Pizza> pizzas;
	private ArrayList<Drink> drinks;
	
	private DeliveryDriver driver;
	
	private float price;
	
	private OrderState state;
	
	public Order() {
		pizzas = new ArrayList<Pizza>();
		drinks = new ArrayList<Drink>();
		state = OrderState.Choice;
		price = 0;
		driver = null;
	}
	
	public Pizza[] getPizzas() {
		return pizzas.toArray(new Pizza[0]);
	}
	public Drink[] getDrinks() {
		return drinks.toArray(new Drink[0]);
	}
	public DeliveryDriver getDriver() {
		return driver;
	}
	public float getPrice() {
		return price;
	}
	public OrderState getState() {
		return state;
	}


	enum Drink {
		Water
	}
	enum OrderState {
		Choice,
		Preparation,
		Delivering,
		Delivered
	}
}
