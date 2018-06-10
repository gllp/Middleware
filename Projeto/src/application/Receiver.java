package application;

import distribution.Message;
import distribution.QueueManagerProxy;

public class Receiver implements Runnable {
	int port;
	
	public Receiver(int port) {
		this.port = port;
	}

	public void run() {
		DatabaseMock db = new DatabaseMock();
		
		QueueManagerProxy proxy = new QueueManagerProxy(null, port, null); 
		
		Message msgUnmarshalled = null;
		
		while(true) {
			try {
				msgUnmarshalled = (Message) proxy.receive();
			} catch(Exception e) {
				System.out.println("Failed to receive message");
				e.printStackTrace();
				return;
			}
			
			db.add(msgUnmarshalled.getBody().getBody());
		}
	}
}
