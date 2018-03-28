package jptrade.core.transaction.iface;

import java.util.List;

import jptrade.core.message.iface.MessageIface;

public interface TransactionFactoryIface
{
    List getTransaction (MessageIface message);
}
