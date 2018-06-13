package application;

public class Server {
	
	public static void main(String[] args) throws InterruptedException {
		String hostQueueServer = "localhost";
		int portQueueServer = 1313;
		int port = 33333;
		
		Thread app = new Thread(new App(hostQueueServer, portQueueServer));
		Thread rcv = new Thread(new Receiver(port));
		
		app.start();
		rcv.start();
		
		app.join();
		rcv.join();
	}
}
