package common.naming;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import client.ClientProxy;

public class Naming implements INaming {
	private NamingRepository repository = new NamingRepository();
	
	public void bind(String serviceName, ClientProxy clientProxy, int objectId) throws UnknownHostException, IOException, Throwable {
		NamingRecord record = new NamingRecord(serviceName, clientProxy, objectId);
		repository.add(record);
	}
	
	public ClientProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable {
		NamingRecord record = repository.find(serviceName);
		if(record != null) {
			return record.getClientProxy();
		}
		else {
			return null;
		}
	}
	
	public ArrayList<String> list() throws UnknownHostException, IOException, Throwable {
		return repository.listServiceNames();
	}
}
