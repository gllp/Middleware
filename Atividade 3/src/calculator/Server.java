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
		namingProxy.setPort(1234);
		
		namingProxy.bind("calculator", calculatorProxy, 1);
		
		invoker.invoke(calculatorProxy);
	} 
}
