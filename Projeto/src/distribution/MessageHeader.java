package distribution;

import java.io.Serializable;

public class MessageHeader implements Serializable {
	static final long serialVersionUID = 1L;
	
	private String destination;
	
	public MessageHeader(){}
	
	public MessageHeader(String destination) {
		this.destination = destination;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
}
