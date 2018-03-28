package jptrade.core.output.impl;

import jptrade.core.output.iface.SalesOutput;

public class SalesOutputImpl implements SalesOutput
{

    private long value;
    private String name;
    private long qty;

    /*
     * No need to check values, just assign them as they came in
     * 
     * (non-Javadoc)
     * @see jptrade.core.output.iface.Sales#add(java.lang.String, long, long)
     */
    @Override

    public void add(String name, long value, long qty)
    {
        this.name = name;
        this.value=value;
        this.qty=qty;
    }

    @Override
    public String getName()
    {
        
        return name;
    }

    @Override
    public long getValue()
    {
        
        return value;
    }

    @Override
    public long getQty()
    {
        
        return qty;
    }

}
