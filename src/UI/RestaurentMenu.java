package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


import DataHandling.ReadnWriteCart;
import models.FoodItems;
import models.Restaurent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class RestaurentMenu {

	private JFrame frame;
	Restaurent restaurent;
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	/**
	 * Create the application.
	 */
	public RestaurentMenu(Restaurent restaurent) {
		this.restaurent=restaurent;
		initialize();
	}

	
	public RestaurentMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 723, 526);
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
		subPanel.setPreferredSize(new Dimension(800,800));
		
		
		JButton add = new JButton("Add food");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addFoodinMenu addFood = new addFoodinMenu(restaurent);
				addFood.getFrame().setVisible(true);
				ReadnWriteCart write = new ReadnWriteCart();
				String name = (restaurent.getUserName()+"menu"+".txt").toString();
				write.writeMenu(name, restaurent);
			}
		});
		subPanel.add(add);
		
		
		JPanel[] panels = new JPanel[restaurent.getFoodList().size()];
		JLabel[][] labels = new JLabel[panels.length][2];
		JButton[] removeButtons = new JButton[panels.length];
		JButton[] updateButtons = new JButton[panels.length];
	
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
			panels[i].setLayout(new GridLayout(0,4));
			panels[i].setBorder(new EmptyBorder(30,30,30,30));
			subPanel.add(panels[i]);
			
			
			for(int k=0; k < 2  ;  ) {
				
					labels[i][k] = new JLabel(restaurent.getFoodList().get(i).getName());
					panels[i].add(labels[i][k]);
					k++;
					
					labels[i][k] = new JLabel(Integer.toString(restaurent.getFoodList().get(i).getPrice()));
					panels[i].add(labels[i][k]);
					k++;
				
			}
			
				int r=i;
				removeButtons[i] = new JButton("Remove");
				removeButtons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						restaurent.getFoodList().remove(r);
						ReadnWriteCart write = new ReadnWriteCart();
						String name = (restaurent.getUserName()+"menu"+".txt").toString();
						write.writeMenu(name, restaurent);
						frame.dispose();
					}
				});
				panels[i].add(removeButtons[i]);
				
				updateButtons[i] = new JButton("Update");
				updateButtons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						updateMenu um = new updateMenu(restaurent,r);
						um.frame.setVisible(true);

						ReadnWriteCart write = new ReadnWriteCart();
						String name = (restaurent.getUserName()+"menu"+".txt").toString();
						write.writeMenu(name, restaurent);
					}
				});
				panels[i].add(updateButtons[i]);
			
			
		}
		
	}

}

class updateMenu{
	JFrame frame;
	
	Restaurent restaurent;int index;
	
	updateMenu(Restaurent restaurent,int i){
		this.restaurent=restaurent;
		this.index=i;
		initialize();
	}
	
	public void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 300, 100);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridLayout(0,2));
		
		JLabel food = new JLabel("Name :");
		food.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		frame.getContentPane().add(food);
		
		
		JTextField foodName = new JTextField();
		foodName.setColumns(10);
		foodName.setBounds(40, 62, 188, 31);
		frame.getContentPane().add(foodName);
		

		JLabel price = new JLabel("Price : ");
		price.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		frame.getContentPane().add(price);
		
		JTextField foodPrice = new JTextField();
		foodPrice.setColumns(10);
		foodPrice.setBounds(40, 62, 188, 31);
		frame.getContentPane().add(foodPrice);
		
		JButton submit = new JButton("submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restaurent.getFoodList().get(index).setName(foodName.getText());
				restaurent.getFoodList().get(index).setPrice(Integer.parseInt(foodPrice.getText()));
				frame.dispose();
			}
		});
		submit.setFont(new Font("Times New Roman", Font.BOLD, 16));
		frame.getContentPane().add(submit);
	}
}
