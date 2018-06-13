package actuatortemp;

import java.io.IOException;

import distribution.QueueManagerProxy;

public class Server {
	public static void main(String[] args) throws IOException, InterruptedException {
		QueueManagerProxy proxy = new QueueManagerProxy("localhost", 1313, "temperatureAction");
		proxy.subscribe();
		
		int port = 22222;
		
		Thread rcv = new Thread(new Receiver(port));
		
		rcv.start();
	}
}
