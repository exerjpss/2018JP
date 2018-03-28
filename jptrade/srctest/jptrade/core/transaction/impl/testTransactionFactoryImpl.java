package jptrade.core.transaction.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jptrade.core.adjustment.impl.Adjustment;
import jptrade.core.constants.Constant;
import jptrade.core.message.impl.Message;
import jptrade.core.product.impl.ProductMap;
import jptrade.core.sale.impl.SaleItem;


class testTransactionFactoryImpl
{

    @Mock Message messageObj;
	private ProductMap<TransactionNodeImpl> pm;
    TransactionNodeImpl tm;
    
    @BeforeEach
    public void setup ()
    {
          MockitoAnnotations.initMocks(this);
    }
    @Test
    void testGetTransaction()
    {
        TransactionFactoryImpl tf = new TransactionFactoryImpl ();
        List transactionList;
       
        when (messageObj.getMessageType()).thenReturn(Constant.STR_ADJUST);
        transactionList = tf.getTransaction(messageObj );
        assertTrue (transactionList != null);
        assertTrue (transactionList.size()==2);
        assertTrue (transactionList.get(0) instanceof Adjustment);
        assertTrue (transactionList.get(1) instanceof SaleItem);
        when (messageObj.getMessageType()).thenReturn(Constant.STR_SALE);
        transactionList = tf.getTransaction(messageObj);
        assertTrue (transactionList != null);
        assertTrue (transactionList.size()==1);
        assertTrue (transactionList.get(0) instanceof SaleItem);
        when (messageObj.getMessageType()).thenReturn("NONE");
        transactionList = tf.getTransaction(messageObj);
        assertTrue (transactionList.size() == 0);
    }

}
