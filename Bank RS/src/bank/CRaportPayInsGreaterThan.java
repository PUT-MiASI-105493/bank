package bank;

public class CRaportPayInsGreaterThan  implements IRaport
{
	public double value;
	
	public CRaportPayInsGreaterThan(double valueM)
	{
		this.value = valueM;
	}
	
	public boolean visit(COperationPayIn p) 
	{
		if(p.getAmount()>value)
			return true;
		else
			return false;
	}

	public boolean visit(COperationTransfer p) 
	{
		return false;
	}
}
