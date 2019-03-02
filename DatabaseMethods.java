//Hamiz Khan INSY 4306 Section 002

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JOptionPane;

public class DatabaseMethods {

	private Connection connection;
	private ResultSet resultSet;
	private ResultSetMetaData metaData;
	private Statement statement;
	private int numberOfColumns;
	private PreparedStatement insertCustomer = null;
	private PreparedStatement insertRental = null;

	public void connectToDatabase() 
	{

		try {
			connection = DriverManager.getConnection("jdbc:derby:books", "davis", "davis");
			statement = connection.createStatement();
			insertCustomer = connection.prepareStatement( "INSERT INTO Customer(Name,Street,City,State,Zip,CreditCardNumber)" + "VALUES(?,?,?,?,?,?)");
			insertRental = connection.prepareStatement("INSERT INTO RENTAL(Name,RentalType,EnumType,Rate,Days)" + "VALUES(?,?,?,?,?)");
			}

		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}
	}

	public void writeCustomerToDatabase(String name, String street, String city, String state, String zip, String cc) 
	{
		try {
			if (customerExists(name) == false) 
			{

				insertCustomer.setString(1, name);

				insertCustomer.setString(2, street);
				insertCustomer.setString(3, city);
				insertCustomer.setString(4, state);
				insertCustomer.setString(5, zip);
				insertCustomer.setString(6, cc);

				insertCustomer.executeUpdate();
			}

			else if (customerExists(name) == true)
			{
				JOptionPane.showMessageDialog(null, "Customer already exits.\nPlease try again.");
			}
		}

		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}

	}

	public void writeRentalToDatabase(String name, String rentalType, String enumType, double rate, int numberOfDays) 
	{

		try 
		{
			if (customerExists(name) == true) 
			{
				insertRental.setString(1, name);
				insertRental.setString(2, rentalType);
				insertRental.setString(3, enumType);
				insertRental.setDouble(4, rate);
				insertRental.setInt(5, numberOfDays);

				insertRental.executeUpdate();
			}

			else if (customerExists(name) == false)
				JOptionPane.showMessageDialog(null, "Customer does not exist.\n Please try again.");
		}

		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
		}
	}

	public void writeCustomerTable() 
	{
		try 
		{

			resultSet = statement.executeQuery("SELECT * FROM Customer");
			metaData = resultSet.getMetaData();
			numberOfColumns = metaData.getColumnCount();

			System.out.println("Customer Table\n");

			for (int i = 1; i <= numberOfColumns; i++) 
			{
				System.out.printf("%-8s\t", metaData.getColumnName(i));
			}

			System.out.println();

			while (resultSet.next()) 
			{
				for (int i = 1; i <= numberOfColumns; i++)
					System.out.printf("%-8s\t", resultSet.getObject(i));
				System.out.println();

			}
		}

		catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}

	}

	public void writeRentalTable()
	{
		try {
			resultSet = statement.executeQuery("SELECT * FROM Rental");
			metaData = resultSet.getMetaData();
			numberOfColumns = metaData.getColumnCount();

			System.out.println("Rental Table\n");

			for (int i = 1; i <= numberOfColumns; i++)
				System.out.printf("%-8s\t", metaData.getColumnName(i));
			System.out.println();

			while (resultSet.next())
			{
				for (int i = 1; i <= numberOfColumns; i++) 
				{
					switch (metaData.getColumnType(i))
					{
					case Types.INTEGER:
						System.out.printf("%-8s\t", resultSet.getInt(i));
						break;
					case Types.VARCHAR:
						System.out.printf("%-8s\t", resultSet.getString(i));
						break;
					case Types.DOUBLE:
						System.out.printf("%-8s\t", resultSet.getDouble(i));

					}

				}
				System.out.println();
			}

		}

		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
		}
	}

	public boolean customerExists(String name) 
	{
		String n = "";
		try 
		{
			resultSet = statement.executeQuery("SELECT NAME FROM Customer");
			metaData = resultSet.getMetaData();
			numberOfColumns = metaData.getColumnCount();

			while (resultSet.next())
			{
				for (int i = 1; i <= numberOfColumns; i++)
					n = resultSet.getString(i);
				if (name.equals(n))
					return true;
			}
			System.out.println();

		} catch (SQLException sqle)
		{
			sqle.printStackTrace();
		}

		return false;
	}

	public String calculateCharge() 
	{
		double rate = 0;
		int days = 0;
		double total = 0.0;
		double grandTotal = 0.0;
		String name = "";
		String cNames = "";
		String cTotal = "";
		StringBuilder result=new StringBuilder();
		String customers[];
		String rates[];

		try 
		{
			resultSet = statement.executeQuery("SELECT * FROM Rental");
			metaData = resultSet.getMetaData();
			numberOfColumns = metaData.getColumnCount();
			
			while (resultSet.next())

			{

				for (int i = 1; i <= numberOfColumns; i++)
				{
					if (metaData.getColumnName(i).equals("NAME")) 
					{
						name = resultSet.getString(i);
					}
				}

				for (int i = 1; i <= numberOfColumns; i++) 
				{

					if (metaData.getColumnName(i).equals("DAYS"))
					{
						days = resultSet.getInt(i);
					}

					if (metaData.getColumnName(i).equals("RATE"))
					{
						rate = resultSet.getDouble(i);
					}
				}

				total = rate * days;

				for (int i = 1; i <= numberOfColumns; i++) 
				{
					if (metaData.getColumnName(i).equals("ENUMTYPE"))
					{
						if (resultSet.getString(i).equals("BED") || resultSet.getString(i).equals("CHAIR") || resultSet.getString(i).equals("COUCH"))
						{
							total = total - (total * 0.05);
						} else if (resultSet.getString(i).equals("COMPUTER"))
						{
							total *= 1.15;
						}
					}
				}
				cTotal += total + ",";
				cNames += name + ",";
			}
			
			customers = cNames.split(",");
			rates = cTotal.split(",");

			result = new StringBuilder();
			for (int i = 0; i < customers.length; i++) 
			{
				grandTotal = 0.0;

				if (result.indexOf(customers[i]) == -1)
				{

					for (int j = 0; j < customers.length; j++) 
					{

						if (customers[i].equals(customers[j])) 
						{
							grandTotal += Double.parseDouble(rates[j]);
						}
					}
					result.append(String.format("%s:\t $%.2f%n", customers[i], grandTotal));

				}
			}

		}

		catch (SQLException sqle) 
		{
			sqle.printStackTrace();
		}

		return result.toString();

	}

}
