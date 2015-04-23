package bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class CBank implements IBank, IBankUtility{
    private List<CAccount> accounts;
    private List<CCustomer> customers;
    private CTransferManager transferManager;
    private List<IOperation> operations;
    private int id;
<<<<<<< HEAD
    private CMediatorELIXIR mediator;
=======
>>>>>>> 103288f5796feae60bb4a43a42d4d57666207e69

    public CBank(int id, IKIRutility ku)
    {
        this.transferManager = new CTransferManager(this, ku);
        this.accounts = new ArrayList<CAccount>();
        this.customers = new ArrayList<CCustomer>();
        this.operations = new ArrayList<IOperation>();
        this.id = id;
    }
<<<<<<< HEAD
    
    
    public void registerMediatorELIXIR(CMediatorELIXIR mediator)
    {
    	this.mediator = mediator; 
    }
 
    public void sendIOperationListToELIXIR(List<IOperation> operationsToSend, int destinationBank)
    {
        mediator.sendIOperationsList(operationsToSend, destinationBank); // Rzeczywista komunikacja odbywa siê za poœrednictwem mediatora!!!
    }
 
    public void recieveIOperationList(List<IOperation> operationsRecieved) {
        
    	return;
    }
    
=======
>>>>>>> 103288f5796feae60bb4a43a42d4d57666207e69

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
        CCustomer c = new CCustomer(name, surname, id, this);
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
        IOperation oper = new COperationPayIn(amount, Calendar.getInstance().getTime());
        acc.DoOperation(oper);
        operations.add(oper);
    }

    public void Transfer(CAccount from, CAccount to, double amount)
    {
        IOperation oper = new COperationTransfer(to, amount, Calendar.getInstance().getTime());
        from.DoOperation(oper);
        operations.add(oper);
    }
    
    public List<IOperation> getTransfersFromDate(Date dateFrom)
    {
 	   IRaport rep = new CRaportTransferOnly(dateFrom);
 	   List<IOperation> ret = new ArrayList<IOperation>();
 	   for(IOperation v : operations)
 		   if(v.accept(rep))
 			   ret.add(v);
 	   return ret;   
    }
    
    public List<IOperation> getPayInsGreatherThan(double payIn)
    {
 	   IRaport rep = new CRaportPayInsGreaterThan(payIn);
 	   List<IOperation> ret = new ArrayList<IOperation>();
 	   for(IOperation v : operations)
 		   if(v.accept(rep))
 			   ret.add(v);
 	   return ret;   
    }
    
    public List<IOperation> getTransfersGreatherThan(double payIn)
    {
 	   IRaport rep = new CRaportTransfersGreaterThan(payIn);
 	   List<IOperation> ret = new ArrayList<IOperation>();
 	   for(IOperation v : operations)
 		   if(v.accept(rep))
 			   ret.add(v);
 	   return ret;   
    }
    
    public boolean tryWithDrawNormal(double money, int accountID)
    {
    	boolean flag = true;
    	int i=0;
    	CAccount ac = null;;
    	while(flag || i==accounts.size())
    	{
    		if(accounts.get(i).accountID == accountID)
    		{
    			flag = false;
    			ac = accounts.get(i);
    		}
    		else
    			i+=1;
    	}
    	
    	return ac.WithDraw(money, true);
    	
    }
    
    public boolean tryWithDrawDecorator(double money, int accountID)
    {
    	boolean flag = true;
    	int i=0;
    	CAccount ac = null;;
    	while(flag || i==accounts.size())
    	{
    		if(accounts.get(i).accountID == accountID)
    		{
    			flag = false;
    			ac = accounts.get(i);
    		}
    		else
    			i+=1;
    	}
    	
    	return new CDekoratorDebet(ac).WithDraw(money, true);	
    }
    
}
