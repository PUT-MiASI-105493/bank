package account;

import operation.IOperation;

public interface IAccount 
{
	public void SetState(IAccountState newState);
    public void setAccountID(int id);
	public IAccountState getState();
	public void request();
	public double GetBalance();
	public int GetAccountID();
	public CHistory GetHistory();
	public void addMoney(double amount);
	public void substrMoney(double amount);
	public void DoOperation(IOperation oper);
	public boolean WithDraw(double amount, boolean accountOperation);
	public void PayIn(double amount);
}