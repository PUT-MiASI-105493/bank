package bank;

import java.util.ArrayList;
import java.util.List;

public class CChainFilterSameOperations extends CChainFilterHandle
{
	List<IOperation> helpList;
	
	public CChainFilterSameOperations()
	{
		helpList = new ArrayList<IOperation>();
	}
	
    @Override
    void handle(IOperation request) 
    {
    	List<IOperation> aw = new ArrayList<IOperation>();
    	
    	for(IOperation o : helpList)
    		if(o.getAmount() == request.getAmount())
    			aw.add(o);
    	
    	if(aw.size()>5)
    	{
    			  System.out.println("Ponowienie przelewu!");
    			  forward(request);
    	}
        else 
        {
        	helpList.add(request);
            forward(request);
        }
    }
}
