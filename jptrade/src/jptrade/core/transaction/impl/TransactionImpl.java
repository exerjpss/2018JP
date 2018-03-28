package jptrade.core.transaction.impl;

import java.util.ArrayList;
import java.util.List;

import jptrade.core.message.iface.MessageIface;
import jptrade.core.transaction.iface.Transaction;
import jptrade.core.transaction.iface.TransactionNode;

public abstract class TransactionImpl implements Transaction
{
    protected TransactionNode        parent      = null;      // Parent Node
    protected ArrayList<Transaction> operations = null;      // List of Transactions - adjustment transactions
    protected long                   itemCountId = 0;         // use as a transaction ID field
    protected int                    qty         = 1;         // default is 1
    protected long                   value;                   // basic unit currency (no decimal -
                                                    // more
                                                    // efficient)

    public void setUp(TransactionNode headerNode, long id, long value)
    {
        setParent(headerNode);
        setId(id);
        setValue(value);
    }

    public void setUp(TransactionNode headerNode, long id, long value, int qty)
    {
        setUp(headerNode, id, value);
        setQty(qty);
    }
    @Override
    public void setupFromMessage(TransactionNode headerNode, long id,MessageIface messageObj)
    {
        
        
    }


    @Override
    public TransactionNode getParent()
    {
        return parent;
    }

    @Override
    public long getCountId()
    {
        return itemCountId;
    }

    @Override
    public List<Transaction> getAdjTransaction()
    {
        return operations;
    }

    
    protected void setParent(TransactionNode headerNode)
    {
        parent = headerNode;
    }
    protected void setValue(long value2)
    {
        value = value2;
    }

    @Override
    public long getValue()
    {
        return value;
    }
    
    protected void setId(long id)
    {
        itemCountId = id;
    }

    @Override
    public long getQty()
    {
        return qty;

    }

    protected void setQty(int qty2)
    {
        qty = qty2;
    }

    /*
     * Actual process
     */

    @Override
    public long getTotalCalculatedAmount()
    {
        return value;
    }
    
    /*
     * For saleitem there is no adjustment to be made
     * (non-Javadoc)
     * @see jptrade.core.transaction.iface.Transaction#adjustValue(long)
     */

    @Override
    public void addOperation(Transaction operations)
    {
        if (getAdjTransaction() == null)
        {
			this.operations = new ArrayList<Transaction>();
        }
        this.operations.add(operations);

    }

    @Override
    public int getOperationSize()
    {
        if (operations!=null)
            return operations.size();
        else return 0;
    }
    @Override
    public long getCalculatedValue(long amount)
    {
        
        return value;
    }


}
