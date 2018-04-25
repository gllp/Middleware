package client;

import java.util.ArrayList;

public class Invocation {
	private String ipAddress;
	private int portNumber;
	private String operationName;
	private ArrayList<Object> parameters;
	private int objectId;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public ArrayList<Object> getParameters() {
		return parameters;
	}

	public void setParameters(ArrayList<Object> parameters) {
		this.parameters = parameters;
	}
	
	public int getObjectId() {
		return objectId;
	}
	
	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
}
