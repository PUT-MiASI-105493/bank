package bank;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import operation.COperationPayIn;
import operation.COperationTransfer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import DI.TestInjector;
import account.IAccount;
import account.IHistory;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class CHistoryTest {

	private IHistory history;
	private COperationPayIn payin;
	private COperationTransfer transf;
	private double amount;
	private Date date;
	private Injector inject;
	
	@Before
	public void setUp() throws Exception 
	{
		inject = Guice.createInjector(new TestInjector());	
		history = inject.getInstance(IHistory.class);
		date = Calendar.getInstance().getTime();
		
		IAccount acc = inject.getInstance(IAccount.class);
		acc.setAccountID(1);
		payin = new COperationPayIn(amount, date);
		transf = new COperationTransfer(acc, amount, date);
	}

	@After
	public void tearDown() throws Exception 
	{

	}

	@Test
	public void testAddToHistory() 
	{
		history.AddToHistory(payin);
		assertNotNull(history.GetOperations());
		
		history.AddToHistory(transf);
		assertEquals(2, history.GetOperations().size());
	}

	@Test
	public void testGetOperations() 
	{
		history.AddToHistory(payin);
		assertNotNull(history.GetOperations());
		
		history.AddToHistory(transf);
		assertEquals(2, history.GetOperations().size());
	}

}
