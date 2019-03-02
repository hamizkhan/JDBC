//Hamiz Khan INSY 4306 Section 002

import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MavGUI extends JFrame
{

	public JDesktopPane desktop;

	private static MavRental mav = new MavRental();
	private static DatabaseMethods db = new DatabaseMethods();
	

	public MavGUI() 
	{
		super("Mav Rental System");
		//readCustomer();
		desktop = new JDesktopPane();
		db.connectToDatabase();

		// Menu Bar
		JMenuBar menuBar = new JMenuBar();

		// Add Menu
		JMenu addMenu = new JMenu("Add");
		JMenuItem addCustomer = new JMenuItem(" Add Customer");
		JMenuItem addRental = new JMenuItem(" Add Rental");
		addMenu.add(addCustomer);
		addMenu.add(addRental);

		// Exit Menu
		JMenu exitMenu = new JMenu("Exit");
		JMenuItem calculateCharge = new JMenuItem(" Calculate Charges");
		JMenuItem close = new JMenuItem(" Close");
		JMenuItem writeDatabase=new JMenuItem("Write Database");
		exitMenu.add(calculateCharge);
		exitMenu.add(writeDatabase);
		exitMenu.add(close);
		
		
		

		// Adding to JFrame
		setJMenuBar(menuBar);
		menuBar.add(addMenu);
		menuBar.add(exitMenu);
		add(desktop);

		// Action Listener for close
		close.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) 
			{
				System.exit(0);
			}

		});

		// Action Listener for Calculate Charge
		calculateCharge.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) 
			{
				JOptionPane.showMessageDialog(null, mav.printInvoice());
			}

		});
		
		//Action Listener for Write Database
		
		writeDatabase.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent event)
			{
				db.writeCustomerTable();
				System.out.println("\n");
				db.writeRentalTable();
			}
			
			});

		// Action Listener for Add Customer
		addCustomer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event)
			{
				JInternalFrame frame = new JInternalFrame(" Add Customer", true, true, true, true);
				desktop.add(frame);
				CustomerPanel cp = new CustomerPanel();
				frame.add(cp);
				frame.pack();
				frame.setVisible(true);
				
			}
		});

		addRental.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event)
			{
				JInternalFrame frame = new JInternalFrame(" Add Rental", true, true, true, true);
				desktop.add(frame);
				RentalPanel rp = new RentalPanel();
				frame.add(rp);
				frame.pack();
				frame.setVisible(true);
			
			}

		});
	}

