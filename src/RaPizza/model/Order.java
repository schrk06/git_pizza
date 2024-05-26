package RaPizza.model;

import java.util.ArrayList;
import java.util.Date;

public class Order {
  public long ID;
	public User client;
	public Pizza[] pizzas;
	public String drinks;
	public Date date;
  public boolean with_hot_sauce;

	public DeliveryDriver driver;

	public float price;

	public OrderState state;

	public Order(User client, ArrayList<Pizza> pizzas, String drinks, boolean hot_sauce) {
		this.client = client;
		this.pizzas = pizzas.toArray(new Pizza[0]);
		this.drinks = drinks;
		this.state = OrderState.Choice;
		this.price = 0;
		this.driver = null;
		this.date = new Date();
    this.with_hot_sauce = hot_sauce;
	}

  public boolean validated(long ID) {
    if (state != OrderState.Choice)
			return false;
    this.ID = ID;
    state = OrderState.Preparation;
    return true;
  }

	public boolean sended(DeliveryDriver driver) {
    System.out.println(state + " " + driver);
		if (state != OrderState.Preparation || driver == null || !driver.isFree)
			return false;
    this.driver = driver;
    driver.isFree = false;
		state = OrderState.Delivering;
		return true;
	}
	public boolean received(boolean tooLate) {
		if (state != OrderState.Delivering)
			return false;
//		if (tooLate);
////			client.isNextPizzaFree = true;
//		else
//			client.balance -= price;
    driver.isFree = true;
		return true;
	}

	public enum OrderState {
    Choice,
		Preparation,
		Delivering
	}
}
