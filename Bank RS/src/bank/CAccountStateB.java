package bank;

public class CAccountStateB implements IAccountState
{
	double intrests_1;
	double intrests_2;
	double intrests_3;

	double threshold_1;
	double threshold_2;

    public CAccountStateB()
    {
    	intrests_1 = 0.1;
    	intrests_2 = 0.2;
    	intrests_3 = 0.3;

        threshold_1 = 1000;
        threshold_2 = 5000;
    }

    public double setInterest(IAccount account)
    {
    	double balance = account.GetBalance();

        if (balance < threshold_1)
        {
            return balance * intrests_1;
        }
        else if (balance > threshold_1 && balance < threshold_2)
        {
            return balance * intrests_2;
        }
        else
            return balance * intrests_3;
    }
}
