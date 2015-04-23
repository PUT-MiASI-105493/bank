package bank;

public interface IChainFiletr 
{
    public void setSuccesor(CChainFilterHandle succesor);
    
    public CChainFilterHandle getSuccesor();
}