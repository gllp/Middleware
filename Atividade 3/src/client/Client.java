package client;

public class Client {
	public static void main(String[] args) throws Throwable {
		NamingProxy  namingProxy = new NamingProxy();
		namingProxy.setHost("localhost");
		namingProxy.setPort(11111);
		
		CalculatorProxy calculatorProxy = (CalculatorProxy) namingProxy.lookup("calculator");
		System.out.println("Lookup done");
		
		if(calculatorProxy == null) {
			System.out.println("Couldn't find service");
			return;
		}
		
		float result = calculatorProxy.add(1, 1);
		System.out.println(result);
	}
}
