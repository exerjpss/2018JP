package jptrade.core.message.impl;

import jptrade.core.constants.Constant;
import jptrade.core.message.iface.MessageIface;

/*
 * Take input string and process the data
 * Check that correct number of parameters are present
 * Do not check values, just that parameters are filled in
 */
public class Message implements MessageIface {

	private String type = "";
	private String qty = "";
	private String value = "";
	private String name = "";
	private String messageType = "";
	private String adjValue = "";

	public boolean processString(String data) {
		boolean result = false;
		if (data != null) {
			String[] dataArray = data.split("#");

			boolean qtyFlag = false;
			boolean typeFlag = false;
			boolean valueFlag = false;
			boolean adjValueFlag = false;
			boolean nameFlag = false;
			boolean messageTypeFlag = false;

			for (String item : dataArray) {
				String[] itemArray = item.split(":");
				if (itemArray.length == 2) {
					switch (itemArray[0]) {
					case Constant.STR_MSG:
						messageTypeFlag = true;
						messageType = itemArray[1];
						break;
					case Constant.STR_TYPE:
						typeFlag = true;
						type = itemArray[1];
						break;
					case Constant.STR_PRODUCT:
						nameFlag = true;
						name = itemArray[1];
						break;
					case Constant.STR_QTY:
						qtyFlag = true;
						qty = itemArray[1];
						break;
					case Constant.STR_VALUE:
						valueFlag = true;
						value = itemArray[1];
						break;
					case Constant.STR_ADJVALUE:
						adjValueFlag = true;
						adjValue = itemArray[1];
						break;

					}
				}
				/*
				 * Check to see if minimum requirements are met
				 */
				if (messageTypeFlag) {
					if (messageType.equals(Constant.STR_ADJUST))
						if (typeFlag && nameFlag && valueFlag && adjValueFlag)
							result = true;
					;
					if (messageType.equals(Constant.STR_SALE))
						if (nameFlag && valueFlag)
							result = true;
				}
			}
		}
		return result;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public void setAdjValue(String adjValue) {
		this.adjValue = adjValue;
	}

	public String getType() {
		return type;
	}

	public String getQty() {
		return qty;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public String getMessageType() {
		return messageType;
	}

	public String getAdjValue() {
		return adjValue;
	}
}
