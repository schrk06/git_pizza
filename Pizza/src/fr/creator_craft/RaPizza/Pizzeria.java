package fr.creator_craft.RaPizza;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pizzeria {
	private String name = "RaPizza";
	private ArrayList<User> clients;
	private ArrayList<CataloguedPizza> catalog;
	private ArrayList<Ingredient> used_ingredients;
	private ArrayList<Order> order_history;
	private ArrayList<Order> current_orders;
	private ArrayList<DeliveryDriver> drivers;
	
	public String getName() { return name; }
	public User[] getClients() { return (User[])clients.toArray(); }
	public CataloguedPizza[] getCatalog() { return (CataloguedPizza[])catalog.toArray(); }
	public Ingredient[] getIngredients() { return (Ingredient[])used_ingredients.toArray(); }
	public Order[] getOrderHistory() { return (Order[])order_history.toArray(); }
	public Order[] getOrders() { return (Order[])current_orders.toArray(); }
	public DeliveryDriver[] getDrivers() { return (DeliveryDriver[])drivers.toArray(); }
	
	public void addClient(User client) { clients.add(client); }
	public void addPizza(CataloguedPizza pizza) { catalog.add(pizza); }
	public void addIngredient(Ingredient ing) { used_ingredients.add(ing); }
	public void addDriver(DeliveryDriver driver) { drivers.add(driver); }
	
	public Pizzeria() {
		clients = new ArrayList<User>();
		catalog = new ArrayList<CataloguedPizza>();
		used_ingredients = new ArrayList<Ingredient>();
		order_history = new ArrayList<Order>();
		current_orders = new ArrayList<Order>();
		drivers = new ArrayList<DeliveryDriver>();
	}
	
	public User getClient(int num) {
		for (User client : clients)
			if (client.phone == num)
				return client;
		return null;
	}
	
	public DeliveryDriver findFreeDriver() {
		for (DeliveryDriver driver : drivers)
			if (driver.isFree)/////////
				return driver;
		return null;
	}
	
	public boolean createUser(int num, String name, String adress, String mail) {
		if (getClient(0) != null)
			return false;
		
		clients.add(new User(name, adress, mail, num, 0, 0, new ArrayList<Ingredient>()));
		
		return true;
	}
	
	public boolean createOrder(User client, ArrayList<Pizza> pizza,  ArrayList<String> drinks) {
		Order order = new Order(client, pizza, drinks);
		if (order.price > client.balance)
			return false;
		current_orders.add(order);
		return true;
	}
	
	public boolean orderSend(Order order) {
		return order.sended(findFreeDriver());
	}
	
	public boolean orderReceive(Order order) {
		if (!order.received(false))
			return false;
		order_history.add(order);
		current_orders.remove(order);
		return true;
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
					pizzas.add(new Pizza(catalog.get(s.nextInt()), s.nextInt(), s.nextInt(), s.nextBoolean()));
				ArrayList<String> drinks = new ArrayList<String>();
				for (int j = s.nextInt(); j > 0; j--)
					drinks.add(s.next());
				order_history.add(new Order(clients.get(s.nextInt()), pizzas, drinks, s.nextFloat(), s.nextLong()));
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
