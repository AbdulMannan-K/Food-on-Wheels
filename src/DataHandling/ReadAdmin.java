package DataHandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.StringTokenizer;

import models.Admin;
import models.Customer;
import models.Restaurent;
import models.Rider;

public class ReadAdmin {
		
	DataHandle data = new DataHandle();
	
	public void read() {
		
		try {
			FileReader read = new FileReader("Admin.txt");
			BufferedReader reader = new BufferedReader(read);
			String line;
			
			
			while((line=reader.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line);
				if(st.hasMoreTokens())
					data.getAdmins().add(new Admin(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	public void writeData() {
		try {
			FileWriter write = new FileWriter("AdminCData.txt");
			
			for(Customer customer : data.getAdmins().get(0).getCustomers()) {
				write.write(customer.getUserName()+","+customer.getPassword()+","+customer.getEmail()+","+customer.getFirstName()+","+customer.getLastName()+","+customer.getPhoneNumber()+","+customer.getAdress()+"," + customer.getMoney() +"\n");
			}
			write.close();
			
			write = new FileWriter("AdminRData.txt");
			Formatter fmt = new Formatter(write);
			
			for(Restaurent restaurent : data.getAdmins().get(0).getRestaurents()) {
				fmt.format("%-25s%25s%35s%25s%25s%55s%10s%10s%10s\n", restaurent.getUserName(),restaurent.getPassword(),restaurent.getEmail(),restaurent.getRestaurentName(),restaurent.getPhoneNumber(),restaurent.getAdress(),restaurent.getRating(),restaurent.getOrderNo(),restaurent.getMoney());
			}
			
			fmt.close();
			write.close();
			
			 write = new FileWriter("AdminRiderData.txt");
			 fmt = new Formatter(write);
			
			for(Rider rider : data.getAdmins().get(0).getRiders()) {
				fmt.format("%-25s%25s%35s%25s%25s%25s%25s%25s%25s%55s%20s%10s\n", rider.getUserName(),rider.getPassword(),rider.getEmail(),rider.getFirstName(),rider.getLastName(),rider.getPhoneNumber(),rider.getVehicle()[0],rider.getVehicle()[1],rider.getVehicle()[2],rider.getAdress(),rider.isBusy(),rider.getMoney());
			}
			
			fmt.close();
			
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void readData() {
		try {
			FileReader file = new FileReader("AdminCData.txt");
			BufferedReader read = new BufferedReader(file);
			String line;
			
			while((line=read.readLine())!=null) {
				String parts[] = line.split(",");
				
				for(Admin admin : data.getAdmins()) {
					admin.getCustomers().add(new Customer(parts[0],parts[1],parts[2],parts[3],parts[4],parts[5],parts[6],Long.parseLong(parts[7])));
				}
			}
			
			FileReader file1 = new FileReader("AdminRData.txt");
			BufferedReader read1 = new BufferedReader(file1);
			String line1;
			
			while((line1=read1.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line1);
				if(st.hasMoreTokens())
					for(Admin admin : data.getAdmins()) {
						admin.getRestaurents().add(new Restaurent(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),Float.parseFloat(st.nextToken()), Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken())));
					}
			}
			
			
			 FileReader file2 = new FileReader("AdminRiderData.txt");
			 BufferedReader read2 = new BufferedReader(file2);
			String line2;
			
			while((line2=read2.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line2);
				if(st.hasMoreTokens()){
					String name = st.nextToken();
					String pass = st.nextToken();
					String mail = st.nextToken();
					String first = st.nextToken();
					String last = st.nextToken();
					String phone = st.nextToken();
					String[] vehicle = {st.nextToken(),st.nextToken(),st.nextToken()};
					String adress = st.nextToken();
					Boolean busy = Boolean.parseBoolean(st.nextToken());
					Long money = Long.parseLong(st.nextToken());
					for(Admin admin : data.getAdmins()) {
						admin.getRiders().add(new Rider(name,pass,mail,first,last,phone,vehicle,adress,busy,money));
					}
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}