package jptrade.core.output.impl;

import java.util.List;

import jptrade.core.output.iface.Adjustments;
import jptrade.core.output.iface.Output;
import jptrade.core.output.iface.ProductAdjustments;
import jptrade.core.output.iface.ProductSales;
import jptrade.core.output.iface.SalesOutput;

public class OutputImpl implements Output
{
	
	/*
	 * Having got the data in an array format output it to the console
	 * 
	 * (non-Javadoc)
	 * @see jptrade.core.output.iface.Output#printSales(jptrade.core.output.iface.ProductSales)
	 */

    @Override
    public void printSales(ProductSales sales)
    {
        List salesList = sales.getProductSales();
        System.out.println("Report Sales Total Numbers");
        for (Object object : salesList)
        {
            SalesOutput saleItem = (SalesOutput)object;
            System.out.println("Product : [" + saleItem.getName() + "] Quamtity : [" + saleItem.getQty() + "] Value : [" + saleItem.getValue() + "]");
        }
        System.out.println("");

    }

    @Override
    public void printAdjustments(ProductAdjustments adjusts)
    {
        List adjList = adjusts.getProductAdjustments();
        System.out.println("Report Sales Adjustment Numbers");
        for (Object object : adjList)
        {
            Adjustments adjItem = (Adjustments)object;
            System.out.println("Product : [" + adjItem.getName() + "] Quamtity : [" + adjItem.getType() + "] Value : [" + adjItem.getAdjVale() + "]");
        }
        System.out.println("");

    }

}
