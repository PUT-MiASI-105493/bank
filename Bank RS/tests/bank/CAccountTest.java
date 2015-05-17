package bank;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CAccountTest 
{
	int accID;
	int clientID;
	private CAccount account;
	private CBank bank;
	CMediatorELIXIR med;
	int bankID;
	
	@Before
	public void setUp() throws Exception 
	{
		bankID=1;
		med = new CMediatorELIXIR();
		bank = new CBank(bankID,med);
		accID=1;
		clientID=1;
		account = new CAccount(accID, clientID);	
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
		assertEquals(sta, account.state);
		
		account.SetState(stb);
		assertEquals(stb, account.state);
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
		CCustomer client = bank.AddCustomer("Jan", "Kowalski", 3);
		
		int accID = bank.CreateAccountForClient(client, new CAccountStateA());
		
		client.AddAccount(accID);

		CAccount acc = bank.GetAccount(accID);
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
