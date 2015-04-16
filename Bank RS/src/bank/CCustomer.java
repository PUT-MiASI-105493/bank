package bank;

import java.util.ArrayList;
import java.util.List;

public class CCustomer 
{
    private String name;
    private String surname; 
    private int id;
    private List<Integer> accountsID;

    public CCustomer(String name, String surname, int id)
    {
        this.accountsID = new ArrayList<Integer>();
        this.name = name;
        this.surname = surname;
        this.id = id;
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
}
