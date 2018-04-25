package common.naming;

import client.ClientProxy;

public class NamingRecord {
	private String serviceName;
	private ClientProxy clientProxy;
	private int objectId;
	
	public NamingRecord(String serviceName, ClientProxy clientProxy,
			int objectId) {
		this.serviceName = serviceName;
		this.clientProxy = clientProxy;
		this.objectId = objectId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public ClientProxy getClientProxy() {
		return clientProxy;
	}

	public void setClientProxy(ClientProxy clientProxy) {
		this.clientProxy = clientProxy;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}
}
