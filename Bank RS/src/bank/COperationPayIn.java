package bank;

import java.util.Date;
import java.util.List;

public class COperationPayIn implements IOperation
{
    private double amount;
    public Date dateOrder;
    public boolean disposed;

    public COperationPayIn(double amount, Date order)
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
        acc.addMoney(amount);
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
