package DI;

import com.google.inject.AbstractModule;

import customer.CCustomer;
import customer.ICustomer;
import elixir.CMediatorELIXIR;
import elixir.IMediatorELIXIR;
import account.CAccount;
import account.CHistory;
import account.IAccount;
import account.IHistory;
import bank.CBank;
import bank.IBank;

public class TestInjector extends AbstractModule
{

	@Override
	protected void configure() 
	{
		bind(IMediatorELIXIR.class).to(CMediatorELIXIR.class);
		bind(IBank.class).to(CBank.class);
		bind(ICustomer.class).to(CCustomer.class);
		bind(IAccount.class).to(CAccount.class);
		bind(IHistory.class).to(CHistory.class);	
	}
}
