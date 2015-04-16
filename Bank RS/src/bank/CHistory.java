package bank;

import java.util.ArrayList;
import java.util.List;

public class CHistory implements IHistory
{
    private List<COperation> operations;

    public CHistory()
    {
        this.operations = new ArrayList<COperation>();
    }

    public void AddToHistory(COperation co)
    {
        operations.add(co);
    }

    public List<COperation> GetOperations()
    {
        return this.operations;
    }
}
