package calculator;

import java.io.IOException;

import client.CalculatorProxy;
import client.NamingProxy;

public class Server {
	
	public static void main(String[] args) throws IOException, Throwable {
		Invoker invoker = new Invoker();
		
		CalculatorProxy calculatorProxy = new CalculatorProxy();
		calculatorProxy.setHost("localhost");
		calculatorProxy.setPort(33333);
		
		NamingProxy  namingProxy = new NamingProxy();
		namingProxy.setHost("localhost");
		namingProxy.setPort(11111);
		
		namingProxy.bind("calculator", calculatorProxy, 1);
		
		System.out.println("Calculator server running");
		invoker.invoke(calculatorProxy);
	} 
}
