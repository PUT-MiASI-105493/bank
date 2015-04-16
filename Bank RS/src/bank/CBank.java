package bank;

import java.util.ArrayList;
import java.util.List;

public class CBank implements IBank, IBankUtility{
    private List<CAccount> accounts;
    private List<CCustomer> customers;
    private List<COperation> transfer;
    private CTransferManager transferManager;
    private int id;

    public CBank(int id, IKIRutility ku)
    {
        this.transferManager = new CTransferManager(this, ku);
        this.transfer = new ArrayList<COperation>();
        this.accounts = new ArrayList<CAccount>();
        this.customers = new ArrayList<CCustomer>();
        this.id = id;
    }

    public void StoreAccount(int id, int ownerID, ITransferUtility transferUtil, IAccountState type)
    {
    	CAccount nc = new CAccount(id, ownerID, transferUtil);
    	nc.SetState(type);
        this.accounts.add(nc);

        CCustomer c = GetCustomer(ownerID);
        c.AddAccount(id);
    }

    public List<CCustomer> GetCustomerList()
    {
        return this.customers;
    }

    public CTransferManager GetTransferManager()
    {
        return this.transferManager;
    }

    public CCustomer AddCustomer(String name, String surname, int id)
    {
        CCustomer c = new CCustomer(name, surname, id);
        this.customers.add(c);
        return c;
    }

    public void AddTransfer(List<COperation> transfer)
    {
        if (transfer != null)
        {
            this.transferManager.makeTransfer(transfer);
        }
        //else
            //throw new Exception("Transfer failed");
    }

    public CCustomer GetCustomer(int id)
    {
        for(CCustomer v : this.customers)
        {
            if (v.GetCustomerID() == id)
                return v;
        }
        return null;
    }

    public boolean CheckAccID(int id)
    {
        for(CAccount v : this.accounts)
        {
            if (v.GetAccountID() == id)
                return false;
        }
        return true;
    }

    public int GetBankID()
    {
        return this.id;
    }

    public CAccount GetAccount(int id)
    {
        for(CAccount v : this.accounts)
        {
            if (v.GetAccountID() == id)
                return v;
        }
        return null;
    }


    public void PayIn(CAccount acc, double amount)
    {
        IOperation oper = new COperationPayIn(amount);
        acc.DoOperation(oper);
    }

    public void Transfer(CAccount from, CAccount to, double amount)
    {
        IOperation oper = new COperationTransfer(to, amount);
        from.DoOperation(oper);
    }
}
