package bank;

public class CAccount implements IAccount{
    protected int accountID;
    protected double saldo;
    private int ownerID;
    protected CHistory history;
    public IAccountState state;

    public CAccount(int id, int ownerID)
    {
        this.history = new CHistory();
        this.saldo = 0;
        this.accountID = id;
        this.ownerID = ownerID;

        state = new CAccountStateA();
    }

    public void SetState(IAccountState newState)
    {
        if(newState != null)
        	state = newState;
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
        return this.history;
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
        //else
          //  throw new Exception("Amount must be a positive value.");
    }
}
