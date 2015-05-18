package chainFilter;

import operation.IOperation;

public abstract class CChainFilterHandle implements IChainFiletr
{
    private CChainFilterHandle succesor;
    
    public void setSuccesor(CChainFilterHandle succesor) {
        this.succesor = succesor;
    }
 
    public CChainFilterHandle getSuccesor() {
        return succesor;
    }
 
    protected void forward(IOperation request){
        if(this.succesor != null){
            this.getSuccesor().handle(request);
        }
    }
 
    public abstract void handle(IOperation request);
}
