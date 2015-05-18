package raport;

import operation.COperationPayIn;
import operation.COperationTransfer;
import operation.COperationWithDraw;

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
	
	public boolean visit(COperationWithDraw p) 
	{
		return false;
	}
}
