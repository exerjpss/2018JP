package jptrade.core.transaction.impl;

import java.util.List;
import java.util.ArrayList;

import jptrade.core.adjustment.impl.Adjustment;
import jptrade.core.constants.Constant;
import jptrade.core.message.iface.MessageIface;
import jptrade.core.sale.impl.SaleItem;
import jptrade.core.transaction.iface.Transaction;
import jptrade.core.transaction.iface.TransactionFactoryIface;

public class TransactionFactoryImpl implements TransactionFactoryIface
{
    private List getTransaction (String type)
    {
        ArrayList<Transaction> trans = null;
        trans = new ArrayList <Transaction> ();
        Transaction transaction=null;
        switch (type)
        {
            case Constant.STR_ADJUST : 
                transaction = new Adjustment ();
                trans.add(transaction);
            //break;  // commented out to add saleitem too
            case Constant.STR_SALE : 
                    transaction = new SaleItem ();
                    trans.add(transaction);
            break;
        }
        return trans;
    }

    @Override
    public List getTransaction(MessageIface message)
    {
        List trans=null;
        trans = getTransaction(message.getMessageType());
        
        return trans;
    }
}
