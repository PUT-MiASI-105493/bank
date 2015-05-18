package bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

import DI.AppInjector;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class CBank implements IBank, IBankUtility
{	
    private List<IAccount> accounts;
    private List<ICustomer> customers;
    private List<IOperation> operations;
    private List<IOperation> operationsFromOtherBank;
    private int id;
    private IMediatorELIXIR mediator;
    
	CChainFilterHandle chainBegin;
	CChainFilterHandle chainEnd;

	@Inject
	public CBank()
    {
        this.accounts = new ArrayList<IAccount>();
        this.customers = new ArrayList<ICustomer>();
        this.operations = new ArrayList<IOperation>();

        setChainFilter();
    }
		
    public void setMediator(IMediatorELIXIR med)
    {
    	this.mediator = med;
    }
	
    public void setId(int id)
    {
    	this.id = id;
    }
	
    
    public void registerMediatorELIXIR(CMediatorELIXIR mediator)
    {
    	this.mediator = mediator; 
    }
 
    public void sendIOperationListToELIXIR(List<IOperation> operationsToSend, int destinationBank)
    {
        mediator.sendIOperationsList(operationsToSend, destinationBank); // Rzeczywista komunikacja odbywa siê za poœrednictwem mediatora!!!
    }
 
    public void recieveIOperationList(List<IOperation> operationsRecieved) 
    {
        this.operationsFromOtherBank = operationsRecieved;
    	return;
    }
    
    public List<IOperation> getOperationsFromOtherBank()
    {
    	return this.operationsFromOtherBank;
    }

    public List<ICustomer> GetCustomerList()
    {
        return this.customers;
    }

    public int CreateAccountForClient(ICustomer client, IAccountState state)
    {
    	int id= accounts.size() + 1;
    	
    	Injector in = Guice.createInjector(new AppInjector());
		IAccount account = in.getInstance(IAccount.class);
		account.setAccountID(id);
		account.setOwnerID(client.GetCustomerID());
    	accounts.add(account);
    	
    	account.SetState(state);
    	return id;
    }

    public ICustomer AddCustomer(String name, String surname, int id)
    {
    	Injector in = Guice.createInjector(new AppInjector());
    	ICustomer c = in.getInstance(ICustomer.class);
		c.setName(name);
		c.setSurname(surname);
		c.setID(id);
    	
        this.customers.add(c);
        return c;
    }

    public ICustomer GetCustomer(int id)
    {
        for(ICustomer v : this.customers)
        {
            if (v.GetCustomerID() == id)
                return v;
        }
        return null;
    }

    public boolean CheckAccID(int id)
    {
        for(IAccount v : this.accounts)
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

    public IAccount GetAccount(int id)
    {
        for(IAccount v : this.accounts)
        {
            if (v.GetAccountID() == id)
                return v;
        }
        return null;
    }

    public void PayIn(IAccount acc, double amount)
    {
        IOperation oper = new COperationPayIn(amount, Calendar.getInstance().getTime());
        acc.DoOperation(oper);
        operations.add(oper);
        runChainFilter(oper);
    }

    public void Transfer(IAccount from, IAccount to, double amount)
    {
        IOperation oper = new COperationTransfer(to, amount, Calendar.getInstance().getTime());
        from.DoOperation(oper);
        operations.add(oper);
        runChainFilter(oper);
    }
    
    public List<IOperation> getOperations()
    {
    	return this.operations;
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
    
    public boolean tryWithDrawDecorator(double money, int accountID)
    {
    	boolean flag = true;
    	int i=0;
    	IAccount ac = null;;
    	while(flag || i==accounts.size())
    	{
    		if(accounts.get(i).GetAccountID() == accountID)
    		{
    			flag = false;
    			ac = accounts.get(i);
    		}
    		else
    			i+=1;
    	}
    	
    	return new CDekoratorDebet(ac).WithDraw(money, true);	
    }
    
    public void setChainFilter()
    {
    	chainBegin = new CChainFilterHighIOperation(2000);
    	chainEnd = new CChainFilterSameOperations();
 
        chainBegin.setSuccesor(chainEnd);
    }
    
    public void runChainFilter(IOperation o)
    {
    	chainBegin.handle(o);
    }
    
    public CChainFilterHandle getChainBegin()
    {
    	return this.chainBegin;
    }
    
    public CChainFilterHandle getChainEnd()
    {
    	return this.chainEnd;
    }

	@Override
	public boolean tryWithDrawNormal(double money, int accountID) 
	{
	    	boolean flag = true;
	    	int i=0;
	    	IAccount ac = null;;
	    	while(flag || i==accounts.size())
	    	{
	    		if(accounts.get(i).GetAccountID() == accountID)
	    		{
	    			flag = false;
	    			ac = accounts.get(i);
	    		}
	    		else
	    			i+=1;
	    	}
	    	
	    	return ac.WithDraw(money, true);    	
	}
    
}
