package RaPizza.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Order {
  public final long ID;
	public User client;
	public Pizza[] pizzas;
	public String[] drinks;
	public Date date;

	public DeliveryDriver driver;

	public float price;

	public OrderState state;

	public Order(User client, ArrayList<Pizza> pizzas, ArrayList<String> drinks, float price, long date, long ID) {
		pizzas.sort(new Comparator<Pizza>() {
		    @Override
		    public int compare(Pizza left, Pizza right) {
		        // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
		        return 0;
		    }
		});
		this.client = client;
		this.pizzas = pizzas.toArray(new Pizza[0]);
		this.drinks = drinks.toArray(new String[0]);
		this.state = OrderState.Delivered;
		this.price = price;
		this.date = new Date();
    this.ID = ID;
	}

	public Order(User client, ArrayList<Pizza> pizzas, ArrayList<String> drinks, long ID) { // new order
		this.client = client;
		pizzas = new ArrayList<Pizza>();
		drinks = new ArrayList<String>();
		state = OrderState.Preparation;
		price = 0;
		driver = null;
		date = new Date();
    this.ID = ID;
	}

	public boolean sended(DeliveryDriver driver) {
		if (state != OrderState.Preparation | driver == null)
			return false;
		state = OrderState.Delivering;
		return true;
	}
	public boolean received(boolean tooLate) {
		if (state != OrderState.Delivering)
			return false;
		state = OrderState.Delivered;
//		if (tooLate);
////			client.isNextPizzaFree = true;
//		else
//			client.balance -= price;
		return true;
	}


	enum OrderState {
//		Choice,
		Preparation,
		Delivering,
		Delivered
	}
}
