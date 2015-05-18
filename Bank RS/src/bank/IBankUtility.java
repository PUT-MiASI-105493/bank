package bank;

import java.util.List;

public interface IBankUtility 
{
    boolean CheckAccID(int id);
    IAccount GetAccount(int id);
    int GetBankID();
}
