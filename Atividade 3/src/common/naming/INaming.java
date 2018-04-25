package common.naming;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import client.ClientProxy;

public interface INaming {
	public void bind(String serviceName, ClientProxy clientProxy, int objectId) throws UnknownHostException, IOException, Throwable;
	
	public ClientProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable;
	
	public ArrayList<String> list() throws UnknownHostException, IOException, Throwable;
}
