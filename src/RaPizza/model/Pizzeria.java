package RaPizza.model;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Scanner;

public class Pizzeria {
  private long orderIDCounter, pizzaIDCounter, ingredientIDCounter, driverIDCounter;
	private String name = "RaPizza";
	private ArrayList<User> clients;
	private ArrayList<CataloguedPizza> catalog;
	private ArrayList<Ingredient> used_ingredients;
	private ArrayList<ArchivedOrder> order_history;
	private ArrayList<Order> current_orders;
	private ArrayList<DeliveryDriver> drivers;

	public String getName() { return name; }
	public User[] getClients() { return clients.toArray(new User[0]);} // (User[])clients.toArray()
	public CataloguedPizza[] getCatalog() { return catalog.toArray(new CataloguedPizza[0]); }
	public Ingredient[] getIngredients() { return used_ingredients.toArray(new Ingredient[0]); }
	public ArchivedOrder[] getOrderHistory() { return order_history.toArray(new ArchivedOrder[0]); }
	public Order[] getOrders() { return current_orders.toArray(new Order[0]); }
	public DeliveryDriver[] getDrivers() { return drivers.toArray(new DeliveryDriver[0]); }

	public void addIngredient(Ingredient ing) { used_ingredients.add(ing); }
	public void addDriver(DeliveryDriver driver) { drivers.add(driver); }

	public Pizzeria() {
		clients = new ArrayList<User>();
		catalog = new ArrayList<CataloguedPizza>();
		used_ingredients = new ArrayList<Ingredient>();
		order_history = new ArrayList<ArchivedOrder>();
		current_orders = new ArrayList<Order>();
		drivers = new ArrayList<DeliveryDriver>();
	}

  public Ingredient getIngredient(long id) {
    for (Ingredient ing : used_ingredients)
      if (ing.id == id)
        return ing;
    return null;
  }

