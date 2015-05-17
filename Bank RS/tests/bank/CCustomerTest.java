package bank;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CCustomerTest {
	private CCustomer client1;
	private CCustomer client2;
	private CBank bank;
	CMediatorELIXIR med;
	int addID1;
	int addID2;
	int clientID1;
	int clientID2;
	int bankID;
	
	@Before
	public void setUp() throws Exception 
	{
		bankID=1;
		clientID1=1;
		clientID2=2;
		med = new CMediatorELIXIR();
		bank = new CBank(bankID,med);
		med.registerNewBank(bank);
		
		client1 = bank.AddCustomer("Jan", "Kowalski", clientID1);
		client2 = bank.AddCustomer("Adam", "Nowak", clientID2);
		
		addID1 = bank.CreateAccountForClient(client1, new CAccountStateA());
		addID2 = bank.CreateAccountForClient(client2, new CAccountStateB());
		
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
	public void testGetCustomerID() 
	{
		assertEquals(clientID1, client1.GetCustomerID());
	}


	@Test
	public void testTryWithDrawNormal() 
	{
		CAccount acc = bank.GetAccount(addID1);
		acc.addMoney(1000);
		
		client1.tryWithDrawNormal(500, addID1);
		
		assertEquals(acc.GetBalance(), 500, 0.01);
		
		client1.tryWithDrawNormal(500, addID1);
	}

}
