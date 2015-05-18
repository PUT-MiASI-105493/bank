package DI;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import bank.*;

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
