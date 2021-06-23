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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DataHandling.DataHandle;
import DataHandling.ReadnWrite;
import DataHandling.ReadnWriteCart;
import DataHandling.ReadnWriteCustomer;
import DataHandling.ReadnWriteRestaurent;
import DataHandling.ReadnWriteRider;
import models.Restaurent;
import models.Rider;

public class RiderWindow {

	private JFrame frame;
	Restaurent restaurent;
	Rider rider;
	DataHandle data = new DataHandle();
	
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Create the application.
	 */
	public RiderWindow(Rider rider) {
		this.rider = rider;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
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
		
		JLabel restaurentName = new JLabel(rider.getUserName());
		restaurentName.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(restaurentName, BorderLayout.CENTER);
		
		JButton menu = new JButton("NotBusy");
		menu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rider.setBusy(false);
			}
		});
		header.add(menu, BorderLayout.WEST);
		
		
		
		JPanel subHeaderPanel = new JPanel();
		subHeaderPanel.setLayout(new FlowLayout());
		header.add(subHeaderPanel, BorderLayout.EAST);
		
		JButton moneybtn = new JButton(Long.toString(rider.getMoney()));
		moneybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RiderMoney win = new RiderMoney(rider);
				win.getFrame().setVisible(true);
			}
		});
		subHeaderPanel.add(moneybtn);
		
		JButton restaurentButton = new JButton("Busy");
		restaurentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rider.setBusy(true);
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
		
		
		
		JPanel[] panels = new JPanel[rider.getOrdersList().getOrders().size()];
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
			
			
			labels = new JLabel[panels.length][rider.getOrdersList().getOrders().get(i).getOrderList().size() * 2];
			int totalMoney=0;
			for(int k=0 ,m=0; k < rider.getOrdersList().getOrders().get(i).getOrderList().size()  ; k++ ) {
				
					labels[i][m] = new JLabel(rider.getOrdersList().getOrders().get(i).getOrderList().get(k).getName());
					panels[i].add(labels[i][m]);
					m++;
					
					labels[i][m] = new JLabel(Integer.toString(rider.getOrdersList().getOrders().get(i).getOrderList().get(k).getPrice()));
					panels[i].add(labels[i][m]);
					m++;
				
					totalMoney = totalMoney + rider.getOrdersList().getOrders().get(i).getOrderList().get(k).getPrice();
			}
			
			JLabel label = new JLabel(Boolean.toString(rider.getOrdersList().getOrders().get(i).isRiderStatus()));
			panels[i].add(label);
			
			
			JLabel label1 = new JLabel(rider.getOrdersList().getOrders().get(i).getOrderTime());
			panels[i].add(label1);
			

			int resNumber = i;
			
			JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
			btnPanel.setBorder(new EmptyBorder(0,30,0,30));
			panels[i].add(btnPanel);
			JButton customerbtn = new JButton(rider.getOrdersList().getOrders().get(i).getCustomer().getFirstName()+" "+rider.getOrdersList().getOrders().get(i).getCustomer().getLastName());
			customerbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JPanel panel = new JPanel();
					subPanel.setVisible(false);
					superPanel.add(panel);
					panel.setVisible(true);
					panel.setLayout(new GridLayout(0,2,20,20));
					
					JLabel customerUserName = new JLabel(rider.getOrdersList().getOrders().get(resNumber).getCustomer().getUserName());
					JLabel customerName = new JLabel(rider.getOrdersList().getOrders().get(resNumber).getCustomer().getFirstName()+" "+rider.getOrdersList().getOrders().get(resNumber).getCustomer().getLastName());
					JLabel customerAdress = new JLabel(rider.getOrdersList().getOrders().get(resNumber).getCustomer().getAdress());
					JLabel customerNum = new JLabel(rider.getOrdersList().getOrders().get(resNumber).getCustomer().getPhoneNumber());
					JLabel customerMail = new JLabel(rider.getOrdersList().getOrders().get(resNumber).getCustomer().getEmail());
					JPanel btnPanel = new JPanel();
					btnPanel.setBorder(new EmptyBorder(50,0,50,100));
					btnPanel.setLayout(new GridLayout());
					JButton back = new JButton("Back");
					back.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
							RiderWindow win = new RiderWindow(rider);
							win.getFrame().setVisible(true);
						}
					});
					btnPanel.add(back);
					
					panel.add(customerUserName);panel.add(customerName);panel.add(customerAdress);panel.add(customerNum);panel.add(customerMail);panel.add(btnPanel);
				}
			});
			btnPanel.add(customerbtn);
			
			JPanel btnPanel1 = new JPanel();
			btnPanel1.setLayout(new FlowLayout(FlowLayout.LEADING));
			btnPanel1.setBorder(new EmptyBorder(0,30,0,30));
			panels[i].add(btnPanel1);
			JButton restaurentbtn = new JButton(rider.getOrdersList().getOrders().get(i).getRestaurent().getRestaurentName());
			restaurentbtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JPanel panel = new JPanel();
					subPanel.setVisible(false);
					superPanel.add(panel);
					panel.setVisible(true);
					panel.setLayout(new GridLayout(0,2,20,20));
					
					JLabel customerUserName = new JLabel(rider.getOrdersList().getOrders().get(resNumber).getRestaurent().getRestaurentName());
					JLabel customerName = new JLabel(rider.getOrdersList().getOrders().get(resNumber).getRestaurent().getRating()+" "+rider.getOrdersList().getOrders().get(resNumber).getRestaurent().getOrderNo());
					JLabel customerAdress = new JLabel(rider.getOrdersList().getOrders().get(resNumber).getRestaurent().getAdress());
					JLabel customerNum = new JLabel(rider.getOrdersList().getOrders().get(resNumber).getRestaurent().getPhoneNumber());
					JLabel customerMail = new JLabel(rider.getOrdersList().getOrders().get(resNumber).getRestaurent().getEmail());
					JPanel btnPanel = new JPanel();
					btnPanel.setBorder(new EmptyBorder(50,0,50,100));
					btnPanel.setLayout(new GridLayout());
					JButton back = new JButton("Back");
					back.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
							RiderWindow win = new RiderWindow(rider);
							win.getFrame().setVisible(true);
						}
					});
					btnPanel.add(back);
					
					panel.add(customerUserName);panel.add(customerName);panel.add(customerAdress);panel.add(customerNum);panel.add(customerMail);panel.add(btnPanel);
				}
			});
			btnPanel1.add(restaurentbtn);
			
			final int price = totalMoney;
			
			JPanel btnPanel2 = new JPanel();
			btnPanel2.setLayout(new FlowLayout(FlowLayout.LEADING));
			btnPanel2.setBorder(new EmptyBorder(0,30,0,30));
			panels[i].add(btnPanel2);
			JButton completed = new JButton("Order Delivered");
			completed.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rider.getOrdersList().getOrders().get(resNumber).setRiderStatus(true);
					restaurent = rider.getOrdersList().getOrders().get(resNumber).getRestaurent();
					for(int i=0 ; i < rider.getOrdersList().getOrders().size() ; i++)  
						for(int j=0 ; j < restaurent.getOrdersList().getOrders().size(); j++)
							if(rider.getOrdersList().getOrders().get(i).getid()==restaurent.getOrdersList().getOrders().get(j).getid()) {
								restaurent.getOrdersList().getOrders().remove(j);
								rider.setMoney(rider.getMoney()-price);
								restaurent.setMoney(restaurent.getMoney()+price);
							}
					
					ReadnWriteCart write = new ReadnWriteCart();
					String file = rider.getUserName() + "R.txt";
					write.write(file, rider);
					ReadnWrite rnw = new ReadnWriteRider();
					rnw.write();
					rnw = new ReadnWriteRestaurent();
					rnw.write();
				}
			});
			btnPanel2.add(completed);
			
			JPanel btnPanel3 = new JPanel();
			btnPanel3.setLayout(new FlowLayout(FlowLayout.LEADING));
			btnPanel3.setBorder(new EmptyBorder(0,30,0,30));
			panels[i].add(btnPanel3);
			JButton cancel = new JButton("Deny Order");
			cancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean notfree = true;
					for(Rider rider1 : data.getRiders()) {
						notfree=false;
						if(!rider1.isBusy() && (!(rider1.getUserName().equals(rider.getUserName())))) {
							rider1.getOrdersList().getOrders().add(rider.getOrdersList().getOrders().get(resNumber));
							rider.getOrdersList().getOrders().remove(resNumber);
							break;
						}
						else {
							notfree = true;
						}
					}
					
					if(notfree) {
						JOptionPane.showMessageDialog(null,"Other Rider not free. Only you are available");
					}
					
					ReadnWriteCart write = new ReadnWriteCart();
					String file = rider.getUserName() + "R.txt";
					write.write(file, rider);
					
				}
			});
			btnPanel3.add(cancel);
			
			
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

class RiderMoney {
	
JFrame frame;
	
	Rider rider;
	
	public JFrame getFrame() {
		return frame;
	}
	
	RiderMoney(Rider rider){
		this.rider=rider;
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
				rider.setMoney(Long.parseLong(newMoney.getText()));
				frame.dispose();
				ReadnWrite rnw = new ReadnWriteRider();
				rnw.write();
			}
		});
		submit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(submit);
	}
	
}

