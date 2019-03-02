//Hamiz Khan INSY 4306 Section 002
public class ElectronicRental extends Rental 
	{
		public enum ElectronicType 
		{
			COMPUTER, TV
		}
		private ElectronicType eType;
		
		public ElectronicRental()
		{
			super();
			setEType(null);
		}
		
		public ElectronicRental(double pricePerDay,int numberOfDays,ElectronicType eType)
		{
			super(pricePerDay, numberOfDays);
			setEType(eType);
		}
		
		
		public void setEType(ElectronicType eType)
		{
			this.eType=eType;
		}
		
		public ElectronicType getEType()
		{
			return eType;
		}
		
		@Override
		public double calculateCharge()
		{
			double total=0.0;
			total=getPricePerDay()*getNumberOfDays();
			
			if(eType==ElectronicType.COMPUTER)
				total*=1.15;
			return total;
		}
		
		public String toString()
		{
			return (super.toString()+" Electronic Type "+eType);
		}
		
		
	}	
		
		
		
		