package jptrade.core.message;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import jptrade.core.constants.Constant;
import jptrade.core.message.impl.Message;

class testMessage
{

    @Test
    void testValidMessage()
    {
        String data = "";
        Message msg = new Message();
        boolean result = false;
        
        data = "";
        result = msg.processString(data);
        assertFalse (result);
        
        data = null;
        result = msg.processString(data);
        assertFalse (result);
        
        data = "####";
        result = msg.processString(data);
        assertFalse (result);
        
        data = ":#:#:##";
        result = msg.processString(data);
        assertFalse (result);
        
        /*
         * Message Type 3
         */
        data =   Constant.STR_MSG + ":" + Constant.STR_ADJUST + "#" 
               + Constant.STR_TYPE + ":" + Constant.STR_MULT + "#"
               + Constant.STR_QTY + ":" + "2" + "#"
               + Constant.STR_PRODUCT + ":" + "Orange" + "#" 
               + Constant.STR_ADJVALUE + ":" + "2" + "#" 
               + Constant.STR_VALUE + ":" + "200"; 
        result = msg.processString(data);
        assertTrue (result);

        /*
         * Message Type 2
         */
        data =   Constant.STR_MSG + ":" + Constant.STR_SALE + "#" 
                + Constant.STR_QTY + ":" + "2" + "#"
                + Constant.STR_PRODUCT + ":" + "Orange" + "#" 
                + Constant.STR_VALUE + ":" + "200"; 
         result = msg.processString(data);
         assertTrue (result);

         /*
          * Message Type 1
          */


         data =   Constant.STR_MSG + ":" + Constant.STR_SALE + "#" 
                 + Constant.STR_PRODUCT + ":" + "Orange" + "#" 
                 + Constant.STR_VALUE + ":" + "200"; 
          result = msg.processString(data);
          assertTrue (result);

    }

}
