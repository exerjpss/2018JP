package jptrade.core.output.iface;

public interface SalesOutput
{
    public void add (String name, long value, long qty);
    public String getName();
    public long getValue();
    public long getQty();
}
