package RaPizza.model;

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
	public User[] getClients() { return clients.toArray(new User[0]);} // (User[])clients.toArray()
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
			if (client.getPhone() == num)
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
		if (order.price > client.getBalance())
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
		
		readCSV("Ingredients.csv", el -> {
			used_ingredients.add(new Ingredient(el[0]));
		});
		
		readCSV("Catalog.csv", el -> {
			ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
			for (String ing_id : el[2].split(","))
				ings.add(used_ingredients.get(Integer.parseInt(ing_id)));
			catalog.add(new CataloguedPizza(el[0], ings, Float.parseFloat(el[1])));
		});
		
		readCSV("Users.csv", el -> {
			ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
			if (el.length > 6)
			for (String ing_id : el[6].split(","))
				ings.add(used_ingredients.get(Integer.parseInt(ing_id)));
			clients.add(new User(el[0], el[1], el[2], Integer.parseInt(el[3]), Integer.parseInt(el[4]), Float.parseFloat(el[5]), ings));
		});
		
		readCSV("OrderHistory.csv", el -> {
			ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
			ArrayList<String> drinks = new ArrayList<String>();
			for (String pizza_desc : el[3].split(",")) {
				String[] pizza_info = pizza_desc.split(":");
				pizzas.add(new Pizza(catalog.get(Integer.parseInt(pizza_info[0])), Integer.parseInt(pizza_info[1]),Integer.parseInt(pizza_info[2]), Boolean.parseBoolean(pizza_info[3])));
			}
			if (el.length > 4)
			for (String drink : el[4].split(","))
				drinks.add(drink);
			order_history.add(new Order(clients.get(Integer.parseInt(el[0])), pizzas, drinks, Float.parseFloat(el[1]), Long.parseLong(el[2])));
		});
	}
	
	private void readCSV(String file_name, CSVLambda l) {
		try {
			int line_nb = 1;
			FileReader reader = new FileReader(file_name);
			Scanner s = new Scanner(reader);
			s.nextLine(); // Skip first line
			while (s.hasNextLine()) {
				try {
					l.processLine(s.nextLine().split(";"));
					line_nb ++;
				} catch (Exception e) {
					System.err.println("Exception while reading line " + line_nb + " of file '" + file_name + "'");
					e.printStackTrace();
					System.exit(-1);
				}
			}
			s.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void saveAll() {
		
	}
}

interface CSVLambda {
	void processLine(String[] elements);
}
