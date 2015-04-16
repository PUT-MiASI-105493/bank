package bank;

public class CAccountStateA implements IAccountState
{
    double intrests;

    public CAccountStateA()
    {
    	intrests = 0.01;
    }

    public double setInterest(CAccount account)
    {
    	double balance = account.GetBalance();
        return balance * intrests;
    }
}
