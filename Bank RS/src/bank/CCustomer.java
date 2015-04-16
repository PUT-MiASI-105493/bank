package bank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CCustomer 
{
    private String name;
    private String surname; 
    private int id;
    private List<Integer> accountsID;
    private CBank bank;

    public CCustomer(String name, String surname, int id, CBank bankInstance)
    {
        this.accountsID = new ArrayList<Integer>();
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.bank = bankInstance;
    }

   public int GetCustomerID()
   {
       return this.id;
   }

   public String GetName()
   {
       return this.name;
   }

   public String GetSurname()
   {
       return this.surname;
   }

   public List<Integer> GetAccountsID()
   {
       return this.accountsID;
   }

   public void AddAccount(int id)
   {
       accountsID.add(id);
   }
   
   public List<IOperation> getTransfersFromDate(Date dateFrom)
   {
	   return bank.getTransfersFromDate(dateFrom);
   }
   
   public List<IOperation> getPayInsGreatherThan(double payIn)
   {
	   return bank.getPayInsGreatherThan(payIn);
   }
   
   public List<IOperation> getTransfersGreatherThan(double payIn)
   {
	   return bank.getTransfersGreatherThan(payIn);
   }
   
}
