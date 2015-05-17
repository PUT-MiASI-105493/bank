package bank;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CBankTest {
	private CCustomer client1;
	private CCustomer client2;
	private CBank bank;
	private CBank otherBank;
	CMediatorELIXIR med;
	int addID1;
	int addID2;
	int clientID1;
	int clientID2;
	int bankID;
	int otherBankID;
	
	@Before
	public void setUp() throws Exception 
	{
		bankID=1;
		otherBankID=2;
		clientID1=1;
		clientID2=2;
		med = new CMediatorELIXIR();
		bank = new CBank(bankID,med);
		otherBank = new CBank(otherBankID,med);
		med.registerNewBank(bank);
		med.registerNewBank(otherBank);
		
		client1 = bank.AddCustomer("Jan", "Kowalski", clientID1);
		client2 = otherBank.AddCustomer("Adam", "Nowak", clientID2);
		
		addID1 = bank.CreateAccountForClient(client1, new CAccountStateA());
		addID2 = otherBank.CreateAccountForClient(client2, new CAccountStateB());
		
		client1.AddAccount(addID1);
		client2.AddAccount(addID2);
	}

	@After
	public void tearDown() throws Exception 
	{
		med = null;
		bank = null;
		client1 = null;
		client2 = null;
	}

	@Test
	public void testSendIOperationListToELIXIR() 
	{
		bank.Transfer(bank.GetAccount(addID1), otherBank.GetAccount(addID2), 100);
		bank.sendIOperationListToELIXIR(bank.getOperations(), otherBankID);
		assertNotNull(otherBank.getOperationsFromOtherBank());
	}

	@Test
	public void testRecieveIOperationList() 
	{
		bank.Transfer(bank.GetAccount(addID1), otherBank.GetAccount(addID2), 100);
		bank.recieveIOperationList(bank.getOperations());
		assertNotNull(bank.getOperationsFromOtherBank());		
	}

	@Test
	public void testGetCustomerList() 
	{
		assertNotNull(bank.GetCustomerList());
	}

	@Test
	public void testCreateAccountForClient() 
	{
		addID1 = bank.CreateAccountForClient(client1, new CAccountStateA());
		assertNotNull(bank.GetAccount(addID1));
	}

	@Test
	public void testAddCustomer() 
	{
		client1 = bank.AddCustomer("Jan", "Kowalski", clientID1);
		assertNotNull(client1);
		assertNotNull(bank.GetCustomer(client1.GetCustomerID()));
	}

	@Test
	public void testGetCustomer() 
	{
		assertNotNull(bank.GetCustomer(client1.GetCustomerID()));
	}

	@Test
	public void testCheckAccID() 
	{
		assert(bank.CheckAccID(addID2));
		assert(!bank.CheckAccID(addID1));
	}

	@Test
	public void testGetAccount() 
	{
		assertNotNull(bank.GetAccount(addID1));
		assertNull(bank.GetAccount(0));
	}

	@Test
	public void testPayIn() 
	{
		CAccount  acc = bank.GetAccount(addID1);
		bank.PayIn(acc, 100);
		assertNotNull(bank.getOperations());
	}

	@Test
	public void testTransfer() 
	{
		CAccount  acc = bank.GetAccount(addID1);
		CAccount  acc2 = bank.GetAccount(addID2);
		bank.Transfer(acc, acc2, 100);
		assertNotNull(bank.getOperations());
	}

	@Test
	public void testGetTransfersFromDate() 
	{
		List<IOperation> list;
		Date fromDate = Calendar.getInstance().getTime();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, 4);
		calendar.getTime();
		
		assertNotNull(client1);
		assertNotNull(client2);
		
		CAccount acc = bank.GetAccount(addID1);
		acc.addMoney(1000);
		assertNotEquals(bank.GetAccount(addID1).GetBalance(),0);
		
		bank.Transfer(bank.GetAccount(addID1), bank.GetAccount(addID2), 100);
		list = bank.getTransfersFromDate(fromDate);
		assertNotNull(list);
	}

	@Test
	public void testGetPayInsGreatherThan() 
	{
		List<IOperation> list;
		
		assertNotNull(client1);
		assertNotNull(client2);
		
		CAccount acc = bank.GetAccount(addID1);
		acc.addMoney(1000);
		assertNotEquals(bank.GetAccount(addID1).GetBalance(),0);
		
		bank.PayIn(bank.GetAccount(addID1), 100);
		list = bank.getPayInsGreatherThan(90);
		assertNotNull(list);
	}

	@Test
	public void testGetTransfersGreatherThan() 
	{
		List<IOperation> list;
		Date fromDate = Calendar.getInstance().getTime();
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR_OF_DAY, 4);
		calendar.getTime();
		
		assertNotNull(client1);
		assertNotNull(client2);
		
		CAccount acc = bank.GetAccount(addID1);
		acc.addMoney(1000);
		assertNotEquals(bank.GetAccount(addID1).GetBalance(),0);
		
		bank.Transfer(bank.GetAccount(addID1), bank.GetAccount(addID2), 100);
		list = bank.getTransfersGreatherThan(90);
		assertNotNull(list);
	}

	@Test
	public void testSetChainFilter() 
	{
		bank.setChainFilter();
		assertNotNull(bank.chainEnd);
		assertNotNull(bank.chainBegin);
	}

	@Test
	public void testRunChainFilter() 
	{
		List<IOperation> list;
		
		CAccount acc = bank.GetAccount(addID1);
		acc.addMoney(5000);
		assertNotEquals(bank.GetAccount(addID1).GetBalance(),0);
		
		bank.Transfer(bank.GetAccount(addID1), bank.GetAccount(addID2), 5000);
		assert(bank.getOperations().get(0).getDispose());
		
		bank.Transfer(bank.GetAccount(addID1), bank.GetAccount(addID2), 500);
		assert(!bank.getOperations().get(1).getDispose());
	}
}
