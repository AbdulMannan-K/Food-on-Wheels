package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DataHandling.ReadnWriteCart;
import models.FoodItems;
import models.Restaurent;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class addFoodinMenu {

	private JFrame frame;
	private JTextField nameField;
	private JTextField priceFIeld;
	Restaurent restaurent;
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public addFoodinMenu(Restaurent restaurent) {
		this.restaurent=restaurent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 512);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 23, 287, 179);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name : ");
		nameLabel.setBounds(10, 39, 75, 26);
		panel.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(98, 39, 155, 26);
		panel.add(nameField);
		nameField.setColumns(10);
		
		priceFIeld = new JTextField();
		priceFIeld.setColumns(10);
		priceFIeld.setBounds(98, 87, 155, 26);
		panel.add(priceFIeld);
		
		JLabel priceLabel = new JLabel("Price : ");
		priceLabel.setBounds(10, 87, 75, 26);
		panel.add(priceLabel);
		
		JButton addFood = new JButton("Add");
		addFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restaurent.getFoodList().add(new FoodItems(nameField.getText(),Integer.parseInt(priceFIeld.getText())));
			}
		});
		addFood.setBounds(98, 134, 89, 23);
		panel.add(addFood);
	}
}

 class updateFood {

	private JFrame frame;
	private JTextField nameField;
	private JTextField priceFIeld;
	Restaurent restaurent;
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public updateFood(Restaurent restaurent) {
		this.restaurent=restaurent;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 710, 512);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 23, 287, 179);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Name : ");
		nameLabel.setBounds(10, 39, 75, 26);
		panel.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setBounds(98, 39, 155, 26);
		panel.add(nameField);
		nameField.setColumns(10);
		
		priceFIeld = new JTextField();
		priceFIeld.setColumns(10);
		panel.add(priceFIeld);
		
		JLabel priceLabel = new JLabel("Price : ");
		priceLabel.setBounds(10, 87, 75, 26);
		panel.add(priceLabel);
		
		JButton addFood = new JButton("Add");
		addFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restaurent.getFoodList().add(new FoodItems(nameField.getText(),Integer.parseInt(priceFIeld.getText())));
				ReadnWriteCart write = new ReadnWriteCart();
				String name = (restaurent.getUserName()+"menu"+".txt").toString();
				write.writeMenu(name, restaurent);
				frame.dispose();
			}
		});
		addFood.setBounds(98, 134, 89, 23);
		panel.add(addFood);
	}
}

