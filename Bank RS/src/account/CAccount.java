package account;

import java.util.Calendar;

import operation.COperationPayIn;
import operation.COperationWithDraw;
import operation.IOperation;
import DI.AppInjector;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class CAccount implements IAccount
{
    protected int accountID;
    public double saldo;
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
        if (amount < this.saldo)
        {
            saldo -= amount;
            if (accountOperation)
            {
            	IOperation operation = new COperationWithDraw(amount, Calendar.getInstance().getTime());
            	DoOperation(operation);
            }
            return true;
        }
        return false;
    }
    
    //Do dekoratora
    public void PayIn(double amount)
    {
        if (amount > 0)
        {
        	IOperation operation = new COperationPayIn(amount, Calendar.getInstance().getTime());
        	DoOperation(operation);
        }
    }
}
