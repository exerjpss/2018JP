package jptrade.core.output.iface;

public interface Adjustments
{
    public void setAdjustment (String name, String type, long adjValue);
    public String getName ();
    public String getType ();
    public long getAdjVale ();
}
