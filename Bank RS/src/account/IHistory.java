package account;

import java.util.List;

import operation.IOperation;

public interface IHistory 
{
	void AddToHistory(IOperation co);
	public List<IOperation> GetOperations();
}
