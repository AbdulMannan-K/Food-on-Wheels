package UI;

import DataHandling.*;
import models.Customer;
import models.Restaurent;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RestaurentSignupWindow {

	private JFrame frame;
	
	DataHandle data = new DataHandle();
	StartingWindow win = new StartingWindow();
	
	private JTextField restaurentField;
	private JTextField mailField;
	private JTextField userField;
	private JTextField phoneField;
	private JTextField adressField;
	private JPasswordField passwordField;

	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public RestaurentSignupWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 791, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel Restaurent = new JLabel("Restaurent Name");
		Restaurent.setBounds(282, 87, 88, 25);
		frame.getContentPane().add(Restaurent);
		
		JLabel password = new JLabel("Password");
		password.setBounds(402, 148, 88, 25);
		frame.getContentPane().add(password);
		
		JLabel Email = new JLabel("Email");
		Email.setBounds(127, 148, 88, 25);
		frame.getContentPane().add(Email);
		
		JLabel userName = new JLabel("User Name");
		userName.setBounds(127, 210, 88, 25);
		frame.getContentPane().add(userName);
		
		JLabel phone = new JLabel("Phone Number");
		phone.setBounds(402, 210, 88, 25);
		frame.getContentPane().add(phone);
		
		JLabel adress = new JLabel("Adress");
		adress.setBounds(127, 262, 88, 25);
		frame.getContentPane().add(adress);
		
		restaurentField = new JTextField();
		restaurentField.setColumns(10);
		restaurentField.setBounds(371, 84, 146, 30);
		frame.getContentPane().add(restaurentField);
		
		mailField = new JTextField();
		mailField.setColumns(10);
		mailField.setBounds(199, 145, 146, 30);
		frame.getContentPane().add(mailField);
		
		userField = new JTextField();
		userField.setColumns(10);
		userField.setBounds(199, 207, 146, 30);
		frame.getContentPane().add(userField);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(491, 207, 146, 30);
		frame.getContentPane().add(phoneField);
		
		adressField = new JTextField();
		adressField.setColumns(10);
		adressField.setBounds(199, 264, 438, 61);
		frame.getContentPane().add(adressField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(491, 145, 146, 30);
		frame.getContentPane().add(passwordField);
		
		JButton btn = new JButton("Submit");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userField.getText().isEmpty()||adressField.getText().isEmpty()||mailField.getText().isEmpty()||passwordField.getText().isEmpty()||phone.getText().isEmpty()||restaurentField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "One or more fields are not filled!");
					return;
				}
				
				if(!checkRepeadity(userField.getText())) {
					JOptionPane.showMessageDialog(null, "UserName is already Registered!");
					return;
				}
				
				data.restaurentSignup(userField.getText(), adressField.getText() , mailField.getText(),passwordField.getText(),restaurentField.getText(),phoneField.getText());
				frame.dispose();
				
				JOptionPane.showMessageDialog(null, "Account Created Succesfully!");
				
				frame.dispose();
				win.getFrame().setVisible(true); // Starting window open
			}
		});
		btn.setBounds(339, 382, 89, 23);
		frame.getContentPane().add(btn);
		
		JLabel signin = new JLabel("Already have an account ..");
		signin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartingWindow win = new StartingWindow();
				win.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		signin.setForeground(Color.RED);
		signin.setBounds(329, 431, 188, 14);
		frame.getContentPane().add(signin);
	}
	
	private boolean checkRepeadity(String userName) {
		for(Restaurent restaurent:data.getRestaurents()) {
			if(userName.equals(restaurent.getUserName()))
				return false;
		}
		return true;
	}
}
