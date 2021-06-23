package DataHandling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.StringTokenizer;

import models.Customer;
import models.FoodItems;
import models.Order;
import models.Restaurent;
import models.Rider;

public class ReadnWriteCart {
	
	DataHandle data = new DataHandle();
	
	public void write(String filename, Customer customer) {
		try {
			FileWriter write = new FileWriter(filename);
			
			for(int i=0 ; i< customer.getCart().getOrders().size() ; i++){
				for(int j=0 ; j < customer.getCart().getOrders().get(i).getOrderList().size(); j++) {
					write.write(customer.getCart().getOrders().get(i).getOrderList().get(j).getName()+","+customer.getCart().getOrders().get(i).getOrderList().get(j).getPrice()+",");
				}
				write.write(customer.getCart().getOrders().get(i).getid()+",");
				write.write(customer.getCart().getOrders().get(i).getRestaurent().getRestaurentName()+",");
				write.write(customer.getCart().getOrders().get(i).getOrderTime()+",");
				write.write(Boolean.toString(customer.getCart().getOrders().get(i).isRiderStatus())+",");
				write.write(Boolean.toString(customer.getCart().getOrders().get(i).isStatus())+"\n");
			}
			
			write.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void write(String filename, Restaurent restaurent) {
		try {
			FileWriter write = new FileWriter(filename);
			
			for(int i=0 ; i< restaurent.getOrdersList().getOrders().size() ; i++){
				for(int j=0 ; j < restaurent.getOrdersList().getOrders().get(i).getOrderList().size(); j++) {
					write.write(restaurent.getOrdersList().getOrders().get(i).getOrderList().get(j).getName()+","+restaurent.getOrdersList().getOrders().get(i).getOrderList().get(j).getPrice()+",");
				}
				write.write(restaurent.getOrdersList().getOrders().get(i).getid()+",");
				write.write(restaurent.getOrdersList().getOrders().get(i).getCustomer().getUserName()+",");
				write.write(restaurent.getOrdersList().getOrders().get(i).getOrderTime()+",");
				write.write(Boolean.toString(restaurent.getOrdersList().getOrders().get(i).isRiderStatus())+",");
				write.write(Boolean.toString(restaurent.getOrdersList().getOrders().get(i).isStatus())+"\n");
			}
			
			write.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void write(String filename, Rider rider) {
		try {
			FileWriter write = new FileWriter(filename);
			
			for(int i=0 ; i< rider.getOrdersList().getOrders().size() ; i++){
				for(int j=0 ; j < rider.getOrdersList().getOrders().get(i).getOrderList().size(); j++) {
					write.write(rider.getOrdersList().getOrders().get(i).getOrderList().get(j).getName()+","+rider.getOrdersList().getOrders().get(i).getOrderList().get(j).getPrice()+",");
				}
				write.write(rider.getOrdersList().getOrders().get(i).getid()+",");
				write.write(rider.getOrdersList().getOrders().get(i).getCustomer().getUserName()+",");
				write.write(rider.getOrdersList().getOrders().get(i).getRestaurent().getRestaurentName()+",");
				write.write(Boolean.toString(rider.getOrdersList().getOrders().get(i).isRiderStatus())+",");
				write.write(rider.getOrdersList().getOrders().get(i).getOrderTime()+ ",");
				write.write(Boolean.toString(rider.getOrdersList().getOrders().get(i).isStatus())+"\n");
			}
			
			write.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void read(String fileName, Customer customer) {
		try {
			File f = new File(fileName);
			
			if(f.exists()) {
				FileReader file = new FileReader(fileName);
				BufferedReader reader = new BufferedReader(file);
				String line;
				
				ArrayList<String> foodName = new ArrayList<String>();
				ArrayList<Integer> price = new ArrayList<Integer>();
			
			
				while((line=reader.readLine())!=null) {
					foodName = new ArrayList<String>();
					price = new ArrayList<Integer>();
					String[] parts = line.split(",");
					for(int i=0 ; i < parts.length-5 ;) {
						foodName.add(parts[i]);
						i++;
						price.add(Integer.parseInt(parts[i]));
						i++;
					}
					String date = parts[parts.length-3];
					int id = Integer.parseInt(parts[parts.length-5]);
					String rest = parts[parts.length-4];
					boolean rider = Boolean.parseBoolean(parts[parts.length-2]);
					boolean status = Boolean.parseBoolean(parts[parts.length-1]);
					
					boolean notfound = true ;
					Restaurent restaurent=null;
					for(int i=0 ; i < data.getRestaurents().size() ; i++) {
						notfound=false;
						if(data.getRestaurents().get(i).getRestaurentName().equals(rest)) {
							restaurent = data.getRestaurents().get(i);
							break;
						}else {
							notfound=true;
						}
					}
					if(!notfound) {
							customer.getCart().getOrders().add(new Order(customer,restaurent,status,id,date,rider));
							for(int j=0 ; j < customer.getCart().getOrders().size() ; j++) {
								if(id==customer.getCart().getOrders().get(j).getid()) {
									for(int i=0 ; i < foodName.size();i++) {
										customer.getCart().getOrders().get(j).getOrderList().add(new FoodItems(foodName.get(i),price.get(i)));
									}
								}
							}
						
					}
					else {
						System.out.println("wrong Entries");
					}
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

public void read(String fileName, Restaurent restaurent) {
	try {
		File f = new File(fileName);
		
		if(f.exists()) {
			FileReader file = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(file);
			String line;
			
			ArrayList<String> foodName = new ArrayList<String>();
			ArrayList<Integer> price = new ArrayList<Integer>();
		
		
			while((line=reader.readLine())!=null) {
				foodName = new ArrayList<String>();
				price = new ArrayList<Integer>();
				String[] parts = line.split(",");
				for(int i=0 ; i < parts.length-5 ;) {
					foodName.add(parts[i]);
					i++;
					price.add(Integer.parseInt(parts[i]));
					i++;
				}
				String date = parts[parts.length-3];
				int id = Integer.parseInt(parts[parts.length-5]);
				String cust = parts[parts.length-4];
				boolean rider = Boolean.parseBoolean(parts[parts.length-2]);
				boolean status = Boolean.parseBoolean(parts[parts.length-1]);
				
				boolean notfound = true ;
				Customer customer=null;
				for(int i=0 ; i < data.getCustomers().size() ; i++) {
					notfound=false;
					if(data.getCustomers().get(i).getUserName().equals(cust)) {
						customer = data.getCustomers().get(i);
						break;
					}else {
						notfound=true;
					}
				}
				if(!notfound) {
						restaurent.getOrdersList().getOrders().add(new Order(customer,restaurent,status,id,date,rider));
						for(int j=0 ; j < restaurent.getOrdersList().getOrders().size() ; j++) {
							if(id==restaurent.getOrdersList().getOrders().get(j).getid()) {
								for(int i=0 ; i < foodName.size();i++) {
									restaurent.getOrdersList().getOrders().get(j).getOrderList().add(new FoodItems(foodName.get(i),price.get(i)));
								}
							}
						}
					
				}
				else {
					System.out.println("wrong Entries");
				}
			}
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void read(String fileName, Rider rider) {
	try {
		File f = new File(fileName);
		
		if(f.exists()) {
			FileReader file = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(file);
			String line;
			
			ArrayList<String> foodName = new ArrayList<String>();
			ArrayList<Integer> price = new ArrayList<Integer>();
		
		
			while((line=reader.readLine())!=null) {
				foodName = new ArrayList<String>();
				price = new ArrayList<Integer>();
				String[] parts = line.split(",");
				for(int i=0 ; i < parts.length-6 ;) {
					foodName.add(parts[i]);
					i++;
					price.add(Integer.parseInt(parts[i]));
					i++;
				}
				String rest = parts[parts.length-4];
				int id = Integer.parseInt(parts[parts.length-6]);
				String cust = parts[parts.length-5];
				boolean riderS = Boolean.parseBoolean(parts[parts.length-3]);
				boolean status = Boolean.parseBoolean(parts[parts.length-1]);
				String date = parts[parts.length-2];
				
				boolean notfound = true ;
				Restaurent restaurent=null;
				Customer customer= null;
				for(int i=0 ; i < data.getRestaurents().size() ; i++) {
					notfound=false;
					if(data.getRestaurents().get(i).getRestaurentName().equals(rest)) {
						restaurent = data.getRestaurents().get(i);
						break;
					}else {
						notfound=true;
					}
				}
				for(int i=0 ; i < data.getCustomers().size() ; i++) {
					notfound=false;
					if(data.getCustomers().get(i).getUserName().equals(cust)) {
						customer = data.getCustomers().get(i);
						break;
					}else {
						notfound=true;
					}
				}
				if(!notfound) {
						rider.getOrdersList().getOrders().add(new Order(customer,restaurent,status,id,date,riderS));
						for(int j=0 ; j < rider.getOrdersList().getOrders().size() ; j++) {
							if(id==rider.getOrdersList().getOrders().get(j).getid()) {
								for(int i=0 ; i < foodName.size();i++) {
									rider.getOrdersList().getOrders().get(j).getOrderList().add(new FoodItems(foodName.get(i),price.get(i)));
								}
							}
						}
					
				}
				else {
					System.out.println("wrong Entries");
				}
			}
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
} 



public void writeMenu(String fileName, Restaurent restaurent) {
	try {
		FileWriter write = new FileWriter(fileName);
		
		for(int i=0 ; i< restaurent.getFoodList().size() ; i++){
			write.write(restaurent.getFoodList().get(i).getName()+","+restaurent.getFoodList().get(i).getPrice()+"\n");
		}
		
		write.close();
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void readMenu(String fileName, Restaurent restaurent) {
	try {
		File f = new File(fileName);
		
		if(f.exists()) {
			FileReader file = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(file);
			String line;
		
			while((line=reader.readLine())!=null) {
				String parts[] = line.split(",");
				restaurent.getFoodList().add(new FoodItems(parts[0],Integer.parseInt(parts[1])));
			}
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}


