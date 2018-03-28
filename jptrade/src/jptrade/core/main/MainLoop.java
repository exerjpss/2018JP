package jptrade.core.main;

import jptrade.core.input.impl.InputFile;
import jptrade.core.message.impl.Message;

import jptrade.core.output.iface.Output;
import jptrade.core.output.iface.ProductAdjustments;
import jptrade.core.output.iface.ProductSales;

import jptrade.core.output.impl.OutputImpl;
import jptrade.core.product.impl.ProductMap;

import jptrade.core.transaction.iface.TransactionFactoryIface;
import jptrade.core.transaction.impl.TransactionFactoryImpl;
import jptrade.core.transaction.impl.TransactionNodeImpl;

public class MainLoop
{
    /*
     * Main processing loop
     * Limited for now to every 10 and at 50 count only.
     */
    public void process (String filename)
    {
        ProductMap <TransactionNodeImpl> productMap = new ProductMap <TransactionNodeImpl> (TransactionNodeImpl::new);
        InputFile if1 = new InputFile();
      
        Message message = new Message();
        TransactionFactoryIface transFact = new TransactionFactoryImpl();
        Output output = new OutputImpl ();
        boolean input = if1.initialise(filename);
        int counter = 0;
        while ((input) && (counter<50))
        {
            message = new Message();
            String data = if1.getLine();
            boolean goodMessage =message.processString(data);
            if (goodMessage)
            {
                counter++;
                productMap.addProductMessage(message, transFact);

                if ((counter % 10) == 0)
                {

                    ProductSales sales = productMap.getSalesOutput();
                    output.printSales(sales);
                    
                }
                if (counter==50)
                //if ((counter % 10) == 0)
                {
                    ProductAdjustments adjusts = productMap.getProductAdjustments();
                    output.printAdjustments(adjusts);
                }

            }
            
        }
    }
}
