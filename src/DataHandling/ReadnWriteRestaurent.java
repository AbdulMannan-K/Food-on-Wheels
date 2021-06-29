package DataHandling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.StringTokenizer;

import models.Restaurent;

public class ReadnWriteRestaurent implements ReadnWrite{
	
	@Override
	public void write() {
		try {
			FileWriter write = new FileWriter("Restaurent.txt");
			Formatter fmt = new Formatter(write);
			
			for(Restaurent restaurent : data.getRestaurents()) {
				fmt.format("%-25s%25s%35s%25s%25s%55s%10s%10s%10s\n", restaurent.getUserName(),restaurent.getPassword(),restaurent.getEmail(),restaurent.getRestaurentName(),restaurent.getPhoneNumber(),restaurent.getAdress(),restaurent.getRating(),restaurent.getOrderNo(),restaurent.getMoney());
			}
			
			fmt.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	@Override
	public void read() {
		
		try {
			FileReader read = new FileReader("Restaurent.txt");
			BufferedReader reader = new BufferedReader(read);
			String line;
			
			
			while((line=reader.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line);
				if(st.hasMoreTokens())
					data.getRestaurents().add(new Restaurent(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken(),Float.parseFloat(st.nextToken()), Integer.parseInt(st.nextToken()) , Long.parseLong(st.nextToken())));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
