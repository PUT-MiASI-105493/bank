package operation;

import java.util.Date;

import account.IAccount;
import raport.IRaport;

public class COperationWithDraw implements IOperation
{
    private double amount;
    public Date dateOrder;
    public boolean disposed;

    public COperationWithDraw(double amount, Date order)
    {
        this.amount = amount;
        this.disposed = false;
        dateOrder = order;
    }
    
    public double getAmount()
    {
    	return amount;
    }

    public void execute(IAccount acc)
    {
    	if(acc.GetBalance()>amount)
    		acc.substrMoney(amount);
    }
    
    public boolean accept(IRaport v)
    {
    	return v.visit(this);
    }
    
    public void Dispose(boolean q)
    {
    	disposed = q;
    }
    
    public boolean getDispose()
    {
    	return disposed;
    }
}
