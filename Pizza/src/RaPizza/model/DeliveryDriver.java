package RaPizza.model;

import java.util.ArrayList;

public class DeliveryDriver {
	public final String name;
	public final ArrayList<Review> reviews;
	public boolean isFree;
	
	public DeliveryDriver(String name) {
		this.name = name;
		this.reviews = new ArrayList<Review>();
		this.isFree = true;
	}
}
