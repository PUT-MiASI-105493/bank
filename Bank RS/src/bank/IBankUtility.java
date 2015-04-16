package bank;

import java.util.List;

public interface IBankUtility 
{
    boolean CheckAccID(int id);
    CAccount GetAccount(int id);
    int GetBankID();
    void AddTransfer(List<COperation> op);
}
