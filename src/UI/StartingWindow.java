package UI;
import java.awt.EventQueue;
import DataHandling.*;
import models.*;


import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class StartingWindow {

	private JFrame frame;
	private int loginIndex;
	
	DataHandle data = new DataHandle();

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public StartingWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setFrame(new JFrame());
		getFrame().getContentPane().setBackground(Color.WHITE);
		getFrame().setBounds(100, 100, 811, 525);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(84, 44, 279, 174);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel userPanel = new JPanel();
		userPanel.setBounds(84, 44, 279, 174);
		frame.getContentPane().add(userPanel);
		userPanel.setLayout(null);
		userPanel.setVisible(false);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.setBounds(84, 44, 279, 174);
		frame.getContentPane().add(passwordPanel);
		passwordPanel.setLayout(null);
		passwordPanel.setVisible(false);
		
		JPanel RestaurentPanel = new JPanel();
		RestaurentPanel.setBounds(84, 44, 279, 174);
		frame.getContentPane().add(RestaurentPanel);
		RestaurentPanel.setLayout(null);
		RestaurentPanel.setVisible(false);
		
		JPanel R_passwordPanel = new JPanel();
		R_passwordPanel.setBounds(84, 44, 279, 174);
		frame.getContentPane().add(R_passwordPanel);
		R_passwordPanel.setLayout(null);
		R_passwordPanel.setVisible(false);
		
		JPanel RiderPanel = new JPanel();
		RiderPanel.setBounds(84, 44, 279, 174);
		frame.getContentPane().add(RiderPanel);
		RiderPanel.setLayout(null);
		RiderPanel.setVisible(false);
		
		JPanel Rider_passwordPanel = new JPanel();
		Rider_passwordPanel.setBounds(84, 44, 279, 174);
		frame.getContentPane().add(Rider_passwordPanel);
		Rider_passwordPanel.setLayout(null);
		Rider_passwordPanel.setVisible(false);
		
		JPanel AdminPanel = new JPanel();
		AdminPanel.setBounds(84, 44, 279, 174);
		frame.getContentPane().add(AdminPanel);
		AdminPanel.setLayout(null);
		AdminPanel.setVisible(false);
		
		JPanel Admin_passwordPanel = new JPanel();
		Admin_passwordPanel.setBounds(84, 44, 279, 174);
		frame.getContentPane().add(Admin_passwordPanel);
		Admin_passwordPanel.setLayout(null);
		Admin_passwordPanel.setVisible(false);

		JTextField userfield = new JTextField();
		userfield.setBounds(40, 62, 188, 31);
		userPanel.add(userfield);
		userfield.setColumns(10);
		
		JTextField restaurentfield = new JTextField();
		restaurentfield.setColumns(10);
		restaurentfield.setBounds(40, 62, 188, 31);
		RestaurentPanel.add(restaurentfield);
		
		JTextField riderFeild = new JTextField();
		riderFeild.setColumns(10);
		riderFeild.setBounds(40, 62, 188, 31);
		RiderPanel.add(riderFeild);
		
		JTextField adminField = new JTextField();
		adminField.setColumns(10);
		adminField.setBounds(36, 62, 188, 31);
		AdminPanel.add(adminField);
		
		
		JPasswordField passwordField_R = new JPasswordField();
		passwordField_R.setBounds(40, 62, 188, 31);
		R_passwordPanel.add(passwordField_R);

		JPasswordField passwordField = new JPasswordField();
		passwordField.setBounds(40, 62, 188, 31);
		passwordPanel.add(passwordField);
		
		JPasswordField passwordField_Rider = new JPasswordField();
		passwordField_Rider.setBounds(40, 62, 188, 31);
		Rider_passwordPanel.add(passwordField_Rider);
		
		JPasswordField passwordField_Admin = new JPasswordField();
		passwordField_Admin.setBounds(42, 61, 188, 31);
		Admin_passwordPanel.add(passwordField_Admin);
		
		
		JLabel user = new JLabel("UserName :");
		user.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		user.setBounds(40, 26, 98, 25);
		userPanel.add(user);
		
		JLabel signup = new JLabel("Don't have an account");
		signup.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		signup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignupWindow win = new SignupWindow();
				frame.dispose();
				win.getframe().setVisible(true);
			}
		});
		signup.setForeground(Color.RED);
		signup.setBounds(40, 138, 188, 25);
		userPanel.add(signup);
		
		JLabel lblNewLabel = new JLabel("Password : ");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel.setBounds(40, 26, 98, 25);
		passwordPanel.add(lblNewLabel);
		
		JLabel signupRestaurent = new JLabel("Don't have an account");
		signupRestaurent.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				RestaurentSignupWindow win = new RestaurentSignupWindow();
				frame.dispose();
				win.getFrame().setVisible(true);
				
			}
		});
		signupRestaurent.setForeground(Color.RED);
		signupRestaurent.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		signupRestaurent.setBounds(48, 138, 188, 25);
		RestaurentPanel.add(signupRestaurent);
		
		JLabel ID = new JLabel("Restaurent ID :");
		ID.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		ID.setBounds(40, 26, 98, 25);
		RestaurentPanel.add(ID);
		
		JLabel passwordlabel_R = new JLabel("Password : ");
		passwordlabel_R.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		passwordlabel_R.setBounds(40, 26, 98, 25);
		R_passwordPanel.add(passwordlabel_R);
		
		JLabel signupRider = new JLabel("Don't have an account");
		signupRider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RiderSignupWindow win = new RiderSignupWindow();
				frame.dispose();
				win.getFrame().setVisible(true);
			}
		});
		signupRider.setForeground(Color.RED);
		signupRider.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		signupRider.setBounds(40, 138, 203, 25);
		RiderPanel.add(signupRider);
		
		JLabel username_Rider = new JLabel("UserName :");
		username_Rider.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		username_Rider.setBounds(40, 26, 98, 25);
		RiderPanel.add(username_Rider);
		
		JLabel passwordlabel_Rider = new JLabel("Password : ");
		passwordlabel_Rider.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		passwordlabel_Rider.setBounds(40, 26, 98, 25);
		Rider_passwordPanel.add(passwordlabel_Rider);
		
		JLabel admin = new JLabel("UserName :");
		admin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		admin.setBounds(36, 26, 98, 25);
		AdminPanel.add(admin);
		
		JLabel passwordlabel_Admin = new JLabel("Password : ");
		passwordlabel_Admin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		passwordlabel_Admin.setBounds(42, 25, 98, 25);
		Admin_passwordPanel.add(passwordlabel_Admin);
		
		
		JButton cancel = new JButton("cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userPanel.setVisible(false);
				panel.setVisible(true);
			}
		});
		cancel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cancel.setBounds(40, 104, 89, 23);
		userPanel.add(cancel);
		
		JButton next = new JButton("Next");
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean notfound=true;
				
				for(Customer customer : data.getCustomers()) {
					notfound=false;
					if(userfield.getText().equals(customer.getUserName())) 
						break;
					else
						notfound=true;
				}
				
				if(notfound) {
					JOptionPane.showMessageDialog(null, "Wrong UserName");
				}else {
					userPanel.setVisible(false);
					passwordPanel.setVisible(true);
				}
			}
		});
		next.setFont(new Font("Times New Roman", Font.BOLD, 15));
		next.setBounds(139, 104, 89, 23);
		userPanel.add(next);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				passwordPanel.setVisible(false);
				userPanel.setVisible(true);
			}
		});
		back.setFont(new Font("Times New Roman", Font.BOLD, 16));
		back.setBounds(40, 104, 89, 23);
		passwordPanel.add(back);
		
		JButton CustomerBtn = new JButton("Customer");
		CustomerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				userPanel.setVisible(true);
			}
		});
		CustomerBtn.setBounds(10, 60, 117, 23);
		panel.add(CustomerBtn);
		
		JButton RestaurentBtn = new JButton("Restaurent");
		RestaurentBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				RestaurentPanel.setVisible(true);
			}
		});
		RestaurentBtn.setBounds(152, 60, 117, 23);
		panel.add(RestaurentBtn);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean notfound=false;
				Customer customer1=null;
				for(Customer customer : data.getCustomers()) {
					notfound=false;
					if(userfield.getText().equals(customer.getUserName())&&passwordField.getText().equals(customer.getPassword())) {
						customer1 = customer;
						break;
					}
					else
						notfound=true;
				}
				
				if(notfound) {
					JOptionPane.showMessageDialog(null, "Wrong Password!");
				}else {
					frame.dispose();
					CustomerWindow win = new CustomerWindow(customer1);
					win.getFrame().setVisible(true);
					
				}
			}
		});
		login.setFont(new Font("Times New Roman", Font.BOLD, 15));
		login.setBounds(139, 104, 89, 23);
		passwordPanel.add(login);
		
		JButton AdminBtn = new JButton("Admin");
		AdminBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				AdminPanel.setVisible(true);

			}
		});
		AdminBtn.setBounds(152, 99, 117, 23);
		panel.add(AdminBtn);
		
		JButton RiderBtn = new JButton("Rider");
		RiderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				RiderPanel.setVisible(true);
			}
		});
		RiderBtn.setBounds(10, 99, 117, 23);
		panel.add(RiderBtn);
		
		JButton next_R = new JButton("Next");
		next_R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean notfound=true;
				
				for(Restaurent restaurent : data.getRestaurents()) {
					notfound=false;
					if(restaurentfield.getText().equals(restaurent.getUserName())) 
						break;
					else
						notfound=true;
				}
				
				if(notfound) {
					JOptionPane.showMessageDialog(null, "Wrong UserName");
				}else {
					RestaurentPanel.setVisible(false);
					R_passwordPanel.setVisible(true);
				}
				
			}
		});
		next_R.setFont(new Font("Times New Roman", Font.BOLD, 15));
		next_R.setBounds(139, 104, 89, 23);
		RestaurentPanel.add(next_R);
		
		JButton cancel_R = new JButton("cancel");
		cancel_R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RestaurentPanel.setVisible(false);
				panel.setVisible(true);
			}
		});
		cancel_R.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cancel_R.setBounds(40, 104, 89, 23);
		RestaurentPanel.add(cancel_R);
		
		
		JButton back_R = new JButton("Back");
		back_R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				R_passwordPanel.setVisible(false);
				RestaurentPanel.setVisible(true);
			}
		});
		back_R.setFont(new Font("Times New Roman", Font.BOLD, 16));
		back_R.setBounds(40, 104, 89, 23);
		R_passwordPanel.add(back_R);
		
		JButton login_R = new JButton("Login");
		login_R.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Restaurent restaurent1=null;
				
				boolean notfound=false;
				for(Restaurent restaurent : data.getRestaurents()) {
					notfound=false;
					if(restaurentfield.getText().equals(restaurent.getUserName())&&passwordField_R.getText().equals(restaurent.getPassword())) {
						restaurent1=restaurent;
						break;
					}
					else
						notfound=true;
				}
				
				if(notfound) {
					JOptionPane.showMessageDialog(null, "Wrong Password!");
				}else {
					frame.dispose();
					RestaurentWindow win = new RestaurentWindow(restaurent1);
					win.getFrame().setVisible(true);
				}
				
			}
		});
		login_R.setFont(new Font("Times New Roman", Font.BOLD, 15));
		login_R.setBounds(139, 104, 89, 23);
		R_passwordPanel.add(login_R);
		
		
		JButton next_Rider = new JButton("Next");
		next_Rider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean notfound=true;
				
				for(Rider rider : data.getRiders()) {
					notfound=false;
					if(riderFeild.getText().equals(rider.getUserName())) 
						break;
					else
						notfound=true;
				}
				
				if(notfound) {
					JOptionPane.showMessageDialog(null, "Wrong UserName");
				}else {
					RiderPanel.setVisible(false);
					Rider_passwordPanel.setVisible(true);
				}
			}
		});
		next_Rider.setFont(new Font("Times New Roman", Font.BOLD, 15));
		next_Rider.setBounds(139, 104, 89, 23);
		RiderPanel.add(next_Rider);
		
		JButton cancel_Rider = new JButton("cancel");
		cancel_Rider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiderPanel.setVisible(false);
				panel.setVisible(true);
			}
		});
		cancel_Rider.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cancel_Rider.setBounds(40, 104, 89, 23);
		RiderPanel.add(cancel_Rider);
		
		JButton back_Rider = new JButton("Back");
		back_Rider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rider_passwordPanel.setVisible(false);
				RiderPanel.setVisible(true);
			}
		});
		back_Rider.setFont(new Font("Times New Roman", Font.BOLD, 16));
		back_Rider.setBounds(40, 104, 89, 23);
		Rider_passwordPanel.add(back_Rider);
		
		JButton login_Rider = new JButton("Login");
		login_Rider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rider rider1=null;
				boolean notfound=false;
				for(Rider rider : data.getRiders()) {
					notfound=false;
					if(riderFeild.getText().equals(rider.getUserName())&&passwordField_Rider.getText().equals(rider.getPassword())) {
						rider1=rider;
						break;
					}
					else
						notfound=true;
				}
				
				if(notfound) {
					JOptionPane.showMessageDialog(null, "Wrong Password!");
				}else {
					RiderWindow win = new RiderWindow(rider1);
					frame.dispose();
					win.getFrame().setVisible(true);
				}
			}
		});
		login_Rider.setFont(new Font("Times New Roman", Font.BOLD, 15));
		login_Rider.setBounds(139, 104, 89, 23);
		Rider_passwordPanel.add(login_Rider);
		
		
		JButton next_Admin = new JButton("Next");
		next_Admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean notfound=true;
				
				for(Admin admin : data.getAdmins()) {
					notfound=false;
					if(adminField.getText().equals(admin.getUserName())) 
						break;
					else
						notfound=true;
				}
				
				if(notfound) {
					JOptionPane.showMessageDialog(null, "Wrong UserName");
				}else {
					AdminPanel.setVisible(false);
					Admin_passwordPanel.setVisible(true);
				}
			}
		});
		next_Admin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		next_Admin.setBounds(139, 104, 89, 23);
		AdminPanel.add(next_Admin);
		
		JButton cancel_Admin = new JButton("cancel");
		cancel_Admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AdminPanel.setVisible(false);
				panel.setVisible(true);
				
			}
		});
		cancel_Admin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		cancel_Admin.setBounds(40, 104, 89, 23);
		AdminPanel.add(cancel_Admin);
		
		JButton back_Admin = new JButton("Back");
		back_Admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin_passwordPanel.setVisible(false);
				AdminPanel.setVisible(true);
			}
		});
		back_Admin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		back_Admin.setBounds(40, 104, 89, 23);
		Admin_passwordPanel.add(back_Admin);
		
		JButton login_Admin = new JButton("Login");
		login_Admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin admin1=null;
				boolean notfound=false;
				for(Admin admin : data.getAdmins()) {
					notfound=false;
					if(adminField.getText().equals(admin.getUserName())&&passwordField_Admin.getText().equals(admin.getPassword())) {
						admin1=admin;
						break;
					}
					else
						notfound=true;
				}
				
				if(notfound) {
					JOptionPane.showMessageDialog(null, "Wrong Password!");
				}else {
					AdminWindow win = new AdminWindow(admin1);
					win.getFrame().setVisible(true);
					frame.dispose();
				}
			}
		});
		login_Admin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		login_Admin.setBounds(139, 104, 89, 23);
		Admin_passwordPanel.add(login_Admin);
		
	}
	
	public void setIndex(int index) {
		this.loginIndex=index;
	}
	
	public int getIndex() {
		return loginIndex;
	}
}
