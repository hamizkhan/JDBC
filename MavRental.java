//Hamiz Khan INSY 4306 Section 002

import java.io.Serializable;
import java.util.*;

public class MavRental implements Invoice,Serializable
{
	DatabaseMethods db=new DatabaseMethods();
	private ArrayList<Customer> customerList=new ArrayList<Customer>();

	public MavRental()
	{
		setCustomerList(new ArrayList<Customer>());
		db.connectToDatabase();
	}
	public MavRental(ArrayList<Customer> customerList) 
	{
		setCustomerList(customerList);
	}
	
	public void setCustomerList(ArrayList<Customer> customerList)
	{
		this.customerList=customerList;
	}
	
	public ArrayList<Customer> getCustomerList()
	{
		return customerList;
	}
	
	public void addCustomer(Customer c)
	{
		customerList.add(c);
	}
	
	public boolean customerExists(String name)
	{
		
		String n;
				for(Customer c:customerList)
				{
					n=c.getName();
					if(n.equals(name))
						return true;
				}
				
				return false;
	}
	
	@Override
	public String printInvoice()
	{
	
		return db.calculateCharge();
			
	}
	
	@Override
	public String toString()
	{
		return "Customer List: " + customerList;
	}
	
	
	
	
	
	
}
