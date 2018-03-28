package jptrade.core.output.iface;

import java.util.List;

public interface ProductSales
{
    public void addProductSale (long sales, long value, String name);
    public List getProductSales ();
}
