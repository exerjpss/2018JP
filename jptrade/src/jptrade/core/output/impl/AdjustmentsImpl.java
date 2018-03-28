package jptrade.core.output.impl;

import jptrade.core.output.iface.Adjustments;
/*
 * For output, classes to hold data
 */

public class AdjustmentsImpl implements Adjustments
{

    private String name;
    private String type;
    private long adjValue;
    
    @Override
    public void setAdjustment(String name, String type, long adjValue)
    {
        this.name = name;
        this.type = type;
        this.adjValue = adjValue;

    }

    @Override
    public String getName()
    {
 
        return name;
    }

    @Override
    public String getType()
    {
    
        return type;
    }

    @Override
    public long getAdjVale()
    {
        
        return adjValue;
    }

}
