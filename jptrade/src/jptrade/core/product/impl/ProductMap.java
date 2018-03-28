package jptrade.core.product.impl;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import jptrade.core.transaction.iface.TransactionNode;
import jptrade.core.output.iface.ProductAdjustments;
import jptrade.core.output.iface.ProductSales;


import jptrade.core.output.impl.ProductAdjustmentsImpl;
import jptrade.core.output.impl.ProductSalesImpl;

import jptrade.core.adjustment.impl.Adjustment;
import jptrade.core.message.iface.MessageIface;
import jptrade.core.product.iface.Product;
import jptrade.core.transaction.iface.Transaction;
import jptrade.core.transaction.iface.TransactionFactoryIface;

public class ProductMap<T extends TransactionNode> implements Product<T>
{
    HashMap<String, TransactionNode> db;
    long counter = 0;

    /*
     * Allow this class to generate a new implementation of TransactionNode
     * NB Java 8+
     */
    private Supplier<T>              supplier;
    T getNewInstance(String name)
    {
        T newNode = supplier.get();
        newNode.setUp(name);
        return newNode;
    }

    /*
     * New product map
     */
    public ProductMap(Supplier<T> supplier)
    {
        this.supplier = supplier;
        db = new HashMap<String, TransactionNode>();
    }

    /*
     * Find and return a collection
     * or add a new instance of collection type
     * and return
     * (non-Javadoc)
     * @see jptrade.core.product.iface.Product#addProduct(java.lang.String)
     */
    @Override
    public TransactionNode addProduct(String productName)
    {
        TransactionNode headNodeItem = null;
        if (productName != null) 
            if (!productName.equals("")) 
                if (db != null)
                {
                    headNodeItem = (TransactionNode) db.get(productName);
                    if (headNodeItem == null)
                    {
                        headNodeItem = getNewInstance(productName);
                        db.put(productName, headNodeItem);
                    }
                }
        return headNodeItem;
    }

    /*
     * Find the collection for product
     * and return or return null
     * (non-Javadoc)
     * @see jptrade.core.product.iface.Product#getProduct(java.lang.String)
     */
    @Override
    public TransactionNode getProduct(String productName)
    {
        TransactionNode listitem = null;
        if (db != null)
        {
            listitem = (TransactionNode) db.get(productName);
        }
        return listitem;
    }

    /*
     * Return the number of products in the map
     * (non-Javadoc)
     * @see jptrade.core.product.iface.Product#getSize()
     */
    @Override
    public int getSize()
    {

        return db.size();
    }
    
    /*
     *  Get a messageObject
     *  Add it to the map
     *  Get a transaction object from the messageObject
     *  Add it to the Node 
     */
    @Override 
    public TransactionNode addProductMessage (MessageIface messageObj, TransactionFactoryIface factory)
    {
         TransactionNode node = null;
        if (messageObj!=null)
            node = addProduct(messageObj.getName());
        if (node!=null)
        {
            List  transactionList = factory.getTransaction(messageObj);
            if (transactionList!=null)
                if (transactionList.size()>0)
                {
                    for (Object object : transactionList)
                    {
                        Transaction transaction = (Transaction)object;
                        transaction.setupFromMessage (node,counter++,messageObj);
                        node.addTransaction(transaction);
                    }
                }
        }
        return node;
    }

    public ProductSales getSalesOutput()
    {
        ProductSales productSales = new ProductSalesImpl();
               
        db.entrySet();
       
        for (Map.Entry<String, TransactionNode> entry: db.entrySet())
        {
            //String name = entry.getKey();

            TransactionNode node = entry.getValue();
            Collection<Transaction> transactions = node.getTransactions();
            long total = 0;
            long qty = 0;
            for (Transaction transaction : transactions)
            {
                total = total + transaction.getTotalCalculatedAmount();
                qty = qty + transaction.getQty();
                
            }
            
            productSales.addProductSale(qty, total, node.getProductName());

        }
        return productSales;
    }
    
    public ProductAdjustments getProductAdjustments()
    {
        ProductAdjustments productAdjusts = new ProductAdjustmentsImpl();
               
        db.entrySet();
        for (Map.Entry<String, TransactionNode> entry: db.entrySet())
        {
            //String name = entry.getKey();
            TransactionNode node = entry.getValue();
            List<Transaction> transactions = node.getTransactions();
            Transaction transaction = transactions.get(0);
            if (transaction!=null)
                if (transaction.getAdjTransaction()!=null)
                    for (Transaction operation : transaction.getAdjTransaction())
                    {
                        Adjustment adjust = (Adjustment) operation;
                        productAdjusts.addProductAdjustment(node.getProductName(), 
                                adjust.getAdjustmentTypeStr(), adjust.getAdjValue());
                    }
                    
                
                    
            
        }
        return productAdjusts;
    }

    
    
}
