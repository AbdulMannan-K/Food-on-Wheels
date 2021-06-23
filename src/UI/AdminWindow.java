package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
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
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import DataHandling.DataHandle;
import DataHandling.ReadnWrite;
import DataHandling.ReadnWriteCart;
import DataHandling.ReadnWriteCustomer;
import DataHandling.ReadnWriteRestaurent;
import DataHandling.ReadnWriteRider;
import models.Admin;
import models.Customer;
import models.FoodItems;
import models.Order;
import models.Restaurent;
import models.Rider;

public class AdminWindow {

	private JFrame frame;
	DataHandle data =  new DataHandle();
	ReadnWrite rnw;
	Admin admin=null;
	
	
	public JFrame getFrame() {
		return this.frame;
	}

	/**
	 * Create the application.
	 */
	public AdminWindow(Admin admin) {
		this.admin=admin;
		initialize();
	}

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
				
			}
		});
		header.add(cartButton, BorderLayout.WEST);
		
		JButton dataButton = new JButton("Data");
		dataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EveryThing et = new EveryThing();
				et.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		header.add(dataButton, BorderLayout.EAST);
		
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
		
		JPanel[] panel = new JPanel[admin.getRestaurents().size()];
		JLabel[][] labels = new JLabel[admin.getRestaurents().size()][6];
		
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
			panel[i].setLayout(new GridLayout(0,3));
			
			
			for(int k=0 ; k < 6 ; ) {
				
				labels[i][k] = new JLabel(admin.getRestaurents().get(i).getRestaurentName());
				labels[i][k].setBounds(10, 11, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
				labels[i][k] = new JLabel(admin.getRestaurents().get(i).getAdress());
				labels[i][k].setBounds(10, 36, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
				labels[i][k] = new JLabel(admin.getRestaurents().get(i).getUserName());
				labels[i][k].setBounds(202, 11, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
				labels[i][k] = new JLabel(admin.getRestaurents().get(i).getPassword());
				labels[i][k].setBounds(417, 36, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
				labels[i][k] = new JLabel(admin.getRestaurents().get(i).getEmail());
				labels[i][k].setBounds(202, 36, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
				labels[i][k] = new JLabel(admin.getRestaurents().get(i).getPhoneNumber());
				labels[i][k].setBounds(417, 11, 139, 23);
				panel[i].add(labels[i][k]);
				k++;
				
			}
			
			int num = i;
			
			JButton reject = new JButton("Reject");
			reject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					admin.getRestaurents().remove(num);
				}
			});
			reject.setBounds(417,36,139,23);
			panel[i].add(reject);
			
			JButton accept = new JButton("Accept");
			reject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					data.getRestaurents().add(admin.getRestaurents().get(num));
					rnw = new ReadnWriteRestaurent();
					rnw.write();
				}
			});
			accept.setBounds(417, 11, 139, 23);
			panel[i].add(accept);
			

	}
		
		JPanel[] panel1 = new JPanel[admin.getCustomers().size()];
		JLabel[][] labels1 = new JLabel[admin.getCustomers().size()][6];
		
		for(int i=0,j=0 ; i < panel1.length ; i++,j+=100) {
			panel1[i] = new JPanel() {
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
			panel1[i].setBackground(Color.WHITE);
			superPanel.add(panel1[i]);
			panel1[i].setLayout(new GridLayout(0,3));
			
			
			for(int k=0 ; k < 6 ; ) {
				
				labels1[i][k] = new JLabel(admin.getCustomers().get(i).getUserName());
				labels1[i][k].setBounds(10, 11, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
				labels1[i][k] = new JLabel(admin.getCustomers().get(i).getAdress());
				labels1[i][k].setBounds(10, 36, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
				labels1[i][k] = new JLabel(admin.getCustomers().get(i).getFirstName()+" "+admin.getCustomers().get(i).getLastName());
				labels1[i][k].setBounds(202, 11, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
				labels1[i][k] = new JLabel(admin.getCustomers().get(i).getPassword());
				labels1[i][k].setBounds(417, 36, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
				labels1[i][k] = new JLabel(admin.getCustomers().get(i).getEmail());
				labels1[i][k].setBounds(202, 36, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
				labels1[i][k] = new JLabel(admin.getCustomers().get(i).getPhoneNumber());
				labels1[i][k].setBounds(417, 11, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
			}
			
			
			int num = i;
			
			JButton reject = new JButton("Reject");
			reject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					admin.getCustomers().remove(num);
				}
			});
			reject.setBounds(417,36,139,23);
			panel1[i].add(reject);
			
			JButton accept = new JButton("Accept");
			accept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					data.getCustomers().add(admin.getCustomers().get(num));
					rnw = new ReadnWriteCustomer();

					rnw.write();
					for(Customer customer1 : data.getCustomers())
						System.out.println(customer1.getUserName());
				}
			});
			accept.setBounds(417, 11, 139, 23);
			panel1[i].add(accept);
		

	}
		
		JPanel[] panel2 = new JPanel[admin.getRiders().size()];
		JLabel[][] labels2 = new JLabel[admin.getRiders().size()][6];
		
		for(int i=0,j=0 ; i < panel2.length ; i++,j+=100) {
			panel2[i] = new JPanel() {
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
			panel2[i].setBackground(Color.WHITE);
			superPanel.add(panel2[i]);
			panel2[i].setLayout(new GridLayout(0,3));
			
			
			for(int k=0 ; k < 6 ; ) {
				
				labels2[i][k] = new JLabel(admin.getRiders().get(i).getUserName());
				labels2[i][k].setBounds(10, 11, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
				labels2[i][k] = new JLabel(admin.getRiders().get(i).getAdress());
				labels2[i][k].setBounds(10, 36, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
				labels2[i][k] = new JLabel(admin.getRiders().get(i).getVehicle()[0]);
				labels2[i][k].setBounds(202, 11, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
				labels2[i][k] = new JLabel(admin.getRiders().get(i).getPassword());
				labels2[i][k].setBounds(417, 36, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
				labels2[i][k] = new JLabel(admin.getRiders().get(i).getEmail());
				labels2[i][k].setBounds(202, 36, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
				labels2[i][k] = new JLabel(admin.getRiders().get(i).getPhoneNumber());
				labels2[i][k].setBounds(417, 11, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
			}
			
			int num = i;
			
			JButton reject = new JButton("Reject");
			reject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					admin.getRiders().remove(num);
				}
			});
			reject.setBounds(417,36,139,23);
			panel2[i].add(reject);
			
			JButton accept = new JButton("Accept");
			accept.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rnw = new ReadnWriteRider();
					data.getRiders().add(admin.getRiders().get(num));
					rnw.write();
				}
			});
			accept.setBounds(417, 11, 139, 23);
			panel2[i].add(accept);
		

	}
		JButton mainbtn = new JButton("Back");
		mainbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				StartingWindow win = new StartingWindow();
				win.getFrame().setVisible(true);
			}
		});
		superSuperPanel.add(mainbtn);

	}
}

