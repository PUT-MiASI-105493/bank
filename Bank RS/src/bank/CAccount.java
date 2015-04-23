package bank;

public class CAccount implements IAccount{
    protected int accountID;
    protected double saldo;
    private int ownerID;
    protected CHistory history;
    protected ITransferUtility transferUtility;
    public IAccountState state;

    public CAccount(int id, int ownerID, ITransferUtility transferUtil)
    {
        this.history = new CHistory();
        this.saldo = 0;
        this.accountID = id;
        this.ownerID = ownerID;
        this.transferUtility = transferUtil;

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

    public void PayIn(double amount)
    {
        COperation operation= null;
        if (amount > 0)
        {
            this.saldo += amount;
            operation = new COperation(this.accountID, this.accountID, amount);
            this.history.AddToHistory(operation);
        }
        //else
          //  throw new Exception("Amount must be a positive value.");
    }

    public boolean WithDraw(double amount, boolean accountOperation)
    {
        COperation operation = null;
        if (amount < this.saldo)
        {
            saldo -= amount;
            if (accountOperation)
            {
                operation = new COperation(this.accountID, this.accountID, amount);
                this.history.AddToHistory(operation);
            }
            return true;
        }
        return false;
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

    public void Transfer(int destinationID, double amount)
    {
        COperation operation = new COperation(this.accountID, destinationID, amount);
        if (this.WithDraw(amount, false))
        {
            this.transferUtility.AddOperation(operation);
            this.history.AddToHistory(operation);
        }
       // else
       //     throw new Exception("Amount of money not available");
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
        oper.execute(this);
    }
}
