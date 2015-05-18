package raport;

import java.util.Date;

import operation.COperationPayIn;
import operation.COperationTransfer;
import operation.COperationWithDraw;

public class CRaportTransferOnly implements IRaport
{
	public Date dateFrom;
	
	public CRaportTransferOnly(Date dat)
	{
		this.dateFrom = dat;
	}
	
	public boolean visit(COperationPayIn p) 
	{
		return false;
	}

	public boolean visit(COperationTransfer p) 
	{
		if(p.dateOrder.after(dateFrom))
			return true;
		else
			return false;
	}
	
	public boolean visit(COperationWithDraw p) 
	{
		return false;
	}
}
