package bank;

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
        COperation operation = null;
        if (amount < ac.saldo + debit)
        {
            ac.saldo -= amount;
            if (accountOperation)
            {
                operation = new COperation(ac.accountID, ac.accountID, amount);
            }
            return true;
        }
        return false;
    }
    
    public double GetBalance()
    {
    	return ac.GetBalance();
    }
    
}
