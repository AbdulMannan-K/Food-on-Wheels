package DataHandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.StringTokenizer;

import models.*;

public class ReadnWriteCustomer implements ReadnWrite{

	@Override
	public void write() {
		try {
			FileWriter write = new FileWriter("Customer.txt");
			Formatter fmt = new Formatter(write);
			
			for(Customer customer : data.getCustomers()) {
				fmt.format("%-25s%25s%35s%25s%25s%25s%55s%10s\n", customer.getUserName(),customer.getPassword(),customer.getEmail(),customer.getFirstName(),customer.getLastName(),customer.getPhoneNumber(),customer.getAdress(),customer.getMoney());
			}
			
			fmt.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void read() {
		
		try {
			FileReader read = new FileReader("Customer.txt");
			BufferedReader reader = new BufferedReader(read);
			String line;
			
			
			while((line=reader.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line);
				if(st.hasMoreTokens())
					data.getCustomers().add(new Customer(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),Long.parseLong(st.nextToken())));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
