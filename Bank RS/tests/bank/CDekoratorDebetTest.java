package bank;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DI.TestInjector;
import account.CAccountStateA;
import account.CAccountStateB;
import account.CDekoratorDebet;
import account.IAccount;

import com.google.inject.Guice;
import com.google.inject.Injector;

import customer.ICustomer;
import elixir.IMediatorELIXIR;

public class CDekoratorDebetTest 
{
	private ICustomer client1;
	private ICustomer client2;
	private IBank bank;
	private IBank otherBank;
	IMediatorELIXIR med;
	int addID1;
	int addID2;
	int clientID1;
	int clientID2;
	int bankID;
	int otherBankID;
	public Injector inject;

	@Before
	public void setUp() throws Exception 
	{
		bankID=1;
		otherBankID=2;
		clientID1=1;
		clientID2=2;
		
		inject = Guice.createInjector(new TestInjector());		
		med = inject.getInstance(IMediatorELIXIR.class);		
		bank = inject.getInstance(IBank.class);
		bank.setMediator(med);
		bank.setId(bankID);
		
		otherBank = inject.getInstance(IBank.class);
		otherBank.setMediator(med);
		otherBank.setId(otherBankID);
		
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
		IAccount  acc = bank.GetAccount(addID1);
		acc.addMoney(200);
		bank.tryWithDrawDecorator(100, addID1);
		assertEquals(100, acc.GetBalance(),0.01);
	}

	@Test
	public void testGetBalance() 
	{
		IAccount  acc = bank.GetAccount(addID1);
		acc.addMoney(200);
		CDekoratorDebet db = new CDekoratorDebet(acc);
		assertEquals(200, db.GetBalance(),0.01);
	}

}
