package jptrade.core.transaction.iface;


import java.util.List;

/*
 * An object that will be the parent node, for an arrayList type object
 * it will provide access to the entire list
 * and product name
 */
public interface TransactionNode
{
    public String getProductName ();
    public List<Transaction> getTransactions (); 
    public void setUp (String name);
    public void addTransaction(Transaction transaction);
}
