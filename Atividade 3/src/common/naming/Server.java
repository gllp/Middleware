package common.naming;

import java.io.IOException;

public class Server {
	public static void main(String[] args) throws IOException, Throwable{
		Invoker invoker = new Invoker();
		
		System.out.println("Naming server running");
		invoker.invoke(11111);
	}
}
