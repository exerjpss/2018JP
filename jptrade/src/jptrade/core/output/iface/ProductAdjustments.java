package jptrade.core.output.iface;

import java.util.List;

public interface ProductAdjustments
{
    public void addProductAdjustment (String name, String type, long adjValue);
    public List getProductAdjustments ();
}
