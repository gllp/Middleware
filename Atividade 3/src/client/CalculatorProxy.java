package client;

import java.util.ArrayList;

import server.ICalculator;
import common.Termination;

public class CalculatorProxy extends ClientProxy implements ICalculator{
	private static final long serialVersionUID = 1L;
	
	public float add(float a, float b) throws Throwable {
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {};
		String methodName = null;
		Requestor requestor = new Requestor();
		
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(a);
		parameters.add(b);
		
		inv.setObjectId(this.getObjectId());
		inv.setIpAddress(this.getHost());
		inv.setPortNumber(this.getPort());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		ter = requestor.invoke(inv);
		
		return (float) ter.getResult();
	}
	
	public float sub(float a, float b) throws Throwable {
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {};
		String methodName = null;
		Requestor requestor = new Requestor();
		
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(a);
		parameters.add(b);
		
		inv.setObjectId(this.getObjectId());
		inv.setIpAddress(this.getHost());
		inv.setPortNumber(this.getPort());
		inv.setOperationName(methodName);
		inv.setParameters(parameters);
		
		ter = requestor.invoke(inv);
		
		return (float) ter.getResult();
	}
}
