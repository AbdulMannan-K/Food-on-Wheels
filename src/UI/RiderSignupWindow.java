package UI;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DataHandling.DataHandle;
import models.Customer;
import models.Rider;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ComboBoxEditor;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RiderSignupWindow {

	private JFrame frame;
	
	
	DataHandle data = new DataHandle();
	StartingWindow win = new StartingWindow();
	
	private JTextField firstField;
	private JTextField lastField;
	private JTextField mailField;
	private JTextField userField;
	private JTextField phoneField;
	private JTextField DetailsField;
	private JPasswordField passwordField;
	private JTextField plateField;
	private JTextField adressField;
	private JTextField detailField;
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public RiderSignupWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 772, 628);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel first = new JLabel("First Name");
		first.setBounds(112, 91, 88, 25);
		frame.getContentPane().add(first);
		
		JLabel last = new JLabel("Last Name");
		last.setBounds(387, 91, 88, 25);
		frame.getContentPane().add(last);
		
		JLabel password = new JLabel("Password");
		password.setBounds(387, 151, 88, 25);
		frame.getContentPane().add(password);
		
		JLabel email = new JLabel("Email");
		email.setBounds(112, 151, 88, 25);
		frame.getContentPane().add(email);
		
		JLabel name = new JLabel("User Name");
		name.setBounds(112, 213, 88, 25);
		frame.getContentPane().add(name);
		
		JLabel phone = new JLabel("Phone Number");
		phone.setBounds(387, 213, 88, 25);
		frame.getContentPane().add(phone);
		
		JLabel vehicleDetails = new JLabel("Vehicle Details");
		vehicleDetails.setBounds(112, 331, 88, 25);
		frame.getContentPane().add(vehicleDetails);
		
		firstField = new JTextField();
		firstField.setColumns(10);
		firstField.setBounds(184, 88, 146, 30);
		frame.getContentPane().add(firstField);
		
		lastField = new JTextField();
		lastField.setColumns(10);
		lastField.setBounds(476, 88, 146, 30);
		frame.getContentPane().add(lastField);
		
		mailField = new JTextField();
		mailField.setColumns(10);
		mailField.setBounds(184, 148, 146, 30);
		frame.getContentPane().add(mailField);
		
		userField = new JTextField();
		userField.setColumns(10);
		userField.setBounds(184, 210, 146, 30);
		frame.getContentPane().add(userField);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(476, 210, 146, 30);
		frame.getContentPane().add(phoneField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(476, 148, 146, 30);
		frame.getContentPane().add(passwordField);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("");
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Car", "MotorCycle", "Other"}));
		comboBox.setBounds(184, 274, 146, 30);
		frame.getContentPane().add(comboBox);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(userField.getText().isEmpty()||adressField.getText().isEmpty()||mailField.getText().isEmpty()||passwordField.getText().isEmpty()||lastField.getText().isEmpty()||phoneField.getText().isEmpty()||plateField.getText().isEmpty()||detailField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "One or more fields are not filled!");
					return;
				}
				
				if(!checkRepeadity(userField.getText())) {
					JOptionPane.showMessageDialog(null, "UserName is already Registered!");
					return;
				}
				
				String[] vehicle = {comboBox.getSelectedItem().toString() , plateField.getText() , detailField.getText()};
				
				data.riderSignup(userField.getText(), adressField.getText() , mailField.getText(),passwordField.getText(),firstField.getText(),lastField.getText(),phoneField.getText(),vehicle);
				frame.dispose();
				
				JOptionPane.showMessageDialog(null, "Account Created Succesfully!");
				
				
				win.getFrame().setVisible(true); // Starting window open
			}
		});
		submit.setBounds(323, 488, 89, 23);
		frame.getContentPane().add(submit);
		
		JLabel signin = new JLabel("Already have an account ..");
		signin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				StartingWindow win = new StartingWindow();
				frame.dispose();
				win.getFrame().setVisible(true);
			}
		});
		signin.setForeground(Color.RED);
		signin.setBounds(314, 536, 188, 14);
		frame.getContentPane().add(signin);
		
		JLabel vehicle = new JLabel("Vehicle");
		vehicle.setBounds(112, 277, 88, 25);
		frame.getContentPane().add(vehicle);
		
		JLabel plate = new JLabel("Number Plate");
		plate.setBounds(387, 277, 88, 25);
		frame.getContentPane().add(plate);
		
		plateField = new JTextField();
		plateField.setColumns(10);
		plateField.setBounds(476, 274, 146, 30);
		frame.getContentPane().add(plateField);
		
		
		
		adressField = new JTextField();
		adressField.setColumns(10);
		adressField.setBounds(184, 416, 438, 61);
		frame.getContentPane().add(adressField);
		
		JLabel adress = new JLabel("Adress");
		adress.setBounds(112, 414, 88, 25);
		frame.getContentPane().add(adress);
		
		detailField = new JTextField();
		detailField.setColumns(10);
		detailField.setBounds(184, 333, 438, 61);
		frame.getContentPane().add(detailField);
	}
	
	private boolean checkRepeadity(String userName) {
		for(Rider rider:data.getRiders()) {
			if(userName.equals(rider.getUserName()))
				return false;
		}
		return true;
	}
}
