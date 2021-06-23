package DataHandling;
import UI.*;
import models.Customer;
import models.FoodItems;
import models.Restaurent;
import models.Rider;

public class Main {
	public static void main(String[] args) {
		
		ReadnWrite read;
		ReadAdmin adm = new ReadAdmin();
		DataHandle data = new DataHandle();

		
		read = new ReadnWriteCustomer();
		read.read();
		
		read = new ReadnWriteRider();
		read.read();
		
		read = new ReadnWriteRestaurent();
		read.read();
		
		adm.read();
		adm.readData();
		
		for(Restaurent restaurent :  data.getRestaurents()) {
			String name = (restaurent.getUserName()+".txt").toString();
			ReadnWriteCart cart = new ReadnWriteCart();
			cart.read(name, restaurent);
			name = (restaurent.getUserName()+"menu"+".txt").toString();
			cart.readMenu(name, restaurent);
		}
		
		for(Customer customer : data.getCustomers()) {
			String name = (customer.getUserName()+".txt").toString();
			ReadnWriteCart cart = new ReadnWriteCart();
			cart.read(name, customer);
		}
		
		for(Rider rider : data.getRiders()) {
			String name = (rider.getUserName()+"R.txt").toString();
			ReadnWriteCart cart = new ReadnWriteCart();
			cart.read(name, rider);
		}
		
		
		
		StartingWindow start = new StartingWindow();
		start.getFrame().setVisible(true);
		
	}
	
}
