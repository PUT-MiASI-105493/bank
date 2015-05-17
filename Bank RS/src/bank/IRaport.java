package bank;

public interface IRaport 
{
	public boolean visit(COperationPayIn p);
	public boolean visit(COperationTransfer p);
}
