package distribution;

import java.io.Serializable;

public class RequestPacketHeader implements Serializable {
	static final long serialVersionUID = 1L;
	
	private String operation;

	public RequestPacketHeader(){}
	
	public RequestPacketHeader(String operation) {
		this.operation = operation;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
