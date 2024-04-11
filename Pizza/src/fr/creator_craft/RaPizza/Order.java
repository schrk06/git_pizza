package fr.creator_craft.RaPizza;

import java.util.ArrayList;

public class Order {
	public User user;
	private ArrayList<Pizza> pizzas;
	private ArrayList<String> drinks;
	
	private DeliveryDriver driver;
	
	private float price;
	
	private OrderState state;
	
	public Order(User user, ArrayList<Pizza> pizzas, ArrayList<String> drinks, float price) {
		this.user = user;
		this.pizzas = pizzas;
		this.drinks = drinks;
		this.state = OrderState.Delivered;
		this.price = price;
	}
	
	public Order(User user) { // new order
		this.user = user;
		pizzas = new ArrayList<Pizza>();
		drinks = new ArrayList<String>();
		state = OrderState.Choice;
		price = 0;
		driver = null;
	}
	
	public Pizza[] getPizzas() {
		return pizzas.toArray(new Pizza[0]);
	}
	public String[] getDrinks() {
		return drinks.toArray(new String[0]);
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


	enum OrderState {
		Choice,
		Preparation,
		Delivering,
		Delivered
	}
}
