package jptrade.core.transaction.impl;

import java.util.ArrayList;

import java.util.List;

import jptrade.core.transaction.iface.Transaction;
import jptrade.core.transaction.iface.TransactionNode;

/*
 * Head of a collection of transactions
 */
public class TransactionNodeImpl implements TransactionNode {

	private String productName = null;
	List<Transaction> transactions = null;

	/*
	 * create a blank array
	 */
	public TransactionNodeImpl() {

		transactions = new ArrayList<Transaction>();
	}

	/*
	 * Create an array but initialise the product name for this collection
	 */
	public TransactionNodeImpl(String product) {
		productName = product;
		transactions = new ArrayList<Transaction>();
	}

	@Override
	public String getProductName() {

		return productName;
	}

	@Override
	public List<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public void setUp(String name) {
		productName = name;
	}

	/*
	 * Add an adjustment to all existing transactions Add a sale to the end of the
	 * list
	 * 
	 * nb. If transaction array is null, do nothing (non-Javadoc)
	 * 
	 * @see
	 * jptrade.core.transaction.iface.TransactionNode#addTransaction(jptrade.core.
	 * transaction.iface.Transaction)
	 */
	@Override
	public void addTransaction(Transaction transaction) {
		if (transaction.isOperation())
			if (transactions != null) {
				for (Transaction trans : transactions) {
					trans.addOperation(transaction);
				}
			}
		if (!transaction.isOperation()) {
			if (transactions != null)
				transactions.add(transaction);
		}
	}

}
