package DataHandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.StringTokenizer;


import models.*;

public class ReadnWriteRider implements ReadnWrite{

	@Override
	public void write() {
		try {
			FileWriter write = new FileWriter("Rider.txt");
			Formatter fmt = new Formatter(write);
			
			for(Rider rider : data.getRiders()) {
				fmt.format("%-25s%25s%35s%25s%25s%25s%25s%25s%25s%55s%20s%10s\n", rider.getUserName(),rider.getPassword(),rider.getEmail(),rider.getFirstName(),rider.getLastName(),rider.getPhoneNumber(),rider.getVehicle()[0],rider.getVehicle()[1],rider.getVehicle()[2],rider.getAdress(),rider.isBusy(),rider.getMoney());
			}
			
			fmt.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void read() {
		try {
			FileReader read = new FileReader("Rider.txt");
			BufferedReader reader = new BufferedReader(read);
			String line;
			
			
			while((line=reader.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line);
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

					data.getRiders().add(new Rider(name,pass,mail,first,last,phone,vehicle,adress,busy,money));
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
