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

public class CMediatorELIXIRTest {
	private IBank bank;
	private IBank otherBank;
	private IMediatorELIXIR med;
	private int bankID;
	private int otherBankID;
	private Injector inject;
	
	@Before
	public void setUp() throws Exception 
	{	
		bankID=1;
		otherBankID=2;
		inject = Guice.createInjector(new TestInjector());
		
		med = inject.getInstance(IMediatorELIXIR.class);
		
		bank = inject.getInstance(IBank.class);
		bank.setMediator(med);
		bank.setId(bankID);
		
		otherBank = inject.getInstance(IBank.class);
		otherBank.setMediator(med);
		otherBank.setId(otherBankID);	
	}

	@After
	public void tearDown() throws Exception 
	{
		med=null;
		bank=null;
		otherBank=null;
	}

	@Test
	public void testRegisterNewBank() 
	{
		med.registerNewBank((CBank)bank);
		assertEquals(1, med.getBanksListCount());
		
		med.registerNewBank((CBank)otherBank);
		assertEquals(2, med.getBanksListCount());
	}

	@Test
	public void testSendIOperationsList() 
	{
		med.registerNewBank((CBank)bank);
		assertEquals(1, med.getBanksListCount());
		
		med.registerNewBank((CBank)otherBank);
		assertEquals(2, med.getBanksListCount());
		
		ICustomer client1 = bank.AddCustomer("Jan", "Kowalski", 1);
		ICustomer client2 = otherBank.AddCustomer("Adam", "Nowak", 2);
	
		int addID1 = bank.CreateAccountForClient(client1, new CAccountStateA());
		int addID2 = otherBank.CreateAccountForClient(client2, new CAccountStateB());
		
		client1.AddAccount(addID1);
		client2.AddAccount(addID2);
		
		bank.Transfer(bank.GetAccount(addID1), otherBank.GetAccount(addID2), 100);
		bank.sendIOperationListToELIXIR(bank.getOperations(), otherBankID);
		assertNotNull(otherBank.getOperationsFromOtherBank());
	}

}
