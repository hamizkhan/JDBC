//Hamiz Khan INSY 4306 Section 002

public class FurnitureRental extends Rental

{

	public enum FurnitureType
	{
		BED, COUCH, CHAIR
	}
	
	private FurnitureType fType;
	
	public FurnitureRental()
	{
		super();
		setFType(null);
	}
	
	public FurnitureRental(double pricePerDay, int numberOfDays, FurnitureType fType)
	{
		super(pricePerDay,numberOfDays);
		setFType(fType);
	}	
	
	public void setFType(FurnitureType fType)
	{
		this.fType=fType;
	}
	
	public FurnitureType getFType()
	{
		return fType;
	}
	
	@Override
	public double calculateCharge()
	{
		double total=0.0;
		total=getPricePerDay()*getNumberOfDays();
		
		if(fType==FurnitureType.BED||fType==FurnitureType.CHAIR||fType==FurnitureType.COUCH)
		{
			total=total-(total*0.05);
		}
		
		return total;
	}
	
	public String toString()
	{
		return (super.toString()+" Furniture Type "+fType);
	}
		
}
