package fr.creator_craft.RaPizza;

import java.util.ArrayList;

public class DeliveryDriver {
	public final String name;
	public final ArrayList<Review> reviews;
	
	public DeliveryDriver(String name) {
		this.name = name;
		this.reviews = new ArrayList<Review>();
	}
}
