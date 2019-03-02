//Hamiz Khan INSY 4306 Section 002

import java.io.Serializable;

public abstract class Rental implements Serializable
{

	private double pricePerDay;
	private int numberOfDays;
	
	public Rental()
	{
		setPricePerDay(0.0);
		setNumberOfDays(0);
	}
	
	public Rental(double pricePerDay,int numberOfDays)
	{
		setPricePerDay(pricePerDay);
		setNumberOfDays(numberOfDays);
		
	}
	
	public void setPricePerDay(double pricePerDay)
	{
		this.pricePerDay=pricePerDay;
	}
	
	public double getPricePerDay()
	{
		return pricePerDay;
	}
	
	public void setNumberOfDays(int numberOfDays)
	{
		this.numberOfDays=numberOfDays;
	}
	
	public int getNumberOfDays()
	{
		return numberOfDays;
	}
	
	public abstract double calculateCharge();
	
	public String toString()
	{
		return ("Price Per Day " +getPricePerDay()+ " Number Of Days "+getNumberOfDays());
	}
}
