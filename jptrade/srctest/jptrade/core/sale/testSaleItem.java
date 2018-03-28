package jptrade.core.sale;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jptrade.core.message.impl.Message;
import jptrade.core.sale.impl.SaleItem;

import jptrade.core.transaction.impl.TransactionNodeImpl;

class testSaleItem {

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	TransactionNodeImpl headerNode;
	@Mock
	SaleItem adjustments;
	@InjectMocks
	SaleItem saleItem;
	@Mock
	SaleItem adjustments2;
	@Mock
	Message message;

	/*
	 * Test the setup function, and that parameters have been stored correctly
	 */
	@Test
	void testSetup() {
		SaleItem sale = new SaleItem();
		assertTrue(sale.getParent() == null);
		sale = new SaleItem();
		sale.setUp(headerNode, 999, 5);
		assertTrue(sale.getParent() == headerNode);
		assertTrue(sale.getValue() == 5);
		assertTrue(sale.getCountId() == 999);
		assertTrue(sale.getQty() == 1);
		sale.setUp(headerNode, 999, 5, 9);
		assertTrue(sale.getParent() == headerNode);
		assertTrue(sale.getCalculatedValue(8) == 72);
		assertTrue(sale.getCountId() == 999);
		assertTrue(sale.getQty() == 9);

	}

	@Test
	void testSetupMessage() {
		SaleItem sale = new SaleItem();
		assertTrue(sale.getParent() == null);
		sale = new SaleItem();
		when(message.getName()).thenReturn("Apple");
		when(message.getQty()).thenReturn("77");
		when(message.getValue()).thenReturn("22");
		sale.setupFromMessage(headerNode, 999, message);

		assertTrue(sale.getParent() == headerNode);
		assertTrue(sale.getValue() == 22);
		assertTrue(sale.getCountId() == 999);
		assertTrue(sale.getQty() == 77);
		assertTrue(sale.getTotalCalculatedAmount() == (77 * 22));
		assertTrue(sale.getCountId() == 999);

	}

	/*
	 * Only checking if loop and pickup last value Since adjustvalue here is not
	 * being tested
	 */
	@Test
	void testGetAdjustedValue() {

		saleItem.addOperation(adjustments);
		saleItem.addOperation(adjustments2);
		assertFalse(saleItem == null);
		assertFalse(adjustments == null);
		when(adjustments.getCalculatedValue(any(long.class))).thenReturn(new Long(4));
		when(adjustments2.getCalculatedValue(any(long.class))).thenReturn(new Long(10));
		saleItem.setUp(null, 0, 15);
		long value = saleItem.getTotalCalculatedAmount();
		assertTrue("value " + value, value == 10);

	}

}
