package distribution;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestPacketBody implements Serializable {
	static final long serialVersionUID = 1L;
	
	private ArrayList<Object> parameters = new ArrayList<Object>();
	private Message message;
	
	public RequestPacketBody(){}
	
	public RequestPacketBody(ArrayList<Object> parameters, Message message) {
		this.parameters = parameters;
		this.message = message;
	}

	public ArrayList<Object> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<Object> parameters) {
		this.parameters = parameters;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
