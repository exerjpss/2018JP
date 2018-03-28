package jptrade.core.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jptrade.core.adjustment.impl.Adjustment;
import jptrade.core.message.impl.Message;
import jptrade.core.product.impl.ProductMap;
import jptrade.core.sale.impl.SaleItem;
import jptrade.core.transaction.iface.Transaction;
import jptrade.core.transaction.iface.TransactionNode;
import jptrade.core.transaction.impl.*;

class testProductMap
{

    @Mock Message messageObj;
    @Mock TransactionFactoryImpl transactionFactory;
    private ProductMap <TransactionNodeImpl> pm;
    TransactionNodeImpl tm;
    
    @BeforeEach
    public void setup ()
    {
        System.out.println("hi");
         pm = new ProductMap <TransactionNodeImpl> (TransactionNodeImpl::new);
         MockitoAnnotations.initMocks(this);
        //(() -> new TransactionNodeImpl (""));
        //pm = new ProductMap()<>(TransactionNodeImpl::new());
    }
    
    

    @Test
    void testAddProduct()
    {
     
        
        
        TransactionNode dataList1 = pm.addProduct("Orange");
        assertTrue (dataList1!=null);
        TransactionNode dataList2 = pm.addProduct("Orange");
        assertTrue (dataList1==(dataList2));
        
        TransactionNode dataList3 = pm.addProduct("Banana");
        assertTrue (dataList3!=null);
        
        TransactionNode dataList4 = pm.addProduct("Orange");
        assertTrue (dataList4==(dataList2));
        TransactionNode dataList5 = pm.addProduct("Pear");
        assertTrue (dataList5!=null);
        
        assertTrue (pm.getSize()==3);
        
    }

    @Test
    void testAddProductFromMessage ()
    {
        ArrayList <Transaction> list = new ArrayList <Transaction> ();
        Transaction trans = new Adjustment();
        list.add(trans);
        trans = new SaleItem();
        list.add(trans);
        when (messageObj.getName()).thenReturn("Orange");
        when (transactionFactory.getTransaction(any(Message.class))).thenReturn(list);
        TransactionNode dataList0c = pm.addProductMessage(messageObj,transactionFactory);
        TransactionNode dataList2 = pm.addProduct("Orange");
        assertTrue (dataList0c==dataList2);
        
    }
    @Test
    void testGetProduct()
    {
		TransactionNode dataList0 = pm.addProduct(null);
        assertTrue (pm.getSize()==0);
        TransactionNode dataList0a = pm.addProduct("");
        assertTrue (pm.getSize()==0);

        
        TransactionNode dataList1 = pm.addProduct("Pear");
        TransactionNode dataList2 = pm.addProduct("Orange");
        TransactionNode dataList3 = pm.addProduct("Banana");
        TransactionNode dataList4 = pm.addProduct("Orange");
        TransactionNode dataList5 = pm.addProduct("Pear");

        TransactionNode dataListGet = pm.getProduct("Orange");
        assertTrue (dataListGet!=null);
        assertTrue (dataListGet==(dataList2));
        
        dataListGet = pm.getProduct("Pear");
        assertTrue (dataListGet!=null);
        assertTrue (dataListGet==(dataList5));
        
        dataListGet = pm.getProduct("Banana");
        assertTrue (dataListGet!=null);
        assertTrue (dataListGet==dataList3);
        assertFalse (dataListGet==dataList1);
        assertFalse (dataListGet==(dataList2));
        assertFalse (dataListGet==(dataList4));
        assertFalse (dataListGet==(dataList5));

        dataListGet = pm.getProduct("Melon");
        assertTrue (dataListGet==null);
        
        

    }

}
