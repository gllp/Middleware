package distribution;

import java.io.Serializable;

public class RequestPacket implements Serializable {
	static final long serialVersionUID = 1L;
	
	private RequestPacketHeader header;
	private RequestPacketBody body;
	
	public RequestPacket(){}
	
	public RequestPacket(RequestPacketHeader header, RequestPacketBody body) {
		super();
		this.header = header;
		this.body = body;
	}
	
	public RequestPacketHeader getHeader() {
		return header;
	}
	
	public void setHeader(RequestPacketHeader header) {
		this.header = header;
	}
	
	public RequestPacketBody getBody() {
		return body;
	}
	
	public void setBody(RequestPacketBody body) {
		this.body = body;
	}
}
