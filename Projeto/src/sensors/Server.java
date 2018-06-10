package sensors;

import java.io.IOException;

import distribution.QueueManagerProxy;

public class Server {
	public static void main(String[] args) throws InterruptedException, IOException {
		QueueManagerProxy proxy = new QueueManagerProxy("localhost", 1313, "temperature");
		
		while(true) {
			proxy.send("lala");
			
			Thread.sleep(2000);
		}
	}
}
