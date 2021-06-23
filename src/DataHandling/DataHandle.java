package DataHandling;
import java.util.*;
import models.*;
import UI.*;

public class DataHandle {

	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private static ArrayList<Rider> riders = new ArrayList<Rider>();
	private static ArrayList<Restaurent> restaurents = new ArrayList<Restaurent>(); 
	private static ArrayList<Admin> admins = new ArrayList<Admin>();
	
	ReadnWrite rnw ;
	
	public void setRestaurents(ArrayList<Restaurent> restaurents) {
		DataHandle.restaurents=restaurents;
	}
	
	public ArrayList<Restaurent> getRestaurents(){
		return DataHandle.restaurents;
	}
	
	public void setRiders(ArrayList<Rider> riders) {
		DataHandle.riders = riders;
	}
	
	public ArrayList<Rider> getRiders(){
		return DataHandle.riders;
	}
	
	public void setCustomers(ArrayList<Customer> customers) {
		DataHandle.customers=customers;
	}
	
	public ArrayList<Customer> getCustomers(){
		return DataHandle.customers;
	}
	
	public void setAdmins(ArrayList<Admin> admins) {
		DataHandle.admins = admins;
	}
	
	public ArrayList<Admin> getAdmins(){
		return DataHandle.admins;
	}
	
	public void customerSignup(String Name, String adress, String mail , String pass,String first,String last, String phone) {
		for(Admin admin : admins) {
			admin.getCustomers().add(new Customer(Name,pass,mail,first,last,phone,adress,(long) 0));
		}
//		customers.add(new Customer(Name,pass,mail,first,last,phone,adress));
		rnw = new ReadnWriteCustomer();
		rnw.write();

		ReadAdmin ra = new ReadAdmin();
		ra.writeData();
	}
	
	public void riderSignup(String Name, String adress, String mail , String pass,String first,String last, String phone, String[] vehicle) {
		for(Admin admin : admins) {
			admin.getRiders().add(new Rider(Name,pass,mail,first,last,phone,vehicle,adress,(long) 0));
		}
//		riders.add(new Rider(Name,pass,mail,first,last,phone,vehicle,adress));
		rnw = new ReadnWriteRider();
		rnw.write();

		ReadAdmin ra = new ReadAdmin();
		ra.writeData();
	}
	
	public void restaurentSignup(String Name, String adress, String mail ,String pass, String restaurentName, String phone) {
		for(Admin admin : admins) {
			admin.getRestaurents().add(new Restaurent(Name,pass,mail,restaurentName,phone,adress,0,0,0));
		}
//		restaurents.add(new Restaurent(Name,pass,mail,restaurentName,phone,adress,0,0));
		rnw = new ReadnWriteRestaurent();
		rnw.write(); 

		ReadAdmin ra = new ReadAdmin();
		ra.writeData();
	}
	
	public void adminSignup(String Name, String adress, String mail ,String pass, String phone) {
		admins.add(new Admin(Name,pass,mail,phone,adress));
	}
	
}
