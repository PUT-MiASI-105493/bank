package bank;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CHistoryTest {

	private CHistory history;
	private COperationPayIn payin;
	private COperationTransfer transf;
	private double amount;
	private Date date;
	
	@Before
	public void setUp() throws Exception 
	{
		history = new CHistory();
		date = Calendar.getInstance().getTime();
		CAccount acc = new CAccount(1, 1);
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
