package bank;

import java.util.List;

public interface IMediatorELIXIR 
{
	public void sendIOperationsList(List<IOperation> list, int bankID);
}
