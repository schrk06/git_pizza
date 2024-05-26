package RaPizza.model;

public class ArchivedOrder {
  public final long ID, clientID, pizzasID[], pizzas_count[], date, driverID;
  public final String drinks;
  public final float price;

  public ArchivedOrder(long ID, long clientID, long[] pizzasID, long[] pizzas_count, long date, long driverID, String drinks, float price) {
    this.ID = ID;
    this.clientID = clientID;
    this.pizzasID = pizzasID;
    this.pizzas_count = pizzas_count;
    this.date = date;
    this.driverID = driverID;
    this.drinks = drinks;
    this.price = price;
  }
}
