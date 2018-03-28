package jptrade.core.output.impl;

import java.util.ArrayList;
import java.util.List;

import jptrade.core.output.iface.Adjustments;
import jptrade.core.output.iface.ProductAdjustments;

/*
 * Generating an array for output
 */
public class ProductAdjustmentsImpl implements ProductAdjustments
{
    ArrayList<Adjustments> adjustments = new ArrayList <Adjustments> ();
    @Override
    public void addProductAdjustment(String name, String type, long adjValue)
    {
        Adjustments adj = new AdjustmentsImpl();
        adj.setAdjustment(name, type, adjValue);
        adjustments.add(adj);
    }

    @Override
    public List getProductAdjustments()
    {

        return adjustments;
    }

}
