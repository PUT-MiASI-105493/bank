package bank;

import java.util.ArrayList;
import java.util.List;

public class CHistory implements IHistory
{
    private List<IOperation> operations;

    public CHistory()
    {
        this.operations = new ArrayList<IOperation>();
    }

    public void AddToHistory(IOperation co)
    {
        operations.add(co);
    }

    public List<IOperation> GetOperations()
    {
        return this.operations;
    }
}
