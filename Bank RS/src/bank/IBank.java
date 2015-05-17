package bank;

public interface IBank 
{
    //void StoreAccount(int id, int ownerID, ITransferUtility trasferUtility, IAccountState type);
    CCustomer AddCustomer(String name, String surname, int id);
}
