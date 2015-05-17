package bank;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CMediatorELIXIRTest {
	private CBank bank;
	private CBank otherBank;
	private CMediatorELIXIR med;
	private int bankID;
	private int otherBankID;
	
	@Before
	public void setUp() throws Exception 
	{
		bankID=1;
		otherBankID=2;
		med = new CMediatorELIXIR();
		bank = new CBank(bankID,med);
		otherBank = new CBank(otherBankID,med);
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
		med.registerNewBank(bank);
		assertEquals(1, med.getBanksListCount());
		
		med.registerNewBank(otherBank);
		assertEquals(2, med.getBanksListCount());
	}

	@Test
	public void testSendIOperationsList() 
	{
		med.registerNewBank(bank);
		assertEquals(1, med.getBanksListCount());
		
		med.registerNewBank(otherBank);
		assertEquals(2, med.getBanksListCount());
		
		CCustomer client1 = bank.AddCustomer("Jan", "Kowalski", 1);
		CCustomer client2 = otherBank.AddCustomer("Adam", "Nowak", 2);
	
		int addID1 = bank.CreateAccountForClient(client1, new CAccountStateA());
		int addID2 = otherBank.CreateAccountForClient(client2, new CAccountStateB());
		
		client1.AddAccount(addID1);
		client2.AddAccount(addID2);
		
		bank.Transfer(bank.GetAccount(addID1), otherBank.GetAccount(addID2), 100);
		bank.sendIOperationListToELIXIR(bank.getOperations(), otherBankID);
		assertNotNull(otherBank.getOperationsFromOtherBank());
	}

}
