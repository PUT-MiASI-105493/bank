package bank;

import java.util.Date;
import java.util.List;

public class COperationTransfer implements IOperation
{
    private double amount;
    CAccount dest;
    public Date dateOrder;
    public boolean disposed;

    public COperationTransfer(CAccount to, double amount, Date order)
    {
        this.amount = amount;
        this.dest = to;
        this.disposed = false;
        dateOrder = order;
    }

    public double getAmount()
    {
    	return amount;
    }
    
    public void execute(CAccount from)
    {
        from.substrMoney(amount);
        dest.addMoney(amount);
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
