package bank;

import java.util.List;

public interface IOperation 
{
	void execute(CAccount acc);
	
	public abstract boolean accept(IRaport v);
}
