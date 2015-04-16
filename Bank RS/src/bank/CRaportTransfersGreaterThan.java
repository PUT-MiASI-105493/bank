package bank;

public class CRaportTransfersGreaterThan implements IRaport
{
	public double value;
	
	public CRaportTransfersGreaterThan(double valueM)
	{
		this.value = valueM;
	}
	
	public boolean visit(COperationPayIn p) 
	{
		return false;
	}

	public boolean visit(COperationTransfer p) 
	{
		if(p.getAmount()>value)
			return true;
		else
			return false;
	}
}
