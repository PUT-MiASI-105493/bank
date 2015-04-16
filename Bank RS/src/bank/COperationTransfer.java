package bank;

public class COperationTransfer implements IOperation
{
    private double amount;
    CAccount dest;

    public COperationTransfer(CAccount to, double amount)
    {
        this.amount = amount;
        this.dest = to;
    }

    public void execute(CAccount from)
    {
        from.substrMoney(amount);
        dest.addMoney(amount);
    }
}
