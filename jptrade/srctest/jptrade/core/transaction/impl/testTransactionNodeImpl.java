package jptrade.core.transaction.impl;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Test;

import jptrade.core.adjustment.impl.Adjustment;
import jptrade.core.sale.impl.SaleItem;
import jptrade.core.transaction.iface.Transaction;

class testTransactionNodeImpl {

	@Test
	void testBasicSetup() {
		TransactionNodeImpl trans = new TransactionNodeImpl();
		trans.setUp("Orange");
		assertTrue(trans.getProductName() != null);
		assertTrue(trans.getProductName().equals("Orange"));

		Collection list = trans.getTransactions();
		assertTrue(list != null);
		boolean array = list instanceof ArrayList;
		assertTrue(array);

	}

	@Test
	void testAddTransactopm() {
		TransactionNodeImpl trans = new TransactionNodeImpl();
		trans.setUp("Orange");
		assertTrue(trans.getProductName() != null);
		assertTrue(trans.getProductName().equals("Orange"));

		Transaction sale = new SaleItem();
		Transaction adjust = new Adjustment();
		ArrayList<Transaction> transaction = (ArrayList<Transaction>) trans.getTransactions();

		trans.addTransaction(adjust);
		trans.addTransaction(sale);
		assertTrue("operations size = " + transaction.get(0).getOperationSize(),
				transaction.get(0).getOperationSize() == 0);
		assertTrue(trans.getTransactions().size() == 1);

		adjust = new Adjustment();
		trans.addTransaction(adjust);
		assertTrue("operations size = " + transaction.get(0).getOperationSize(),
				transaction.get(0).getOperationSize() == 1);
		assertTrue(trans.getTransactions().size() == 1);

		sale = new SaleItem();
		trans.addTransaction(sale);
		assertTrue("operations size = " + transaction.get(0).getOperationSize(),
				transaction.get(0).getOperationSize() == 1);
		assertTrue("operations size = " + transaction.get(1).getOperationSize(),
				transaction.get(1).getOperationSize() == 0);
		assertTrue(trans.getTransactions().size() == 2);

		trans.addTransaction(adjust);
		assertTrue("operations size = " + transaction.get(1).getOperationSize(),
				transaction.get(1).getOperationSize() == 1);
		assertTrue("operations size = " + transaction.get(0).getOperationSize(),
				transaction.get(0).getOperationSize() == 2);
		assertTrue(trans.getTransactions().size() == 2);

	}

}
