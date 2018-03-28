package jptrade.core.transaction.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;



import jptrade.core.transaction.iface.Transaction;


//@RunWith (MockitoJUnitRunner.class)
class testTransactionImpl
{

    @BeforeEach public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }
    TransactionNodeImpl headerNode;
    @Mock TransactionImpl adjustments;
    @InjectMocks TransactionImplT saleItem;
    @Mock TransactionImpl adjustments2;
     
    /*
     * Test the setup function, and that parameters have been stored correctly 
     */
    @Test
    void testSetup()
    {
        Transaction trans = new TransactionImplT ();
        assertTrue (trans.getParent()==null);
        //sale.setParent (headerNode);
        //assertTrue (sale.getParent()==headerNode);
        trans = new TransactionImplT ();
        trans.setUp (headerNode,999,5);
        assertTrue (trans.getParent()==headerNode);
        assertTrue (trans.getValue()==5);
        assertTrue (trans.getCountId()==999);
        assertTrue (trans.getQty() == 1);
        trans.setUp (headerNode,999,5,9);
        assertTrue (trans.getParent()==headerNode);
        assertTrue (trans.getCalculatedValue(8)==5);
        assertTrue (trans.getCountId()==999);
        assertTrue (trans.getQty() == 9);

     }
    /*
     * Only checking if loop and pickup last value
     * Since adjustvalue here is not being tested
     */
    @Test
    void testGetAdjustedValue()
    {
      
        saleItem.addOperation (adjustments);
        saleItem.addOperation (adjustments2);
        assertFalse (saleItem==null);
        assertFalse (adjustments==null);
        when (adjustments.getCalculatedValue(any(long.class))).thenReturn(new Long(4));
        when (adjustments2.getCalculatedValue(any(long.class))).thenReturn(new Long(10));
        //when (adjustments.getAdjTransaction()).thenReturn(adjList);
        //when (adjustments2.getAdjTransaction()).thenReturn(null);
        saleItem.setUp(null, 0, 15);
        long value = saleItem.getTotalCalculatedAmount();
        assertTrue ("vaalue " + value, value==15);
        
    }

}
