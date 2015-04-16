package bank;

import java.util.Date;
import java.util.List;

public class COperationPayIn implements IOperation
{
    private double amount;
    public Date dateOrder;

    public COperationPayIn(double amount, Date order)
    {
        this.amount = amount;
        dateOrder = order;
    }
    
    public double getAmount()
    {
    	return amount;
    }

    public void execute(CAccount acc)
    {
        acc.addMoney(amount);
    }
    
    public boolean accept(IRaport v)
    {
    	return v.visit(this);
    }
}
