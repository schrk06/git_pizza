package fr.creator_craft.RaPizza;

import java.util.ArrayList;

public class Pizzeria {
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
		// ingredients, catalog, order_history, clients
		
	}
	
	public void saveAll() {
		
	}
}
