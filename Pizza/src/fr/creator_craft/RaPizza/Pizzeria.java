package fr.creator_craft.RaPizza;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pizzeria {
	String name;
	ArrayList<User> clients;
	ArrayList<CataloguedPizza> catalog;
	ArrayList<Ingredient> used_ingredients;
	ArrayList<Order> order_history;
	
	public Pizzeria() {
		clients = new ArrayList<User>();
		catalog = new ArrayList<CataloguedPizza>();
		used_ingredients = new ArrayList<Ingredient>();
		order_history = new ArrayList<Order>();
		
//		try {
//			FileReader reader = new FileReader("User.dat");
//			Scanner s = new Scanner(reader);
//			
//			client = new User(s.next(), s.next(), s.next(), s.nextInt(), s.nextFloat(), null);
//			
//			s.close();
//			reader.close();
//			
//			reader = new FileReader("Catalog.dat");
//			s = new Scanner(reader);
//			
//			catalog = new CataloguedPizza[s.nextInt()];
//			
//			s.close();
//			reader.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public void loadAll() {
		// The order is important !
		// ingredients, catalog, clients, order_history
		
		try {
			FileReader reader = new FileReader("Ingredients.dat");
			Scanner s = new Scanner(reader);
			for (int i = s.nextInt(); i > 0; i--)
				used_ingredients.add(new Ingredient(s.next(), s.nextBoolean()));
			s.close();
			reader.close();
			
			reader = new FileReader("Catalog.dat");
			s = new Scanner(reader);
			for (int i = s.nextInt(); i > 0; i--) { // pizza
				ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
				for (int j = s.nextInt(); j > 0; j--)
					ings.add(used_ingredients.get(s.nextInt()));
				catalog.add(new CataloguedPizza(s.next(), ings, s.nextFloat()));
			}
			s.close();
			reader.close();
			
			reader = new FileReader("Users.dat");
			s = new Scanner(reader);
			for (int i = s.nextInt(); i > 0; i--) {
				ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
				for (int j = s.nextInt(); j > 0; j--)
					ings.add(used_ingredients.get(s.nextInt()));
				clients.add(new User(s.next(), s.next(), s.next(), s.nextInt(), s.nextInt(), s.nextFloat(), ings));
			}
			s.close();
			reader.close();

			reader = new FileReader("OrderHistory.dat");
			s = new Scanner(reader);
			for (int i = s.nextInt(); i > 0; i--) {
				ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
				for (int j = s.nextInt(); j > 0; j--)
					pizzas.add(new Pizza(catalog.get(s.nextInt()), s.nextInt(), s.nextBoolean()));
				ArrayList<String> drinks = new ArrayList<String>();
				for (int j = s.nextInt(); j > 0; j--)
					drinks.add(s.next());
				order_history.add(new Order(clients.get(s.nextInt()), pizzas, drinks, s.nextFloat()));
			}
			s.close();
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveAll() {
		
	}
}
