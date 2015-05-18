package raport;

import operation.COperationPayIn;
import operation.COperationTransfer;
import operation.COperationWithDraw;

public interface IRaport 
{
	public boolean visit(COperationPayIn p);
	public boolean visit(COperationTransfer p);
	public boolean visit(COperationWithDraw p);
}
