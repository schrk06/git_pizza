package RaPizza.model;

public class DeliveryDriver {
	public final String name;
  public final long id;
	// public final ArrayList<Review> reviews;
	public boolean isFree;

	public DeliveryDriver(String name, long id) {
		this.name = name;
    this.id = id;
		// this.reviews = new ArrayList<Review>();
		this.isFree = true;
	}
}
