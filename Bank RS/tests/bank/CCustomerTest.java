package bank;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DI.TestInjector;
import account.CAccountStateA;
import account.CAccountStateB;

import com.google.inject.Guice;
import com.google.inject.Injector;

import customer.ICustomer;
import elixir.IMediatorELIXIR;

public class CCustomerTest {
	private ICustomer client1;
	private ICustomer client2;
	private IBank bank;
	IMediatorELIXIR med;
	int addID1;
	int addID2;
	int clientID1;
	int clientID2;
	int bankID;
	private Injector inject;
	
	@Before
	public void setUp() throws Exception 
	{
		bankID=1;
		clientID1=1;
		clientID2=2;

		inject = Guice.createInjector(new TestInjector());		
		med = inject.getInstance(IMediatorELIXIR.class);		
		bank = inject.getInstance(IBank.class);
		bank.setMediator(med);
		bank.setId(bankID);
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

}
