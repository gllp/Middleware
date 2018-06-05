package distribution;

import java.io.Serializable;

public class MessageBody implements Serializable {
	static final long serialVersionUID = 1L;
	
	private Object body;

	public MessageBody(){}
	
	public MessageBody(Object body) {
		this.body = body;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}
}
