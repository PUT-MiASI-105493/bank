package customer;

import java.util.Date;
import java.util.List;

import bank.IBank;
import operation.IOperation;

public interface ICustomer {

    public void setName(String name);
    public void setSurname(String surname);
    public void setID(int id);
   public void setBank(IBank bank);
   public int GetCustomerID();
   public String GetName();
   public String GetSurname();
   public List<Integer> GetAccountsID();
   public void AddAccount(int id);
   public List<IOperation> getTransfersFromDate(Date dateFrom);
   public List<IOperation> getPayInsGreatherThan(double payIn);
   public List<IOperation> getTransfersGreatherThan(double payIn);
   public boolean tryWithDrawNormal(double money, int accountID);
}
