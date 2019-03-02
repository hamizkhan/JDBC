//Hamiz Khan INSY 4306 Section 002

import java.util.ArrayList;
import java.io.*;
public class Customer implements Serializable
{

	private String name;
	private Address address;
	private String creditCardNumber;
	private ArrayList<Rental> rentalList=new ArrayList<Rental>();
	
	public Customer()
	{
		setName("");
		setAddress(new Address());
		setCreditCardNumber("");
	}
	
	public Customer (String name,Address address,String creditCardNumber)
	{
		setName(name);
		setAddress(address);
		setCreditCardNumber(creditCardNumber);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Address getAddress() 
	{
		return address;
	}

	public void setAddress(Address address) 
	{
		this.address = address;
	}

	public String getCreditCardNumber()
	{
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) 
	{
		this.creditCardNumber = creditCardNumber;
	}
	
	public void addRental(Rental r)
	{
		rentalList.add(r);
	}
	
	public ArrayList<Rental> getRentalList()
	{
		return rentalList;
	}
	
	public String toString()
	{
		return ("Name: "+ getName()+","+" Address: "+getAddress()+ " Credit Card Number: "+getCreditCardNumber()+ " Rental"
				+ "List: " + rentalList+"\n");
			
	}
	
}