class EveryThing{
	
private JFrame frame;
	
	DataHandle data = new DataHandle();

	
	public EveryThing(){
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
		
		JButton cartButton = new JButton("Back");
		cartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StartingWindow win  = new StartingWindow();
				win.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		header.add(cartButton, BorderLayout.WEST);
		
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
			panel[i].setLayout(new GridLayout(0,3));
			
			
			for(int k=0 ; k < 5 ; ) {
				
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
				
			}
			String Name = data.getRestaurents().get(i).getRestaurentName();
			int resNumber = i;
			panel[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {

					superSuperPanel.add(subPanel);
					superSuperPanel.remove(superPanel);
					superSuperPanel.remove(back);
					
					
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
							foodbtn[i] = new JButton("Remove");
							foodbtn[i].addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									data.getRestaurents().get(resNumber).getFoodList().remove(foodIndex);
								}
							});
							foodbtn[i].setBounds(250, 11, 99, 24);
							foodInfo[i].add(foodbtn[i]);
							
							


						}
					}
				}
			});
			
			int restaurent = i;
			JButton remove = new JButton("Remove");
			remove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					data.getRestaurents().remove(restaurent);
					JOptionPane.showMessageDialog(null, "Account Removed successfully");
				}
			});
			panel[i].add(remove);
		}
		
		
		JPanel[] panel1 = new JPanel[data.getCustomers().size()];
		JLabel[][] labels1 = new JLabel[data.getCustomers().size()][6];
		
		for(int i=0,j=0 ; i < panel1.length ; i++,j+=100) {
			panel1[i] = new JPanel() {
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
			panel1[i].setBackground(Color.WHITE);
			superPanel.add(panel1[i]);
			panel1[i].setLayout(new GridLayout(0,3));
			
			
			for(int k=0 ; k < 5 ; ) {
				
				labels1[i][k] = new JLabel(data.getCustomers().get(i).getUserName());
				labels1[i][k].setBounds(10, 11, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
				labels1[i][k] = new JLabel(data.getCustomers().get(i).getAdress());
				labels1[i][k].setBounds(10, 36, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
				labels1[i][k] = new JLabel((data.getCustomers().get(i).getPassword()));
				labels1[i][k].setBounds(202, 11, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
				labels1[i][k] = new JLabel(data.getCustomers().get(i).getEmail());
				labels1[i][k].setBounds(202, 36, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
				labels1[i][k] = new JLabel(data.getCustomers().get(i).getPhoneNumber());
				labels1[i][k].setBounds(417, 11, 139, 23);
				panel1[i].add(labels1[i][k]);
				k++;
				
			}
			
			int customer = i;
			JButton remove = new JButton("Remove");
			remove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					data.getCustomers().remove(customer);
					JOptionPane.showMessageDialog(null, "Account Removed successfully");
				}
			});
			panel1[i].add(remove);
			
		}
		
		JPanel[] panel2 = new JPanel[data.getRiders().size()];
		JLabel[][] labels2 = new JLabel[data.getRiders().size()][6];
		
		for(int i=0,j=0 ; i < panel2.length ; i++,j+=100) {
			panel2[i] = new JPanel() {
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
			panel2[i].setBackground(Color.WHITE);
			superPanel.add(panel2[i]);
			panel2[i].setLayout(new GridLayout(0,3));
			
			
			for(int k=0 ; k < 6 ; ) {
				
				labels2[i][k] = new JLabel(data.getRiders().get(i).getUserName());
				labels2[i][k].setBounds(10, 11, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
				labels2[i][k] = new JLabel(data.getRiders().get(i).getAdress());
				labels2[i][k].setBounds(10, 36, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
				labels2[i][k] = new JLabel((data.getRiders().get(i).getPassword()));
				labels2[i][k].setBounds(202, 11, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
				labels2[i][k] = new JLabel(data.getRiders().get(i).getEmail());
				labels2[i][k].setBounds(202, 36, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
				labels2[i][k] = new JLabel(data.getRiders().get(i).getPhoneNumber());
				labels2[i][k].setBounds(417, 11, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
				labels2[i][k] = new JLabel(data.getRiders().get(i).getFirstName()+data.getRiders().get(i).getLastName());
				labels2[i][k].setBounds(417, 36, 139, 23);
				panel2[i].add(labels2[i][k]);
				k++;
				
			}
			int rider = i;
			JButton remove = new JButton("Remove");
			remove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					data.getRiders().remove(rider);
					JOptionPane.showMessageDialog(null, "Account Removed successfully");
				}
			});
			panel2[i].add(remove);
			
		}
		
		back.setAlignmentX(0.5f);
		back.setVerticalAlignment(SwingConstants.BOTTOM);
		superSuperPanel.add(back);
	}
	
	public void finalize() {
		ReadnWrite rnw;
		rnw = new ReadnWriteCustomer();
		rnw.write();
		rnw = new ReadnWriteRestaurent();
		rnw.write();
		rnw = new ReadnWriteRider();
		rnw.write();
		
		ReadnWriteCart rnwc = new ReadnWriteCart();
		
		for(Restaurent restaurent : data.getRestaurents())
			rnwc.writeMenu((restaurent.getUserName()+"menu.txt"), restaurent);
		
	}
	
}
