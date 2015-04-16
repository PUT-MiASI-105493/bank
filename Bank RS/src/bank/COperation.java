package bank;

public class COperation 
{
    protected int destinationID;
    protected int sourceID;
    protected double amount;

    public COperation(int sourceID, int destinationID, double amount)
    {
        this.sourceID = sourceID;
        this.destinationID = destinationID;
        this.amount = amount;
    }

    public int GetDestinationID()
    {
        return this.destinationID;
    }

    public int GetSourceID()
    {
        return this.sourceID;
    }

    public double GetAmount()
    {
        return this.amount;
    }
}
