package UI;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;

import DataHandling.DataHandle;
import DataHandling.ReadnWrite;
import DataHandling.ReadnWriteCart;
import DataHandling.ReadnWriteCustomer;
import DataHandling.ReadnWriteRider;
import models.Admin;
import models.Cart;
import models.Customer;
import models.FoodItems;
import models.Order;

import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

public class CartWindow {

	private JFrame frame;
	Customer customer;
	DataHandle data = new DataHandle();
	
	
	public JFrame getFrame() {
		return frame;
	}
	
	
	public CartWindow(Customer customer) {
		this.customer = customer;
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(10);
		borderLayout.setHgap(10);
		frame.getContentPane().setLayout(borderLayout);
		
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);		
        scrollPane.getVerticalScrollBar().setUnitIncrement(30);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(30);
        frame.getContentPane().add(scrollPane);
        
        JPanel superPanel = new JPanel();
		superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(superPanel);
		superPanel.setBorder(new EmptyBorder(30,30,30,30));
		
		JPanel subPanel = new JPanel();
		superPanel.add(subPanel);
		subPanel.setLayout(new GridLayout(0, 2, 20, 20));
		subPanel.setBorder(new EmptyBorder(30,30,30,30));
        
		JPanel[] panels = new JPanel[customer.getCart().getOrders().size()];
		JLabel[][] labels ;
		
		for(int i =0 ; i < customer.getCart().getOrders().size() ; i++) {
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
			
			labels = new JLabel[panels.length][customer.getCart().getOrders().get(i).getOrderList().size() * 2];
			
			int totalMoney=0;
			
			for(int k=0 ,m=0; k < customer.getCart().getOrders().get(i).getOrderList().size()  ; k++ ) {
				
					labels[i][m] = new JLabel(customer.getCart().getOrders().get(i).getOrderList().get(k).getName());
					panels[i].add(labels[i][m]);
					m++;
					
					labels[i][m] = new JLabel(Integer.toString(customer.getCart().getOrders().get(i).getOrderList().get(k).getPrice()));
					panels[i].add(labels[i][m]);
					m++;
					
					totalMoney= totalMoney + customer.getCart().getOrders().get(i).getOrderList().get(k).getPrice();
			}
			
			JLabel label = new JLabel(Boolean.toString(customer.getCart().getOrders().get(i).isStatus()));
			panels[i].add(label);
			
			JLabel label2 = new JLabel(Boolean.toString(customer.getCart().getOrders().get(i).isRiderStatus()));
			panels[i].add(label2);
			
			JLabel label1 = new JLabel(customer.getCart().getOrders().get(i).getOrderTime());
			panels[i].add(label1);
			
			int j=i;
			
			JPanel btnpanel = new JPanel();
			panels[i].add(btnpanel);
			btnpanel.setBackground(Color.WHITE);
			btnpanel.setBorder(new EmptyBorder(50,0,50,100));
			btnpanel.setLayout(new GridLayout());
			
			JButton rem = new JButton("Remove");
			rem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					customer.getCart().getOrders().remove(j);
					String file = customer.getUserName() + ".txt";
					ReadnWriteCart write = new ReadnWriteCart();
					write.write(file, customer);
				}
			});
			btnpanel.add(rem);
			
			JLabel TotalPrice = new JLabel(Integer.toString(totalMoney));
			panels[i].add(TotalPrice);
			
			final int price = totalMoney;
			
			JButton pay = new JButton("Pay Order");
			pay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					customer.setMoney(customer.getMoney()-price);
					for(int i=0 ; i < data.getRiders().size() ; i++ ) {
						for(int j=0 ; j < data.getRiders().get(i).getOrdersList().getOrders().size() ; j++)
						if(data.getRiders().get(i).getOrdersList().getOrders().get(j).getCustomer()==customer)
							data.getRiders().get(i).setMoney(data.getRiders().get(i).getMoney()+price);
					}
				}
			});
			panels[i].add(pay);
			ReadnWrite rnw = new ReadnWriteCustomer();
			rnw.write();
			rnw = new ReadnWriteRider();
			rnw.write();
			
		}
	}
	
	
	

}
