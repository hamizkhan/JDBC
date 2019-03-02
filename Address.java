//Hamiz Khan INSY 4306 Section 002
import java.io.Serializable;

public class Address implements Serializable
{
	private String street;
	private String state;
	private String city;
	private String zip;
	
	public Address()
	{
		setStreet("");
		setState("");
		setCity("");
		setZip("");
	}
	
	public Address(String street,String city,String state,String zip)
	{
		setStreet(street);
		setState(state);
		setCity(city);
		setZip(zip);
	}
	
	public void setStreet(String street)
	{
		this.street=street;
	}
	
	public String getStreet()
	{
		return street;
	}
	
	public void setState(String state)
	{
		this.state=state;
	}
	
	public String getState()
	{
		return state;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getZip() 
	{
		return zip;
	}

	public void setZip(String zip) 
	{
		this.zip = zip;
	}
	
	public String toString()
	{
		return ("Street: "+street+","+" City: "+city+","+" State: "+state+","+" Zip: "+zip+",");
	}
	
	
	
}
