package common;

import java.io.Serializable;

public class MessageHeader implements Serializable{
	private static final long serialVersionUID = 1L;
	private String magic;
	private int vesion;
	private boolean byteOrder;
	private int messageType;
	private long messageSize;

	public MessageHeader(String magic, int vesion, boolean byteOrder,
			int messageType, long messageSize) {
		this.magic = magic;
		this.vesion = vesion;
		this.byteOrder = byteOrder;
		this.messageType = messageType;
		this.messageSize = messageSize;
	}
	
	public String getMagic() {
		return magic;
	}
	
	public void setMagic(String magic) {
		this.magic = magic;
	}
	
	public int getVesion() {
		return vesion;
	}
	
	public void setVesion(int vesion) {
		this.vesion = vesion;
	}
	
	public boolean isByteOrder() {
		return byteOrder;
	}
	
	public void setByteOrder(boolean byteOrder) {
		this.byteOrder = byteOrder;
	}
	
	public int getMessageType() {
		return messageType;
	}
	
	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}
	
	public long getMessageSize() {
		return messageSize;
	}
	
	public void setMessageSize(long messageSize) {
		this.messageSize = messageSize;
	}
}
