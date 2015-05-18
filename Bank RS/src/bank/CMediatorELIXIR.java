package bank;

import java.util.HashMap;
import java.util.List;

import com.google.inject.Singleton;

@Singleton
public class CMediatorELIXIR implements IMediatorELIXIR
{
    private HashMap<Integer, IBank> banks = new HashMap<Integer, IBank>();
    
    public void registerNewBank(IBank k) {
        k.registerMediatorELIXIR(this);
        banks.put(k.GetBankID(), k);
    }
 
    public void sendIOperationsList(List<IOperation> operationsList, int destinationBankID) 
    {	
    	IBank b = getBank(destinationBankID);
    	b.recieveIOperationList(operationsList);
    }
    
    private IBank getBank(int destinationBankID)
    {
    	return banks.get(destinationBankID);
    }
    
    public int getBanksListCount()
    {
    	return banks.size();
    }
}
