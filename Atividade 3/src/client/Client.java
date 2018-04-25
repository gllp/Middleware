package client;

public class Client {
	public static void main(String[] args) throws Throwable {
		NamingProxy  namingProxy = new NamingProxy();
		namingProxy.setHost("localhost");
		namingProxy.setPort(1234);
		
		CalculatorProxy calculatorProxy = (CalculatorProxy) namingProxy.lookup("calculator");
		
		if(calculatorProxy == null) {
			System.out.println("Couldn't find service");
			return;
		}
		
		float result = calculatorProxy.add(1, 1);
		System.out.println(result);
	}
}
