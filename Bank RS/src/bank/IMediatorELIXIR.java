package bank;

import java.util.List;

public interface IMediatorELIXIR 
{
	public void registerNewBank(IBank k);
	public int getBanksListCount();
	public void sendIOperationsList(List<IOperation> list, int bankID);
}
