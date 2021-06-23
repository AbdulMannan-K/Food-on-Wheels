package models;

import java.util.ArrayList;

public class Rider extends User{
	
	private String[] vehicle = new String[3];
	private String firstName;
	private String lastName;
	private Cart ordersList = new Cart();
	private boolean isBusy ;
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	public String[] getVehicle() {
		return vehicle;
	}
	public void setVehicle(String[] vehicle) {
		this.vehicle = vehicle;
	}
	
	public Rider(String user, String pass , String mail, String firstName, String last, String phone,String[] vehicle, String city,Long money){
		super(user,pass,mail,phone,city,money);
		this.vehicle=vehicle;
		this.firstName=firstName;
		this.lastName=last;
		this.isBusy=false;
	}

	public Rider(String user, String pass , String mail, String firstName, String last, String phone,String[] vehicle, String city,Boolean busy,Long money){
		super(user,pass,mail,phone,city,money);
		this.vehicle=vehicle;
		this.firstName=firstName;
		this.lastName=last;
		this.isBusy=busy;
	}
	
	
	public Cart getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(Cart ordersList) {
		this.ordersList = ordersList;
	}
	public boolean isBusy() {
		return isBusy;
	}
	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

}
