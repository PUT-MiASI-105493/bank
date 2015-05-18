package chainFilter;

import operation.IOperation;

public class CChainFilterHighIOperation extends CChainFilterHandle
{
	double Threshold;
	
	public CChainFilterHighIOperation(double thres)
	{
		this.Threshold = thres;
	}
	
    @Override
    public void handle(IOperation request) 
    {
        if (request.getAmount() > Threshold) 
        {
            System.out.println("Za duza wartosc przelewu!");
            forward(request);
            request.Dispose(true);
        } 
        else 
        {
            forward(request);
        }
    }
}
