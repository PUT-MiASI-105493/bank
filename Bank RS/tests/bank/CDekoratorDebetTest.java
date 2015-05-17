package bank;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CDekoratorDebetTest 
{
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
	public void testWithDraw() 
	{
		CAccount  acc = bank.GetAccount(addID1);
		acc.addMoney(200);
		bank.tryWithDrawDecorator(100, addID1);
		assertEquals(100, acc.GetBalance(),0.01);
	}

	@Test
	public void testGetBalance() 
	{
		CAccount  acc = bank.GetAccount(addID1);
		acc.addMoney(200);
		CDekoratorDebet db = new CDekoratorDebet(acc);
		assertEquals(200, db.GetBalance(),0.01);
	}

}
