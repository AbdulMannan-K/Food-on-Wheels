package models;

import java.util.ArrayList;

public class Cart {
	
	private ArrayList<Order> orders = new ArrayList<>();

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}
	
	
	
}
