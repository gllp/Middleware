package application;

public class Server {
	
	public static void main(String[] args) throws InterruptedException {
		String hostQueueServer = "192.168.43.225";
		int portQueueServer = 1313;
		int port = 8080;
		
		Thread app = new Thread(new App(hostQueueServer, portQueueServer));
		Thread rcv = new Thread(new Receiver(port));
		
		app.start();
		rcv.start();
		
		app.join();
		rcv.join();
	}
}
