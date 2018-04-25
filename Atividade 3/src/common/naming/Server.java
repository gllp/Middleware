package common.naming;

import java.io.IOException;

public class Server {
	public static void main(String[] args) throws IOException, Throwable{
		Invoker invoker = new Invoker();
		
		invoker.invoke(1234);
	}
}
