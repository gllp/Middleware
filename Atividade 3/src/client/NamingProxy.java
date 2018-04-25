package client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import common.Termination;
import common.naming.INaming;

public class NamingProxy extends ClientProxy implements INaming{
	private static final long serialVersionUID = 1L;
	
	public void bind(String serviceName, ClientProxy clientProxy, int objectId) throws UnknownHostException, IOException, Throwable {
		Invocation inv = new Invocation();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {};
		String methodName = null;
		Requestor requestor = new Requestor();
		
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(serviceName);
		parameters.add(clientProxy);
		parameters.add(objectId);
		
		inv.setObjectId(this.getObjectId());
		inv.setIpAddress(this.getHost());
		inv.setPortNumber(this.getPort());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		requestor.invoke(inv);
	}
	
	public ClientProxy lookup(String serviceName) throws UnknownHostException, IOException, Throwable {
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {};
		String methodName = null;
		Requestor requestor = new Requestor();
		
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(serviceName);
		
		inv.setObjectId(this.getObjectId());
		inv.setIpAddress(this.getHost());
		inv.setPortNumber(this.getPort());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		ter = requestor.invoke(inv);
		
		return (ClientProxy) ter.getResult();
	}
	
	public ArrayList<String> list() throws UnknownHostException, IOException, Throwable {
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		class Local {};
		String methodName = null;
		Requestor requestor = new Requestor();
		
		methodName = Local.class.getEnclosingMethod().getName();
		
		inv.setObjectId(this.getObjectId());
		inv.setIpAddress(this.getHost());
		inv.setPortNumber(this.getPort());
		inv.setOperationName(methodName);
		inv.setParameters(null);
		
		ter = requestor.invoke(inv);
		
		return (ArrayList<String>) ter.getResult();
	}
}
