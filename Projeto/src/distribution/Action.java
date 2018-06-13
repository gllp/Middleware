package distribution;

import java.io.Serializable;

public class Action implements Serializable {
	static final long serialVersionUID = 1L;
	String action;
	String value;
	
	public Action(String action, String value) {
		this.action = action;
		this.value = value;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
