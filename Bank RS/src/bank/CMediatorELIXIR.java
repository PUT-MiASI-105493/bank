package bank;

import java.util.HashMap;
import java.util.List;

public class CMediatorELIXIR implements IMediatorELIXIR
{
    private HashMap<Integer, CBank> banks = new HashMap<Integer, CBank>();
    
    public void registerNewBank(CBank k) {
        k.registerMediatorELIXIR(this);
        banks.put(k.GetBankID(), k);
    }
 
    public void sendIOperationsList(List<IOperation> operationsList, int destinationBankID) 
    {	
    	CBank b = getBank(destinationBankID);
    	b.recieveIOperationList(operationsList);
    }
    
    private CBank getBank(int destinationBankID)
    {
    	return banks.get(destinationBankID);
    }
    
    public int getBanksListCount()
    {
    	return banks.size();
    }
}
