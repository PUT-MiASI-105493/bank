package bank;

public interface IAccount 
{
    void PayIn(double amount);
    boolean WithDraw(double amount, boolean accountOperation);
    double GetBalance();
}
