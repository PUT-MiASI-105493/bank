package bank;

import java.util.Date;
import java.util.List;

import customer.ICustomer;
import account.IAccount;
import account.IAccountState;
import operation.IOperation;
import chainFilter.CChainFilterHandle;
import elixir.CMediatorELIXIR;
import elixir.IMediatorELIXIR;

public interface IBank 
{
    public void setMediator(IMediatorELIXIR med);
    public void setId(int id);
    public void registerMediatorELIXIR(CMediatorELIXIR mediator);
    public void sendIOperationListToELIXIR(List<IOperation> operationsToSend, int destinationBank);
    public void recieveIOperationList(List<IOperation> operationsRecieved);    
    public List<IOperation> getOperationsFromOtherBank();
    public List<ICustomer> GetCustomerList();
    public int CreateAccountForClient(ICustomer client, IAccountState state);
    public ICustomer AddCustomer(String name, String surname, int id);
    public ICustomer GetCustomer(int id);
    public boolean CheckAccID(int id);
    public int GetBankID();
    public IAccount GetAccount(int id);
    public void PayIn(IAccount acc, double amount);
    public void Transfer(IAccount from, IAccount to, double amount);
    public List<IOperation> getOperations();
    public List<IOperation> getTransfersFromDate(Date dateFrom);
    public List<IOperation> getPayInsGreatherThan(double payIn);
    public List<IOperation> getTransfersGreatherThan(double payIn);
    public boolean tryWithDrawNormal(double money, int accountID);   
    public boolean tryWithDrawDecorator(double money, int accountID);   
    public void setChainFilter();
    public void runChainFilter(IOperation o);
    public CChainFilterHandle getChainBegin();
    public CChainFilterHandle getChainEnd();
}
