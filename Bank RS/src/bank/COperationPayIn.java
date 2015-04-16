package bank;

public class COperationPayIn implements IOperation
{
    private double amount;

    public COperationPayIn(double amount)
    {
        this.amount = amount;
    }

    public void execute(CAccount acc)
    {
        acc.addMoney(amount);
    }
}
