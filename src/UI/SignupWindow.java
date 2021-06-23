package UI;
import java.awt.EventQueue;
import models.*;
import DataHandling.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignupWindow {

	private JFrame frame;
	DataHandle data = new DataHandle();
	StartingWindow win = new StartingWindow();
	
	public JFrame getframe() {
		return frame;
	}
	
	
	/**
	 * Create the application.
	 */
	public SignupWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(134, 151, 88, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSecondName = new JLabel("Last Name");
		lblSecondName.setBounds(409, 151, 88, 25);
		frame.getContentPane().add(lblSecondName);
		
		JLabel lblUserName = new JLabel("Password");
		lblUserName.setBounds(409, 211, 88, 25);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(134, 211, 88, 25);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPhoneNumber = new JLabel("User Name");
		lblPhoneNumber.setBounds(134, 273, 88, 25);
		frame.getContentPane().add(lblPhoneNumber);
		
		JLabel lblUserName_2 = new JLabel("Phone Number");
		lblUserName_2.setBounds(409, 273, 88, 25);
		frame.getContentPane().add(lblUserName_2);
		
		JLabel lblAdress = new JLabel("Adress");
		lblAdress.setBounds(134, 325, 88, 25);
		frame.getContentPane().add(lblAdress);
		
		JTextField First = new JTextField();
		First.setBounds(206, 148, 146, 30);
		frame.getContentPane().add(First);
		First.setColumns(10);
		
		JTextField last = new JTextField();
		last.setColumns(10);
		last.setBounds(498, 148, 146, 30);
		frame.getContentPane().add(last);
		
		JTextField email = new JTextField();
		email.setColumns(10);
		email.setBounds(206, 208, 146, 30);
		frame.getContentPane().add(email);
		
		JTextField user = new JTextField();
		user.setColumns(10);
		user.setBounds(206, 270, 146, 30);
		frame.getContentPane().add(user);
		
		JTextField phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(498, 270, 146, 30);
		frame.getContentPane().add(phone);
		
		JTextField adress = new JTextField();
		adress.setColumns(10);
		adress.setBounds(206, 327, 438, 61);
		frame.getContentPane().add(adress);
		
		JPasswordField pass = new JPasswordField();
		pass.setBounds(498, 208, 146, 30);
		frame.getContentPane().add(pass);
		
		JButton btn = new JButton("Submit");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(user.getText().isEmpty()||adress.getText().isEmpty()||email.getText().isEmpty()||pass.getText().isEmpty()||last.getText().isEmpty()||phone.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "One or more fields are not filled!");
					return;
				}
				
				if(!checkRepeadity(user.getText())) {
					JOptionPane.showMessageDialog(null, "UserName is already Registered!");
					return;
				}
				
				data.customerSignup(user.getText(), adress.getText() , email.getText(),pass.getText(),First.getText(),last.getText(),phone.getText());
				frame.dispose();
				
				JOptionPane.showMessageDialog(null, "Account Created Succesfully!");
				
				
				win.getFrame().setVisible(true); // Starting window open
			}
		});
		btn.setBounds(346, 445, 89, 23);
		frame.getContentPane().add(btn);
		
		JLabel signin = new JLabel("Already have an account ..");
		signin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				win.getFrame().setVisible(true);
			}
		});
		signin.setForeground(Color.RED);
		signin.setBounds(336, 494, 188, 14);
		frame.getContentPane().add(signin);
	}
	
	private boolean checkRepeadity(String userName) {
		for(Customer customer:data.getCustomers()) {
			if(userName.equals(customer.getUserName()))
				return false;
		}
		return true;
	}
	
}
