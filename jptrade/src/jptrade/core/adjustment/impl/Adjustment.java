package jptrade.core.adjustment.impl;



import jptrade.core.constants.Constant;
import jptrade.core.message.iface.MessageIface;

import jptrade.core.transaction.iface.TransactionNode;
import jptrade.core.transaction.impl.TransactionImpl;

/*
 * Class to hold adjustments
 */
public class Adjustment extends TransactionImpl
{
    
    private int adjustmentType =0;
    private long adjValue=0;
    public int getAdjustmentType()
    {
        return adjustmentType;
    }
    public void setAdjustmentType(int adjustmentType)
    {
        this.adjustmentType = adjustmentType;
    }
    
    /*
     * Translate the input string into a value
     */
    public void setAdjustmentType(String adjustmentType)
    {
        int val = 0;
        if (adjustmentType!=null)
        switch (adjustmentType)
        {
            case Constant.STR_ADD : val = Constant.ADJ_ADD;
            break;
            case Constant.STR_SUB : val =  Constant.ADJ_SUB;
            break;
            case Constant.STR_MULT : val =  Constant.ADJ_MULT;
            break;
            
        }
        this.adjustmentType = val;
    }
	/*
	 * Setup calls, direct and using a message interface
	 * (non-Javadoc)
	 * @see jptrade.core.transaction.impl.TransactionImpl#setupFromMessage(jptrade.core.transaction.iface.TransactionNode, long, jptrade.core.message.iface.MessageIface)
	 */
    @Override
    public void setupFromMessage(TransactionNode headerNode, long id,MessageIface message)
    {
        setUp(headerNode, id, 0);
        setAdjustmentType(message.getType());
        setAdjustmentValue(message.getAdjValue());
    }
    public void setUp(TransactionNode headerNode, long id, long value, int type, long adjValue)
    {
     
        setUp(headerNode, id, value);
        setAdjustmentType(type);
        setAdjustmentValue(adjValue);
    }
    private void setAdjustmentValue(String adjValue)
    {
        long val = 0;
        if (adjValue!=null)
        try
        {
        val = Long.parseLong(adjValue);
        } catch (Exception e)
        {
            
        }
        
        this.setAdjValue(val);;
        
    }
    private void setAdjustmentValue(long adjValue)
    {
        this.setAdjValue(adjValue);
        
    }
    @Override
    public long getCalculatedValue (long amount)
    {
        switch (adjustmentType )
        {
            case Constant.ADJ_ADD:
                amount = amount + getAdjValue();
                break;
            case Constant.ADJ_SUB:
                amount = amount-getAdjValue();
                break;
            case Constant.ADJ_MULT:
                amount = amount * getAdjValue ();
                break;
                default :
                    //Logger.log.error;
                
        }
        return amount;
    }
    public long getAdjValue()
    {
        return adjValue;
    }
    public void setAdjValue(long adjValue)
    {
        this.adjValue = adjValue;
    }
    /*
     * Mark this class/type as a an operation e.g. not a sale type
     * (non-Javadoc)
     * @see jptrade.core.transaction.iface.Transaction#isOperation()
     */
    @Override
    public boolean isOperation()
    {
        return true;
    }
    /*
     * used by output translate type back to a string for output
     */
    public String getAdjustmentTypeStr()
    {
        String val="";
        switch (adjustmentType)
        {
            case Constant.ADJ_ADD : val = Constant.STR_ADD;
            break;
            case Constant.ADJ_SUB : val =  Constant.STR_SUB;
            break;
            case Constant.ADJ_MULT : val =  Constant.STR_MULT;
            break;
            
        }
        return val;
    }
    
}
