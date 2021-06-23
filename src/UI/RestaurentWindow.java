package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.Data;

import DataHandling.DataHandle;
import DataHandling.ReadnWrite;
import DataHandling.ReadnWriteCart;
import DataHandling.ReadnWriteCustomer;
import DataHandling.ReadnWriteRestaurent;
import models.Admin;
import models.Customer;
import models.Restaurent;
import models.Rider;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RestaurentWindow {

	private JFrame frame;
	Restaurent restaurent;

	
	public JFrame getFrame() {
		return frame;
	}
	
	public RestaurentWindow(Restaurent restaurent) {
		this.restaurent=restaurent;
		initialize();
	}

	/**
	 * Create the application.
	 */
//	public RestaurentWindow() {
//		initialize();
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(10);
		borderLayout.setHgap(10);
		frame.getContentPane().setLayout(borderLayout);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);		
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(30);
        frame.getContentPane().add(scrollPane);
        
        JPanel header = new JPanel();
		frame.getContentPane().add(header,BorderLayout.NORTH);
		header.setPreferredSize(new Dimension(800, 30));
		header.setLayout(new BorderLayout(0, 0));
		
		JLabel restaurentName = new JLabel(restaurent.getRestaurentName());
		restaurentName.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(restaurentName, BorderLayout.CENTER);
		
		JButton menu = new JButton("Menu");
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReadnWriteCart write = new ReadnWriteCart();
				String name = (restaurent.getUserName()+"menu"+".txt").toString();
				write.writeMenu(name, restaurent);
				RestaurentMenu m = new RestaurentMenu(restaurent);
				m.getFrame().setVisible(true);
			}
		});
		header.add(menu, BorderLayout.WEST);
		
		JPanel subHeaderPanel = new JPanel();
		subHeaderPanel.setLayout(new FlowLayout());
		header.add(subHeaderPanel, BorderLayout.EAST);
		
		JButton moneybtn = new JButton(Long.toString(restaurent.getMoney()));
		moneybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResmoneyWindow win = new ResmoneyWindow(restaurent);
				win.getFrame().setVisible(true);
			}
		});
		subHeaderPanel.add(moneybtn);
		
		JButton restaurentButton = new JButton("Account");
		restaurentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				RestaurentWindow win = new RestaurentWindow(restaurent);
				win.getFrame().setVisible(true);
				String file = restaurent.getUserName() + ".txt";
				ReadnWriteCart write = new ReadnWriteCart();
				write.write(file, restaurent);
				file = restaurent.getUserName() + "menu.txt";
				write.writeMenu(file, restaurent);
			}
		});
		subHeaderPanel.add(restaurentButton);
		
		JPanel superPanel = new JPanel();
		superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(superPanel);
		superPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		JPanel subPanel = new JPanel();
		superPanel.add(subPanel);
		subPanel.setLayout(new GridLayout(0, 2, 20, 20));
		subPanel.setBorder(new EmptyBorder(30,30,30,30));
		subPanel.setPreferredSize(new Dimension(800,800));
		
		
		
		JPanel[] panels = new JPanel[restaurent.getOrdersList().getOrders().size()];
		JLabel[][] labels ;
	
		for(int i =0 ; i < panels.length; i++) {
			panels[i] = new JPanel() {
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
			panels[i].setBackground(Color.white);
			panels[i].setLayout(new GridLayout(0,2));
			panels[i].setBorder(new EmptyBorder(30,30,30,30));
			subPanel.add(panels[i]);
			
			
			labels = new JLabel[panels.length][restaurent.getOrdersList().getOrders().get(i).getOrderList().size() * 2];
			
			for(int k=0 ,m=0; k < restaurent.getOrdersList().getOrders().get(i).getOrderList().size()  ; k++ ) {
				
					labels[i][m] = new JLabel(restaurent.getOrdersList().getOrders().get(i).getOrderList().get(k).getName());
					panels[i].add(labels[i][m]);
					m++;
					
					labels[i][m] = new JLabel(Integer.toString(restaurent.getOrdersList().getOrders().get(i).getOrderList().get(k).getPrice()));
					panels[i].add(labels[i][m]);
					m++;
				
			}
			
			JLabel label = new JLabel(Boolean.toString(restaurent.getOrdersList().getOrders().get(i).isStatus()));
			panels[i].add(label);
			
			JLabel label2 = new JLabel(Boolean.toString(restaurent.getOrdersList().getOrders().get(i).isRiderStatus()));
			panels[i].add(label2);
			
			JLabel label1 = new JLabel(restaurent.getOrdersList().getOrders().get(i).getOrderTime());
			panels[i].add(label1);
			
			
			
			int resNumber = i;
			
			
			JButton completed = new JButton("Order completed");
			completed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Rider rider1=null;
					if(!restaurent.getOrdersList().getOrders().get(resNumber).isStatus()) {
						restaurent.getOrdersList().getOrders().get(resNumber).setStatus(true);
						DataHandle data = new DataHandle();
						boolean notfree = true;
						for(Rider rider : data.getRiders()) {
							notfree=false;
							if(rider.isBusy()==false) {
								rider1=rider;
								rider.getOrdersList().getOrders().add(restaurent.getOrdersList().getOrders().get(resNumber));
								break;
							}
							else
								notfree=true;
						}
						
						if(notfree)
							JOptionPane.showMessageDialog(null, "No Rider Available, you have to wait");
						String file = restaurent.getUserName() + ".txt";
						ReadnWriteCart write = new ReadnWriteCart();
						write.write(file, restaurent);
						file = rider1.getUserName() + "R.txt";
						write.write(file, rider1);
					}
					else {
						JOptionPane.showMessageDialog(null, "Order already send to riders");
					}
				}
			});
			panels[i].add(completed);
			panels[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					JPanel panel = new JPanel();
					subPanel.setVisible(false);
					superPanel.add(panel);
					panel.setVisible(true);
					panel.setLayout(new GridLayout(0,2,20,20));
					
					JLabel customerUserName = new JLabel(restaurent.getOrdersList().getOrders().get(resNumber).getCustomer().getUserName());
					JLabel customerName = new JLabel(restaurent.getOrdersList().getOrders().get(resNumber).getCustomer().getFirstName()+" "+restaurent.getOrdersList().getOrders().get(resNumber).getCustomer().getLastName());
					JLabel customerAdress = new JLabel(restaurent.getOrdersList().getOrders().get(resNumber).getCustomer().getAdress());
					JLabel customerNum = new JLabel(restaurent.getOrdersList().getOrders().get(resNumber).getCustomer().getPhoneNumber());
					JLabel customerMail = new JLabel(restaurent.getOrdersList().getOrders().get(resNumber).getCustomer().getEmail());
					JPanel btnPanel = new JPanel();
					btnPanel.setBorder(new EmptyBorder(50,0,50,100));
					btnPanel.setLayout(new GridLayout());
					JButton back = new JButton("Back");
					back.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
							RestaurentWindow win = new RestaurentWindow(restaurent);
							win.getFrame().setVisible(true);
						}
					});
					btnPanel.add(back);
					
					panel.add(customerUserName);panel.add(customerName);panel.add(customerAdress);panel.add(customerNum);panel.add(customerMail);panel.add(btnPanel);
				}
			});
			
			
			
		}
		JPanel btnPanel = new JPanel();
		JButton Back = new JButton("Back");
		subPanel.add(btnPanel);
		JButton back = new JButton("Back");
		Back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				StartingWindow win = new StartingWindow();
				win.getFrame().setVisible(true);
			}
		});
		btnPanel.add(Back);
	}
}


class ResmoneyWindow {
	
JFrame frame;
	
	Restaurent restaurent;
	
	public JFrame getFrame() {
		return frame;
	}
	
	ResmoneyWindow(Restaurent restaurent){
		this.restaurent=restaurent;
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
				restaurent.setMoney(Long.parseLong(newMoney.getText()));
				frame.dispose();
				ReadnWrite rnw = new ReadnWriteRestaurent();
				rnw.write();
			}
		});
		submit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(submit);
	}
	
}