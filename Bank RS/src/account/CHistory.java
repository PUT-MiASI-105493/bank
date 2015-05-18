package account;

import java.util.ArrayList;
import java.util.List;

import operation.IOperation;

import com.google.inject.Inject;

public class CHistory implements IHistory
{
    private List<IOperation> operations;

    @Inject
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
