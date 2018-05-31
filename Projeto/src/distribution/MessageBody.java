package distribution;

public class MessageBody {
	static final long serialVersionUID = 1L;
	
	private String body;

	public MessageBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
