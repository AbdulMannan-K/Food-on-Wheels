package models;
import java.util.*;

public class Admin extends User{
	
	private static ArrayList<Customer> customers = new ArrayList<>();
	private static ArrayList<Restaurent> restaurents = new ArrayList<>();
	private static ArrayList<Rider> riders = new ArrayList<>();
	
	public Admin(String user, String pass , String mail, String phone, String adress){
		super(user,pass,mail,phone,adress,(long) 0);
	}

	public ArrayList<Customer> getCustomers() {
		return Admin.customers;
	}

	public void setCustomers(ArrayList<Customer> customers) {
		Admin.customers = customers;
	}

	public ArrayList<Restaurent> getRestaurents() {
		return Admin.restaurents;
	}

	public void setRestaurents(ArrayList<Restaurent> restaurents) {
		Admin.restaurents = restaurents;
	}

	public ArrayList<Rider> getRiders() {
		return Admin.riders;
	}

	public void setRiders(ArrayList<Rider> riders) {
		Admin.riders = riders;
	}

}
