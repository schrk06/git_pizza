package fr.creator_craft.RaPizza;

import java.util.ArrayList;
import java.util.Arrays;

public class User {
	String name, adress, mail;
	int phone, pizza_count;
	float balance;
	ArrayList<Ingredient> allergies;
	
	Order current_order = null;
	
	public User(String name, String adress, String mail, int phone, int pizza_count, float balance, Ingredient[] allergies) {
		this.name = name;
		this.adress = adress;
		this.mail = mail;
		this.phone = phone;
		this.pizza_count = pizza_count;
		this.balance = balance;
		this.allergies = new ArrayList<Ingredient>(Arrays.asList(allergies));
	}
	
	
	
}