//	public void readCustomer() 
//	{
//
//		Scanner input;
//		String line;
//		String array[];
//
//		try
//		{
//			input = new Scanner(new File("customers.txt"));
//
//			while (input.hasNext())
//			{
//				line = input.nextLine();
//				array = line.split(",");
//
//				if (mav.customerExists(array[0]) == false)
//				{
//					mav.addCustomer(new Customer(array[0], new Address(array[1], array[2], array[3], array[4]), array[5]));
//
//					for (Customer customer : mav.getCustomerList())
//					{
//						if (customer.getName().equals(array[0]))
//
//						{
//							if (array[6].equals("Furniture"))
//							{
//								customer.addRental(new FurnitureRental(Double.parseDouble(array[8]),
//										Integer.parseInt(array[9]), FurnitureRental.FurnitureType.valueOf(array[7])));
//							} 
//							else if (array[6].equals("Electronic")) 
//							{
//								customer.addRental(new ElectronicRental(Double.parseDouble(array[8]),
//										Integer.parseInt(array[9]), ElectronicRental.ElectronicType.valueOf(array[7])));
//							}
//						}
//
//					}
//				}
//
//
//				else if (mav.customerExists(array[0]) == true)
//				{
//
//					for (Customer customer : mav.getCustomerList())
//					{
//
//						if (customer.getName().equals(array[0])) 
//						{
//
//							if (array[6].equals("Furniture"))
//							{
//								customer.addRental(new FurnitureRental(Double.parseDouble(array[8]),
//										Integer.parseInt(array[9]), FurnitureRental.FurnitureType.valueOf(array[7])));
//							} 
//							else if (array[6].equals("Electronic"))
//							{
//								customer.addRental(new ElectronicRental(Double.parseDouble(array[8]),
//										Integer.parseInt(array[9]), ElectronicRental.ElectronicType.valueOf(array[7])));
//							}
//
//						}
//					}
//
//				}
//
//			}
//
//		}
//
//		catch (IOException ioe) 
//		{
//			ioe.printStackTrace();
//		}
//
//	}
//	
//	public void writeFile()
//	{
//		
//		ObjectOutputStream output;
//		MavRental mavList=new MavRental(mav.getCustomerList());
//		
//		try
//		{
//			output=new ObjectOutputStream(new FileOutputStream("customers.ser"));
//			
//			output.writeObject(mavList);
//		}
//		
//		catch(IOException ioe)
//		{
//			ioe.printStackTrace();
//		}
//		
//	}
	
	

	class CustomerPanel extends JPanel 
	{

		private JLabel nameLabel;
		private JTextField nameTextField;
		private JLabel streetLabel;
		private JTextField streetTextField;
		private JLabel cityLabel;
		private JTextField cityTextField;
		private JLabel stateLabel;
		private JTextField stateTextField;
		private JLabel zipLabel;
		private JTextField zipTextField;
		private JLabel creditCardLabel;
		private JTextField creditCardTextField;
		private JLabel doneLabel;
		private JButton doneButton;

		public CustomerPanel() 
		{
			setLayout(new GridLayout(7, 2));

			nameLabel = new JLabel("Enter name");
			nameTextField = new JTextField(20);
			streetLabel = new JLabel("Enter street");
			streetTextField = new JTextField(20);
			cityLabel = new JLabel("Enter city");
			cityTextField = new JTextField(20);
			stateLabel = new JLabel("Enter state");
			stateTextField = new JTextField(20);
			zipLabel = new JLabel("Enter zip");
			zipTextField = new JTextField(20);
			creditCardLabel = new JLabel("Enter credit card number");
			creditCardTextField = new JTextField(20);
			doneLabel = new JLabel("Click When Done");
			doneButton = new JButton("Submit");

			add(nameLabel);
			add(nameTextField);
			add(streetLabel);
			add(streetTextField);
			add(cityLabel);
			add(cityTextField);
			add(stateLabel);
			add(stateTextField);
			add(zipLabel);
			add(zipTextField);
			add(creditCardLabel);
			add(creditCardTextField);
			add(doneLabel);
			add(doneButton);

			MyListener handler = new MyListener();
			doneButton.addActionListener(handler);
			
		}

		class MyListener implements ActionListener

		{

			private String name;
			private String street;
			private String city;
			private String state;
			private String zip;
			private String ccNumber;
		

			public void actionPerformed(ActionEvent event)
			{

				name = nameTextField.getText();
				street = streetTextField.getText();
				city = cityTextField.getText();
				state = stateTextField.getText();
				zip = zipTextField.getText();
				ccNumber = creditCardTextField.getText();

				if(valid())
				{
					if (db.customerExists(name) == false) 
				
					{
						db.writeCustomerToDatabase(name, street, city, state, zip, ccNumber);
					}

					else if (db.customerExists(name) == true)
						JOptionPane.showMessageDialog(null, "Customer already exits.\nPlease try again.");

					nameTextField.setText("");
					streetTextField.setText("");
					cityTextField.setText("");
					stateTextField.setText("");
					zipTextField.setText("");
					creditCardTextField.setText("");
					nameTextField.requestFocus();
				}	
			}
				
				
			public boolean valid()
			{
				boolean validData=true;
				
				if(nameTextField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Name cannot be left blank");
					nameTextField.requestFocus();
					validData=false;
				}	
				if(streetTextField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Street cannot be left blank");
					streetTextField.requestFocus();
					validData=false;
				}
				
				if(city.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "City cannot be left blank");
					cityTextField.requestFocus();
					validData=false;
				}
				
				if(state.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "State cannot be left blank");
					stateTextField.requestFocus();
					validData=false;
				}
				
				if(zip.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Zip cannot be left blank");
					zipTextField.requestFocus();
					validData=false;
				}
				
				if(ccNumber.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Credit Card cannot be left blank");
					creditCardTextField.requestFocus();
					validData=false;
				}
				
				if(!zip.isEmpty())
				{
					if(!zip.matches("([1-9]\\d{4}|[1-9]\\d{4}.\\d{4})"))
					{
						JOptionPane.showMessageDialog(null, "Zip Code not correct");
						zipTextField.requestFocus();
						zipTextField.setText("");
						validData=false;
					}
					
				}
				
				if(!ccNumber.isEmpty())
				{
					if(!ccNumber.matches("[4-6]\\d{3}-\\d{4}-\\d{4}-\\d{4}"))
					{
						JOptionPane.showMessageDialog(null, "Credit Card not accepted");
						creditCardTextField.requestFocus();
						creditCardTextField.setText("");
						validData=false;
					}
					
				}
				
				return validData;
				
			}
								
		}

	}

	class RentalPanel extends JPanel
	{
		private JLabel customerNameLabel;
		private JTextField customerNameTextField;
		private JCheckBox furnitureCheckBox;
		private JCheckBox electronicCheckBox;
		private ButtonGroup buttonGroup;
		private JLabel daysLabel;
		private JTextField daysTextField;
		private JLabel priceLabel;
		private JTextField priceTextField;
		private JComboBox<String> electronicTypeBox;
		private JComboBox<String> furnitureTypeBox;
		private JLabel doneLabel;
		private JButton doneButton;
		private String electronicType[] = { "SELECT ONE", "COMPUTER", "TV" };
		private String furnitureType[] = { "SELECT ONE", "COUCH", "BED","CHAIR" };

		public RentalPanel() 
		{

			setLayout(new GridLayout(7, 2));
			
			customerNameLabel = new JLabel("Enter customer name");
			customerNameTextField = new JTextField(20);
			furnitureCheckBox = new JCheckBox("Furniture");
			electronicCheckBox = new JCheckBox("Electronic");
			
			buttonGroup = new ButtonGroup();
			buttonGroup.add(furnitureCheckBox);
			buttonGroup.add(electronicCheckBox);
			
			daysLabel = new JLabel("Enter number of days");
			daysTextField = new JTextField(20);
			priceLabel = new JLabel("Enter price per day");
			priceTextField = new JTextField(20);
			electronicTypeBox = new JComboBox<String>(electronicType);
			furnitureTypeBox = new JComboBox<String>(furnitureType);
			doneLabel = new JLabel("Click When Done");
			doneButton = new JButton("SUBMIT");

			electronicCheckBox.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event)
				{
					electronicTypeBox.setEnabled(true);
					furnitureTypeBox.setEnabled(false);
				}

			});

			furnitureCheckBox.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent event)
				{
					furnitureTypeBox.setEnabled(true);
					electronicTypeBox.setEnabled(false);

				}
			});

			add(customerNameLabel);
			add(customerNameTextField);
			add(furnitureCheckBox);
			add(electronicCheckBox);
			add(daysLabel);
			add(daysTextField);
			add(priceLabel);
			add(priceTextField);
			add(furnitureTypeBox);
			add(electronicTypeBox);
			add(doneLabel);
			add(doneButton);

			MyListener2 handler = new MyListener2();
			doneButton.addActionListener(handler);

		}

		class MyListener2 implements ActionListener
		
		{
			private String name;
			private String checkBox;
			private int numberOfDays;
			private double pricePerDay;
			private String type;

			public void actionPerformed(ActionEvent event) 	
			{
				name=customerNameTextField.getText();
				
				
				if(furnitureCheckBox.isSelected())
				{
					checkBox=furnitureCheckBox.getText();
					type=furnitureType[furnitureTypeBox.getSelectedIndex()];
				}
				else if(electronicCheckBox.isSelected())
				{
					checkBox=electronicCheckBox.getText();
					type=electronicType[electronicTypeBox.getSelectedIndex()];
				}
				
				if(valid())
				{
					numberOfDays=Integer.parseInt(daysTextField.getText());
					pricePerDay=Double.parseDouble(priceTextField.getText());
					
					if(db.customerExists(name)==true)
					{
						db.writeRentalToDatabase(name, checkBox, type, pricePerDay, numberOfDays);
					
					}	
					else if(db.customerExists(name)==false)
						JOptionPane.showMessageDialog(null,"Customer does not exist.\n Please try again.");
						
						
					customerNameTextField.setText("");
					daysTextField.setText("");
					priceTextField.setText("");
					buttonGroup.clearSelection();
					electronicTypeBox.setSelectedIndex(0);
					furnitureTypeBox.setSelectedIndex(0);
					customerNameTextField.requestFocus();
				}	
				
			}
			
			public boolean valid()
			{
				boolean validData=true;
				
				if(name.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Name cannot be blank");
					customerNameTextField.requestFocus();
					validData=false;
				}
				
				if(!furnitureCheckBox.isSelected()&&!electronicCheckBox.isSelected())
				{
					JOptionPane.showMessageDialog(null, "Check Box not selected");
					validData=false;
				}
				
				if(daysTextField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Days cannot be blank");
					daysTextField.requestFocus();
					validData=false;
				}
				
				if(priceTextField.getText().isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Price cannot be blank");
					priceTextField.requestFocus();
					validData=false;
				}
				
				if(electronicTypeBox.getSelectedIndex()==0&&furnitureTypeBox.getSelectedIndex()==0)
				{
					JOptionPane.showMessageDialog(null, "Furniture and Electronic type cannot be blank");
					validData=false;
				}
				
				if(!daysTextField.getText().isEmpty())
				{
					if(!daysTextField.getText().matches("([1-9]\\d*|\\d\\d+)"))
					{
						JOptionPane.showMessageDialog(null, "Days is not an integer");
						daysTextField.requestFocus();
						daysTextField.setText("");
						validData=false;
					}
				}
				
				if(!priceTextField.getText().isEmpty())
				{
					if(!priceTextField.getText().matches("\\d*\\.\\d\\d"))
					{
						JOptionPane.showMessageDialog(null, "Price is not a double");
						priceTextField.requestFocus();
						priceTextField.setText("");
						validData=false;
					}
				}
				
				return validData;
			}


		}

	}

}
