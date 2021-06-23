package models;

import java.util.ArrayList;

public class Restaurent extends User{
	
	private String restaurentName;
	private double rating;
	private int orderNo;
	private ArrayList<FoodItems> foodList = new ArrayList<>();
	private Cart ordersList = new Cart();
	
	public Restaurent(String user, String pass , String mail, String restaurent, String phone, String adress,double rating , int orders,long money) {
		super(user,pass,mail,phone,adress,money);
		this.restaurentName=restaurent;
		this.rating=rating;
		this.orderNo=orders; 
	}


	public String getRestaurentName() {
		return restaurentName;
	}



	public void setRestaurentName(String restaurentName) {
		this.restaurentName = restaurentName;
	}



	public double getRating() {
		return rating;
	}



	public void setRating(double rating) {
		this.rating = rating;
	}



	public int getOrderNo() {
		return orderNo;
	}



	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}


	public ArrayList<FoodItems> getFoodList() {
		return foodList;
	}


	public void setFoodList(ArrayList<FoodItems> foodList) {
		this.foodList = foodList;
	}


	public Cart getOrdersList() {
		return ordersList;
	}


	public void setOrdersList(Cart orders) {
		this.ordersList = orders;
	}
	
}