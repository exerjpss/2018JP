package jptrade.core.transaction.iface;

import java.util.List;

import jptrade.core.message.iface.MessageIface;


public interface Transaction
{
    
    public TransactionNode getParent ();
    public long getCountId ();
    public List<Transaction> getAdjTransaction ();
    public long getCalculatedValue (long amount);
    public long getValue();
    
    public void setUp(TransactionNode headerNode, long id, long value);
    public void setUp(TransactionNode headerNode, long id, long value, int qty);
    public long getTotalCalculatedAmount();
    public void setupFromMessage(TransactionNode headerNode, long id, MessageIface messageObj);
    public boolean isOperation ();
    public void addOperation(Transaction operations);
    public int getOperationSize ();
    public long getQty();
       
}
