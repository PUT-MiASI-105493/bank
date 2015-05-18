package bank;

import java.util.List;

public interface IHistory 
{
	void AddToHistory(IOperation co);
	public List<IOperation> GetOperations();
}
