package UI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import DataHandling.DataHandle;
import DataHandling.ReadnWrite;
import DataHandling.ReadnWriteCart;
import DataHandling.ReadnWriteCustomer;
import models.Customer;
import models.FoodItems;
import models.Order;
import models.Restaurent;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JScrollBar;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Component;
import javax.swing.ScrollPaneConstants;

public class CustomerWindow {
	
	private JFrame frame;
	
	DataHandle data = new DataHandle();
	Customer customer;
	Restaurent restaurent;

	Order order1=null;
	
	public CustomerWindow(Customer customer){
		this.customer=customer;
		initialize();
	}

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(10);
		borderLayout.setHgap(10);
		frame.getContentPane().setLayout(borderLayout);
		

		JPanel header = new JPanel();
		frame.getContentPane().add(header,BorderLayout.NORTH);
		header.setPreferredSize(new Dimension(800, 30));
		header.setLayout(new BorderLayout(0, 0));
		
		JLabel webName = new JLabel("Food On Wheels");
		webName.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(webName, BorderLayout.CENTER);
		
		JButton cartButton = new JButton("Cart");
		cartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CartWindow win = new CartWindow(customer);
				win.getFrame().setVisible(true);
			}
		});
		header.add(cartButton, BorderLayout.WEST);
		
		JButton moneybtn = new JButton(Long.toString(customer.getMoney()));
		moneybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				moneyWindow win = new moneyWindow(customer);
				win.getFrame().setVisible(true);
			}
		});
		header.add(moneybtn, BorderLayout.EAST);
		
		JPanel subPanel = new JPanel();
		subPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		subPanel.setBounds(10, 51, 764, 477);
		frame.getContentPane().add(subPanel);
		subPanel.setVisible(false);
		
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				StartingWindow win = new StartingWindow();
				win.getFrame().setVisible(true);
			}
		});
		
		JButton subBack = new JButton("Back");
		subBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new CustomerWindow(customer).getFrame().setVisible(true);
			}
		});
		
		JButton cart = new JButton("Add to Cart");
		

		JPanel superSuperPanel = new JPanel();
		superSuperPanel.setBorder(new EmptyBorder(30,30,30,30));
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		superSuperPanel.setLayout(new BoxLayout(superSuperPanel, BoxLayout.Y_AXIS));
		JPanel superPanel = new JPanel();
		superPanel.setAlignmentY(2.0f);
		GridLayout gl_superPanel = new GridLayout(0,2);
		gl_superPanel.setVgap(50);
		gl_superPanel.setHgap(50);
		superPanel.setLayout(gl_superPanel);
		
		subPanel.setPreferredSize(new Dimension(800, 600));
		subPanel.setLayout(new GridLayout(0, 2, 20, 20));
		
		
		superPanel.setPreferredSize(new Dimension(1000,1000));
		
		superSuperPanel.add(superPanel);

		scrollPane.setViewportView(superSuperPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(30);
        
        frame.getContentPane().add(scrollPane);
		
		JPanel[] panel = new JPanel[data.getRestaurents().size()];
		JLabel[][] labels = new JLabel[data.getRestaurents().size()][6];
		
		for(int i=0,j=0 ; i < panel.length ; i++,j+=100) {
			panel[i] = new JPanel() {
				protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        Dimension arcs = new Dimension(15,15);
			        int width = getWidth();
			        int height = getHeight();
			        Graphics2D graphics = (Graphics2D) g;
			        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


			        //Draws the rounded opaque panel with borders.
			        graphics.setColor(getBackground());
			        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
			        graphics.setColor(getForeground());
			        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
			     }
			};
			panel[i].setBackground(Color.WHITE);
			superPanel.add(panel[i]);
			panel[i].setLayout(null);
			
			
			for(int k=0 ; k < 6 ; ) {
				
				labels[i][k] = new JLabel(data.getRestaurents().get(i).getRestaurentName());
				labels[i][k].setBounds(10, 11, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
				labels[i][k] = new JLabel(data.getRestaurents().get(i).getAdress());
				labels[i][k].setBounds(10, 36, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
				labels[i][k] = new JLabel(Double.toString(data.getRestaurents().get(i).getRating()));
				labels[i][k].setBounds(202, 11, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
				labels[i][k] = new JLabel(data.getRestaurents().get(i).getEmail());
				labels[i][k].setBounds(202, 36, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
				labels[i][k] = new JLabel(data.getRestaurents().get(i).getPhoneNumber());
				labels[i][k].setBounds(417, 11, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
				labels[i][k] = new JLabel(Integer.toString(data.getRestaurents().get(i).getOrderNo()));
				labels[i][k].setBounds(417, 36, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
			}
			
			String Name = data.getRestaurents().get(i).getRestaurentName();
			int resNumber = i;
			Order order = new Order(customer,data.getRestaurents().get(resNumber));
			order.setOrderTime();
			panel[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

					superSuperPanel.add(subPanel);
					superSuperPanel.remove(superPanel);
					superSuperPanel.remove(back);
					
					restaurent = data.getRestaurents().get(resNumber);
					
					for(int m =0 ; m<panel.length ; m++ )
						panel[m].setVisible(false);
					subPanel.setVisible(true);
					
					JPanel[] foodInfo = new JPanel[data.getRestaurents().get(resNumber).getFoodList().size()];
					JLabel[][] foodlabels = new JLabel[data.getRestaurents().get(resNumber).getFoodList().size()][2];
					JButton[] foodbtn = new JButton[data.getRestaurents().get(resNumber).getFoodList().size()];
					JLabel name = new JLabel(Name + " :");
					name.setBounds(100, 11, 150, 31);
					subPanel.add(name);
					
					JLabel rate = new JLabel("Rating" + " :");
					name.setBounds(100, 11, 150, 31);
					subPanel.add(rate);
					
					
					for(int i=0,j=0 ; i < foodInfo.length ; i++,j+=50) {
						foodInfo[i] = new JPanel() {
							protected void paintComponent(Graphics g) {
						        super.paintComponent(g);
						        Dimension arcs = new Dimension(15,15);
						        int width = getWidth();
						        int height = getHeight();
						        Graphics2D graphics = (Graphics2D) g;
						        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


						        //Draws the rounded opaque panel with borders.
						        graphics.setColor(getBackground());
						        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
						        graphics.setColor(getForeground());
						        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
						     }
						};
						foodInfo[i].setBackground(Color.WHITE);
						subPanel.add(foodInfo[i]);
						foodInfo[i].setLayout(null);
						for(int k =0 ; k < 2 ; ) {
							foodlabels[i][k] = new JLabel(data.getRestaurents().get(resNumber).getFoodList().get(i).getName());
							foodlabels[i][k].setBounds(10, 11, 111, 24);
							foodInfo[i].add(foodlabels[i][k]);
							k++;
							
							foodlabels[i][k] = new JLabel(Integer.toString(data.getRestaurents().get(resNumber).getFoodList().get(i).getPrice()));
							foodlabels[i][k].setBounds(161, 11, 111, 24);
							foodInfo[i].add(foodlabels[i][k]);
							k++;
							int foodIndex = i;
							foodbtn[i] = new JButton("Add to Order");
							foodbtn[i].addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									FoodItems food = new FoodItems(data.getRestaurents().get(resNumber).getFoodList().get(foodIndex).getName(),data.getRestaurents().get(resNumber).getFoodList().get(foodIndex).getPrice());
									order.getOrderList().add(food);
									order.setid();
								}
							});
							foodbtn[i].setBounds(250, 11, 99, 24);
							foodInfo[i].add(foodbtn[i]);
							
							

							superSuperPanel.add(subBack);
							superSuperPanel.add(cart);

							order1=order;
						}
						
					}
				}
			});
		}
		back.setAlignmentX(0.5f);
		back.setVerticalAlignment(SwingConstants.BOTTOM);
		superSuperPanel.add(back);
		cart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer.getCart().getOrders().add(order1);
				restaurent.getOrdersList().getOrders().add(order1);
				String file = customer.getUserName() + ".txt";
				ReadnWriteCart write = new ReadnWriteCart();
				write.write(file, customer);
				String file1 = restaurent.getUserName() + ".txt";
				write.write(file1, restaurent);
				
			}
		});

	}
}


class moneyWindow {
	
JFrame frame;
	
	Customer customer;
	
	public JFrame getFrame() {
		return frame;
	}
	
	moneyWindow(Customer customer){
		this.customer=customer;
		initialize();
	}
	
	public void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 300, 100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridLayout(0,2));
		
		JLabel money = new JLabel("New Price :");
		money.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		frame.getContentPane().add(money);
		
		
		JTextField newMoney = new JTextField();
		newMoney.setColumns(10);
		newMoney.setBounds(40, 62, 188, 31);
		frame.getContentPane().add(newMoney);
		
		JButton submit = new JButton("submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer.setMoney(Long.parseLong(newMoney.getText()));
				frame.dispose();
				ReadnWrite rnw = new ReadnWriteCustomer();
				rnw.write();
			}
		});
		submit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(submit);
	}
	
}
