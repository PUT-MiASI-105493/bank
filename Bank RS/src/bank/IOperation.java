package bank;

public interface IOperation 
{
	void execute(IAccount acc);
	
    public double getAmount();
	public abstract boolean accept(IRaport v);
	public void Dispose(boolean state);
	public boolean getDispose();
}
