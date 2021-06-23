package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import DataHandling.DataHandle;

public class Order {
	private ArrayList<FoodItems> orderList = new ArrayList<>() ;
	private Customer customer ;
	private Restaurent restaurent ;
	private String OrderTime;
	private boolean status;
	private boolean RiderStatus;
	private int id; 
	
	
	public Order() {
		
	}
	
	public Order(Customer customer , Restaurent restaurent) {
		this.customer=customer;
		this.restaurent=restaurent;
		this.RiderStatus=false;
	}
	
	public Order(Customer customer , Restaurent restaurent, boolean status,int id,String date,Boolean riderStatus) {
		this.customer=customer;
		this.restaurent=restaurent;
		this.status= status;
		this.id=id;
		this.OrderTime=date;
		this.RiderStatus=riderStatus;
	}

	public ArrayList<FoodItems> getOrderList() {
		return orderList;
	}

	public void setOrderList(ArrayList<FoodItems> orderList) {
		this.orderList = orderList;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Restaurent getRestaurent() {
		return restaurent;
	}

	public void setRestaurent(Restaurent restaurent) {
		this.restaurent = restaurent;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public void setid() {

	Random rand = new Random();
	DataHandle data = new DataHandle();
	for(int i=0 ; i< data.getRestaurents().size() ; i++) {
		for(int j=0 ; j < data.getRestaurents().get(i).getOrdersList().getOrders().size() ; j++) {
			do {
				this.id = rand.nextInt(2000);
			}
			while(id==data.getRestaurents().get(i).getOrdersList().getOrders().get(j).id);
		}
		
	}
	}
	
	
	public int getid() {
		return this.id;
	}

	public String getOrderTime() {
		return OrderTime;
	}

	public void setOrderTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		this.OrderTime=dtf.format(now);
	}

	public boolean isRiderStatus() {
		return RiderStatus;
	}

	public void setRiderStatus(boolean riderStatus) {
		RiderStatus = riderStatus;
	}
	
	
	
}
