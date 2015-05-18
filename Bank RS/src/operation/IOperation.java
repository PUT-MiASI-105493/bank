package operation;

import account.IAccount;
import raport.IRaport;

public interface IOperation 
{
	void execute(IAccount acc);
	
    public double getAmount();
	public abstract boolean accept(IRaport v);
	public void Dispose(boolean state);
	public boolean getDispose();
}
