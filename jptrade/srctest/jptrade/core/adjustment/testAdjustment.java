package jptrade.core.adjustment;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jptrade.core.adjustment.impl.Adjustment;
import jptrade.core.constants.Constant;
import jptrade.core.message.impl.Message;


class testAdjustment
{

    @Mock Message messageObj;
    
    @BeforeEach
    public void setup ()
    {
         MockitoAnnotations.initMocks(this);
    }
    
    @Test
    void testGetCalculatedValue()
    {
        Adjustment adj = new Adjustment ();
        adj.setUp(null, 989, 2000, Constant.ADJ_ADD,200);
        assertTrue (adj.getAdjValue()==200);
        long val = adj.getCalculatedValue(10);
        assertTrue ("value = " + val,val==210);
    }
    
    @Test
    void testSetupWithMessage()
    {
        Adjustment adj = new Adjustment ();
        when (messageObj.getType()).thenReturn(Constant.STR_ADD);
        when (messageObj.getAdjValue()).thenReturn("200");
        adj.setupFromMessage(null, 989, messageObj);
        assertTrue (adj.getAdjValue()==200);
        long val = adj.getCalculatedValue(10);
        assertTrue ("value = " + val,val==210);

        adj = new Adjustment ();
        when (messageObj.getType()).thenReturn(Constant.STR_SUB);
        when (messageObj.getAdjValue()).thenReturn("200");
        adj.setupFromMessage(null, 989, messageObj);
        assertTrue (adj.getAdjValue()==200);
        val = adj.getCalculatedValue(390);
        assertTrue ("value = " + val,val==190);

        adj = new Adjustment ();
        when (messageObj.getType()).thenReturn(Constant.STR_MULT);
        when (messageObj.getAdjValue()).thenReturn("200");
        adj.setupFromMessage(null, 989, messageObj);
        assertTrue (adj.getAdjValue()==200);
        val = adj.getCalculatedValue(10);
        assertTrue ("value = " + val,val==2000);

        adj = new Adjustment ();
        when (messageObj.getType()).thenReturn("");
        when (messageObj.getAdjValue()).thenReturn("200");
        adj.setupFromMessage(null, 989, messageObj);
        assertTrue (adj.getAdjValue()==200);
        val = adj.getCalculatedValue(10);
        assertTrue ("value = " + val,val==10);

        adj = new Adjustment ();
        when (messageObj.getType()).thenReturn(null);
        when (messageObj.getAdjValue()).thenReturn("200");
        adj.setupFromMessage(null, 989, messageObj);
        assertTrue (adj.getAdjValue()==200);
        val = adj.getCalculatedValue(10);
        assertTrue ("value = " + val,val==10);

        adj = new Adjustment ();
        when (messageObj.getType()).thenReturn(null);
        when (messageObj.getAdjValue()).thenReturn("");
        adj.setupFromMessage(null, 989, messageObj);
        assertTrue (adj.getAdjValue()==0);
        val = adj.getCalculatedValue(10);
        assertTrue ("value = " + val,val==10);
        
        adj = new Adjustment ();
        when (messageObj.getType()).thenReturn(null);
        when (messageObj.getAdjValue()).thenReturn(null);
        adj.setupFromMessage(null, 989, messageObj);
        assertTrue (adj.getAdjValue()==0);
        val = adj.getCalculatedValue(10);
        assertTrue ("value = " + val,val==10);
    }

}
