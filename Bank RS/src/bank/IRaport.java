package bank;

import java.util.List;

public interface IRaport 
{
	public boolean visit(COperationPayIn p);
	public boolean visit(COperationTransfer p);
}
