package RaPizza.model;

import java.util.ArrayList;

public class User {
	private String name, address, mail;
	private int phone, pizza_count;
	private float balance;
	ArrayList<Ingredient> allergies;

	Order current_order = null;

	public User(String name, String address, String mail, int phone, int pizza_count, float balance, ArrayList<Ingredient> allergies) {
		this.name = name;
		this.address = address;
		this.mail = mail;
		this.phone = phone;
		this.pizza_count = pizza_count;
		this.balance = balance;
		this.allergies = allergies;
	}

	public float getBalance() { return balance; }
	public int getPhone() { return phone; }
	public int getPizzaCount() { return pizza_count; }
	public String getName() { return name; }
	public String getAddress() { return address; }
	public String getMail() { return mail; }


}
