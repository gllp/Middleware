package client;

import java.util.ArrayList;

public class Invocation {
	private ClientProxy clientProxy;
	private String operationName;
	private ArrayList<Object> parameters;
	
	public Invocation(ClientProxy clientProxy, String operationName,
			ArrayList<Object> parameters) {
		this.clientProxy = clientProxy;
		this.operationName = operationName;
		this.parameters = parameters;
	}

	public ClientProxy getClientProxy() {
		return clientProxy;
	}

	public void setClientProxy(ClientProxy clientProxy) {
		this.clientProxy = clientProxy;
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
}
