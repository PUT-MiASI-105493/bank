package account;

import java.util.Calendar;

import operation.COperationWithDraw;
import operation.IOperation;

public class CDekoratorDebet implements IAccount 
{
	private CAccount ac;
	private double debit;
	
	public CDekoratorDebet(IAccount p)
	{
		ac = (CAccount)p;
		debit = 1000;
	}
	
    public void PayIn(double amount)
    {
    	ac.PayIn(amount);
    }
    
    public boolean WithDraw(double amount, boolean accountOperation)
    {
        if (amount < ac.saldo + debit)
        {
            ac.saldo -= amount;
            if (accountOperation)
            {
            	IOperation operation = new COperationWithDraw(amount, Calendar.getInstance().getTime());
            	ac.DoOperation(operation);
            }
            return true;
        }
        return false;
    }
    
    public double GetBalance()
    {
    	return ac.GetBalance();
    }


	@Override
	public void SetState(IAccountState newState) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public IAccountState getState() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void request() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public int GetAccountID() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public CHistory GetHistory() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void addMoney(double amount) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void substrMoney(double amount) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void DoOperation(IOperation oper) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setAccountID(int id) {
		// TODO Auto-generated method stub
		
	}   
}
