package bank;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DI.TestInjector;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class CAccountTest 
{
	int accID;
	int clientID;
	private IAccount account;
	private IBank bank;
	IMediatorELIXIR med;
	int bankID;
	private Injector inject;
	
	@Before
	public void setUp() throws Exception 
	{
		bankID=1;
		inject = Guice.createInjector(new TestInjector());		
		med = inject.getInstance(IMediatorELIXIR.class);		
		bank = inject.getInstance(IBank.class);
		bank.setMediator(med);
		bank.setId(bankID);
		
		accID=1;
		clientID=1;
			
		account = inject.getInstance(IAccount.class);
		account.setAccountID(accID);
		account.setOwnerID(clientID);
	}

	@After
	public void tearDown() throws Exception 
	{
		account=null;
	}

	@Test
	public void testSetState() 
	{
		CAccountStateA sta = new CAccountStateA();
		CAccountStateB stb = new CAccountStateB();
		account.SetState(sta);
		assertEquals(sta, account.getState());
		
		account.SetState(stb);
		assertEquals(stb, account.getState());
	}

	@Test
	public void testRequest() 
	{
		CAccountStateA sta = new CAccountStateA();
		CAccountStateB stb = new CAccountStateB();
		
		account.addMoney(100);		
		assertEquals(100, account.GetBalance(), 0.01);
		
		account.SetState(sta);
		double expA = sta.setInterest(account) + account.GetBalance();
		account.request();
		assertEquals(expA, account.GetBalance(), 0.01);
		
		account.SetState(stb);
		double expB = stb.setInterest(account) + account.GetBalance();
		account.request();
		assertEquals(expB, account.GetBalance(), 0.01);
	}

	@Test
	public void testGetBalance() 
	{
		account.addMoney(100);		
		assertEquals(100, account.GetBalance(), 0.01);
	}

	@Test
	public void testGetAccountID() 
	{
		assertEquals(accID, account.GetAccountID());
	}

	@Test
	public void testGetHistory() 
	{
		ICustomer client = bank.AddCustomer("Jan", "Kowalski", 3);
		
		int accID = bank.CreateAccountForClient(client, new CAccountStateA());
		
		client.AddAccount(accID);

		IAccount acc = bank.GetAccount(accID);
		bank.PayIn(acc, 100);
		assertNotNull(acc.GetHistory());		
	}

	@Test
	public void testAddMoney() 
	{
		account.addMoney(100);		
		assertEquals(100, account.GetBalance(), 0.01);
	}

	@Test
	public void testSubstrMoney() 
	{
		account.addMoney(100);
		assertEquals(100, account.GetBalance(), 0.01);
		
		account.substrMoney(50);		
		assertEquals(50, account.GetBalance(), 0.01);
	}

	@Test
	public void testDoOperation() 
	{
		Date fromDate = Calendar.getInstance().getTime();
		
		COperationPayIn oper = new COperationPayIn(1000, fromDate);
		account.DoOperation(oper);
		
		assertNotNull(account.GetHistory().GetOperations());
	}
}
