package elixir;

import java.util.List;

import bank.IBank;
import operation.IOperation;

public interface IMediatorELIXIR 
{
	public void registerNewBank(IBank k);
	public int getBanksListCount();
	public void sendIOperationsList(List<IOperation> list, int bankID);
}
