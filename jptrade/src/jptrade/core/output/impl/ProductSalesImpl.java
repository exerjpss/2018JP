package jptrade.core.output.impl;

import java.util.ArrayList;
import java.util.List;

import jptrade.core.output.iface.ProductSales;
import jptrade.core.output.iface.SalesOutput;

public class ProductSalesImpl implements ProductSales
{

    ArrayList <SalesOutput> sales = new ArrayList<SalesOutput>();
    @Override
    public void addProductSale(long qty, long value, String name)
    {
        SalesOutput sale = new SalesOutputImpl ();
        sale.add(name, value, qty);
        sales.add(sale);
    }

    @Override
    public List getProductSales()
    {
        
        return sales;
    }

}
