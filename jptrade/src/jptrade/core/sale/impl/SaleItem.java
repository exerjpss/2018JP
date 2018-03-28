package jptrade.core.sale.impl;


import java.util.List;

import jptrade.core.message.iface.MessageIface;

import jptrade.core.transaction.iface.Transaction;
import jptrade.core.transaction.iface.TransactionNode;
import jptrade.core.transaction.impl.TransactionImpl;
import jptrade.core.transaction.impl.TransactionNodeImpl;

/*
 * Hold a sale Transaction item
 */
public class SaleItem extends TransactionImpl {
	/*
	 * Default new generation Use setup to setup values (non-Javadoc)
	 * 
	 * @see jptrade.core.transaction.iface.Transaction#getParent()
	 */

	/*
	 * Standard getters (non-Javadoc)
	 * 
	 * @see jptrade.core.transaction.iface.Transaction#getParent()
	 */

	@Override
	public long getTotalCalculatedAmount() {
		long amount = getValue();
		List<Transaction> adjList = this.getAdjTransaction();
		if (adjList != null)
			for (Transaction nextPtr : adjList) {
				amount = nextPtr.getCalculatedValue(amount);
			}
		return amount * qty;
	}

	/*
	 * For saleitem get the sale amount value for adjustment get the adjusted amount
	 * 
	 * (non-Javadoc)
	 * 
	 * @see jptrade.core.transaction.iface.Transaction#adjustValue(long)
	 */
	@Override
	public long getCalculatedValue(long amount) {
		return amount * qty;
	}

	public void setUp(TransactionNodeImpl headerNode, long id, long value) {
		setUp(headerNode, id, value, 1);

	}

	public void setUp(TransactionNodeImpl headerNode, long id, long value, int qty) {
		setParent(headerNode);
		setId(id);
		setValue(value);
		setQty(qty);

	}

	@Override
	public void setupFromMessage(TransactionNode headerNode, long id, MessageIface messageObj) {
		long value = 0;
		int qty = 1;

		try {
			value = Long.parseLong(messageObj.getValue());
		} catch (Exception e) {
			System.out.println("Error during conversion of value");

		}
		if (messageObj.getQty() != null)
			if (!messageObj.getQty().equals(""))
				try {
					qty = Integer.parseInt(messageObj.getQty());
				} catch (Exception e) {
					System.out.println("Error during conversion of qty");
				}
		setUp(headerNode, id, value, qty);

	}
	

	@Override
	public boolean isOperation() {
		return false;
	}

}
