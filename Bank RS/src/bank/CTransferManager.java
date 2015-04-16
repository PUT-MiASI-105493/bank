package bank;

import java.util.ArrayList;
import java.util.List;

public class CTransferManager 
{
    private List<COperation> kirOperations;
    private boolean toKIR;
    private int bankID;
    private IBankUtility bankUtility;
    private IKIRutility kirUtility;

    public CTransferManager(IBankUtility bu, IKIRutility ku)
    {
        this.bankUtility = bu;
        this.kirUtility = ku;
        this.kirOperations = new ArrayList<COperation>();
    }

    public void AddOperation(COperation co)
    {
        checkID(co);
        if (toKIR)
            this.kirOperations.add(co);
        else
            Send(co);
    }

    public void Send()  //Send pack to KIR
    {
        this.kirUtility.AddToKIR(this.kirOperations);
        this.kirOperations = new ArrayList<COperation>();
    }

    public void makeTransfer(List<COperation> transfer) //Get operations from KIR
    {
        for(COperation v : transfer)
            Send(v);
    }

    private void Send(COperation co)    //Inner send
    {
        int dest = co.GetDestinationID();
        this.bankUtility.GetAccount(dest).PayIn(co.GetAmount());
    }

    private void SetToKir(boolean b)
    {
        this.toKIR = b;
    }

    private void checkID(COperation co)
    {
        SetToKir(this.bankUtility.CheckAccID(co.GetDestinationID()));
    }
}
