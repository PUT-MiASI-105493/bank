package bank;

import DI.AppInjector;
import DI.TestInjector;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.assistedinject.Assisted;

public class CAccount implements IAccount
{
	private Injector inject;
    protected int accountID;
    protected double saldo;
    private int ownerID;
    protected IHistory history;
    public IAccountState state;

    @Inject
    public CAccount()
    {
        this.saldo = 0;

        state = new CAccountStateA();
        setHistory();
    }
    
    public void setAccountID(int id)
    {
    	this.accountID=id;
    }
    
    public void setOwnerID(int id)
    {
    	this.ownerID=id;
    }
    
    private void setHistory()
    {
    	Injector inject = Guice.createInjector(new AppInjector());	
    	this.history = inject.getInstance(IHistory.class);
    }

    public void SetState(IAccountState newState)
    {
        if(newState != null)
        	state = newState;
    }

    public IAccountState getState()
    {
    	return this.state;
    }
    
    public void request()
    {
        this.saldo += state.setInterest(this);
    }

    public double GetBalance()
    {
        return this.saldo; 
    }

    public int GetAccountID()
    {
        return this.accountID;
    }

    public CHistory GetHistory()
    {
        return (CHistory)this.history;
    }

    public void addMoney(double amount)
    {
        this.saldo += amount;
    }


    public void substrMoney(double amount)
    {
        this.saldo -= amount;
    }

    public void DoOperation(IOperation oper)
    {
    	history.AddToHistory(oper);
        oper.execute(this);
    }
    
    //Do dekoratora
    public boolean WithDraw(double amount, boolean accountOperation)
    {
        COperation operation = null;
        if (amount < this.saldo)
        {
            saldo -= amount;
            if (accountOperation)
            {
                operation = new COperation(this.accountID, this.accountID, amount);
            }
            return true;
        }
        return false;
    }
    
    //Do dekoratora
    public void PayIn(double amount)
    {
        COperation operation= null;
        if (amount > 0)
        {
            this.saldo += amount;
            operation = new COperation(this.accountID, this.accountID, amount);
        }
    }
}
