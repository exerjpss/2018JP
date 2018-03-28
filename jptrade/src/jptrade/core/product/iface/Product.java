package jptrade.core.product.iface;

import jptrade.core.message.iface.MessageIface;
import jptrade.core.output.iface.ProductSales;

import jptrade.core.transaction.iface.TransactionFactoryIface;
import jptrade.core.transaction.iface.TransactionNode;

/*
 * Implement a storage for products which point to array of items
 * Using generics to allow new to be used
 */
public interface Product <T extends TransactionNode>
{
    // Add and return, or return the list for product name
    public TransactionNode addProduct (String productName);
    
    // Return the list for product name
    public TransactionNode getProduct (String productName);
    
    // return the size of the map
    public int getSize();

    public TransactionNode addProductMessage(MessageIface messageObj, TransactionFactoryIface factory);
    
    public ProductSales getSalesOutput();
    
    
}
