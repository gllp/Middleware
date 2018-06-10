package application;

public class Server {
	
	public static void main(String[] args) {
		String hostQueueServer = "localhost";
		int portQueueServer = 1313;
		int port = 33333;
		
		new Thread(new App(hostQueueServer, portQueueServer));
		new Thread(new Receiver(port));
	}
}