  public Order getOrder(long id) {
    System.out.println(id);
    for (Order order : current_orders) {
      System.out.println(order.ID + " - " + (id == order.ID));
      if (order.ID == id)
        return order;
    }
    return null;
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

  public void createPizza(String name, ArrayList<Ingredient> ingredients, float base_price) {
    System.out.println(ingredients.size());
    catalog.add(new CataloguedPizza(name, ingredients, base_price, pizzaIDCounter++));
  }

  public boolean createUser(String num, String name, String address, String mail) {
		try {
      return num.length() == 10 && createUser(Integer.parseInt(num.charAt(0) == '0' ? num.substring(1, 10) : num), name, address, mail);
    } catch (Exception e) {
      return false;
    }
	}
	public boolean createUser(int num, String name, String address, String mail) {
		if (getClient(num) != null)
			return false;

		clients.add(new User(name, address, mail, num, 0, 0, new ArrayList<Ingredient>()));

		return true;
	}

	public boolean createOrder(User client, ArrayList<Pizza> pizza,  String drinks, boolean hot_sauce) {
		Order order = new Order(client, pizza, drinks, hot_sauce);
		if (client == null || order.price > client.getBalance())
			return false;
		current_orders.add(order);
		return true;
	}
  public boolean validateOrder(Order order) {
    if (order.validated(orderIDCounter)) {
      orderIDCounter++;
      return true;
    }
    return false;
  }
	public boolean orderSend(Order order) {
    System.out.println("Send");
		return order.sended(findFreeDriver());
	}
	public boolean orderReceive(Order order) {
		if (!order.received(false))
			return false;

    long[] pizzasID = new long[order.pizzas.length], pizzasCount = new long[order.pizzas.length];
    for (int i = 0; i < order.pizzas.length; i++) {
      pizzasID[i] = order.pizzas[i].pizza.id;
      pizzasCount[i] = order.pizzas[i].count;
    }
		order_history.add(new ArchivedOrder(order.ID, order.client.getPhone(), pizzasID, pizzasCount, order.date.getTime(), order.driver.id, order.drinks, order.price)); // TODO
		current_orders.remove(order);
		return true;
	}

  public void createDriver(String name) {
    drivers.add(new DeliveryDriver(name, driverIDCounter++));
  }
  public void createIngredient(String name) {
    used_ingredients.add(new Ingredient(name, ingredientIDCounter++));
  }

	public void loadAll() {
		// The order is important !
		// ingredients, catalog, clients, order_history

    readCSV("res/data", e -> {
      name = e[0];
      orderIDCounter = Integer.parseInt(e[1]);
      pizzaIDCounter = Integer.parseInt(e[2]);
      ingredientIDCounter = Integer.parseInt(e[3]);
      driverIDCounter = Integer.parseInt(e[4]);
    });

    readCSV("res/Drivers.csv", el -> {
			drivers.add(new DeliveryDriver(el[0], Long.parseLong(el[1])));
		});

		readCSV("res/Ingredients.csv", el -> {
			used_ingredients.add(new Ingredient(el[0], Long.parseLong(el[1])));
		});

		readCSV("res/Catalog.csv", el -> {
			ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
			for (String ing_id : el[2].split(","))
				ings.add(used_ingredients.get(Integer.parseInt(ing_id)));
			catalog.add(new CataloguedPizza(el[0], ings, Float.parseFloat(el[1]), Long.parseLong(el[3])));
		});

		readCSV("res/Users.csv", el -> {
			ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
			if (el.length > 6)
			for (String ing_id : el[6].split(","))
				ings.add(getIngredient(Long.parseLong(ing_id)));
			clients.add(new User(el[0], el[1], el[2], Integer.parseInt(el[3]), Integer.parseInt(el[4]), Float.parseFloat(el[5]), ings));
		});

		readCSV("res/OrderHistory.csv", el -> {
		  long[] pizzas_id = new long[Integer.parseInt(el[8])];
			long[] pizzas_count = new long[pizzas_id.length];
      int i = 0;
			for (String id_str : el[4].split(","))
				pizzas_id[i++] = Long.parseLong(id_str);
      i = 0;
      for (String count_str : el[5].split(","))
				pizzas_count[i++] = Long.parseLong(count_str);
			order_history.add(new ArchivedOrder(Long.parseLong(el[0]), Integer.parseInt(el[1]), pizzas_id, pizzas_count, Long.parseLong(el[3]), Long.parseLong(el[7]), el[6], Float.parseFloat(el[2])));
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

    try {
      PrintStream writer = new PrintStream("res/data");
      writer.println("pizzeria_name;order_id_counter;pizza_id_counter;ingredient_id_counter;driver_id_counter");
      writer.println(name + ";" + orderIDCounter + ";" + pizzaIDCounter + ";" + ingredientIDCounter + ";" + driverIDCounter);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    new CSVWriter<Ingredient>("res/Ingredients.csv", "name;id", used_ingredients,
      o -> o.name + ";" + o.id);

      new CSVWriter<DeliveryDriver>("res/Drivers.csv", "name;id", drivers,
      o -> o.name + ";" + o.id);

    new CSVWriter<CataloguedPizza>("res/Catalog.csv", "pizza_name;base_price;ingredients;id", catalog, o -> {
      String ings = "";
      for (Ingredient ing : o.ingredients) ings += ing.id + ",";
      if (ings.length() > 0) ings = ings.substring(0, ings.length() - 1);
      return o.name + ";" + o.base_price + ";" + ings + ";" + o.id;
    });

    new CSVWriter<ArchivedOrder>("res/OrderHistory.csv", "order_id;client_id;price;date;pizzas_id;pizzas_count;drinks;driver_id;nb_pizzas", order_history, o -> {
      String pizzasID = "", pizzasCount = "";
      for (long id : o.pizzasID)
        pizzasID += id + ",";
      for (long count : o.pizzas_count)
        pizzasCount += count + ",";
      if (pizzasID.length() > 0) pizzasID = pizzasID.substring(0, pizzasID.length() - 1);
      if (pizzasCount.length() > 0) pizzasCount = pizzasCount.substring(0, pizzasCount.length() - 1);
      return "" + o.ID + ";" + o.clientID + ";" + o.price + ";" + o.date + ";" + pizzasID+ ";" + pizzasCount + ";" + o.drinks + ";" + o.driverID + ";" + o.pizzasID.length;
    });

    new CSVWriter<User>("res/Users.csv", "name;adress;mail;phone;pizza_count;balance;allergie", clients, o ->  {
      String ings = "";
      for (Ingredient ing : o.allergies) ings += ing.id + ",";
      if (ings.length() > 0) ings = ings.substring(0, ings.length() - 1);
      return o.getName() + ";" + o.getAddress() + ";" + o.getMail() + ";" + o.getPhone() + ";" + o.getPizzaCount() + ";" + o.getBalance() + ";" + ings;
    });
	}

}

interface CSVLambda {
	void processLine(String[] elements);
}

class CSVWriter<T> {
  CSVWriter(String file_name, String first_line, ArrayList<T> list, CSVWriteLambda<T> l) {
		try {
      PrintStream writer = new PrintStream(file_name);
      writer.println(first_line);
			for (T o : list)
        writer.println(l.processElement(o));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
}

interface CSVWriteLambda<T> {
	String processElement(T o);
}
