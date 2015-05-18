package customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import operation.IOperation;
import bank.IBank;

import com.google.inject.Inject;

public class CCustomer implements ICustomer
{
    private String name;
    private String surname; 
    private int id;
    private List<Integer> accountsID;
    private IBank bank;

    @Inject
    public CCustomer()
    {
        this.accountsID = new ArrayList<Integer>();
    }
    
    public void setName(String name)
    {
    	this.name=name;
    }
    
    public void setSurname(String surname)
    {
    	this.surname=surname;
    }
    
    public void setID(int id)
    {
    	this.id=id;
    }
    
    public void setBank(IBank bank)
    {
    	this.bank = bank;
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
   
   public boolean tryWithDrawNormal(double money, int accountID)
   {
	   return bank.tryWithDrawNormal(money, accountID);
   }
   
}
